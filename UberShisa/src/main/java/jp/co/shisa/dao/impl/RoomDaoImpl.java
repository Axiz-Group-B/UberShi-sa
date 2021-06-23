package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.RoomDao;
import jp.co.shisa.entity.OrderInfo;
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
	public void insertOrder(Integer roomId, Integer shopId, Integer totalPrice, String dateTime) {
		String sql = "insert into order_info "
				+ " (room_id, shop_id, total_price, status, date_time) "
				+ " values (:roomId, :shopId, :totalPrice, 1, to_timestamp(:dateTime, 'YY/MM/DD HH:mi:ss))";//statusは１確定

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
	public void insertLog(Integer orderId, String dateTime) {
		String sql = "insert into log(order_id, status, date_time, check_flag) "
				+ " values(:orderId, 1, :dateTime, 1);";//statusは1で確定check_flagは、0の時通知してないって感じにするのか？１は通知しないからどっちでもいいけど…

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		param.addValue("dateTime", dateTime);

		namedJT.update(sql, param);
	}

	//insert後に、order_idほしいので、探す
	public OrderInfo getByRoomIdTime(Integer roomId, String dateTime) {
		String sql = "select * from order_info where room_id=:roomId and date_time=:dateTime";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);
		param.addValue("dateTime", dateTime);

		List<OrderInfo>list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		return list.isEmpty() ? null : list.get(0);
	}

	//１番新しい時間のオーダー取ってくる
	public OrderInfo getRecentOrder(Integer roomId) {
		String sql = "select * from order Info where room_id = :id order by date_time desc";

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);

		List<OrderInfo>list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}


}
