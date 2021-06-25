package jp.co.shisa.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
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
		public void insertOrder(Integer roomId, Integer shopId, Integer totalPrice, Timestamp dateTime);
		//insert後に、order_idほしいので、探す
		public OrderInfo getByRoomIdTime(Integer roomId, Timestamp dateTime);
		//insert order_item
		public void insertItem(Integer orderId, Integer productId, Integer amount, Integer subtotal);
		//insert log
		public void insertLog(Integer orderId, Timestamp dateTime, Integer status);
		//update stock
		public void updateStock(Integer productId, Integer amount);

	//
		public OrderInfo getRecentOrder(Integer roomId);

	//orderIdからorderItemとる。ほしいのはproductName,amount,subtotal,なので、productNameのためにJOINする
		public List<OrderItem> getOrderItem(Integer orderId);

	//orderIdからorderInfoとる
		public OrderInfo getOrderInfo(Integer orderId);

	//statusが6,7以外(進行中注文)を取る
		public List<OrderInfo> getUncompOrder(Integer roomId);

	//orderIdのstatusを更新する
		public void statusUpdate(Integer orderId, Integer status);

/////////////////////////////////////////////////////////////////
	//ホテルに届きました通知のために、roomIdと任意のstatusでレコード探す。１以上あれば通知するから、リストで返さない
		//予定だったけど、そうもいかない
	public List<OrderInfo> searchStatus(Integer roomId, Integer status);

	//Shop用
	public OrderInfo statusForShop(Integer shopId, Integer status);

	//hotel用
	public List<OrderInfo> statusForHotel(Integer status);

}
