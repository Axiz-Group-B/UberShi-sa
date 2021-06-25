package jp.co.shisa.service.impl;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.shisa.dao.RoomDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.service.RoomService;

@Service
@Transactional
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

	//productIdから情報取得
	@Override
	public Product productById(Integer productId) {
		return roomDao.productById(productId);
	}

	//注文。トランザクションのために１つのメソッドで全部のinsert実行する
	@Override
	public void insertOrderAll(Integer roomId, Integer shopId, Integer totalPrice, List<OrderItem> list, Integer status) {

		Timestamp dateTime = new Timestamp(System.currentTimeMillis());

		roomDao.insertOrder(roomId, shopId, totalPrice, dateTime);
		OrderInfo order = roomDao.getRecentOrder(roomId);

		for(OrderItem i : list) {
			roomDao.insertItem(order.getOrderId(), i.getProductId(), i.getAmount(), i.getSubtotal());
			roomDao.updateStock(i.getProductId(), i.getAmount());
		}

		roomDao.insertLog(order.getOrderId(), dateTime, status);
	}

	//
	public OrderInfo getByRoomIdTime(Integer roomId, Timestamp dateTime) {
		return roomDao.getByRoomIdTime(roomId, dateTime);
	}

	//１番新しい時間のオーダー取ってくる
	public OrderInfo getRecentOrder(Integer roomId) {
		return roomDao.getRecentOrder(roomId);
	}

	//orderIdからorderItemとる。ほしいのはproductName,amount,subtotal,なので、productNameのためにJOINする
	public List<OrderItem> getOrderItem(Integer orderId){
		return roomDao.getOrderItem(orderId);
	}

	//orderIdからorderItemとる
	public OrderInfo getOrderInfo(Integer orderId) {
		return roomDao.getOrderInfo(orderId);
	}

	//statusが6,7以外(進行中注文)を取る
	public List<OrderInfo> getUncompOrder(Integer roomId){
		return roomDao.getUncompOrder(roomId);
	}

	////////////////////////////////////////////////////////////////
	//ホテル届きました通知のために、roomIdと任意のstatusでレコード探す。１以上あれば通知するから、リストで返さない
	public List<OrderInfo> searchStatus(Integer roomId, Integer status) {
		return roomDao.searchStatus(roomId, status);
	}

	//shop
	public OrderInfo statusForShop(Integer shopId, Integer status) {
		return roomDao.statusForShop(shopId, status);
	}

	//hotel
	public List<OrderInfo> statusForHotel(Integer status) {
		return roomDao.statusForHotel(status);
	}

	//キャンセル処理
	@Override
	public void cansel(Integer orderId, Integer status, Timestamp dateTime) {
		roomDao.statusUpdate(orderId, status);
		roomDao.insertLog(orderId, dateTime, status);
	}

}
