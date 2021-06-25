package jp.co.shisa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.controller.form.hotelAddStoreForm;
import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;

	@Override
	public List<Shop> shopFindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.shopFindAll();
	}

	@Override
	public List<OrderInfo> orderInfoFind(Integer shopId,String dateTime) {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.orderInfoFind(shopId,dateTime);
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
	public Integer totalPrice(Integer shopId,String dateTime) {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.totalPrice(shopId,dateTime);
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




}

