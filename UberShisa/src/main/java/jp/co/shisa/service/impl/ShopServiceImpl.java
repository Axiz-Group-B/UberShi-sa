package jp.co.shisa.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.shisa.dao.DeliveryManDao;
import jp.co.shisa.dao.ShopDao;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;
import jp.co.shisa.entity.Product;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.service.ShopService;

@Service
@Transactional
public class ShopServiceImpl implements ShopService{

	@Autowired
	ShopDao shopDao;
	@Override
	public void updateShopInfo(UserInfo userInfo, Shop shop) {
		// TODO 自動生成されたメソッド・スタブ
		shopDao.updateShopInfo(userInfo, shop);
	}


	@Autowired
	DeliveryManDao deliveryManDao;


	public OrderInfo checkOrder(Integer orderId) {
		return shopDao.checkOrder(orderId);
	}
	public List<OrderItem> checkOrderContents(Integer orderId) {
		return shopDao.checkOrderContents(orderId);
	}

	public List<Product> selectAllProductByShopId(Integer shopId) {
		return shopDao.selectAllProductByShopId(shopId);
	}

	public void insertProduct(Product product) {
		shopDao.insertProduct(product);
	}

	public Product selectUpdateProductByProductId(Integer productId) {
		return shopDao.selectUpdateProductByProductId(productId);
	}

	public Product updateProductAndGetProductByProductId(Product product) {
		shopDao.updateProduct(product);

		return shopDao.selectUpdateProductByProductId(product.getProductId());
	}

	public void deleteProducts(List<Integer> deleteProductList) {
		shopDao.deleteProducts(deleteProductList);
	}

	public List<Product> searchMyProductsByProductName(Integer shopId,String productName) {
		return shopDao.searchMyProductsByProductName(shopId,productName);
	}
}
