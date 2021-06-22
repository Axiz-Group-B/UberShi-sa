package jp.co.shisa.dao;

import jp.co.shisa.controller.form.LoginForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;

public interface AuthDao {
	public UserInfo loginCheck(LoginForm form);
	public Room loginByRoom(UserInfo userInfo);
	public DeliveryMan loginByDeliveryMan(UserInfo userInfo);
	public Shop loginByShop(UserInfo userInfo);
}
