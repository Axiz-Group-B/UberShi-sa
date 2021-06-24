package jp.co.shisa.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.RoomDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
@Repository
public class RoomDaoImpl implements RoomDao{
	@Autowired
	private JdbcTemplate jT;
	@Autowired
	private NamedParameterJdbcTemplate namedJT;

	//店全件表示
	@Override
	public List<Shop> findAll(){
		String sql = "select * from shop";
		List<Shop> list = jT.query(sql, new BeanPropertyRowMapper<Shop>(Shop.class));
		return list;
	}

	//１つのshopIdの商品を全表示
	@Override
	public List<Product> allProduct(Integer shopId){
		String sql = "select p.*, s.* from product p inner join shop s on p.shop_id=s.shop_id "
				+ " where p.shop_id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", shopId);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	//全商品からあいまい検索
	@Override
	public List<Product> searchFromAll(String productName){
		String sql = "select p.*, s.* from product p inner join shop s on p.shop_id=s.shop_id "
				+ " where p.product_name like '%' ||:productName|| '%'";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productName", productName);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	//１つのshopIdからあいまい検索
	@Override
	public List<Product> searchFromOne(String productName, Integer shopId){
		String sql = "select p.*, s.* from product p inner join shop s on p.shop_id=s.shop_id "
				+ " where p.product_name like '%' || :productName || '%'"
				+ " and p.shop_id=:id ";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productName", productName);
		param.addValue("id", shopId);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	//productIdから情報取得
	@Override
	public Product productById(Integer productId) {
		String sql = "select * from product where product_id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", productId);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list.isEmpty() ? null : list.get(0);
	}

	//注文、insert order_info
	@Override
	public void insertOrder(Integer roomId, Integer shopId, Integer totalPrice, Timestamp dateTime) {
		String sql = "insert into order_info "
				+ " (room_id, shop_id, total_price, status, date_time) "
				+ " values (:roomId, :shopId, :totalPrice, 1, to_timestamp(:dateTime , 'YYYY-MM-DD HH24:MI:SS'))";//statusは１確定

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);
		param.addValue("shopId", shopId);
		param.addValue("totalPrice", totalPrice);
		param.addValue("dateTime", dateTime);

		namedJT.update(sql, param);
	}

	//insert order_item
	@Override
	public void insertItem(Integer orderId, Integer productId, Integer amount, Integer subtotal) {
		String sql = "insert into order_item (order_id, product_id, amount, subtotal) "
				+ " values(:orderId, :productId, :amount, :subtotal)";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		param.addValue("productId", productId);
		param.addValue("amount", amount);
		param.addValue("subtotal", subtotal);

		namedJT.update(sql, param);
	}

	//insert log
	public void insertLog(Integer orderId, Timestamp dateTime) {
		String sql = "insert into log(order_id, status, date_time, check_flag) "
				+ " values(:orderId, 1, :dateTime, 1);";//statusは1で確定check_flagは、0の時通知してないって感じにするのか？１は通知しないからどっちでもいいけど…

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		param.addValue("dateTime", dateTime);

		namedJT.update(sql, param);
	}

	//insert後に、order_idほしいので、探す
	public OrderInfo getByRoomIdTime(Integer roomId, Timestamp dateTime) {
		String sql = "select * from order_info where room_id=:roomId and date_time=:dateTime";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);
		param.addValue("dateTime", dateTime);

		List<OrderInfo>list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		return list.isEmpty() ? null : list.get(0);
	}

	//１番新しい時間のオーダー取ってくる
	public OrderInfo getRecentOrder(Integer roomId) {
		String sql = "select * from order_info where room_id = :id order by date_time desc";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", roomId);

		List<OrderInfo>list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}

	//orderIdからorderItemとる。ほしいのはproductName,amount,subtotal,なので、productNameのためにJOINする
	public List<OrderItem> getOrderItem(Integer orderId){
		String sql = "select i.*, p.* from order_item i join product p "
				+ " on i.product_id=p.product_id "
				+ " where i.order_id=:orderId";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		return namedJT.query(sql, param, new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
	}

	//orderIdからorderInfoとる
	public OrderInfo getOrderInfo(Integer orderId) {
		String sql = "select * from order_info where order_id=:orderId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		List<OrderInfo> list= namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}

	//statusが6,7以外(進行中注文)を取る
	public List<OrderInfo> getUncompOrder(Integer roomId){
		String sql = "select * from order_info where room_id=:roomId and status <> 6 and status <> 7";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list;
	}

	////////////////////////////////////////////////////////////////////////
	//ホテル届きました通知のために、roomIdと任意のstatusでレコード探す
	@Override
	public List<OrderInfo> searchStatus(Integer roomId, Integer status) {
		String sql = "select * from order_info where room_id=:roomId and status=:status";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);
		param.addValue("status", status);
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list;
	}

	//Shop用
	@Override
	public OrderInfo statusForShop(Integer shopId, Integer status) {
		String sql = "select * from order_info where shop_id=:shopId and status=:status";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId", shopId);
		param.addValue("status", status);
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}

	//hotel用
	@Override
	public OrderInfo statusForHotel(Integer status) {
		String sql = "select * from order_info where status=:status";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("status", status);
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}
}
