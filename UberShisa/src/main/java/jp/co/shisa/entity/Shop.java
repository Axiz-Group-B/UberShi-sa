package jp.co.shisa.entity;

public class Shop {
	private Integer shopId;
	private Integer userId;
	private String name;
	private String tel;
	private String address;


	public Shop() {

	}



	public Shop(Integer shopId, Integer userId, String name, String tel, String address) {
		super();
		this.shopId = shopId;
		this.userId = userId;
		this.name = name;
		this.tel = tel;
		this.address = address;
	}

	//検索のために作りました
	public Shop(Integer shopId, String name) {
		this.shopId = shopId;
		this.name = name;
	}




	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


}
