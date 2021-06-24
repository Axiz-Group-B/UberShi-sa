package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

@Repository
public class ShopDaoImpl implements ShopDao{
	private String SELECT_FROM_ORDERINFO_BY_ORDER_ID = "SELECT oi.*,delivery_man_name,delivery_man_tel FROM order_info oi JOIN delivery_man dm ON oi.delivery_man_id = dm.delivery_man_id WHERE order_id = :orderId";
	private String SELECT_FROM_ORDERITEM_BY_ORDERID = "SELECT oi.*,product_name,price FROM order_item oi JOIN product p ON oi.product_id = p.product_id  WHERE order_id = :orderId";

	@Autowired
	NamedParameterJdbcTemplate namedJT;

	public OrderInfo checkOrder(Integer orderId) {
		String sql = SELECT_FROM_ORDERINFO_BY_ORDER_ID;
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

	private static final String UPDATE_SHOP_INFO = "BEGIN; "
			+ " update user_info set login_id = :loginId, pass = :pass "
			+ " where user_id = :userId; "
			+ " update shop set shop_name = :shopName, shop_tel = :shopTel, address = :address "
			+ " where user_id = :userId; "
			+ " COMMIT;";
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void updateShopInfo(UserInfo userInfo, Shop shop) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = UPDATE_SHOP_INFO;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("loginId", userInfo.getLoginId());
		param.addValue("pass", userInfo.getPass());
		param.addValue("shopName", shop.getShopName());
		param.addValue("shopTel", shop.getShopTel());
		param.addValue("address", shop.getAddress());
		param.addValue("userId", userInfo.getUserId());
		jdbcTemplate.update(sql, param);
		System.out.println("店舗情報更新された");
	}

	public List<Product> selectAllProductByShopId(Integer shopId) {
		String sql = "SELECT * FROM product WHERE shop_id = :shopId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId", shopId);
		List<Product> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Product>(Product.class));

		return list.isEmpty() ? null : list;
	}

}
