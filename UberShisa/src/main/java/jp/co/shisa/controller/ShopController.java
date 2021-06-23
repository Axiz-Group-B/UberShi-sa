package jp.co.shisa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.controller.form.UpdateStoreForm;
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

	@RequestMapping(value="/storeManage")
	public String storeManage( Model model) {
		UpdateStoreForm updateStoreForm = new UpdateStoreForm();
		Shop shop = (Shop) session.getAttribute("loginUser");
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		model.addAttribute("updateStoreForm", updateStoreForm);
		model.addAttribute("oldShop", shop);
		model.addAttribute("oldUserInfo", userInfo);
//		session.setAttribute("updateStoreForm", updateStoreForm);
		updateStoreForm.setLoginId(userInfo.getLoginId());
		updateStoreForm.setPass(userInfo.getPass());
		updateStoreForm.setShopName(shop.getShopName());
		updateStoreForm.setShopTel(shop.getShopTel());
		updateStoreForm.setAddress(shop.getAddress());
			return "storeManage";
	}
	@RequestMapping(value="/updateStore", params="backStore")
	public String backStore(Model model) {
		return "store";
	}
	@RequestMapping(value="/updateStore", params="updateStore")
	public String updateStore(@Validated UpdateStoreForm updateStoreForm,
			BindingResult bindingResult,  Model model) {
		if(bindingResult.hasErrors()) {
			return "storeManage";
		}
		else {
			Shop oldShop = (Shop) session.getAttribute("loginUser");
			UserInfo oldUserInfo = (UserInfo) session.getAttribute("userInfo");
			UserInfo userInfo = new UserInfo(oldUserInfo.getUserId(), updateStoreForm.getLoginId(),
					updateStoreForm.getPass(), oldUserInfo.getRoleId());
			Shop shop = new Shop(updateStoreForm.getShopName(), updateStoreForm.getShopTel(),
					updateStoreForm.getAddress());
			model.addAttribute("oldShop", oldShop);
			model.addAttribute("oldUserInfo", oldUserInfo);
			shopService.updateShopInfo(userInfo, shop);
			session.setAttribute("loginUser", shop);
			session.setAttribute("userInfo", userInfo);
			return "storeManage";
		}
	}
}
