package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.dao.AuthDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

@Repository
public class AuthDaoImpl implements AuthDao{

	String SELECT_BY_LOGINID_AND_PASS_AND_ROLEID = "SELECT * FROM user_info WHERE login_id = :loginId AND pass = :pass AND role_id = :roleId";
	String SELECT_FROM_USERINFO_AND_ROOM = "SELECT ui.*, room_id,room_name FROM user_info ui JOIN room r ON ui.user_id = r.user_id WHERE ui.user_id = :userId";
	String SELECT_FROM_USERINFO_AND_DELIVERY_MAN = "SELECT ui.*,delivery_man_id,delivery_man_name,delivery_man_tel FROM user_info ui JOIN delivery_man dm ON ui.user_id = dm.user_id WHERE ui.user_id = :userId ";
	String SELECT_FROM_USERINFO_AND_SHOP = "SELECT ui.*,shop_id,shop_name,shop_tel,address FROM user_info ui JOIN shop s ON ui.user_id = s.user_id WHERE ui.user_id = :userId";

	String SELECT_FROM_USERINFO_FINISHED_ORDER_BY_SHOP = "SELECT oi.*, delivery_man_name, delivery_man_tel"
			+ " FROM order_info oi JOIN delivery_man dm ON oi.delivery_man_id = dm.delivery_man_id "
			+ " WHERE shop_id = :shopId AND status >= 4 "
//			+ " ORDER BY oi.date_time DESC "
			+ " UNION "
			+ " SELECT *, NULL as deliveryman, NULL as deliverytel "
			+ " FROM order_info "
			+ " WHERE delivery_man_id IS NULL AND shop_id = :shopId AND status >= 4"
			+ " ORDER BY date_time DESC";

	String SELECT_FROM_USERINFO_NOT_FINISHED_ORDER_BY_SHOP = "SELECT oi.*, delivery_man_name, delivery_man_tel"
			+ " FROM order_info oi JOIN delivery_man dm ON oi.delivery_man_id = dm.delivery_man_id "
			+ " WHERE shop_id = :shopId AND status BETWEEN 1 AND 3 "
//			+ " ORDER BY oi.date_time "
			+ " UNION "
			+ " SELECT *,NULL as deliveryman, NULL as deliverytel "
			+ " FROM order_info "
			+ " WHERE delivery_man_id IS NULL AND shop_id = :shopId AND status BETWEEN 1 AND 3"
			+ " ORDER BY date_time ";

	@Autowired
	NamedParameterJdbcTemplate namedJT;

	public UserInfo loginCheck (LoginForm form) {
		String sql = SELECT_BY_LOGINID_AND_PASS_AND_ROLEID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", form.getLoginId());
		param.addValue("pass", form.getPass());
		param.addValue("roleId", form.getRoleId());

		List<UserInfo> list = namedJT.query(sql,param,new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public Room loginByRoom(UserInfo userInfo) {
		String sql = SELECT_FROM_USERINFO_AND_ROOM;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userInfo.getUserId());
		List<Room> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Room>(Room.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public DeliveryMan loginByDeliveryMan(UserInfo userInfo) {
		String sql = SELECT_FROM_USERINFO_AND_DELIVERY_MAN;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userInfo.getUserId());
		List<DeliveryMan> list = namedJT.query(sql,param,new BeanPropertyRowMapper<DeliveryMan>(DeliveryMan.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public OrderInfo checkNotFinishedOrderByDeliveryManId(Integer deliveryManId) {
		String sql = "SELECT * FROM order_info oi JOIN shop s  ON oi.shop_id = s.shop_id WHERE status = 2 AND delivery_man_id = :deliveryManId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("deliveryManId", deliveryManId);
		List<OrderInfo> list = namedJT.query(sql,param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);

	}

	public Shop loginByShop(UserInfo userInfo) {
		String sql = SELECT_FROM_USERINFO_AND_SHOP;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userInfo.getUserId());
		List<Shop> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Shop>(Shop.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public List<OrderInfo> checkNoDeliveryManOrder() {
		String sql = "SELECT oi.*,shop_name,address FROM order_info oi JOIN shop s ON oi.shop_id = s.shop_id WHERE status = 1";
		List<OrderInfo> list = namedJT.query(sql,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list;
	}

	public List<OrderInfo> checkFinishedOrderByShop(Shop shop) {
		String sql = SELECT_FROM_USERINFO_FINISHED_ORDER_BY_SHOP;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId", shop.getShopId());
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list;
	}

	public List<OrderInfo> checkNotFinishedOrderByShop(Shop shop) {
		String sql = SELECT_FROM_USERINFO_NOT_FINISHED_ORDER_BY_SHOP;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId",shop.getShopId());
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list;
	}

	public List<Room> checkAllRoom() {
	String sql = "SELECT * FROM room ORDER BY room_id";
		List<Room> list = namedJT.query(sql,new BeanPropertyRowMapper<Room>(Room.class));
		return list;
	}

	public void checkHasOrderByRoom(Room room) {
		String sql = "SELECT * FROM order_info WHERE status BETWEEN 1 AND 5  AND room_id = :roomId GROUP BY order_id,room_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("roomId",room.getRoomId());
		List<Room> roomList = namedJT.query(sql,param,new BeanPropertyRowMapper<Room>(Room.class));
		if(roomList.isEmpty()) {
			room.setOrderFlag(false);
		}else {
			room.setOrderFlag(true);
		}
	}
}
