package jp.co.shisa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
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

}
