package jp.co.shisa.controller;


import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.shisa.controller.form.RoomOrderForm;
import jp.co.shisa.controller.form.hotelAddStoreForm;
import jp.co.shisa.controller.form.hotelDeliveryForm;
import jp.co.shisa.controller.form.hotelOrderHistoryForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;


@Controller
//@EnableAutoConfiguration
public class HotelController implements Serializable {

	@Autowired
	private HotelService hotelService;

	@Autowired
	HttpSession session;

	//注文履歴の初期画面遷移
	@RequestMapping("/hotel/orderHistory")
	public String hotelOrderHistory(@ModelAttribute("orderHistory") hotelOrderHistoryForm form,
									Model model){
		List<Shop> sList = null;
		List<OrderInfo> oList = null;
		Integer total = 0;

		sList = hotelService.shopFindAll();
		oList = hotelService.orderInfoFind(form.getOrderShopId(),form.getYear(),form.getMonth());
		if(hotelService.totalPrice(form.getOrderShopId(),form.getYear(),form.getMonth()) != null) {
			total = hotelService.totalPrice(form.getOrderShopId(),form.getYear(),form.getMonth());
		}else {
			total = 0;
		}

		//確認
		//System.out.println(oList.get(0));

		if(form.getOrderListId() != null) {
			model.addAttribute("orderListId",form.getOrderListId());
		}else {
			model.addAttribute("orderListId",0);
			model.addAttribute("subTotal",0);
		}

		model.addAttribute("sList",sList);
		session.setAttribute("sList",sList);
		model.addAttribute("oList",oList);
		session.setAttribute("oList",oList);
		model.addAttribute("mainTotal",total);
		session.setAttribute("mainTotal",total);

		return "hotelOrderHistory";
	}

	//注文ID押した時の画面遷移
	@SuppressWarnings("unchecked")
	@RequestMapping("/hotel/orderHistoryFind")
	public String hotelOrderHistoryFindId(@ModelAttribute("orderHistory") hotelOrderHistoryForm form,
									Model model){
		List<Shop> sList = (List<Shop>) session.getAttribute("sList");
		List<OrderInfo> oList = (List<OrderInfo>) session.getAttribute("oList");
		List<OrderInfo> oFindList = null;
		Integer mTotal = (Integer) session.getAttribute("mainTotal");
		Integer sTotal = 0;

		oFindList = hotelService.OrderInfoFindId(form.getOrderListId());
		sTotal = hotelService.priceSum(form.getOrderListId());

		//確認
		//System.out.println(form.getMonth());
		//System.out.println(total);

		model.addAttribute("orderListId",form.getOrderListId());
		model.addAttribute("sList",sList);
		model.addAttribute("oList",oList);
		model.addAttribute("oFindList",oFindList);
		model.addAttribute("mainTotal",mTotal);
		model.addAttribute("subTotal",sTotal);

		return "hotelOrderHistory";
	}

	//配達員一覧の初期画面遷移
	@RequestMapping("/hotel/delivery")
	public String hotelDelivery(@ModelAttribute("hotelDelivery") hotelDeliveryForm form,
								Model model){
		List<DeliveryMan> dList = null;

		dList = hotelService.DeliveryManFindAll();

		model.addAttribute("dList",dList);

		return "hotelDelivery";
	}

	//削除ボタン押した後の配達員一覧の画面遷移
	@RequestMapping("/hotel/deliveryListDelete")
	public String hotelDeliveryListDelete(@ModelAttribute("hotelDelivery") hotelDeliveryForm form,Model model){
		List<DeliveryMan> dList = null;

		hotelService.UserInfoDelete(form.getDeliveryListDelete());
		hotelService.DeliveryManDelete(form.getDeliveryListDelete());

		dList = hotelService.DeliveryManFindAll();

		//確認
		//System.out.println(form.getDeliveryListDelete());

		model.addAttribute("dList",dList);

		return "hotelDelivery";
	}


	//店舗を削除して店舗管理画面へ遷移-----------------------------------
	@RequestMapping("/hotelAddStoreDelete")
	public String hotelAddStoreDelete(@ModelAttribute("hotelAddStore") hotelAddStoreForm form, Model model) {
		List<Shop> list =  null;

		//hotelService.hotelUserInfoDelete(form.getHotelShopDelete());
		hotelService.HotelShopDelete(form.getHotelShopDelete());

		list = hotelService.shopFindAll();

		System.out.println(form.getHotelShopDelete());

		model.addAttribute("shop",list);
		return "hotelAddStore"; //hotelAddStoreに遷移
	}




	//shopをリストに取得して店舗管理画面へ遷移
	@RequestMapping("/hotelAddStore")
	public String hotelAddStore(Model model) {
		List<Shop> list = hotelService.shopFindAll();
		model.addAttribute("shop",list);
		return "hotelAddStore"; //hotelAddStoreに遷移
	}


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





	@RequestMapping("/roomSearch")
	public String roomNameSearch(@Validated @ModelAttribute("roomNameForm")RoomOrderForm form, BindingResult bindingResult,Model model) {
	Room getRoomInfo = hotelService.roomNameSearch(form.getRoomName());
	if(getRoomInfo == null) {
		 model.addAttribute("nullMsg","検索した部屋番号はありません");
		 return "hotel";
	} return "hotelOrderOfRoom";
	}
}


