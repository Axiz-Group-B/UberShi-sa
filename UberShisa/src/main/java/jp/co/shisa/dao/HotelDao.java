package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.controller.form.hotelAddStoreForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;

public interface HotelDao {
	public List<Shop> shopFindAll();

	public List<OrderInfo> orderInfoFind(Integer shopId,String year,String month);

	public List<DeliveryMan> DeliveryManFindAll();

	public List<OrderInfo> OrderInfoFindId(Integer orderId);

	public void UserInfoDelete(Integer deliveryManId);


	public Room roomNameSearch(String roomName);

	public void DeliveryManDelete(Integer deliveryManId);

	public Integer totalPrice(Integer shopId,String year,String month);


	public Integer priceSum(Integer orderId);

	public void hotelUserInfoDelete(Integer shopId);

	public void HotelShopDelete(Integer shopId);



	public Room roomLoginIdAndPassSearch(Integer userId);

	public List<OrderInfo> orderAndDeliveryManSearch(Integer roomId);

	public Room getRoomInfo(Integer roomId);

	public List<OrderInfo> getOrderListByRoomId(Integer roomId);

	public Room getRoomInfoByUserId(Integer userId);


	public List<OrderInfo> selectOrderByRoomId(Integer roomId);
	public void changeOrderStatusByRoomId(Integer roomId);
	public void orderStatusChageSixAddLog(Integer orderId);
	public boolean checkLoginId(String loginId);
	public void updateLoginId(Integer userId,String loginId);
	public boolean checkPass(String pass);
	public void updatePass(Integer userId,String pass);

	//---------------------------------------------
	public String checkLoginId(hotelAddStoreForm signupForm);
	public void insertUserInfo(hotelAddStoreForm userInfo);
	public void insertShop(hotelAddStoreForm hotelShop, Integer userId);
	public Integer UserId(hotelAddStoreForm signupForm);

	//---------------------------------------------

	public void updateOrderStatusIsFive(Integer orderId);
	public void changeStatusIsFiveLog(Integer orderId);
	public void updateOrderStatusIsSix(Integer orderId);
	public void changeStatusIsSixLog(Integer orderId);
	public List<OrderInfo> selectCancelOrderInfo();
	public List<Room> selectCancelOrderRoomList();
	public OrderInfo selectCancelOrderInfo(Integer orderId);
	public Room selectCancelOrderRoom(Integer roomId);
	public void cancelOrderComplete(Integer orderId);
	public void cancelOrderCompleteLog(Integer orderId);
}

