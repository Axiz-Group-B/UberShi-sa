package jp.co.shisa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;

	@Override
	public List<Shop> findAll(){
		return shopDao.findAll();
	}
}
