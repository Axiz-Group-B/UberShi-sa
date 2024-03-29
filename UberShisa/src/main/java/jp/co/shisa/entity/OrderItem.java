package jp.co.shisa.entity;

public class OrderItem {
	private Integer itemId;
	private Integer orderId;
	private Integer productId;
	private Integer amount;
	private Integer subtotal;

	//カートに入れるときに便利なので入れました
	private String productName;
	private Integer ShopId;
	private Integer price;
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	private Integer totalPrice;

	private String text;

	public OrderItem() {

	}

	public OrderItem(Integer itemId, Integer orderId, Integer productId, Integer amount, Integer subtotal) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
		this.subtotal = subtotal;
	}

	//カートに入れるためにつくりました
	public OrderItem(Integer productId, Integer amount, Integer subtotal, String productName, Integer shopId) {
		this.productId = productId;
		this.amount = amount;
		this.subtotal = subtotal;
		this.productName = productName;
		this.ShopId = shopId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getShopId() {
		return ShopId;
	}

	public void setShopId(Integer shopId) {
		ShopId = shopId;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



}
