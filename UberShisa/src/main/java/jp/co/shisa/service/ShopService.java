package jp.co.shisa.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
@Transactional
public interface ShopService {

	public void updateShopInfo(UserInfo userInfo, Shop shop);
	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);
	public List<Product> selectAllProductByShopId(Integer shopId);
	public void insertProduct(Product product);
	public Product selectUpdateProductByProductId(Integer productId);
	public Product updateProductAndGetProductByProductId(Product product);
	public void deleteProducts(List<Integer> deleteProductList);
	public List<Product> searchMyProductsByProductName(Integer shopId,String productName);

}
