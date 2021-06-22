package jp.co.shisa.entity;

public class Hotel extends UserInfo{

	public Hotel () {

	}

	public Hotel (Integer userId,String loginId,String pass,Integer roleId) {
		super(userId,loginId,pass,roleId);
	}
}
