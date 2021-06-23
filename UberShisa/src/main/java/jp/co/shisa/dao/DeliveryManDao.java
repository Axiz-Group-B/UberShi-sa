package jp.co.shisa.dao;

import jp.co.shisa.controller.form.SignupForm;

public interface DeliveryManDao {
//テーブルuser_info、delivery_manそれぞれにinsertするメソッド名、戻り値、引数だけ書いとく
	//引数あるやつとないやつの違い？
	public void insertDeliveryMan(SignupForm deliveryMan, Integer userId);

	public void insertUserInfo(SignupForm userInfo);

	public Integer UserId(SignupForm signupForm);

}
