package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface ShopDao {

	public void updateShopInfo(UserInfo userInfo, Shop shop);
	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);
	
}
