package jp.co.shisa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.RoomDao;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.RoomService;

@Repository
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;

	//店テーブル全件表示
	@Override
	public List<Shop> findAll(){
		return roomDao.findAll();
	}

	//１つのshopIdの商品を全表示
	@Override
	public List<Product> allProduct(Integer shopId){
		return roomDao.allProduct(shopId);
	}

	//全商品からあいまい検索
	public List<Product> searchFromAll(String productName){
		return roomDao.searchFromAll(productName);
	}

	//１つのshopIdからあいまい検索
	public List<Product> searchFromOne(String productName, Integer shopId){
		return roomDao.searchFromOne(productName, shopId);
	}


}
