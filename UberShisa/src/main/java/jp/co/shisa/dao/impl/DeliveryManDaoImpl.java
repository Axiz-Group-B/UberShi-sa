package jp.co.shisa.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.dao.DeliveryManDao;

@Repository
public class DeliveryManDaoImpl implements DeliveryManDao {
	private static final String INSERT_DELIVERY_MAN = "INSERT INTO delivery_man "
			+ "(user_id, delivery_name, delivery_tel) "
			+ "VALUES(:user_id, :delivery_name, :delivery_tel)";
	private static final String INSERT_USER_INFO = "INSERT INTO user_info"
			+"(login_id, pass, role_id) "
			+"VALUES(:login_id, :pass, :role_id)";
	private static final String SELECT_USER_ID = "SELECT user_id from user_info"
			+ " where login_id = :login_id and pass = :pass";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	public void insertDeliveryMan(SignupForm deliveryMan, Integer userId) {
		//テーブルuser_info、delivery_manそれぞれにinsertするメソッド
		String sql = INSERT_DELIVERY_MAN;

		 MapSqlParameterSource param = new MapSqlParameterSource();
//		 param.addValue("login_id", deliveryMan.getLoginId());
		 param.addValue("user_id", userId);
		 param.addValue("delivery_name", deliveryMan.getDeliveryManName());
		 param.addValue("delivery_tel", deliveryMan.getDeliveryManTel());

		 jdbcTemplate.update(sql, param);
	}

	public void insertUserInfo(SignupForm userInfo) {
		String sql = INSERT_USER_INFO;

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("login_id", userInfo.getLoginId());
		param.addValue("pass", userInfo.getPass());
		param.addValue("role_id", 2);

		jdbcTemplate.update(sql, param);
	}

	//user_idを取るメソッド
	public Integer UserId(SignupForm signupForm) {
		String sql = SELECT_USER_ID;

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("login_id", signupForm.getLoginId());
		param.addValue("pass", signupForm.getPass());

		return jdbcTemplate.queryForObject(sql, param, Integer.class);
	}

}
