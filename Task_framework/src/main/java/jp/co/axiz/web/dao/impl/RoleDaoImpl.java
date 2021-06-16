package jp.co.axiz.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.axiz.web.dao.RoleDao;
import jp.co.axiz.web.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	private static final String SELECT_ALL_ROLE = "SELECT * FROM role";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Role> selectAllRole() {
		// TODO 自動生成されたメソッド・スタブ
		String sql = SELECT_ALL_ROLE;
		List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
		return roleList.isEmpty() ? null : roleList;
	}
}
