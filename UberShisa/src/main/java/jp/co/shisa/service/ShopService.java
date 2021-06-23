package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface ShopService {

	public void updateShopInfo(UserInfo userInfo, Shop shop);


	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);

}
