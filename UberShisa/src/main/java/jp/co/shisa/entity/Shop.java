package jp.co.shisa.entity;

public class Shop {
	private Integer shopId;
	private Integer userId;
	private String shopName;
	private String shopTel;
	private String address;

	public Shop() {

	}

	public Shop(Integer shopId, Integer userId, String name, String tel, String address) {
		super();
		this.shopId = shopId;
		this.userId = userId;
		this.shopName = name;
		this.shopTel = tel;
		this.address = address;
	}

	//検索のために作りました
	public Shop(Integer shopId, String name) {
		this.shopId = shopId;
		this.shopName = name;
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
