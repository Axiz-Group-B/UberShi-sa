package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;

public interface HotelDao {
	public List<Shop> shopFindAll();

	public List<OrderInfo> orderInfoFindAll();

	public List<DeliveryMan> DeliveryManFindAll();
}
