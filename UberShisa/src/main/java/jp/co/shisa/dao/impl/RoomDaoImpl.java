package jp.co.shisa.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.RoomDao;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
@Repository
public class RoomDaoImpl implements RoomDao{
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

	//１つのshopIdの商品を全表示
	@Override
	public List<Product> allProduct(Integer shopId){
		String sql = "select p.*, s.* from product p inner join shop s on p.shop_id=s.shop_id "
				+ " where p.shop_id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", shopId);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	//全商品からあいまい検索
	@Override
	public List<Product> searchFromAll(String productName){
		String sql = "select p.*, s.* from product p inner join shop s on p.shop_id=s.shop_id "
				+ " where p.product_name like '%' ||:productName|| '%'";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productName", productName);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	//１つのshopIdからあいまい検索
	@Override
	public List<Product> searchFromOne(String productName, Integer shopId){
		String sql = "select p.*, s.* from product p inner join shop s on p.shop_id=s.shop_id "
				+ " where p.product_name like '%' || :productName || '%'"
				+ " and p.shop_id=:id ";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("productName", productName);
		param.addValue("id", shopId);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	//productIdから情報取得
	@Override
	public Product productById(Integer productId) {
		String sql = "select * from product where product_id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", productId);

		List<Product> list = namedJT.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
		return list.isEmpty() ? null : list.get(0);
	}
}
