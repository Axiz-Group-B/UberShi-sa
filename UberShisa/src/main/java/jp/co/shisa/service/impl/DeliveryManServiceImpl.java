package jp.co.shisa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.dao.DeliveryManDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.service.DeliveryManService;

@Service
public class DeliveryManServiceImpl implements DeliveryManService{

	@Autowired
	DeliveryManDao deliveryManDao;

	public OrderInfo checkOrder(Integer orderId) {
		return deliveryManDao.checkOrder(orderId);
	}

	public List<OrderItem> checkOrderContents(Integer orderId) {
		return deliveryManDao.checkOrderContents(orderId);
	}
}
