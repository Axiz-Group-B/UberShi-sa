package jp.co.shisa.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.AuthService;
import jp.co.shisa.service.DeliveryManService;
import jp.co.shisa.service.RoomService;

@Controller
@EnableAutoConfiguration
public class AuthController {
	@Autowired
	AuthService authService;

	@Autowired
	RoomService roomService;

	@Autowired
	HttpSession session;

	@Autowired
	DeliveryManService deliveryManService;

	@RequestMapping({ "/", "/index" })
	public String index(@ModelAttribute("login")LoginForm form,BindingResult bindingResult,Model model) {
		//30分配達員決まらなかったらそのorderのstatus更新

		//定期的に実行する処理
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				//orderInfoから、statusが1のレコードをとる。orderDateTime取得して、現在時刻と比較。30分以上経ってたらstatusを3にする
				if (roomService.statusForHotel(1) != null) {//null回避。nullなら何も処理しない
					//現在時刻取得
					LocalDateTime nowTime = LocalDateTime.now();//計算にはこのクラスの方がよさそう
					List<OrderInfo> list = roomService.statusForHotel(1);
					for (OrderInfo o : list) {
						LocalDateTime orderTime = o.getDateTime().toLocalDateTime();
						if (orderTime.plusMinutes(30).isBefore(nowTime)) {//orderTime＋３０分して現在の時刻より前になったら、status３にする
							//DBアクセスして、このorderIdのstatusを3にする
							Timestamp dateTime = new Timestamp(System.currentTimeMillis());//引数のためにtimestamp型
							roomService.cansel(o.getOrderId(), 3, dateTime);
						}
					}
				}
			}
		};

		//実行する頻度とかの設定
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 1000, 600000);
		//scheduleAtFixedRate(定期的に実行したいタスク,初回のタスク実行までの時間(ms),実行するタスクの間隔(ms))

		return "index";
	}

	@RequestMapping("/login")
	public String login(@Validated @ModelAttribute("login") LoginForm form,BindingResult bindingResult, Model model,
			RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return "index";
		}

		UserInfo userInfo = authService.loginCheck(form);

		if (userInfo == null) {

			String errorMsg = "IDまたはPASSが間違っています";
			attr.addFlashAttribute("errorMsg", errorMsg);
			return "redirect:index";
		}

		Integer roleId = userInfo.getRoleId();

		switch (roleId) {
		case 1:

			Room room = authService.loginByRoom(userInfo);
			session.setAttribute("loginRoom", room);

			List<Shop> list = roomService.findAll();
			//全検索用に、listにadd
			Shop shopPullDown = new Shop(0, "全店舗から検索");
			list.add(0, shopPullDown);
			String str = "0";
			session.setAttribute("totalPrice", str);
			session.setAttribute("shopList", list);
			return "order";
		case 2:
			DeliveryMan deliveryMan = authService.loginByDeliveryMan(userInfo);
			session.setAttribute("loginDeliveryMan", deliveryMan);
			session.setAttribute("userInfo", userInfo);
			List<OrderInfo> noDeliveryManOrderList = authService.checkNoDeliveryManOrder();
			session.setAttribute("noDeliveryManOrderList", noDeliveryManOrderList);
			OrderInfo orderInfo = authService.checkNotFinishedOrderByDeliveryManId(deliveryMan.getDeliveryManId());
			if(orderInfo!=null) {
				List<OrderItem> orderItem = deliveryManService.checkOrderContents(orderInfo.getOrderId());
				session.setAttribute("orderInfoForDeliveryMan", orderInfo);
				session.setAttribute("orderItemForDeliveryMan", orderItem);
				return "deliveryOrderSelected";
			}
			return "/delivery";
		case 3:
			Shop shop = authService.loginByShop(userInfo);
			session.setAttribute("loginShop", shop);
			session.setAttribute("userInfo", userInfo);
			List<OrderInfo> finishedOrderList = authService.checkFinishedOrderByShop(shop);
			List<OrderInfo> notFinishedOrderList = authService.checkNotFinishedOrderByShop(shop);
			session.setAttribute("finishedOrderList", finishedOrderList);
			session.setAttribute("notFinishedOrderList", notFinishedOrderList);
			return "/store";
		case 4:
			session.setAttribute("loginUser", userInfo);
			List<Room> AllRoomList = authService.checkAllRoomAndHasOrder();
			session.setAttribute("AllRoomList", AllRoomList);
			return "/hotel";
		default:
			String errorMsg = "IDまたはPASSが間違っています";
			attr.addFlashAttribute("errorMsg", errorMsg);
			return "redirect:/index";
			}

	}

	/*
	* ログアウト
	*/
	@RequestMapping("/logout")
	public String logout(@ModelAttribute("login")LoginForm form,BindingResult bindingResult,Model model) {
		session.invalidate();
		return "index";
		//indexに遷移
	}

}
