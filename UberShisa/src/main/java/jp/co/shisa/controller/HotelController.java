package jp.co.shisa.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.controller.form.RoomOrderForm;
import jp.co.shisa.controller.form.hotelDeliveryForm;
import jp.co.shisa.controller.form.hotelOrderHistoryForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;


@Controller
@EnableAutoConfiguration
public class HotelController {

	@Autowired
	private HotelService hotelService;


	@RequestMapping("/hotel/orderHistory")
	public String hotelOrderHistory(@ModelAttribute("orderHistory") hotelOrderHistoryForm form,
									Model model){
		List<Shop> sList = null;
		List<OrderInfo> oList = null;

		sList = hotelService.shopFindAll();
		oList = hotelService.orderInfoFind(form.getOrderShopId());

		System.out.println(form.getOrderShopId());

		model.addAttribute("orderListId",form.getOrderListId());
		model.addAttribute("orderShopId",form.getOrderShopId());
		model.addAttribute("sList",sList);
		model.addAttribute("oList",oList);

		return "hotelOrderHistory";
	}

	@RequestMapping("/hotel/orderHistoryFind")
	public String hotelOrderHistoryFindId(@ModelAttribute("orderHistory") hotelOrderHistoryForm form,
									Model model){
		List<Shop> sList = null;
		List<OrderInfo> oList = null;
		List<OrderInfo> oFindList = null;

		sList = hotelService.shopFindAll();
		oList = hotelService.orderInfoFind(form.getOrderShopId());
		oFindList = hotelService.OrderInfoFindId(form.getOrderListId());

		//System.out.println(form.getOrderListId());

		model.addAttribute("orderListId",form.getOrderListId());
		model.addAttribute("sList",sList);
		model.addAttribute("oList",oList);
		model.addAttribute("oFindList",oFindList);

		return "hotelOrderHistory";
	}

	@RequestMapping("/hotel/delivery")
	public String hotelDelivery(@ModelAttribute("hotelDelivery") hotelDeliveryForm form,
								Model model){
		List<DeliveryMan> dList = null;

		dList = hotelService.DeliveryManFindAll();

		model.addAttribute("dList",dList);

		return "hotelDelivery";
	}

	@RequestMapping("/hotel/deliveryListDelete")
	public String hotelDeliveryListDelete(@ModelAttribute("hotelDelivery") hotelDeliveryForm form,
											Model model){
		List<DeliveryMan> dList = null;

		dList = hotelService.DeliveryManFindAll();

		//hotelService.UserInfoDelete(Integer deliveryManId);

		//確認
		System.out.println(form.getDeliveryListDelete());

		//model.addAttribute("ListDelete",form.getDeliveryListDelete());
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

	@RequestMapping("/roomSearch")
	public String roomNameSearch(@Validated @ModelAttribute("roomNameForm")RoomOrderForm form, BindingResult bindingResult,Model model) {
	Room getRoomInfo = hotelService.roomNameSearch(form.getRoomName());
	if(getRoomInfo == null) {
		 model.addAttribute("nullMsg","検索した部屋番号はありません");
		 return "hotel";
	} return "hotelOrderOfRoom";
	}
}