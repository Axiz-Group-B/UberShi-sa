package jp.co.shisa.controller.form;

import javax.validation.constraints.NotNull;

public class hotelAddStoreForm {
	//店舗IDをcontrollerに持ってくるために使用
	@NotNull
	private Integer hotelShopDelete;

	public Integer getHotelShopDelete() {
		return hotelShopDelete;
	}

	public void setHotelShopDelete(Integer hotelShopDelete) {
		this.hotelShopDelete = hotelShopDelete;
	}
}
