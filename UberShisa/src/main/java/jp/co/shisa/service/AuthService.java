package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface AuthService {
	public UserInfo loginCheck(LoginForm form);
	public Room loginByRoom(UserInfo userInfo);
	public DeliveryMan loginByDeliveryMan(UserInfo userInfo);
	public OrderInfo checkNotFinishedOrderByDeliveryManId(Integer deliveryManId);
	public Shop loginByShop(UserInfo userInfo);
	public List<OrderInfo> checkNoDeliveryManOrder();
	public List<OrderInfo> checkFinishedOrderByShop(Shop shop);
	public List<OrderInfo> checkNotFinishedOrderByShop(Shop shop);
	public List<Room> checkAllRoomAndHasOrder();
	}
