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
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

@Repository
public class AuthDaoImpl implements AuthDao{

	String SELECT_BY_LOGINID_AND_PASS_AND_ROLEID = "SELECT * FROM user_info WHERE login_id = :loginId AND pass = :pass AND role_id = :roleId";
	String SELECT_FROM_USERINFO_AND_ROOM = "SELECT ui.*, room_id,room_name FROM user_info ui JOIN room r ON ui.user_id = r.user_id WHERE ui.user_id = :userId";
	String SELECT_FROM_USERINFO_AND_DELIVERY_MAN = "SELECT ui.*,delivery_man_id,name,tel FROM user_info ui JOIN delivery_man dm ON ui.user_id = dm.user_id WHERE ui.user_id = :userId ";
	String SELECT_FROM_USERINFO_AND_SHOP = "SELECT ui.*,shop_id,name,tel,address FROM user_info ui JOIN shop s ON ui.user_id = s.user_id WHERE ui.user_id = :userId";

	@Autowired
	NamedParameterJdbcTemplate namedJT;

	public UserInfo loginCheck (LoginForm form) {
		String sql = "SELECT * FROM user_info WHERE login_id = :loginId AND pass = :pass AND role_id = :roleId";
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


}
