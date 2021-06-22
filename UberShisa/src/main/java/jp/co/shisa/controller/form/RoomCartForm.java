package jp.co.shisa.controller.form;

public class RoomCartForm {

	private String image;
	private String text;
	private String productName;
	private Integer price;
	private Integer amount;
	private Integer subtotal;

	//カートに追加する時に便利なので入れました
	private Integer productId;

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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
