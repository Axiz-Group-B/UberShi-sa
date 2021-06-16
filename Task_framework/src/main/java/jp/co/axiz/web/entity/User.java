package jp.co.axiz.web.entity;

public class User {
	private Integer userId;
	private String loginId;
	private String userName;
	private String telephone;
	private String password;
	private Integer roleId;

	@Override
	public String toString() {
		return loginId + userName + telephone + password + roleId;
//		return String.format(loginId, userName, telephone, password, roleId);
	}

	public User() {

	}

	public User(Integer id, String logId, String name, String tel, String pass, Integer role) {
		userId = id;
		loginId = logId;
		userName = name;
		telephone = tel;
		password = pass;
		roleId = role;
	}

	public User(String logId, String name, String tel, String pass, Integer role) {
		loginId = logId;
		userName = name;
		telephone = tel;
		password = pass;
		roleId = role;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
