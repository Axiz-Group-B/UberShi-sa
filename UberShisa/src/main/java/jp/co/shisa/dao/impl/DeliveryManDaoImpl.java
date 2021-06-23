package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.DeliveryManDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;

@Repository
public class DeliveryManDaoImpl implements DeliveryManDao {
	private String SELECT_FROM_ORDERINFO_BY_ORDERID = "SELECT oi.*,shop_name,shop_tel,address FROM order_info oi JOIN shop s ON oi.shop_id = s.shop_id WHERE order_id = :orderId";
	private String SELECT_FROM_ORDERITEM_BY_ORDERID = "SELECT oi.*,product_name,text FROM order_item oi JOIN product p ON oi.product_id = p.product_id  WHERE order_id = :orderId";



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

}
