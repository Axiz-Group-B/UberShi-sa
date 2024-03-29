package jp.co.shisa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.dao.DeliveryManDao;
import jp.co.shisa.dao.impl.DeliveryManDaoImpl;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.service.DeliveryManService;

@Service
public class DeliveryManServiceImpl implements DeliveryManService {
	@Autowired
	DeliveryManDaoImpl deliveryManDaoImpl;

	public void insertDeliveryMan(SignupForm deliveryMan) {
		Integer userId = deliveryManDaoImpl.UserId(deliveryMan);

		deliveryManDaoImpl.insertDeliveryMan(deliveryMan, userId);
		//return無ければ実行されるだけ
	}

	public void insertUserInfo(SignupForm userInfo) {
		deliveryManDaoImpl.insertUserInfo(userInfo);
	}

	@Autowired
	DeliveryManDao deliveryManDao;

	public OrderInfo checkOrder(Integer orderId) {
		return deliveryManDao.checkOrder(orderId);
	}

	public List<OrderItem> checkOrderContents(Integer orderId) {
		return deliveryManDao.checkOrderContents(orderId);
	}

	//	配達員情報の更新
	@Override
	public void updateDeliveryManInfo(DeliveryMan deliveryMan) {
		// TODO 自動生成されたメソッド・スタブ
		deliveryManDao.updateDeliveryManInfo(deliveryMan);
	}

	public boolean checkLoginId(SignupForm signupForm) {
		//checkLoginIdを実行して、それをnullかどうか判断する

		String checkLoginId = deliveryManDao.checkLoginId(signupForm);

		if (checkLoginId == null) {
			return true;
		} else {
			return false;
		}
	}

	public void addDeliveryManIdInOrderAndAddLog(Integer orderId, Integer deliveryManId) {
		deliveryManDao.addDeliveryManIdInOrder(orderId, deliveryManId);
		deliveryManDao.insertLogStatusIsTwo(orderId);
	}



}
