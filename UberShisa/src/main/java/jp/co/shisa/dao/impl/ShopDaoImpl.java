package jp.co.shisa.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
@Repository
public class ShopDaoImpl implements ShopDao{

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

}
