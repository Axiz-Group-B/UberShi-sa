package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;

public interface HotelService {

	public List<Shop> shopFindAll();
	public List<OrderInfo> orderInfoFind(Integer shopId,String year,String month);
	public List<DeliveryMan> DeliveryManFindAll();
	public List<OrderInfo> OrderInfoFindId(Integer orderId);
	public void UserInfoDelete(Integer deliveryManId);

	public Room roomNameSearch(String roomName);
	public Room roomLoginIdAndPassSearch(Room userId);
	public void DeliveryManDelete(Integer deliveryManId);
	public Integer totalPrice(Integer shopId,String year,String month);
	public Integer priceSum(Integer orderId);
	public void hotelUserInfoDelete(Integer shopId);
	public void HotelShopDelete(Integer shopId);

	public List<OrderInfo> orderAndDeliveryManSearch(Integer roomId);

	public Room getRoomInfo(Integer roomId);
	public List<OrderInfo> getOrderListByRoomId(Integer roomId);
	public Room getRoomInfoByUserId(Integer userId);
	public void changeLoginIdAndPass(Integer userId,String loginId,String password);

	public void deleteOrder(Integer orderId);
}

