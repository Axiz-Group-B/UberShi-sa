package jp.co.shisa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.form.RoomOrderForm;
import jp.co.shisa.service.RoomService;

@Controller
@EnableAutoConfiguration
public class RoomController {
	@Autowired
	private RoomService roomS;
	@Autowired
	private HttpSession session;

	@GetMapping("/room/order")
	public String order(Model model) {

		List<Shop> list = roomS.findAll();
		//全検索用に、listにadd
		Shop shop = new Shop(0,"全店舗から検索");
		list.add(0,shop);
		session.setAttribute("shopList", list);

		return "order";
	}

	@GetMapping("/room/select")
	public String roomSelect(@ModelAttribute("roomSelect") RoomOrderForm form, Model model) {
		//formのshopIdで検索
		//shopId==0なら、全部の店から検索
		if(form.getShopId() == 0) {
			List<Product> list = roomS.searchFromAll(form.getProductName());
			if(list.isEmpty()){
				model.addAttribute("msg", "検索条件に当てはまるものはありませんでした");
				return "order";
			}
			model.addAttribute("productList", list);
			return "order";
		}

		List<Product> list = roomS.searchFromOne(form.getProductName(), form.getShopId());
		if(list.isEmpty()){//アルゴリズム力ない
			model.addAttribute("msg", "検索条件に当てはまるものはありませんでした");
			return "order";
		}
		model.addAttribute("productList", list);
		return "order";
	}


	//js用
	@GetMapping("/room/selectBox")
	@ResponseBody
	public String selectBox(@RequestParam("a") Integer shopId,@ModelAttribute("roomSelect") RoomOrderForm form, Model model) {
		List<Product> list = roomS.allProduct(shopId);
		String html ="<table> <tr> <th>商品名</th> <th>単価(円)</th> <th>店舗名</th> </tr> ";
		for(Product p : list) {
			html += " <tr> <td> " + p.getProductName() + "</td> "
					+ " <td>" + p.getPrice() + "</td> "
					+ " <td>" + p.getName() + "</td> </tr> ";
		}
		html += "</table>";
		return html;
	}

	//パラメーター付き

}
