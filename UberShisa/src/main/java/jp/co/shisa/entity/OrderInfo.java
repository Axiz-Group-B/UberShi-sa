package jp.co.shisa.entity;

import java.sql.Timestamp;

public class OrderInfo {
	private Integer orderId;
	private Integer roomId;
	private Integer shopid;
	private Integer deliveryManId;
	private Integer totalPrice;
	private Integer status;
	private Timestamp dateTime;
	private String shopName;
	private String shopTel;
	private String deliveryManName;
	private String deliveryManTel;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getDeliveryManId() {
		return deliveryManId;
	}

	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
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

	public String getDeliveryManName() {
		return deliveryManName;
	}

	public void setDeliveryManName(String deliveryManName) {
		this.deliveryManName = deliveryManName;
	}

	public String getDeliveryManTel() {
		return deliveryManTel;
	}

	public void setDeliveryManTel(String deliveryManTel) {
		this.deliveryManTel = deliveryManTel;
	}



}
