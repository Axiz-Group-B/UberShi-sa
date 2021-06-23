package jp.co.shisa.entity;

public class Room extends UserInfo{

	private Integer roomId;
	private String roomName;

	//注文が入っているか入っていないかを判定するのに仕様
	private boolean orderFlag;


	public Room() {

	}


	public Room(Integer userId,String loginId,String pass,Integer roleId,Integer roomId,String roomName) {
		super(userId,loginId,pass,roleId);
		this.roomId = roomId;
		this.roomName = roomName;
	}


	public Integer getRoomId() {
		return roomId;
	}


	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public boolean isOrderFlag() {
		return orderFlag;
	}


	public void setOrderFlag(boolean orderFlag) {
		this.orderFlag = orderFlag;
	}









}
