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
		System.out.println(orderInfo.getDeliveryManName());
		session.setAttribute("orderInfoForShop", orderInfo);
		session.setAttribute("orderItemForShop", list);
		return "shopOrderInfo";
	}



}
