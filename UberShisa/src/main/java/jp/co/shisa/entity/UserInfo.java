package jp.co.shisa.entity;

public class UserInfo {
	private Integer userId;
	private String loginId;
	private String pass;
	private Integer roleId;

	public UserInfo () {

	}


	public UserInfo(Integer userId, String loginId, String pass, Integer roleId) {
		this.userId = userId;
		this.loginId = loginId;
		this.pass = pass;
		this.roleId = roleId;
	}



	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
