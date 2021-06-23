package jp.co.shisa.entity;

public class Shop {
	private Integer shopId;
	private Integer userId;
	private String shopName;
	private String tel;
	private String address;


	public Shop() {

	}



	public Shop(Integer shopId, Integer userId, String shopName, String tel, String address) {
		super();
		this.shopId = shopId;
		this.userId = userId;
		this.shopName = shopName;
		this.tel = tel;
		this.address = address;
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
