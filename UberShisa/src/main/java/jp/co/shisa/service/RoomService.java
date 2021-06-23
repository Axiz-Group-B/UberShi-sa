package jp.co.shisa.service;

import java.util.List;

import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;

public interface RoomService {
	//店テーブル全件表示
	public List<Shop> findAll();

	//１つのshopIdの商品を全表示
	public List<Product> allProduct(Integer shopId);

	//全商品からあいまい検索
	public List<Product> searchFromAll(String productName);

	//１つのshopIdからあいまい検索
	public List<Product> searchFromOne(String productName, Integer shopId);

	//productIdから情報取得
	public Product productById(Integer productId);

	//注文、insert order_info
	public void insertOrderAll(Integer roomId, Integer shopId, Integer totalPrice, List<OrderItem> list);

}
