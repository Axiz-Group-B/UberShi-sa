package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;

@Repository
public class HotelDaoImpl implements HotelDao{
	private static final String SHOP_FINDALL = "SELECT * FROM shop";
	private static final String ORDER_INFO_FIND = "SELECT * FROM order_info WHERE shop_id= :shopId";
	private static final String DELIVERY_MAN_FINDALL = "SELECT * FROM delivery_man";
	private static final String ORDER_INFO_FIND_ID = "SELECT order_info.order_id as order_id, product_name, amount, price FROM order_info JOIN order_item ON order_info.order_id = order_item.order_id JOIN product ON order_item.product_id = product.product_id WHERE order_info.order_id= :orderId";
	private static final String USER_INFO_DELETE = "DELETE FROM user_info WHERE user_id = (SELECT u.user_id FROM user_info AS u LEFT JOIN delivery_man AS d ON u.user_id = d.user_id WHERE d.delivery_man_id = :deliveryManId)";
	private static final String DELIVERY_MAN_DELETE = "DELETE FROM delivery_man WHERE delivery_man_id = :deliveryManId";
	private static final String TOTAL_PRICE = "SELECT SUM(total_price) FROM order_info WHERE shop_id= :shopId";
	private static final String PRICE_SUM = "SELECT SUM(total_price) FROM order_info JOIN order_item ON order_info.order_id = order_item.order_id JOIN product ON order_item.product_id = product.product_id WHERE order_info.order_id= :orderId";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	//shop全検索
	 public List<Shop> shopFindAll() {
		 String sql = SHOP_FINDALL;

		 List<Shop> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Shop>(Shop.class));

		 return list;
	 }

	//order検索
	 public List<OrderInfo> orderInfoFind(Integer shopId) {
		 String sql = ORDER_INFO_FIND;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("shopId", shopId);

		 List<OrderInfo> list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		 return list;
	 }

	//配達員を全表示するときに使う
	 public List<DeliveryMan> DeliveryManFindAll() {
		 String sql = DELIVERY_MAN_FINDALL;

		 List<DeliveryMan> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<DeliveryMan>(DeliveryMan.class));

		 return list;
	 }

	 //注文情報を出すときに使用
	 public List<OrderInfo> OrderInfoFindId(Integer orderId){
		 String sql = ORDER_INFO_FIND_ID;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId", orderId);

		 List<OrderInfo> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		 return list;
	 }

	 public void UserInfoDelete(Integer deliveryManId) {
		 String sql = USER_INFO_DELETE;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("deliveryManId", deliveryManId);

		 jdbcTemplate.update(sql, param);
	 }


	 public void DeliveryManDelete(Integer deliveryManId) {
		 String sql = DELIVERY_MAN_DELETE;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("deliveryManId", deliveryManId);

		 jdbcTemplate.update(sql, param);
	 }



	 //ホテルが店舗を削除-------------------------------------
	 public void HotelShopDelete(Integer shopId) {
		 String sql = USER_INFO_DELETE;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("shopId", shopId);

		 jdbcTemplate.update(sql, param);
	 }
     //-------------------------------------------------------


	 //合計金額表示
	 public Integer totalPrice(Integer shopId) {
		 String sql = TOTAL_PRICE;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("shopId", shopId);

		 Integer list = jdbcTemplate.queryForObject(sql, param,Integer.class);

		 return list;
	 }

	//合計金額表示
	 public Integer priceSum(Integer orderId) {
		 String sql = PRICE_SUM;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId", orderId);

		 Integer list = jdbcTemplate.queryForObject(sql, param,Integer.class);

		 return list;
	}
}
