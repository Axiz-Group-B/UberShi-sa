package jp.co.shisa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.dao.AuthDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.AuthService;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{
	@Autowired
	AuthDao authDao;

	public UserInfo loginCheck(LoginForm form) {
		return authDao.loginCheck(form);
	}

	public Room loginByRoom(UserInfo userInfo) {
		return authDao.loginByRoom(userInfo);
	}

	public DeliveryMan loginByDeliveryMan(UserInfo userInfo) {
		return authDao.loginByDeliveryMan(userInfo);
	}

	public OrderInfo checkNotFinishedOrderByDeliveryManId(Integer deliveryManId) {
		return authDao.checkNotFinishedOrderByDeliveryManId(deliveryManId);
	}

	public Shop loginByShop(UserInfo userInfo) {
		return authDao.loginByShop(userInfo);
	}

	public List<OrderInfo> checkNoDeliveryManOrder() {
		return authDao.checkNoDeliveryManOrder();
	}

	public List<OrderInfo> checkFinishedOrderByShop(Shop shop) {
		return authDao.checkFinishedOrderByShop(shop);
	}

	public List<OrderInfo> checkNotFinishedOrderByShop(Shop shop) {
		return authDao.checkNotFinishedOrderByShop(shop);
	}

	public List<Room> checkAllRoomAndHasOrder() {
		List<Room> roomList = authDao.checkAllRoom();
		for(Room r : roomList) {
			authDao.checkHasOrderByRoom(r);
		}

		return roomList;
	}





}

