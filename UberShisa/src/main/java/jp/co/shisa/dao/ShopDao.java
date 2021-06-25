package jp.co.shisa.dao;

import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface ShopDao {

	public void updateShopInfo(UserInfo userInfo, Shop shop);
	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);
	public List<Product> selectAllProductByShopId(Integer shopId);
	public void insertProduct(Product product);
	public Product selectUpdateProductByProductId(Integer productId);
	public void passedOrder(OrderInfo orderInfo);
}
