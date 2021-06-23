package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;

public class UpdateStoreForm {

	@NotBlank
	private String loginId;
	@NotBlank
	private String pass;
	@NotBlank
	private String shopName;
	@NotBlank
	private String shopTel;
	@NotBlank
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
