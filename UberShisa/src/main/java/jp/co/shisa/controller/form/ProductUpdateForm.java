package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class ProductUpdateForm {
	private Integer productId;

	@NotBlank(message = "商品名は必須です")
	private String productName;

	@NotNull(message = "単価は必須です")
	@Positive(message = "正の数値を入力してください")
	private Integer price;

	@NotNull(message = "在庫数は必須です")
	@PositiveOrZero(message ="0以上の数値を入力してください")
	private Integer stock;

	@NotBlank(message = "商品の説明は必須です")
	private String text;


	private String image;

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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
