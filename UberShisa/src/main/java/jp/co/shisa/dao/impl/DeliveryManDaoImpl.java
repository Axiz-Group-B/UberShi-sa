package jp.co.shisa.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.dao.DeliveryManDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;


@Repository
public class DeliveryManDaoImpl implements DeliveryManDao {

	private String SELECT_FROM_ORDERINFO_BY_ORDERID = "SELECT oi.*,shop_name,shop_tel,address FROM order_info oi JOIN shop s ON oi.shop_id = s.shop_id WHERE order_id = :orderId";
	private String SELECT_FROM_ORDERITEM_BY_ORDERID = "SELECT oi.*,product_name,text FROM order_item oi JOIN product p ON oi.product_id = p.product_id  WHERE order_id = :orderId";

//	配達員情報を更新のSQL
	private static final String UPDATE_DELIVERY_MAN_INFO = "BEGIN; "
			+ " update user_info set login_id = :loginId, pass = :pass "
			+ " where user_id = :userId; "
			+ " update delivery_man set delivery_man_name = :deliveryManName, delivery_man_tel = :deliveryManTel "
			+ " where user_id = :userId;"
			+ " COMMIT;";

	private static final String CHECK_LOGINID = "SELECT login_id from user_info where login_id = :login_id";

	@Autowired
	NamedParameterJdbcTemplate namedJT;

	public OrderInfo checkOrder(Integer orderId) {
		String sql = SELECT_FROM_ORDERINFO_BY_ORDERID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		List<OrderInfo> list = namedJT.query(sql,param,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}

	public List<OrderItem> checkOrderContents(Integer orderId) {
		String sql = SELECT_FROM_ORDERITEM_BY_ORDERID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		List<OrderItem> list = namedJT.query(sql,param,new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
		return list.isEmpty() ? null : list;


	}
//	配達員情報を更新(pha) start----------------------------------------------------------------------

	@Override
	public void updateDeliveryManInfo(DeliveryMan deliveryMan) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = UPDATE_DELIVERY_MAN_INFO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", deliveryMan.getLoginId());
		param.addValue("pass", deliveryMan.getPass());
		param.addValue("deliveryManName", deliveryMan.getDeliveryManName());
		param.addValue("deliveryManTel", deliveryMan.getDeliveryManTel());
		param.addValue("userId", deliveryMan.getUserId());

		namedJT.update(sql, param);
	}

//	配達員情報を更新(pha) end------------------------------------------------------------------------

	private static final String INSERT_DELIVERY_MAN = "INSERT INTO delivery_man "
			+ "(user_id, delivery_man_name, delivery_man_tel) "
			+ "VALUES(:user_id, :delivery_man_name, :delivery_man_tel)";
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
		 param.addValue("delivery_man_name", deliveryMan.getDeliveryManName());
		 param.addValue("delivery_man_tel", deliveryMan.getDeliveryManTel());

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

	public String checkLoginId(SignupForm signupForm) {
		try{
			String sql = CHECK_LOGINID;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("login_id", signupForm.getLoginId());
		return jdbcTemplate.queryForObject(sql, param, String.class);
		} catch(Exception e){

			return null;
		}
	}
}
