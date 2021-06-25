package jp.co.shisa.controller.form;

public class RoomOrderForm {


	private String productName;
	private Integer shopId;

	//部屋検索に仕様
	private String roomName;


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}





}
