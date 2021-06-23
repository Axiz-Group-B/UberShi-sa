package jp.co.shisa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.shisa.dao.DeliveryManDao;
import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.service.ShopService;

@Service
@Transactional
public class ShopServiceImpl implements ShopService{

	@Autowired
	ShopDao shopDao;

	@Autowired
	DeliveryManDao deliveryManDao;


	public OrderInfo checkOrder(Integer orderId) {
		return shopDao.checkOrder(orderId);
	}
	public List<OrderItem> checkOrderContents(Integer orderId) {
		return shopDao.checkOrderContents(orderId);
	}

}
