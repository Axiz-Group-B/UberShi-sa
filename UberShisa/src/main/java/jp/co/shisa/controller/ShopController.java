package jp.co.shisa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.shisa.controller.form.ProductAddForm;
import jp.co.shisa.controller.form.ProductUpdateForm;
import jp.co.shisa.controller.form.SearchMyProductsForm;
import jp.co.shisa.controller.form.SelectProductDeleteForm;
import jp.co.shisa.controller.form.UpdateStoreForm;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.AuthService;
import jp.co.shisa.service.ShopService;

@Controller
@EnableAutoConfiguration
public class ShopController {
	@Autowired
	ShopService shopService;

	@Autowired
	AuthService authService;

	@Autowired
	HttpSession session;

	@Autowired
	ServletContext context;

	@RequestMapping("/shop/shopOrderInfo/{orderId}")
	public String shopOrderList(@PathVariable Integer orderId, Model model) {
		OrderInfo orderInfo = shopService.checkOrder(orderId);
		List<OrderItem> list = shopService.checkOrderContents(orderId);
		System.out.println("order Info: " + orderInfo + "order ID: " + orderId);
		session.setAttribute("orderInfoForShop", orderInfo);
		session.setAttribute("orderItemForShop", list);
		return "storeOrderInfo";
	}

	//	店舗オーダーリスト(pha) start-------------------------------------------------------------
	@RequestMapping(value = "/storeOrderList")
	public String orderList(Model model) {
		return "store";
	}

	@RequestMapping(value = "/orderPassed", params = "backStoreBtn")
	public String backStore1(Model model) {
		return "store";
	}

	@RequestMapping(value = "/orderPassed", params = "passedBtn")
	public String orderPassed(Model model) {
		Shop shop = (Shop) session.getAttribute("loginShop");
		OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfoForShop");

		shopService.passedOrder(orderInfo);
		List<OrderInfo> finishedOrderList = authService.checkFinishedOrderByShop(shop);
		List<OrderInfo> notFinishedOrderList = authService.checkNotFinishedOrderByShop(shop);
		session.setAttribute("finishedOrderList", finishedOrderList);
		session.setAttribute("notFinishedOrderList", notFinishedOrderList);
		return "store";
	}
	//	店舗オーダーリスト(pha) end---------------------------------------------------------------

	//	店舗情報更新(pha) start---------------------------------------------------------
	@RequestMapping(value = "/storeManage")
	public String storeManage(Model model) {
		UpdateStoreForm updateStoreForm = new UpdateStoreForm();
		Shop shop = (Shop) session.getAttribute("loginShop");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		model.addAttribute("updateStoreForm", updateStoreForm);
		updateStoreForm.setLoginId(userInfo.getLoginId());
		updateStoreForm.setPass(userInfo.getPass());
		updateStoreForm.setShopName(shop.getShopName());
		updateStoreForm.setShopTel(shop.getShopTel());
		updateStoreForm.setAddress(shop.getAddress());
		return "storeManage";
	}

	//	戻るボタンを押すとき
	@RequestMapping(value = "/updateStore", params = "backStore")
	public String backStore(Model model) {
		return "store";
	}

	//	更新ボタンを押すとき
	@RequestMapping(value = "/updateStore", params = "updateStore")
	public String updateStore(@Validated UpdateStoreForm updateStoreForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "storeManage";
		} else {
			//			Shop oldShop = (Shop) session.getAttribute("loginUser");
			UserInfo oldUserInfo = (UserInfo) session.getAttribute("userInfo");
			UserInfo userInfo = new UserInfo(oldUserInfo.getUserId(), updateStoreForm.getLoginId(),
					updateStoreForm.getPass(), oldUserInfo.getRoleId());
			Shop shop = new Shop(updateStoreForm.getShopName(), updateStoreForm.getShopTel(),
					updateStoreForm.getAddress());
			shopService.updateShopInfo(userInfo, shop);
			return "storeManage";
		}
	}
	//	店舗情報更新(pha) end---------------------------------------------------------

	@RequestMapping("/shop/storeProductManage")
	public String storeProductManage(Model model) {
		Shop shop = (Shop) session.getAttribute("loginShop");
		List<Product> list = shopService.selectAllProductByShopId(shop.getShopId());
		session.setAttribute("shopProductList", list);
		return "storePoductManage";
	}

	@RequestMapping("/shop/storeProductAdd")
	public String storeProductAdd(@ModelAttribute("productAdd") ProductAddForm form, BindingResult bindingResult,
			Model model) {
		return "storePoductAdd";
	}

	@RequestMapping(value = "/shop/productAddComplete", params = "add" ,method=RequestMethod.POST)
	public String shopProductAddComplete(@ModelAttribute("productAdd") ProductAddForm form,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {

			return "storePoductAdd";
		}

		Shop loginShop = (Shop) session.getAttribute("loginUser");
		String fileName = form.getImage().getOriginalFilename();
		String filePath = context.getRealPath("\\") + "\\..\\resources\\static\\images\\" + fileName;
		Product addProduct = new Product(loginShop.getShopId(), fileName, form.getText(), form.getProductName(),
				form.getPrice(), form.getStock());
		shopService.insertProduct(addProduct);
		java.io.File uploadFile = new java.io.File(filePath);

		try {
			form.getImage().transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		Shop shop = (Shop) session.getAttribute("loginUser");
		List<Product> list = shopService.selectAllProductByShopId(shop.getShopId());
		session.setAttribute("shopProductList", list);

		return "redirect:/shop/storeProductManage";
	}

	@RequestMapping("/shop/productUpdate/{productId}")
	public String productUpdate(@PathVariable Integer productId, @ModelAttribute("update") ProductUpdateForm form,
			Model model) {
		Product updatingProduct = shopService.selectUpdateProductByProductId(productId);
		session.setAttribute("updatingProduct", updatingProduct);

		return "storePoductUpdate";
	}

	@RequestMapping("/shop/updatedProduct")
	public String updatedProduct(@ModelAttribute("update") ProductUpdateForm form, Model model) {
		String fileName = form.getImage().getOriginalFilename();
		if(fileName.isEmpty()) {
			String newFileName = shopService.selectImageByProductId(form.getProductId());
			Product product = new Product(form.getProductId(), form.getProductName(), form.getPrice(),
					form.getStock(), form.getText(), newFileName);
			Product updateProduct = shopService.updateProductAndGetProductByProductId(product);
			session.setAttribute("updateingProduct", updateProduct);
			return "redirect:/shop/productUpdate/" + updateProduct.getProductId();
		}

		String filePath = context.getRealPath("\\") + "\\..\\resources\\static\\images\\" + fileName;
		Product updatingProduct = new Product(form.getProductId(), form.getProductName(), form.getPrice(),
				form.getStock(), form.getText(), fileName);
		Product updatedProduct = shopService.updateProductAndGetProductByProductId(updatingProduct);
		java.io.File uploadFile = new java.io.File(filePath);

		try {
			form.getImage().transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		session.setAttribute("updateingProduct", updatedProduct);
		return "redirect:/shop/productUpdate/" + updatedProduct.getProductId();
	}

	@RequestMapping("/shop/productDelete")
	public String deleteProduct(@ModelAttribute("delete") SelectProductDeleteForm form, Model model,
			RedirectAttributes attr) {

		if (form.getCheckProductList().isEmpty()) {
			attr.addFlashAttribute("errorMsg", "削除する対象をチェックしてください");
		}
		List<Integer> checkProductList = form.getCheckProductList();
		shopService.deleteProducts(checkProductList);
		return "redirect:/shop/storeProductManage";
	}

	@RequestMapping("/shop/searchMyProducts")
	public String searchMyProducts(@ModelAttribute("search") SearchMyProductsForm form, Model model) {
		Shop shop = (Shop) session.getAttribute("loginUser");
		List<Product> searchedProductList = shopService.searchMyProductsByProductName(shop.getShopId(),
				form.getSearchWord());
		session.setAttribute("shopProductList", searchedProductList);
		return "storePoductManage";
	}

}
