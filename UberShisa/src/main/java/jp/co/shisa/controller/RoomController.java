package jp.co.shisa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.shisa.controller.form.RoomCartForm;
import jp.co.shisa.controller.form.RoomOrderForm;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.RoomService;

@Controller
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
		//リストnullなら合計金額も０にしたい

		if(session.getAttribute("roomCart")!=null) {
			List<OrderItem> itemList = (List<OrderItem>)session.getAttribute("roomCart");
			if(itemList.isEmpty()) {
			session.setAttribute("totalPrice","0");
			}
		}

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
			form.setShopId(0);//null回避
			return "order";
		}

		List<Product> list = roomS.searchFromOne(form.getProductName(), form.getShopId());
		if(list.isEmpty()){//アルゴリズム力ない
			model.addAttribute("msg", "検索条件に当てはまるものはありませんでした");
			return "order";
		}
		model.addAttribute("productList", list);
		form.setShopId(form.getShopId());
		return "order";
	}



	//js用
	@GetMapping("/room/selectBox")
	@ResponseBody
	public String selectBox(@RequestParam("a") Integer shopId,@ModelAttribute("roomSelect") RoomOrderForm form, Model model) {
		List<Product> list = roomS.allProduct(shopId);
		String html ="<table> <tr> <th>商品名</th> <th>単価(円)</th> <th>店舗名</th> </tr> ";
		for(Product p : list) {
			html += " <tr> <td> <a href=\"/room/select/"
					+ p.getProductId() + "\" class=\"btn-mini\"> "
					+  p.getProductName() + "</a> </td> "
					+ " <td>" + p.getPrice() + "</td> "
					+ " <td>" + p.getShopName() + "</td> </tr> ";
		}
		html += "</table>";
		return html;
	}

	//パラメーター付き
	@RequestMapping("/room/select/{productId}")
	public String orderDetail(@PathVariable Integer productId, @ModelAttribute("roomCart") RoomCartForm form, Model model) {
		Product p = roomS.productById(productId);
		session.setAttribute("product", p);
		return "orderDetail";
	}

	//商品詳細画面から
	@RequestMapping(value="/room/order",params="roomBack", method=RequestMethod.POST)//もどるボタン
	public String roomOrderBack(Model model) {
		List<Shop> list = roomS.findAll();
		//全検索用に、listにadd
		Shop shop = new Shop(0,"全店舗から検索");
		list.add(0,shop);
		session.setAttribute("shopList", list);

		return "order";
	}

	@RequestMapping(value="/room/order",params="cart", method=RequestMethod.POST)//カートに入れるボタン
	public String roomOrderCart(@ModelAttribute("roomCart") RoomCartForm form,Model model) {//これ、余裕があれば上のメソッドと合わせる
		List<Shop> list = roomS.findAll();
		//全検索用に、listにadd
		Shop shop = new Shop(0,"全店舗から検索");
		list.add(0,shop);
		session.setAttribute("shopList", list);

		//orderItem型のインスタンスをセッションに入れる…？
		//先にセッションからリスト取得→そこにaddして、セッションにリストを入れなおす
		OrderItem order = new OrderItem(form.getProductId(),form.getAmount(), form.getSubtotal(), form.getProductName(), form.getShopId());
		List<OrderItem> cartList = new ArrayList<OrderItem>();
		if(session.getAttribute("roomCart") != null) {
			cartList = (List<OrderItem>)session.getAttribute("roomCart");//null回避
			for(OrderItem i : cartList) {
				if(i.getProductId() == form.getProductId()) {//カートに入れる商品IDが、リストに入ってると、はじくように。
					model.addAttribute("msg", "この商品は選択済みです。一度カートから削除して、追加してください。");
					return "orderDetail";
				}
			}
		}
		cartList.add(order);
		session.setAttribute("roomCart", cartList);

		Integer total =0;
		if(cartList != null) {//null回避
			for(OrderItem i : cartList) {
				total += i.getSubtotal();
			}
		}
		session.setAttribute("totalPrice", total);
		return "order";
	}

	//カートから商品を削除
	@RequestMapping(value="/room/deleteCart", params="deleteOne", method=RequestMethod.POST)//1つだけ削除
	public String deleteCart(@ModelAttribute("roomCart") RoomCartForm form,Model model) {
		List<OrderItem> list = (List<OrderItem>)session.getAttribute("roomCart");//ここに来る＝リストnullではない
		int index=0;
		if(list != null) {//null回避
			for(OrderItem i : list) {
				if(i.getProductId() == form.getProductId()) {//削除ボタンと同じpIdなら、nullとする
					list.remove(index);
						break;//removeしたらfor文を抜けたい
				}
				index ++;
				//for文に行かせたくない

			}
		}
		session.setAttribute("roomCart", list);
		Integer total =0;
		if(list != null) {//消した後に…null回避
			for(OrderItem i : list) {
				total += i.getSubtotal();
			}
		} else {
			total=0;
		}
		session.setAttribute("totalPrice", total);

		if(form.getFrom().equals("order")) {//どのページから来たか判別
			return "order";
		}
		return "orderDetail";
	}

	//全消し
	@RequestMapping(value="/room/deleteCart", params="deleteAll", method=RequestMethod.POST)//1つだけ削除
	public String deleteAll(@ModelAttribute("roomCart") RoomCartForm form,Model model) {
		List<OrderItem> list = new ArrayList<OrderItem>();
		if(session.getAttribute("roomCart")!=null){//セッションがnullじゃないときにだけ、listにいれれる
			list = (List<OrderItem>)session.getAttribute("roomCart");
		}
		if(!list.isEmpty()) {
			list.clear();
		}

		session.setAttribute("roomCart", list);
		session.setAttribute("totalPrice", "0");
		if(form.getFrom().equals("order")) {//どのページから来たか判別
			return "order";
		}
		return "orderDetail";
	}

	//注文
	@RequestMapping(value="/room/deleteCart", params="order", method=RequestMethod.POST)
	public String orderCart(@ModelAttribute("roomCart") RoomCartForm form,Model model) {
		//カート空なら、はじく
		List<OrderItem> list = new ArrayList<OrderItem>();
		if(session.getAttribute("roomCart")!=null){//セッションがnullじゃないときにだけ、listにいれれる
			list = (List<OrderItem>)session.getAttribute("roomCart");
		}
		if(list.isEmpty() || session.getAttribute("roomCart")==null) {//セッションnullか、listが空のとき
			model.addAttribute("error","カートが空です");

			if(form.getFrom().equals("order")) {//どのページから来たか判別
				return "order";
			} else {
				return "orderDetail";
			}
		}

		//if文抜ける＝注文できる(order_info,order_item,logにinsertする)
		Room room = (Room) session.getAttribute("loginUser");//部屋情報
		Integer totalPrice = (Integer) session.getAttribute("totalPrice");
		roomS.insertOrderAll(room.getRoomId(), form.getShopId(), totalPrice, list);

		return "orderHistoryDetail";

	}

	//カート削除ＪＳ用できなさそうだからやめた
	/*@GetMapping("/room/deleteCart/{b}")
	@ResponseBody
	public String deleteCart(@PathVariable("b") Integer productId, @ModelAttribute("roomCart") RoomCartForm form,Model model){
		List<OrderItem> list = (List<OrderItem>)session.getAttribute("roomCart");//ここに来る＝リストnullではない
		int index=0;
		for(OrderItem i : list) {
			if(i.getProductId() == productId) {//削除ボタンと同じpIdなら、nullとする
				list.remove(index);
			}
			index ++;
		}
		session.setAttribute("roomCart", list);
		Integer total =0;
		if(list != null) {//null回避
			for(OrderItem i : list) {
				total += i.getSubtotal();
			}
		}
		session.setAttribute("totalPrice", total);
		//HTML
		String html = "<caption>カート</caption><tr><th>商品名</th><th>個数</th><th></th></tr>";
		if(list != null) {//消したうえでリストがnullじゃないか確認
			for(OrderItem i : list) {
				html += "<tr><td>"+ i.getProductName()+"</td>"
							+ "<td>"+ i.getAmount() +"<td>"
							+ "<td><button class=\"btn-mini del-btn-mini deleteItem\" value=\""+i.getProductId()+"\">削除</button></td>";
			}
		}
		html += "</table>";
		return html;
	}
	*/

}


