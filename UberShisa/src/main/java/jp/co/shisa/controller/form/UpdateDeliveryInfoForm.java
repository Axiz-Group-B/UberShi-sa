package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;

public class UpdateDeliveryInfoForm {

	@NotBlank(message = "ログインIDは必須です")
	private String loginId;
	@NotBlank(message = "名前は必須です")
	private String deliveryManName;
	@NotBlank(message = "電話番号は必須です")
	private String deliveryManTel;
	@NotBlank(message = "パスワードは必須です")
	private String pass;
	@NotBlank(message = "確認パスワードは必須です")
	private String rePass;
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getDeliveryManName() {
		return deliveryManName;
	}
	public void setDeliveryManName(String deliveryManName) {
		this.deliveryManName = deliveryManName;
	}
	public String getDeliveryManTel() {
		return deliveryManTel;
	}
	public void setDeliveryManTel(String deliveryManTel) {
		this.deliveryManTel = deliveryManTel;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRePass() {
		return rePass;
	}
	public void setRePass(String rePass) {
		this.rePass = rePass;
	}


}
