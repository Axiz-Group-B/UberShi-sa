package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.entity.Shop;

public interface ShopDao {

	//店テーブル全件表示
	public List<Shop> findAll();
}
