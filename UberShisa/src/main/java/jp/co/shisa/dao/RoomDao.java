package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;

public interface RoomDao {

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

	//注文
		//insert order_info
		public void insertOrder(Integer roomId, Integer shopId, Integer totalPrice, String dateTime);
		//insert後に、order_idほしいので、探す
		public OrderInfo getByRoomIdTime(Integer roomId, String dateTime);
		//insert order_item
		public void insertItem(Integer orderId, Integer productId, Integer amount, Integer subtotal);
		//insert log
		public void insertLog(Integer orderId, String dateTime);
}
