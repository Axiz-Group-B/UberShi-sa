package jp.co.shisa.service;

import java.sql.Timestamp;
import java.util.List;

import jp.co.shisa.entity.OrderInfo;
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
	public void insertOrderAll(Integer roomId, Integer shopId, Integer totalPrice, List<OrderItem> list, Integer status);

	//
	public OrderInfo getByRoomIdTime(Integer roomId, Timestamp dateTime);

	//１番新しい時間のオーダー取ってくる
	public OrderInfo getRecentOrder(Integer roomId);

	//orderIdからorderItemとる。ほしいのはproductName,amount,subtotal,なので、productNameのためにJOINする
	public List<OrderItem> getOrderItem(Integer orderId);

	//orderIdからorderInfoとる
	public OrderInfo getOrderInfo(Integer orderId);

	//statusが6,7以外(進行中注文)を取る
	public List<OrderInfo> getUncompOrder(Integer roomId);

	//ホテル届きました通知のために、roomIdと任意のstatusでレコード探す
	public List<OrderInfo> searchStatus(Integer roomId, Integer status);

	//Shop通知用
	public OrderInfo statusForShop(Integer shopId, Integer status);

	//hotel
	public List<OrderInfo> statusForHotel(Integer status) ;

	//キャンセル注文
	public void cansel(Integer orderId, Integer status, Timestamp dateTime);

}
