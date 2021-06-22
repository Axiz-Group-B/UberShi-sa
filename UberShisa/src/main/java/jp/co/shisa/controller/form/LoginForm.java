package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	@NotBlank
	private String loginId;

	@NotBlank
	private String pass;

	private Integer roleId;


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


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
