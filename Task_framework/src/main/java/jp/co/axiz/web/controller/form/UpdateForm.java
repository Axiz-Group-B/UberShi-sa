package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;

public class UpdateForm {
	@NotBlank
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
