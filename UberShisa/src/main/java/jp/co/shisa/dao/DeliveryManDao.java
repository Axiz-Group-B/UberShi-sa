package jp.co.shisa.dao;


import java.util.List;

import jp.co.shisa.controller.form.SignupForm;
import jp.co.shisa.entity.DeliveryMan;
import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.OrderItem;

public interface DeliveryManDao {
//テーブルuser_info、delivery_manそれぞれにinsertするメソッド名、戻り値、引数だけ書いとく

	public void insertDeliveryMan(SignupForm deliveryMan, Integer userId);

	public void insertUserInfo(SignupForm userInfo);

	public Integer UserId(SignupForm signupForm);





	public OrderInfo checkOrder(Integer orderId);
	public List<OrderItem> checkOrderContents(Integer orderId);
//	配達員情報を更新(pha)----------------------------------------------------------------------
	public void updateDeliveryManInfo(DeliveryMan deliveryMan);
//	配達員情報を更新(pha)----------------------------------------------------------------------
}
