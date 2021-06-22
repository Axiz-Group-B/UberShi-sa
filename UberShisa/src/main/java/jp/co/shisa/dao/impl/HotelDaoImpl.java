package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;

@Repository
public class HotelDaoImpl implements HotelDao{
	private static final String SHOP_FINDALL = "SELECT * FROM shop";
	private static final String ORDER_INFO_FINDALL = "SELECT * FROM order_info";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	 public List<Shop> shopFindAll() {
		 String sql = SHOP_FINDALL;

		 List<Shop> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Shop>(Shop.class));

		 return list;
	 }

	 public List<OrderInfo> orderInfoFindAll() {
		 String sql = ORDER_INFO_FINDALL;

		 List<OrderInfo> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		 return list;
	 }
}
