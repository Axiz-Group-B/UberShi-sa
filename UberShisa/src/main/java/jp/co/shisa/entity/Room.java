package jp.co.shisa.entity;

public class Room extends UserInfo{

	private Integer roomId;
	private String roomName;


	public Room() {

	}


	public Room(Integer userId,String loginId,String pass,Integer roleId,Integer roomId,String roomName) {
		super(userId,loginId,pass,roleId);
		this.roomId = roomId;
		this.roomName = roomName;
	}




	Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public void setUserId(Integer userId) {

	}
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}




}
