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

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.controller.form.UpdateDeliveryInfoForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.AuthService;
import jp.co.shisa.service.DeliveryManService;

@Controller
@EnableAutoConfiguration

public class DeliveryManController {
	@Autowired
	private DeliveryManService deliveryManService;

	@Autowired
	AuthService authService;

	@Autowired
	HttpSession session;

	//signup.htmlでアカウントを持っている方を押した時と新規ユーザー登録したときにindex画面に遷移
	@RequestMapping("/newInsert")
	public String index( @ModelAttribute("insert") LoginForm form) {
		return "signup";
	}
	//バリデーションどこだっけ

	//signup.htmlでパスワードと確認パスワードが間違ってた時とポップアップで戻る押した時にsignup.htmlに戻る
	@RequestMapping("/signup")
	public String insertDeliveryMan(@Validated @ModelAttribute("insert") SignupForm form, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorPassMsg", "値が入力されていない場所があります");
			return "signup";
		}
		if (form.getPass().equals(form.getRePass())) {
			deliveryManService.checkLoginId(form);
			if(deliveryManService.checkLoginId(form)) {
				deliveryManService.insertUserInfo(form);
				deliveryManService.insertDeliveryMan(form);

				return "index";
			}else {
				model.addAttribute("errorPassMsg", "ログインIDが重複しています");
				return "signup";
			}

		} else {
			//passとrepassが同じじゃなかったらエラーメッセージ
			model.addAttribute("errorPassMsg", "パスワードと確認パスワードが一致しません");
			return "signup";

		}
		//ポップアップで戻る選択した時
	}


	@RequestMapping("/deliveryMan/delivery")
	public String deliveryView(Model model) {
		return "delivery";
	}


	/*	@RequestMapping("/deliveryMan/deliveryOrderSelected")
		public String deliveryOrderSelected(Model model) {
			return "deliveryOrderSelected";
		}
	*/
	@RequestMapping("/deliveryMan/deliveryOrderSelect/{orderId}")
	public String deliveryOrderSelect(@PathVariable Integer orderId, Model model) {
		OrderInfo orderInfo = deliveryManService.checkOrder(orderId);
		List<OrderItem> orderItem = deliveryManService.checkOrderContents(orderId);
		session.setAttribute("orderInfoForDeliveryMan", orderInfo);
		session.setAttribute("orderItemForDeliveryMan", orderItem);
		return "deliveryOrderSelect";
	}

	//	配達員情報　更新するため(pha)start-----------------------------------------------------------------


	//
	@RequestMapping("/deliveryMan/deliveryOrderSelected")
	public String deliveryOrderSelected(Model model) {
		OrderInfo orderInfo = (OrderInfo) session.getAttribute("orderInfoForDeliveryMan");
		DeliveryMan deliveryMan = (DeliveryMan) session.getAttribute("loginUser");
			deliveryManService.addDeliveryManIdInOrderAndAddLog(orderInfo.getOrderId(),deliveryMan.getDeliveryManId());
		return "deliveryOrderSelected";
	}

	//配達完了後、初期画面に戻る前にリストを最新の情報に更新
	@RequestMapping("/deliveryMan/deliveryCompleted")
	public String deliveryOrderSelect(Model model) {
		List<OrderInfo> noDeliveryManOrderList = authService.checkNoDeliveryManOrder();
		session.setAttribute("noDeliveryManOrderList", noDeliveryManOrderList);
		return "redirect:delivery";
	}
//	配達員情報　更新するため(pha)start-----------------------------------------------------------------


	@RequestMapping(value = "/deliveryInfo")
	public String deliveryInfo(Model model) {
		UpdateDeliveryInfoForm updateDeliveryInfoForm = new UpdateDeliveryInfoForm();
		DeliveryMan deliveryMan = (DeliveryMan) session.getAttribute("loginUser");
		model.addAttribute("updateDeliveryInfoForm", updateDeliveryInfoForm);
		updateDeliveryInfoForm.setLoginId(deliveryMan.getLoginId());
		updateDeliveryInfoForm.setDeliveryManName(deliveryMan.getDeliveryManName());
		updateDeliveryInfoForm.setDeliveryManTel(deliveryMan.getDeliveryManTel());
		updateDeliveryInfoForm.setPass(deliveryMan.getPass());

		return "deliveryInfo";
	}

	@RequestMapping(value = "/updateDeliveryInfo", params = "backDelivery")
	public String backDelivery(Model model) {
		return "delivery";
	}

	@RequestMapping(value = "/updateDeliveryInfo", params = "updateDelivery")
	public String updateDelivery(@Validated @ModelAttribute() UpdateDeliveryInfoForm updateDeliveryInfoForm,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("入力ミス");
			return "deliveryInfo";
		} else {

			String pass = updateDeliveryInfoForm.getPass();
			String rePass = updateDeliveryInfoForm.getRePass();
			if (!pass.equals(rePass)) {
				model.addAttribute("passErrMsg", "パスワードは一致していません");
				return "deliveryInfo";
			} else {
				UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
				DeliveryMan deliveryMan = new DeliveryMan(userInfo.getUserId(),
						updateDeliveryInfoForm.getLoginId(), updateDeliveryInfoForm.getPass(),
						userInfo.getRoleId(), updateDeliveryInfoForm.getDeliveryManName(),
						updateDeliveryInfoForm.getDeliveryManTel());
				deliveryManService.updateDeliveryManInfo(deliveryMan);
				System.out.println("配達員の情報を更新された");
				return "deliveryInfo";
			}

		}

	}

	//	配達員情報　更新するため(pha)end-----------------------------------------------------------------
}
