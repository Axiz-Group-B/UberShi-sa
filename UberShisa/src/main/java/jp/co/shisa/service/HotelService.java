package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;

public interface HotelService {

	public List<Shop> shopFindAll();
	public List<OrderInfo> orderInfoFind(Integer shopId);
	public List<DeliveryMan> DeliveryManFindAll();
	public List<OrderInfo> OrderInfoFindId(Integer orderId);
	public void UserInfoDelete(Integer deliveryManId);
	public void DeliveryManDelete(Integer deliveryManId);
	public Integer totalPrice(Integer shopId);

	public void hotelUserInfoDelete(Integer shopId);
	public void HotelShopDelete(Integer shopId);


}

