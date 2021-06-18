package jp.co.shisa.entity;

public class DeliveryMan extends UserInfo{
	private Integer deliveryManId;
	private String name;
	private String tel;


	public DeliveryMan() {

	}

	public DeliveryMan(Integer userId,String loginId,String pass,Integer roleId,Integer deliveryManId,String name,String tel) {
		super(userId,loginId,pass,roleId);
		this.deliveryManId = deliveryManId;
		this.name = name;
		this.tel = tel;
	}



	public Integer getDeliveryManId() {
		return deliveryManId;
	}
	public void setDeliveryManId(Integer deliveryManId) {
		this.deliveryManId = deliveryManId;
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


}
