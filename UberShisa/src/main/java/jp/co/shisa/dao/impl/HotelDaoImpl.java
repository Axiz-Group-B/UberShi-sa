package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.HotelDao;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;

@Repository
public class HotelDaoImpl implements HotelDao{
	private static final String SHOP_FINDALL = "SELECT * FROM shop";
	private static final String ORDER_INFO_FINDALL = "SELECT * FROM order_info";
	private static final String DELIVERY_MAN_FINDALL = "SELECT * FROM delivery_man";

	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	//shop全検索
	 public List<Shop> shopFindAll() {
		 String sql = SHOP_FINDALL;

		 List<Shop> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Shop>(Shop.class));

		 return list;
	 }

	//order全検索
	 public List<OrderInfo> orderInfoFindAll() {
		 String sql = ORDER_INFO_FINDALL;

		 List<OrderInfo> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));

		 return list;
	 }

	//DeliveryMan全検索
	 public List<DeliveryMan> DeliveryManFindAll() {
		 String sql = DELIVERY_MAN_FINDALL;

		 List<DeliveryMan> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<DeliveryMan>(DeliveryMan.class));

		 return list;
	 }


}
