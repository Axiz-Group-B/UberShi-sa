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

@Controller
@EnableAutoConfiguration
public class ShopController {
	@Autowired
	HttpSession session;

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
	@RequestMapping(value="/updateManage")
	public String updateStore(@Validated UpdateStoreForm updateStoreForm,
			BindingResult bindingResult,  Model model) {
		if(bindingResult.hasErrors()) {
			return "storeManage";
		}
		else {

			return "storeManage";
		}
	}
}
