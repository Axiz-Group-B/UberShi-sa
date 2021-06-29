package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface AuthDao {
	public UserInfo loginCheck(LoginForm form);
	public Room loginByRoom(UserInfo userInfo);
	public DeliveryMan loginByDeliveryMan(UserInfo userInfo);
	public OrderInfo checkNotFinishedOrderByDeliveryManId(Integer deliveryManId);
	public Shop loginByShop(UserInfo userInfo);
	public List<OrderInfo> checkNoDeliveryManOrder();
	public List<OrderInfo> checkFinishedOrderByShop(Shop shop);
	public List<OrderInfo> checkNotFinishedOrderByShop(Shop shop);
	public List<Room> checkAllRoom();
	public void checkHasOrderByRoom(Room room);
}
