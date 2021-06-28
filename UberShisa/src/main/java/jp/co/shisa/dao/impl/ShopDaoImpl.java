package jp.co.shisa.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private String SELECT_FROM_ORDERINFO_BY_ORDER_ID = "SELECT oi.*, delivery_man_name, delivery_man_tel "
			+ " FROM order_info oi JOIN delivery_man dm ON oi.delivery_man_id = dm.delivery_man_id "
			+ " WHERE order_id = :orderId "
			+ " UNION "
			+ " SELECT *, NULL, NULL "
			+ " FROM order_info "
			+ " WHERE delivery_man_id IS NULL AND order_id = :orderId";

	private String SELECT_FROM_ORDERITEM_BY_ORDERID = "SELECT oi.*, p.product_name, p.price "
			+ " FROM order_item oi JOIN product p ON oi.product_id = p.product_id "
			+ " WHERE order_id = :orderId";
//	店舗情報更新　SQL(pha)
	private static final String UPDATE_SHOP_INFO = "BEGIN; "
			+ " update user_info set login_id = :loginId, pass = :pass "
			+ " where user_id = :userId; "
			+ " update shop set shop_name = :shopName, shop_tel = :shopTel, address = :address "
			+ " where user_id = :userId; "
			+ " COMMIT;";
//	店舗側でオーダーの商品を渡すSQL(pha)
	private static final String UPDATE_STATUS_ORDER = "update order_info set status = 4 "
			+ " where order_id = :orderId";

	@Autowired
	NamedParameterJdbcTemplate namedJT;
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public OrderInfo checkOrder(Integer orderId) {
		String sql = SELECT_FROM_ORDERINFO_BY_ORDER_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		List<OrderInfo> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		return list.isEmpty() ? null : list.get(0);
	}


	public List<OrderItem> checkOrderContents(Integer orderId) {
		String sql = SELECT_FROM_ORDERITEM_BY_ORDERID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		List<OrderItem> list = namedJT.query(sql, param, new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
		return list.isEmpty() ? null : list;

	}



//	店舗情報更新（pha）start-------------------------------------------------------------------------------
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
//	店舗情報更新（pha）end---------------------------------------------------------------------------------

	public List<Product> selectAllProductByShopId(Integer shopId) {
		String sql = "SELECT * FROM product WHERE shop_id = :shopId ORDER BY product_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId", shopId);
		List<Product> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Product>(Product.class));

		return list.isEmpty() ? null : list;
	}

	public void insertProduct(Product product) {
		String sql = "INSERT INTO product (shop_id,image,text,product_name,price,stock) VALUES (:shopId,:image,:text,:productName,:price,:stock)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("shopId", product.getShopId());
		param.addValue("image",product.getImage());
		param.addValue("text", product.getText());
		param.addValue("productName",product.getProductName());
		param.addValue("price", product.getPrice());
		param.addValue("stock",product.getStock());
		jdbcTemplate.update(sql, param);
		System.out.println("商品情報追加");
	}

	public String selectImageByProductId(Integer productId) {
		String sql = "SELECT * FROM product WHERE product_id = :productId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productId", productId);
		List<Product> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Product>(Product.class));
		return list.get(0).getImage();
	}

	public Product selectUpdateProductByProductId(Integer productId) {
		String sql = "SELECT * FROM product WHERE product_id = :productId ORDER BY product_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productId", productId);
		List<Product> list = namedJT.query(sql,param, new BeanPropertyRowMapper<Product>(Product.class));
		return list.isEmpty() ?  null : list.get(0);
	}

//	オーダーの商品を渡す機能(pha) start-------------------------------------------------------------------
	@Override
	public void passedOrder(OrderInfo orderInfo) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = UPDATE_STATUS_ORDER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderInfo.getOrderId());
		jdbcTemplate.update(sql, param);
		System.out.println("商品が渡された");
	}


//	オーダーの商品を渡す機能(pha) end----------------------------------------------------------------------
	public void passedOrderLog(Integer orderId) {
		String sql ="INSERT INTO log (order_id,status,date_time) VALUES (:orderId,4,current_timestamp)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("orderId", orderId);
		jdbcTemplate.update(sql, param);
	}
	public void updateProduct(Product product) {
		String sql = "UPDATE product SET image = :image,text = :text,product_name = :productName, price = :price,stock = :stock WHERE product_id = :productId";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("image",product.getImage());
		param.addValue("text",product.getText());
		param.addValue("productName",product.getProductName());
		param.addValue("price",product.getPrice());
		param.addValue("stock",product.getStock());
		param.addValue("productId", product.getProductId());
		namedJT.update(sql, param);

	}

	public void deleteProducts(List<Integer> deleteProductList) {
		String sql = "DELETE FROM product WHERE product_id IN (:productId)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		Set<Integer> productId = new HashSet<>();
		for(Integer id : deleteProductList) {
			productId.add(id);
		}
		param.addValue("productId", productId);
		namedJT.update(sql, param);
	}

	public List<Product> searchMyProductsByProductName(Integer shopId,String productName) {
		String sql = "SELECT * FROM product  WHERE shop_id = :shopId AND product_name LIKE '%' ||:productName|| '%' ORDER BY product_id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productName",productName);
		param.addValue("shopId", shopId);
		List<Product> list = namedJT.query(sql, param,new BeanPropertyRowMapper<Product>(Product.class));
		return list.isEmpty() ? null : list;
	}

}
