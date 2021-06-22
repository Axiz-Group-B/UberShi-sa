package jp.co.shisa.entity;

public class DeliveryMan extends UserInfo{
	private Integer deliveryManId;
	private String deliveryManName;
	private String deliveryManTel;


	public DeliveryMan() {

	}

	public DeliveryMan(Integer userId,String loginId,String pass,Integer roleId,Integer deliveryManId,String deliveryManName,String deliveryManTel) {
		super(userId,loginId,pass,roleId);
		this.deliveryManId = deliveryManId;
		this.deliveryManName = deliveryManName;
		this.deliveryManTel = deliveryManTel;
	}



	public Integer getDeliveryManId() {
		return deliveryManId;
	}
	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
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
