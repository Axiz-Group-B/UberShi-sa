package jp.co.shisa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.AuthService;
import jp.co.shisa.service.RoomService;

@Controller
@EnableAutoConfiguration
public class AuthController {
	@Autowired
	AuthService authService;

	@Autowired
	RoomService roomService;

	@Autowired
	HttpSession session;

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		return "index";
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


			switch(roleId) {
			case 1:

				Room room = authService.loginByRoom(userInfo);
				session.setAttribute("loginUser", room);

				List<Shop> list = roomService.findAll();
				//全検索用に、listにadd
				Shop shopPullDown = new Shop(0,"全店舗から検索");
				list.add(0,shopPullDown);
				session.setAttribute("shopList", list);

				return "order";

		switch (roleId) {
		case 1:
			Room room = authService.loginByRoom(userInfo);
			session.setAttribute("loginUser", room);
			return "order";
		case 2:
			DeliveryMan deliveryMan = authService.loginByDeliveryMan(userInfo);
			session.setAttribute("loginUser", deliveryMan);
			session.setAttribute("userInfo", userInfo);
			List<OrderInfo> noDeliveryManOrderList = authService.checkNoDeliveryManOrder();
			session.setAttribute("noDeliveryManOrderList", noDeliveryManOrderList);
			return "delivery";
		case 3:
			Shop shop = authService.loginByShop(userInfo);
			session.setAttribute("loginUser", shop);
			session.setAttribute("userInfo", userInfo);
			List<OrderInfo> finishedOrderList = authService.checkFinishedOrderByShop(shop);
			List<OrderInfo> notFinishedOrderList = authService.checkNotFinishedOrderByShop(shop);
			session.setAttribute("finishedOrderListBy", finishedOrderList);
			session.setAttribute("notFinishedOrderList", notFinishedOrderList);
			return "store";
		case 4:
			session.setAttribute("loginUser", userInfo);
			List<Room> AllRoomList = authService.checkAllRoomAndHasOrder();
			session.setAttribute("AllRoomList", AllRoomList);
			return "hotel";
		default:
			String errorMsg = "IDまたはPASSが間違っています";
			attr.addFlashAttribute("errorMsg", errorMsg);
			return "redirect:index";
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
