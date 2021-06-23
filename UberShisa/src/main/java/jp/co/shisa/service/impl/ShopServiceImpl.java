package jp.co.shisa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	ShopDao shopDao;
	@Override
	public void updateShopInfo(UserInfo userInfo, Shop shop) {
		// TODO 自動生成されたメソッド・スタブ
		shopDao.updateShopInfo(userInfo, shop);
	}


}
