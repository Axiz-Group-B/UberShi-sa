package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;

public interface HotelDao {
	public List<Shop> shopFindAll();

	public List<OrderInfo> orderInfoFind(Integer shopId);

	public List<DeliveryMan> DeliveryManFindAll();

	public List<OrderInfo> OrderInfoFindId(Integer orderId);

	public void UserInfoDelete(Integer deliveryManId);

	public Room roomNameSearch(String roomName);
}
