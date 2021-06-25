package jp.co.shisa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.shisa.controller.form.hotelAddStoreForm;
import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;

	@Override
	public List<Shop> shopFindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.shopFindAll();
	}

	@Override
	public List<OrderInfo> orderInfoFind(Integer shopId,String year,String month) {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.orderInfoFind(shopId,year,month);
	}

	@Override
	public List<DeliveryMan> DeliveryManFindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return  hotelDao.DeliveryManFindAll();
	}

	@Override
	public List<OrderInfo> OrderInfoFindId(Integer orderId) {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.OrderInfoFindId(orderId);
	}

	@Override
	public Integer totalPrice(Integer shopId,String year,String month) {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.totalPrice(shopId,year,month);
	}


	public Room roomNameSearch(String roomName) {
		return hotelDao.roomNameSearch(roomName);
	}


	@Override
	public void UserInfoDelete(Integer deliveryManId) {
		// TODO 自動生成されたメソッド・スタブ
		hotelDao.UserInfoDelete(deliveryManId);
	}

	@Override
	public void DeliveryManDelete(Integer deliveryManId) {
		// TODO 自動生成されたメソッド・スタブ
		hotelDao.DeliveryManDelete(deliveryManId);
	}



	@Override

	public void hotelUserInfoDelete(Integer shopId) {
		// TODO 自動生成されたメソッド・スタブ
		hotelDao.hotelUserInfoDelete(shopId);
	}

	@Override
	public Integer priceSum(Integer orderId) {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.priceSum(orderId);
	}

	@Override
	public void HotelShopDelete(Integer shopId) {
		// TODO 自動生成されたメソッド・スタブ

		hotelDao.HotelShopDelete(shopId);

	}


	public Room roomLoginIdAndPassSearch(Room getUserInfo) {
		Room getLoginIdPass = hotelDao.roomLoginIdAndPassSearch(getUserInfo.getUserId());
		getUserInfo.setLoginId(getLoginIdPass.getLoginId());
		getUserInfo.setPass(getLoginIdPass.getPass());
		return getUserInfo;
	}

	public List<OrderInfo> orderAndDeliveryManSearch(Integer roomId){
		return hotelDao.orderAndDeliveryManSearch(roomId);
	}


	public Room getRoomInfo(Integer roomId) {
		return hotelDao.getRoomInfo(roomId);
	}

	public List<OrderInfo> getOrderListByRoomId(Integer roomId) {
		return hotelDao.getOrderListByRoomId(roomId);
	}

	public Room getRoomInfoByUserId(Integer userId) {
		return hotelDao.getRoomInfoByUserId(userId);
	}

	public boolean checkLoginId(String loginId) {
		return hotelDao.checkLoginId(loginId);
	}


	public void updateLoginId(Integer userId,String loginId) {
		hotelDao.updateLoginId(userId,loginId);
	}

	public boolean checkPass(String pass) {
		return hotelDao.checkPass(pass);
	}

	public void updatePass(Integer userId,String pass) {
		hotelDao.updatePass(userId,pass);
	}



	 //神山-----------------------------------------------------------



	public boolean checkLoginId(hotelAddStoreForm signupForm) {
		String checkLoginId = hotelDao.checkLoginId(signupForm);

		if (checkLoginId == null) {
			return true;
		} else {
			return false;
		}
	}



	public void insertUserInfo(hotelAddStoreForm userInfo) {
		hotelDao.insertUserInfo(userInfo);
	}


	@Override
	public void insertShop(hotelAddStoreForm hotelShop) {
		Integer userId = hotelDao.UserId(hotelShop);

		hotelDao.insertShop(hotelShop, userId);
	}


	public void deleteOrder(Integer orderId) {
		hotelDao.deleteOrder(orderId);
	}

}


