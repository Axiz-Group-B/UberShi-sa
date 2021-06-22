package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;

public interface DeliveryManService {
	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);
}
