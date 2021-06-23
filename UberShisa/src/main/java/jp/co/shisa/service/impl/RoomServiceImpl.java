package jp.co.shisa.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	public void insertOrderAll(Integer roomId, Integer shopId, Integer totalPrice, List<OrderItem> list) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mi:ss");
		String dateTime = sdf.format(timestamp);

		roomDao.insertOrder(roomId, shopId, totalPrice, dateTime);
		OrderInfo order = roomDao.getByRoomIdTime(roomId, dateTime);

		for(OrderItem i : list) {
			roomDao.insertItem(order.getOrderId(), i.getProductId(), i.getAmount(), i.getSubtotal());
		}

		roomDao.insertLog(order.getOrderId(), dateTime);
	}

	//
	public OrderInfo getByRoomIdTime(Integer roomId, String dateTime) {
		return roomDao.getByRoomIdTime(roomId, dateTime);
	}

	//１番新しい時間のオーダー取ってくる
	public OrderInfo getRecentOrder(Integer roomId) {
		return roomDao.getRecentOrder(roomId);
	}

}
