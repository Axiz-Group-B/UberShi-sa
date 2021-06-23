package jp.co.shisa.entity;

public class Hotel extends UserInfo{

	public Hotel () {

	}

	public Hotel (Integer userId,String loginId,String pass,Integer roleId) {
		super(userId,loginId,pass,roleId);
	}


	//店舗管理画面　店舗一覧表示
	public class shop{
		public String name;
		public String address;
		public String tel;

		//ゲッターname
		public String getName() {
			return name;
		}

		//セッターname
		public void setName(String name) {
			this.name = name;
		}


		//ゲッターaddress
		public String getAddress() {
			return address;
		}

		//セッターaddress
		public void setAddress(String address) {
			this.address = address;
		}


		//ゲッターtel
		public String getTel() {
			return tel;
		}

		//セッターtel
		public void setTel(String tel) {
			this.tel = tel;
		}
	}
}