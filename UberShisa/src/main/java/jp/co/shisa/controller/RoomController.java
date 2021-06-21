package jp.co.shisa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.ShopService;

@Controller
@EnableAutoConfiguration
public class RoomController {
	@Autowired
	private ShopService shopS;

	@RequestMapping("/room/order")
	public String order(Model model) {

		List<Shop> list = shopS.findAll();
		model.addAttribute("shopList", list);

		return "order";
	}
}
