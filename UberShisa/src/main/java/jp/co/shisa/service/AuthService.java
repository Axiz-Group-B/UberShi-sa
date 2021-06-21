package jp.co.shisa.service;

import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.Room;
import jp.co.shisa.entity.Shop;
import jp.co.shisa.entity.UserInfo;
import jp.co.shisa.form.LoginForm;

public interface AuthService {
	public UserInfo loginCheck(LoginForm form);
	public Room loginByRoom(UserInfo userInfo);
	public DeliveryMan loginByDeliveryMan(UserInfo userInfo);
	public Shop loginByShop(UserInfo userInfo);

}
