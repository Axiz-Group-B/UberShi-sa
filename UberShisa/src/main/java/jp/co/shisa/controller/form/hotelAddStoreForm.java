package jp.co.shisa.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class hotelAddStoreForm {
	@NotBlank
	private String shopLoginId;
	@NotBlank
	private String shopPass;
	@NotBlank
	private String shopName;
	@NotBlank
	private String shopAddress;
	@NotBlank
	private String shopTels;


	//shopLoginIdゲッター
	public String getShopLoginId() {
		return shopLoginId;
	}
	//shopLoginIdセッター
	public void setShopLoginId(String shopLoginId) {
		this.shopLoginId = shopLoginId;
	}

	//shopPassゲッター
	public String getShopPass() {
		return shopPass;
	}
	//shopPassセッター
	public void setShopPass(String shopPass) {
		this.shopPass = shopPass;
	}

	//shopNameゲッター
	public String getShopName() {
		return shopName;
	}
	//shopNameセッター
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	//shopAddressゲッター
	public String getShopAddress() {
		return shopAddress;
	}
	//shopAddressセッター
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	//shopTelsゲッター
	public String getShopTels() {
		return shopTels;
	}
	//shopTelsセッター
	public void setShopTels(String shopTels) {
		this.shopTels = shopTels;
	}





	//店舗IDをcontrollerに持ってくるために使用
	@NotNull
	private Integer hotelShopDelete;

	public Integer getHotelShopDelete() {
		return hotelShopDelete;
	}

	public void setHotelShopDelete(Integer hotelShopDelete) {
		this.hotelShopDelete = hotelShopDelete;
	}
}
