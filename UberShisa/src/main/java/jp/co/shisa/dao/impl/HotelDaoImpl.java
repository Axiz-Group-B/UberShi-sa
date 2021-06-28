package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.controller.form.hotelAddStoreForm;
import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

@Repository
public class HotelDaoImpl implements HotelDao {
	private static final String SHOP_FINDALL = "SELECT * FROM shop";
	private static final String ORDER_INFO_FIND = "SELECT * FROM order_info WHERE shop_id= :shopId AND to_char(date_time, 'YYYY/MM/DD') like :year || '/' || :month || '/%'";
	private static final String DELIVERY_MAN_FINDALL = "SELECT * FROM delivery_man";
	private static final String ORDER_INFO_FIND_ID = "SELECT order_info.order_id as order_id, product_name, amount, price FROM order_info JOIN order_item ON order_info.order_id = order_item.order_id JOIN product ON order_item.product_id = product.product_id WHERE order_info.order_id= :orderId";
	private static final String USER_INFO_DELETE = "DELETE FROM user_info WHERE user_id = (SELECT u.user_id FROM user_info AS u LEFT JOIN delivery_man AS d ON u.user_id = d.user_id WHERE d.delivery_man_id = :deliveryManId)";
	private static final String DELIVERY_MAN_DELETE = "DELETE FROM delivery_man WHERE delivery_man_id = :deliveryManId";
	private static final String TOTAL_PRICE = "SELECT SUM(total_price) FROM order_info WHERE shop_id= :shopId AND to_char(date_time, 'YYYY/MM/DD') like :year || '/' || :month || '/%'";
	private static final String PRICE_SUM = "SELECT SUM(total_price) FROM order_info JOIN order_item ON order_info.order_id = order_item.order_id JOIN product ON order_item.product_id = product.product_id WHERE order_info.order_id= :orderId";
	private static final String SHOP_DELETE = "DELETE FROM shop WHERE shop_id = :shopId";
	private static final String CHECK_LOGINID = "SELECT login_id from user_info where login_id = :login_id";
	private static final String INSERT_USER_INFO = "INSERT INTO user_info (login_id, pass, role_id) VALUES (:login_id, :pass, :role_id)";
	private static final String INSERT_SHOP = "INSERT INTO shop (user_id,shop_name, address, shop_tel) VALUES (:userId,:shop_name, :address, :shop_tel)";
	private static final String SELECT_USER_ID = "SELECT user_id from user_info"+ " where login_id = :login_id and pass = :pass";
	private static final String SHOP_USER_INFO_DELETE = "DELETE FROM user_info WHERE user_id = (SELECT u.user_id FROM user_info AS u LEFT JOIN shop AS d ON u.user_id = d.user_id WHERE d.shop_id = :shopId)";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;



	//店舗追加　神山　はじめ--------------------------------------------

	//ID重複チェック
	public String checkLoginId(hotelAddStoreForm signupForm) {

		try{
			String sql = CHECK_LOGINID;
			MapSqlParameterSource param = new MapSqlParameterSource();

			param.addValue("login_id", signupForm.getShopLoginId());
			return jdbcTemplate.queryForObject(sql, param, String.class);
			} catch(Exception e){

				return null;
			}
	}


	//user_infoに追加
	public void insertUserInfo(hotelAddStoreForm userInfo) {
		String sql = INSERT_USER_INFO;

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("login_id", userInfo.getShopLoginId());
		param.addValue("pass", userInfo.getShopPass());
		param.addValue("role_id", 3);

		jdbcTemplate.update(sql, param);
	}


	//shopに追加
	public void insertShop(hotelAddStoreForm hotelShop, Integer userId) {
		String sql = INSERT_SHOP;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("userId", userId);
		 param.addValue("shop_name", hotelShop.getShopName());
		 param.addValue("address", hotelShop.getShopAddress());
		 param.addValue("shop_tel", hotelShop.getShopTel());

		 jdbcTemplate.update(sql, param);
	}

	//user_idを取るメソッド
	public Integer UserId(hotelAddStoreForm signupForm) {
		String sql = SELECT_USER_ID;

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("login_id", signupForm.getShopLoginId());
		param.addValue("pass", signupForm.getShopPass());

		return jdbcTemplate.queryForObject(sql, param, Integer.class);
	}


	//店舗追加　神山　終わり---------------------------------------------



	//shop全検索
	public List<Shop> shopFindAll() {
		String sql = SHOP_FINDALL;

		List<Shop> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Shop>(Shop.class));

		return list;
	}

	//order検索

	 public List<OrderInfo> orderInfoFind(Integer shopId,String year,String month) {
		String sql = ORDER_INFO_FIND;

		if(year != null && (year.isEmpty()) == false) {

		}else {
			year = "%";
		}

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId", shopId);

		param.addValue("year", year);
		param.addValue("month", month);


		List<OrderInfo> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		return list;
	}

	//配達員を全表示するときに使う
	public List<DeliveryMan> DeliveryManFindAll() {
		String sql = DELIVERY_MAN_FINDALL;

		List<DeliveryMan> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DeliveryMan>(DeliveryMan.class));

		return list;
	}

	//注文情報を出すときに使用
	public List<OrderInfo> OrderInfoFindId(Integer orderId) {
		String sql = ORDER_INFO_FIND_ID;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);

		List<OrderInfo> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		return list;
	}

     //ホテルが配達員を削除-----------------------------------

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

	 //------------------------------------------------------



	 //ホテルが店舗を削除-------------------------------------

	 public void hotelUserInfoDelete(Integer shopId) {
		 String sql = SHOP_USER_INFO_DELETE;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("shopId", shopId);

		 jdbcTemplate.update(sql, param);
	 }


	 public void HotelShopDelete(Integer shopId) {
		 String sql = SHOP_DELETE;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("shopId", shopId);

		 jdbcTemplate.update(sql, param);
	 }
     //-------------------------------------------------------


	 //合計金額表示
	 public Integer totalPrice(Integer shopId,String year,String month) {
		 String sql = TOTAL_PRICE;

		 if(year != null && (year.isEmpty()) == false) {

		 }else {
			 year = "%";
		 }

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("shopId", shopId);
		 param.addValue("year", year);
		 param.addValue("month", month);

		 Integer total = jdbcTemplate.queryForObject(sql, param,Integer.class);

	 return total;
	}

	//合計金額表示
	 public Integer priceSum(Integer orderId) {
		 String sql = PRICE_SUM;

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId", orderId);

		 Integer list = jdbcTemplate.queryForObject(sql, param,Integer.class);

		 return list;

	}


	private static final String ROOM_NAME_SEARCH = "select * from room where room_name = :roomName";
	private static final String LOGIN_ID_PASS_SEARCH = "select login_id , pass from user_info where user_id = :userId";
	private static final String ORDER_AND_DELIVERY_MAN_SEARCH = "select * from order_info o left outer join delivery_man d on o.delivery_man_id = d.delivery_man_id "
			+"where room_id = :roomId  and status >= 1 and status <= 5 order by order_id";

	public Room roomNameSearch(String roomName) {

			String sql = ROOM_NAME_SEARCH;
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("roomName", roomName);
			List<Room> list =  jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Room>(Room.class));
			return list.isEmpty() ? null : list.get(0);
	 }

	public Room roomLoginIdAndPassSearch(Integer userId) {
		String sql = LOGIN_ID_PASS_SEARCH;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<Room> list =  jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Room>(Room.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public List<OrderInfo> orderAndDeliveryManSearch(Integer roomId){
		String sql = ORDER_AND_DELIVERY_MAN_SEARCH;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId", roomId);
		List<OrderInfo> list =  jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list;
	}
	 public Room getRoomInfo(Integer roomId) {
		 String sql = "SELECT ui.*,r.* FROM user_info ui JOIN room r ON ui.user_id = r.user_id WHERE room_id = :roomId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("roomId", roomId);

		 List<Room> list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Room>(Room.class));
		 return list.isEmpty() ? null : list.get(0);

	 }

	 public List<OrderInfo> getOrderListByRoomId(Integer roomId) {
		 String sql = "SELECT * FROM order_info WHERE status BETWEEN 1 AND 5  AND room_id = :roomId GROUP BY order_id,room_id";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("roomId", roomId);
		 List<OrderInfo> list = jdbcTemplate.query(sql,param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		 return list;

	 }

	 public Room getRoomInfoByUserId(Integer userId) {
		 String sql = "SELECT ui*,r* FROM user_info ui JOIN room r ON ui.user_id = r.user_id  WHERE user_id = :userId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("userId", userId);
		 List<Room> list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Room>(Room.class));
		 return list.isEmpty() ? null : list.get(0);
	 }

	 public List<OrderInfo> selectOrderByRoomId(Integer roomId) {
		 String sql = "SELECT * FROM order_info WHERE status = 6 and room_id = :roomId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("roomId",roomId);
		 List<OrderInfo> list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		 return list;
	 }

	 public void changeOrderStatusByRoomId(Integer roomId) {
		 String sql = "UPDATE order_info SET status = 7 WHERE status = 6 AND room_id = :roomId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("roomId",roomId);
		 jdbcTemplate.update(sql, param);
	 }

	 public void orderStatusChageSixAddLog(Integer orderId) {
		 String sql = "INSERT INTO log (order_id,status,date_time) VALUES (:orderId,7,current_timestamp)";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId",orderId);
		 jdbcTemplate.update(sql, param);
	 }

	 public boolean checkLoginId(String loginId) {
		 String sql = "SELECT * FROM user_info where login_id = :loginId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("loginId", loginId);
		 List<UserInfo> list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		 return list.isEmpty() ? true  : false;

	 }

	 public void updateLoginId(Integer userId,String loginId) {
		 String sql = "UPDATE  user_info SET login_id = :loginId WHERE user_id = :userId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("loginId", loginId);
		 param.addValue("userId", userId);
		 jdbcTemplate.update(sql,param);
	 }

	 public boolean checkPass(String pass) {
		 String sql = "SELECT * FROM user_info where pass = :pass";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("pass", pass);
		 List<UserInfo> list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		 return list.isEmpty() ? false : true;

	 }

	 public void updatePass(Integer userId,String pass) {
		 String sql = "UPDATE user_info SET pass = :pass WHERE user_id = :userId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("pass", pass);
		 param.addValue("userId", userId);
		 jdbcTemplate.update(sql,param);
	 }











	 public void deleteOrder(Integer orderId) {

		 String sql ="update  order_info  set status = 6 where order_id = :orderId";

		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId", orderId);

		 jdbcTemplate.update(sql, param);
	 }

	 public List<OrderInfo> selectCancelOrderInfo() {
		 String sql ="SELECT * FROM order_info oi JOIN shop s ON oi.shop_id = s.shop_id WHERE status = 3 ORDER BY order_id";
		List<OrderInfo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list;
	 }

	 public List<Room> selectCancelOrderRoomList() {
		 String sql = "SELECT * FROM room r JOIN order_info oi ON r.room_id = oi.room_id WHERE status = 3 ORDER BY order_id";
		 List<Room> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Room>(Room.class));
		 return list;
	 }

	 public OrderInfo selectCancelOrderInfo(Integer orderId) {
		 String sql = "SELECT oi.*,s.* FROM order_info oi JOIN shop s ON oi.shop_id = s.shop_id WHERE order_id = :orderId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId", orderId);
		 List<OrderInfo> list =  jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		 return list.isEmpty() ? null : list.get(0);
	 }

	 public Room selectCancelOrderRoom(Integer roomId) {
		 String sql = "SELECT * FROM room WHERE room_id = :roomId ";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("roomId", roomId);
		 List<Room> list =  jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Room>(Room.class));
		 return list.isEmpty() ? null : list.get(0);
	 }

	 public void cancelOrderComplete(Integer orderId) {
		 String sql ="UPDATE order_info SET status = 6 WHERE order_id = :orderId";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId",orderId);
		 jdbcTemplate.update(sql, param);
	 }

	 public void cancelOrderCompleteLog(Integer orderId) {
		 String sql = "INSERT INTO log (order_id,status,date_time) VALUES (:orderId,6,current_timestamp)";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("orderId",orderId);
		 jdbcTemplate.update(sql, param);
	 }
}
