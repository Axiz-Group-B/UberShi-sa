package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class ProductAddForm {

	@NotBlank(message = "商品名は必須です")
	private String productName;

	private String image;

	@NotNull(message = "単価は必須です")
	@Positive(message = "正の数値だけを入力してください")
	private Integer price;

	@NotNull(message = "在庫数は必須です")
	@PositiveOrZero(message = "正の数値だけを入力してください")
	private Integer stock;

	@NotBlank(message = "商品の説明は必須です")
	private String text;

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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


}
