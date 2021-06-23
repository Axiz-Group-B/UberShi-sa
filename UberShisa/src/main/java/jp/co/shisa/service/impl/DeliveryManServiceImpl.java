package jp.co.shisa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.dao.impl.DeliveryManDaoImpl;
import jp.co.shisa.service.DeliveryManService;

@Service
public class DeliveryManServiceImpl implements DeliveryManService{
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



}
