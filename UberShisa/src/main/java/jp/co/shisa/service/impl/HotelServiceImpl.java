package jp.co.shisa.service.impl;

<<<<<<< HEAD

import org.springframework.stereotype.Service;

import jp.co.shisa.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelDao hotelDao;

	@Override
	public List<Shop> shopFindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.shopFindAll();
	}

	@Override
	public List<OrderInfo> orderInfoFindAll() {
		// TODO 自動生成されたメソッド・スタブ
		return hotelDao.orderInfoFindAll();
	}
>>>>>>> refs/remotes/origin/main

}
