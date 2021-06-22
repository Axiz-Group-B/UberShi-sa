package jp.co.shisa.entity;

public class OrderItem {
	private Integer itemId;
	private Integer orderId;
	private Integer productId;
	private Integer amount;
	private Integer subtotal;

	//カートに入れるときに便利なので入れました
	private String productName;

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
	public OrderItem(Integer productId, Integer amount, Integer subtotal, String productName) {
		this.productId = productId;
		this.amount = amount;
		this.subtotal = subtotal;
		this.productName = productName;
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



}
