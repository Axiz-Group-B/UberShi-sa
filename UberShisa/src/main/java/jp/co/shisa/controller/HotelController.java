package jp.co.shisa.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
			Model model) {
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

		if (form.getOrderListId() != null) {
			model.addAttribute("orderListId", form.getOrderListId());
		} else {
			model.addAttribute("orderListId", 0);
			model.addAttribute("subTotal", 0);
		}

		model.addAttribute("sList", sList);
		session.setAttribute("sList", sList);
		model.addAttribute("oList", oList);
		session.setAttribute("oList", oList);
		model.addAttribute("mainTotal", total);
		session.setAttribute("mainTotal", total);

		return "hotelOrderHistory";
	}

	//注文ID押した時の画面遷移
	@SuppressWarnings("unchecked")
	@RequestMapping("/hotel/orderHistoryFind")
	public String hotelOrderHistoryFindId(@ModelAttribute("orderHistory") hotelOrderHistoryForm form,
			Model model) {
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

		model.addAttribute("orderListId", form.getOrderListId());
		model.addAttribute("sList", sList);
		model.addAttribute("oList", oList);
		model.addAttribute("oFindList", oFindList);
		model.addAttribute("mainTotal", mTotal);
		model.addAttribute("subTotal", sTotal);

		return "hotelOrderHistory";
	}

	//配達員一覧の初期画面遷移
	@RequestMapping("/hotel/delivery")
	public String hotelDelivery(@ModelAttribute("hotelDelivery") hotelDeliveryForm form,
			Model model) {
		List<DeliveryMan> dList = null;

		dList = hotelService.DeliveryManFindAll();

		model.addAttribute("dList", dList);

		return "hotelDelivery";
	}

	//削除ボタン押した後の配達員一覧の画面遷移
	@RequestMapping("/hotel/deliveryListDelete")
	public String hotelDeliveryListDelete(@ModelAttribute("hotelDelivery") hotelDeliveryForm form, Model model) {
		List<DeliveryMan> dList = null;

		hotelService.UserInfoDelete(form.getDeliveryListDelete());
		hotelService.DeliveryManDelete(form.getDeliveryListDelete());

		dList = hotelService.DeliveryManFindAll();

		//確認
		//System.out.println(form.getDeliveryListDelete());

		model.addAttribute("dList", dList);

		return "hotelDelivery";
	}

	//店舗を削除して店舗管理画面へ遷移-----------------------------------
	@RequestMapping("/hotelAddStoreDelete")
	public String hotelAddStoreDelete(@ModelAttribute("hotelAddStore") hotelAddStoreForm form, Model model) {
		List<Shop> list = null;

		//hotelService.hotelUserInfoDelete(form.getHotelShopDelete());
		hotelService.HotelShopDelete(form.getHotelShopDelete());

		list = hotelService.shopFindAll();

		System.out.println(form.getHotelShopDelete());

		model.addAttribute("shop", list);
		return "hotelAddStore"; //hotelAddStoreに遷移
	}

	//shopをリストに取得して店舗管理画面へ遷移
	@RequestMapping("/hotelAddStore")
	public String hotelAddStore(Model model) {
		List<Shop> list = hotelService.shopFindAll();
		model.addAttribute("shop", list);
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


	public String roomNameSearch(@Validated @ModelAttribute("roomNameForm") RoomOrderForm form,
			BindingResult bindingResult, Model model) {
		Room getRoomInfo = hotelService.roomNameSearch(form.getRoomName());
		if (getRoomInfo == null) {
			model.addAttribute("nullMsg", "検索した部屋番号はありません");
			return "hotel";
		}

		List<OrderInfo> getOrderInfo = hotelService.orderAndDeliveryManSearch(getRoomInfo.getRoomId());
		if(getOrderInfo.isEmpty()) {

			/*Room selectRoom = hotelService.getRoomInfo(getRoomInfo.getRoomId());
			List<OrderInfo> MyOrderList = hotelService.getOrderListByRoomId(selectRoom.getRoomId());*/
			session.setAttribute("selectingRoom",getRoomInfo);
			return "hotelRoomUpdate";
		}
		getRoomInfo = hotelService.roomLoginIdAndPassSearch(getRoomInfo);
		session.setAttribute("getOrderInfo", getOrderInfo);
		session.setAttribute("getRoomInfo", getRoomInfo);
		model.addAttribute("listNomber", getOrderInfo.get(0));
		model.addAttribute("getRoomInfo", getRoomInfo);
		model.addAttribute("getOrderInfo", getOrderInfo);
		return "hotelOrderOfRoom";
		}

		@RequestMapping(value = "/orderChange",method=RequestMethod.POST)
		public String orderChange(@Validated @ModelAttribute("roomNameForm")RoomOrderForm form,
				BindingResult bindingResult, Model model, @RequestParam(name = "index")int index) {
		 @SuppressWarnings("unchecked")
		List<OrderInfo> getOrderInfo = (List<OrderInfo>)session.getAttribute("getOrderInfo");
		 Room getRoomInfo = (Room)session.getAttribute("getRoomInfo");
		 model.addAttribute("listNomber", getOrderInfo.get(index));
			model.addAttribute("getRoomInfo", getRoomInfo);
			model.addAttribute("getOrderInfo", getOrderInfo);
			return "hotelOrderOfRoom";
		}


	@RequestMapping(value="/hotel/roomInfo", params="roomBtn")
	public String roomInfo(HttpServletRequest request,Model model) {
		Integer selectRoomId = Integer.parseInt(request.getParameter("roomBtn"));
		Room selectRoom = hotelService.getRoomInfo(selectRoomId);
		List<OrderInfo> MyOrderList = hotelService.getOrderListByRoomId(selectRoom.getRoomId());

		if(MyOrderList.isEmpty()) {
			session.setAttribute("selectingRoom",selectRoom);
			return "hotelRoomUpdate";
		}


			return "hotelOrderOfRoom";
	}



	//店舗追加----------------------------------------------------
	@RequestMapping("/addStore")
	public String insertHotelShop(@Validated @ModelAttribute("insert") hotelAddStoreForm form, BindingResult bindingResult,Model model) {

		System.out.println(form.getShopLoginId());
		List<Shop> list = hotelService.shopFindAll();
		model.addAttribute("shop",list);


		//もし空白があったらエラー吐いて同じ画面出す
		/*if (bindingResult.hasErrors()) {
			model.addAttribute("errorPassMsg", "入力されていない項目があります");
			return "hotelAddStore";
		}*/

		//無ければ↓
			if(hotelService.checkLoginId(form)) {//IDに重複がないか確認
			    hotelService.insertUserInfo(form);//userInfoに追加
			    hotelService.insertShop(form);//shopに追加
			    list = hotelService.shopFindAll();
				model.addAttribute("shop",list);

				return "hotelAddStore";//hotelAddStoreに遷移(同じ画面)

			//重複してれば重複してると通知
			}else {
				model.addAttribute("errorPassMsg", "ログインIDが重複しています");

				return "hotelAddStore";
			}

			//return "hotelAddStore";

	//-------------------------------------------------------------



	}

	@RequestMapping("/hotel/updateIdAndPass")
	public String updateIdAndPass(Model model) {
		Room room = (Room) session.getAttribute("selectingRoom");

		String newLoginId = createWord();
		String newLoginPass = createWord();

		while(hotelService.checkLoginId(newLoginId)) {
			newLoginId = createWord();
			System.out.println(newLoginId);
		}
		System.out.println(newLoginId);
		hotelService.updateLoginId(room.getUserId(),newLoginId);

		while(hotelService.checkPass(newLoginPass)) {
			newLoginPass = createWord();
			System.out.println(newLoginPass);
		}
		System.out.println(newLoginPass);
		hotelService.updatePass(room.getUserId(),newLoginPass);

		Room newRoom = hotelService.getRoomInfo(room.getRoomId());
		session.setAttribute("selectingRoom",newRoom);

		return "redirect:/hotel/hotelRoomUpdate";

	}

	@RequestMapping("/hotel/hotelRoomUpdate")
	public String  updateRoomView(Model model) {
		return "hotelRoomUpdate";
	}

	public String createWord() {
		String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] randomWord = {word.charAt((int)(Math.random() * word.length())) , word.charAt((int)(Math.random() * word.length())), word.charAt((int)(Math.random() * word.length())) ,word.charAt((int)(Math.random() * word.length()))};
		String newRandomWord = new String(randomWord);
		return newRandomWord;
	}












	@RequestMapping(value = "/deleteOrder",params = "get",method=RequestMethod.POST)
	public String deleteOrder(Model model, @RequestParam(name = "orderId")int orderId) {

		hotelService.deleteOrder(orderId);
		Room getRoomInfo = (Room)session.getAttribute("getRoomInfo");

		List<OrderInfo> roomId = hotelService.orderAndDeliveryManSearch(getRoomInfo.getRoomId()) ;
		session.setAttribute("getOrderInfo", roomId);
		model.addAttribute("listNomber", roomId.get(0));
		model.addAttribute("getRoomInfo", getRoomInfo);
		model.addAttribute("getOrderInfo",roomId);
		return "hotelOrderOfRoom";

	}


	@RequestMapping(value = "/deleteOrder",params = "back",method=RequestMethod.POST)
	public String roomMonitorBack(@ModelAttribute("roomNameForm")RoomOrderForm form,
			BindingResult bindingResult, Model model) {

	return "hotel";


	}


}
