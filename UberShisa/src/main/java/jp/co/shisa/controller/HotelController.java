package jp.co.shisa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.controller.form.hotelDeliveryForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;


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


	@RequestMapping("/hotel/delivery")
	public String hotelDelivery(Model model){
		List<DeliveryMan> dList = null;

		dList = hotelService.DeliveryManFindAll();

		model.addAttribute("dList",dList);

		return "hotelDelivery";
	}

	@RequestMapping("/hotel/deliveryListDelete")
	public String hotelDeliveryListDelete(
											@ModelAttribute("hotelDelivery") hotelDeliveryForm form,
											Model model){
		List<DeliveryMan> dList = null;

		dList = hotelService.DeliveryManFindAll();

		System.out.println(form.getDeliveryListDelete());

		model.addAttribute("ListDelete",form.getDeliveryListDelete());
		model.addAttribute("dList",dList);

		return "hotelDelivery";
	}


	//店舗管理画面　店舗一覧表
	//@RequestMapping("hotel/hotelAddStore")
//	public String index(Model model) {
//		List<Shop> list = hotelService.shopFindAll();
//		model.addAttribute("shop",list);
//		return "index";
//	}


    // @GetMapping("hotelshop")






	//ホテルトップへ遷移
	@RequestMapping("/hotel")
	public String hotel(Model model) {
		//session.invalidate();
		return "hotel";
		//hotelに遷移
	}


	//注文履歴画面へ遷移
		@RequestMapping("/order")
		public String order(Model model) {
			return "hotelOrderHistory";
			//hotelOrderHistoryに遷移
		}


	//キャンセル注文画面へ遷移
	@RequestMapping("/cancel")
	public String cancel(Model model) {
		return "hotelCancelOrderOfRoom";
		//hotelCancelOrderOfRoomに遷移
	}


	//shopをリストに取得して店舗管理画面へ遷移
	@RequestMapping("hotelAddStore")
	public String hotelAddStore(Model model) {
		List<Shop> list = hotelService.shopFindAll();
		model.addAttribute("shop",list);
		return "hotelAddStore"; //hotelAddStoreに遷移
	}
}