package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;

public class SignupForm {
	@NotBlank
	private String loginId;
	@NotBlank
	private String deliveryManName;
	@NotBlank
	private String deliveryManTel;
	@NotBlank
	private String pass;
	@NotBlank
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
