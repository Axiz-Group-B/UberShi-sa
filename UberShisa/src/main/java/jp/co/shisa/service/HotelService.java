package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.controller.form.hotelAddStoreForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;

public interface HotelService {

	public List<Shop> shopFindAll();
	public List<OrderInfo> orderInfoFind(Integer shopId,String dateTime);
	public List<DeliveryMan> DeliveryManFindAll();
	public List<OrderInfo> OrderInfoFindId(Integer orderId);
	public void UserInfoDelete(Integer deliveryManId);

	public Room roomNameSearch(String roomName);

	public void DeliveryManDelete(Integer deliveryManId);
	public Integer totalPrice(Integer shopId,String dateTime);
	public Integer priceSum(Integer orderId);
	public void hotelUserInfoDelete(Integer shopId);
	public void HotelShopDelete(Integer shopId);


	//神山-----------------------------------------------
	public boolean checkLoginId(hotelAddStoreForm signupForm);
	public void insertUserInfo(hotelAddStoreForm userInfo);
	public void insertShop(hotelAddStoreForm hotelShop);
    //---------------------------------------------------
}

