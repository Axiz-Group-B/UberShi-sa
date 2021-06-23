package jp.co.shisa.controller.form;

import javax.validation.constraints.NotNull;

public class hotelDeliveryForm {
	//配達員IDをcontrollerに持ってくるために使用
	@NotNull
	private Integer deliveryListDelete;

	public Integer getDeliveryListDelete() {
		return deliveryListDelete;
	}

	public void setDeliveryListDelete(Integer deliveryListDelete) {
		this.deliveryListDelete = deliveryListDelete;
	}

}
