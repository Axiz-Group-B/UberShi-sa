package jp.co.shisa.entity;

public class Product {
	private Integer productId;
	private Integer shopId;
	private String image;
	private String text;
	private String productName;
	private Integer price;
	private Integer stock;

	//商品検索時に店名必要なので入れました
	private String shopName;

	public Product() {

	}

	public Product(Integer productId, Integer shopId, String image, String text, String productName, Integer price,
			Integer stock) {
		super();
		this.productId = productId;
		this.shopId = shopId;
		this.image = image;
		this.text = text;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}


	//
	public Product(Integer shopId, String image,String text,String productName, Integer price,Integer stock) {
		super();
		this.shopId = shopId;
		this.image = image;
		this.text = text;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}


	public Product(Integer productId,String productName,Integer price,Integer stock,String text,String image) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
		this.text = text;
		this.image = image;
	}


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}



}
