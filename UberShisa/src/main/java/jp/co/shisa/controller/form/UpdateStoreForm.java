package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;

public class UpdateStoreForm {

	@NotBlank(message = "ログインIDは必須です")
	private String loginId;
	@NotBlank(message = "パスワードは必須です")
	private String pass;
	@NotBlank(message = "店舗名は必須です")
	private String shopName;
	@NotBlank(message = "店舗の電話番号は必須です")
	private String shopTel;
	@NotBlank(message = "店舗の住所は必須です")
	private String address;

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


}
