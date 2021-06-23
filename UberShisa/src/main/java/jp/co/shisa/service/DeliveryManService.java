package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;

public interface DeliveryManService {
	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);
//	配達員情報を更新(pha)----------------------------------------------------------------------
	public void updateDeliveryManInfo(DeliveryMan deliveryMan);
}
