package jp.co.axiz.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.UserDao;
import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.util.ParamUtil;

@Repository
public class UserDaoImpl implements UserDao {
	private static final String FIND_BY_ID = "SELECT * FROM user_info WHERE login_id = :loginId ";
	private static final String ORDER_BY_USERID = " ORDER BY user_id";
	private static final String AND_PASS = " AND password = :pass";

	private static final String FIND_ALL_USER = "SELECT * FROM user_info ";
	private static final String FIND_ALL_WHERE = "SELECT * FROM user_info WHERE  ";

	private static final String INSERT_INTO = "INSERT INTO user_info(login_id, user_name, telephone, password, role_id)"
			+ " VALUES (:loginId, :userName, :telephone, :password, :roleId)";

	private static final String DELETE_BY_LOGINID = "DELETE FROM user_info WHERE login_id = :loginId";

	private static final String UPDATE = "UPDATE user_info SET login_id = :loginId, user_name = :userName, "
			+ "telephone = :telephone, password = :password, role_id = :roleId "
			+ "WHERE user_id = :userId";

	private static final String CHECK_EXISTED_LOGINID = "SELECT login_id FROM user_info WHERE "
			+ "login_id = :loginId AND user_id != :userId";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public User findById(String loginId) {
		String sql = FIND_BY_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", loginId);
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList.get(0);
	}

	@Override
	public User loginAuth(String loginId, String pass) {
		String sql = FIND_BY_ID + AND_PASS;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", loginId);
		param.addValue("pass", pass);
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList.get(0);
	}

	@Override
	public List<User> findAllUser() {
		// TODO 自動生成されたメソッド・スタブ
		String sql = FIND_ALL_USER + ORDER_BY_USERID;
		List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList;
	}

	@Override
	public List<User> findByCondition(String name, String tel) {
		// TODO 自動生成されたメソッド・スタブ
		List<String> parameter = new ArrayList<String>();
		List<String> conditions = new ArrayList<String>();
		List<String> columnName = new ArrayList<String>();

		if (ParamUtil.isNullOrEmpty(name) && ParamUtil.isNullOrEmpty(tel)) {
			return findAllUser();
		} else {
			if (!ParamUtil.isNullOrEmpty(name)) {
				parameter.add(name);
				conditions.add("login_id = :loginId");
				columnName.add("loginId");

			}
			if (!ParamUtil.isNullOrEmpty(tel)) {
				parameter.add(tel);
				conditions.add("telephone = :telephone");
				columnName.add("telephone");
			}
		}

		String whereString = String.join(" AND ", conditions.toArray(new String[0]));
		String sql = FIND_ALL_WHERE + whereString + ORDER_BY_USERID;
		System.out.println(sql);

		MapSqlParameterSource param = new MapSqlParameterSource();
		for (int i = 0; i < parameter.size(); i++) {
			param.addValue(columnName.get(i), parameter.get(i));
		}

		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList;
	}

	@Override
	public void userRegister(User user) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = INSERT_INTO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", user.getLoginId());
		param.addValue("userName", user.getUserName());
		param.addValue("telephone", user.getTelephone());
		param.addValue("password", user.getPassword());
		param.addValue("roleId", user.getRoleId());

		jdbcTemplate.update(sql, param);
		System.out.println("レコードを挿入しました。");
	}

	@Override
	public void delete(String loginId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = DELETE_BY_LOGINID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", loginId);
		jdbcTemplate.update(sql, param);
	}

	@Override
	public void update(User user) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = UPDATE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", user.getLoginId());
		param.addValue("userName", user.getUserName());
		param.addValue("telephone", user.getTelephone());
		param.addValue("password", user.getPassword());
		param.addValue("roleId", user.getRoleId());
		param.addValue("userId", user.getUserId());
		jdbcTemplate.update(sql, param);
	}

	@Override
	public boolean isExistedLoginId(User user) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = CHECK_EXISTED_LOGINID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", user.getLoginId());
		param.addValue("userId", user.getUserId());
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? false : true;
	}

}
