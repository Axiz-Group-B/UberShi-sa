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
			+ " UNION "
			+ " SELECT *, NULL as deliveryman, NULL as deliverytel "
			+ " FROM order_info "
			+ "  WHERE delivery_man_id IS NULL AND shop_id = :shopId AND status >= 4";

	String SELECT_FROM_USERINFO_NOT_FINISHED_ORDER_BY_SHOP = "SELECT oi.*, delivery_man_name, delivery_man_tel"
			+ " FROM order_info oi JOIN delivery_man dm ON oi.delivery_man_id = dm.delivery_man_id "
			+ " WHERE shop_id = :shopId AND status BETWEEN 1 AND 3 "
			+ " UNION "
			+ " SELECT *,NULL as deliveryman, NULL as deliverytel "
			+ " FROM order_info "
			+ "  WHERE delivery_man_id IS NULL AND shop_id = :shopId AND status BETWEEN 1 AND 3";

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

	public Shop loginByShop(UserInfo userInfo) {
		String sql = SELECT_FROM_USERINFO_AND_SHOP;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userInfo.getUserId());
		List<Shop> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Shop>(Shop.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public List<OrderInfo> checkNoDeliveryManOrder() {
		String sql = "SELECT oi.*,shop_name,address FROM order_info oi JOIN shop s ON oi.shop_id = s.shop_id WHERE status IN(1,2)";
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

	public List<Room> checkAllRoomAndHasOrder() {
	String sql = "SELECT r.*,order_id FROM room r FULL OUTER JOIN order_info oi ON r.room_id = oi.room_id WHERE status BETWEEN 1 AND 6";
		List<Room> list = namedJT.query(sql,new BeanPropertyRowMapper<Room>(Room.class));
		return list.isEmpty() ? null : list;
	}
}
