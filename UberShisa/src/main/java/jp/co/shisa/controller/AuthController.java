package jp.co.shisa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.form.LoginForm;
import jp.co.shisa.service.AuthService;

@Controller
@EnableAutoConfiguration
public class AuthController {
	@Autowired
	AuthService authService;

	@Autowired
	HttpSession session;

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		return "index.html";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("login") LoginForm form, BindingResult bindingResult, Model model,
			RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return "index";
		}

		UserInfo userInfo = authService.loginCheck(form);
		if (userInfo == null) {
			String errorMsg = "IDまたはPASSが間違っています";
			attr.addFlashAttribute("errorMsg", errorMsg);
			return "redirect:index";
		}

		Integer roleId = userInfo.getRoleId();

		switch (roleId) {
		case 1:
			Room room = authService.loginByRoom(userInfo);
			session.setAttribute("loginUser", room);
			return "order";

		case 2:
			DeliveryMan deliveryMan = authService.loginByDeliveryMan(userInfo);
			session.setAttribute("loginUser", deliveryMan);
			return "delivery";
		case 3:
			Shop shop = authService.loginByShop(userInfo);
			session.setAttribute("loginUser", shop);
			return "store";

		case 4:
			session.setAttribute("loginUser", userInfo);
			return "hotel";
		default:
			String errorMsg = "IDまたはPASSが間違っています";
			attr.addFlashAttribute("errorMsg", errorMsg);
			return "redirect:";
		}

	}










	/*
	* ログアウト
	*/
	@RequestMapping("/logout")
	public String logout(Model model) {
		session.invalidate();
		return "index";
		//indexに遷移
	}

}