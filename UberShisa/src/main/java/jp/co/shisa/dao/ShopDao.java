package jp.co.shisa.dao;

import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface ShopDao {

	public void updateShopInfo(UserInfo userInfo, Shop shop);
}
