package jp.co.axiz.web.controller.form;

import javax.validation.constraints.NotBlank;

public class RePassUpdateForm {
	@NotBlank
	private String rePass;

	public String getRePass() {
		return rePass;
	}

	public void setRePass(String rePass) {
		this.rePass = rePass;
	}
}
