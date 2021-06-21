package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.Shop;
@Repository
public class ShopDaoImpl implements ShopDao{
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
}
