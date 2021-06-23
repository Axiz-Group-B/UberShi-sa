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

import jp.co.shisa.controller.form.UpdateDeliveryInfoForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.DeliveryManService;

@Controller
@EnableAutoConfiguration
public class DeliveryManController {

	@Autowired
	DeliveryManService deliveryManService;

	@Autowired
	HttpSession session;

	@RequestMapping("/deliveryman/delivery")
	public String delivery(Model model) {
		return "delivery";
	}

	@RequestMapping("/deliveryMan/deliveryOrderSelect/{orderId}")
	public String deliveryOrderSelect(@PathVariable Integer orderId,Model model) {
		OrderInfo orderInfo = deliveryManService.checkOrder(orderId);
		List<OrderItem> orderItem = deliveryManService.checkOrderContents(orderId);
		session.setAttribute("orderInfoForRoom",orderInfo);
		session.setAttribute("orderItemForRoom", orderItem);
		return "deliveryOrderSelect";
	}
//	配達員情報　更新するため(pha)start-----------------------------------------------------------------

	@RequestMapping(value="/deliveryInfo")
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
	@RequestMapping(value="/updateDeliveryInfo", params="backDelivery")
	public String backDelivery(Model model) {
		return "delivery";
	}
	@RequestMapping(value="/updateDeliveryInfo", params="updateDelivery")
	public String updateDelivery(@Validated @ModelAttribute() UpdateDeliveryInfoForm updateDeliveryInfoForm,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("入力ミス");
			return "deliveryInfo";
		}
		else {

			String pass = updateDeliveryInfoForm.getPass();
			String rePass = updateDeliveryInfoForm.getRePass();
			if(!pass.equals(rePass)) {
				model.addAttribute("passErrMsg", "パスワードは一致していません");
				return "deliveryInfo";
			}
			else {
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
