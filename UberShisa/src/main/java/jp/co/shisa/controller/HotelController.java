package jp.co.shisa.controller;

<<<<<<< HEAD
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;
>>>>>>> refs/remotes/origin/main

@Controller
@EnableAutoConfiguration
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@RequestMapping("/hotel/orderHistory")
	public String hotelOrderHistory(Model model){

		List<Shop> sList = null;
		List<OrderInfo> oList = null;

		sList = hotelService.shopFindAll();
		oList = hotelService.orderInfoFindAll();

		model.addAttribute("sList",sList);
		model.addAttribute("oList",oList);

		return "hotelOrderHistory";
	}
}
