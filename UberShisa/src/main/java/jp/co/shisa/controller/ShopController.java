package jp.co.shisa.controller;

import java.util.List;

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

import jp.co.shisa.controller.form.ProductAddForm;
import jp.co.shisa.controller.form.UpdateStoreForm;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.ShopService;

@Controller
@EnableAutoConfiguration
public class ShopController {
	@Autowired
	ShopService shopService;

	@Autowired
	HttpSession session;

	@RequestMapping("/shop/shopOrderInfo/{orderId}")
	public String shopOrderList(@PathVariable Integer orderId,Model model) {
		OrderInfo orderInfo = shopService.checkOrder(orderId);
		List<OrderItem> list = shopService.checkOrderContents(orderId);
		System.out.println("order Info: " + orderInfo + "order ID: " + orderId);
		session.setAttribute("orderInfoForShop", orderInfo);
		session.setAttribute("orderItemForShop", list);
		return "storeOrderInfo";
	}

//	店舗オーダーリスト(pha) start-------------------------------------------------------------
	@RequestMapping(value="/storeOrderList")
	public String orderList(Model model) {
		return "store";
	}

	@RequestMapping(value="/orderPassed", params="backStoreBtn")
	public String orderPassed(Model model) {
		return "store";
	}
//	店舗オーダーリスト(pha) end---------------------------------------------------------------

//	店舗情報更新(pha) start---------------------------------------------------------
	@RequestMapping(value="/storeManage")
	public String storeManage( Model model) {
		UpdateStoreForm updateStoreForm = new UpdateStoreForm();
		Shop shop = (Shop) session.getAttribute("loginUser");
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
	@RequestMapping(value="/updateStore", params="backStore")
	public String backStore(Model model) {
		return "store";
	}
//	更新ボタンを押すとき
	@RequestMapping(value="/updateStore", params="updateStore")
	public String updateStore(@Validated UpdateStoreForm updateStoreForm,
			BindingResult bindingResult,  Model model) {
		if(bindingResult.hasErrors()) {
			return "storeManage";
		}
		else {
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
		Shop shop = (Shop) session.getAttribute("loginUser");
		List<Product> list = shopService.selectAllProductByShopId(shop.getShopId());
		session.setAttribute("shopProductList",list);
		return "storePoductManage";
	}

	@RequestMapping("/shop/storeProductAdd")
	public String storeProductAdd(@ModelAttribute("productAdd")ProductAddForm form,BindingResult bindingResult,Model model){
		return "storePoductAdd";
	}

	@RequestMapping(value="/shop/productAddComplete" ,params="add")
	public String shopProductAddComplete(@Validated @ModelAttribute("productAdd")ProductAddForm form,BindingResult bindingResult,Model model) {
		if(bindingResult.hasErrors()) {

			return "storePoductAdd";
		}

				Shop loginShop = (Shop) session.getAttribute("loginUser");
				Product addProduct = new Product(loginShop.getShopId(),form.getImage(),form.getText(),form.getProductName(),form.getPrice(),form.getStock());
				shopService.insertProduct(addProduct);
				Shop shop = (Shop) session.getAttribute("loginUser");
				List<Product> list = shopService.selectAllProductByShopId(shop.getShopId());
				session.setAttribute("shopProductList",list);

		return "redirect:/shop/storeProductManage";
	}


	@RequestMapping("/shop/productUpdate/{productId}")
	public String productUpdate(@PathVariable Integer productId,Model model) {
		Product updatingProduct = shopService.selectUpdateProductByProductId(productId);
		session.setAttribute("updateingProduct", updatingProduct);

		return "#";
	}

}
