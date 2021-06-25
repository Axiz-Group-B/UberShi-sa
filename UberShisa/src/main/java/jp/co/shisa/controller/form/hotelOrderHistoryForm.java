package jp.co.shisa.controller.form;

public class hotelOrderHistoryForm {
	private Integer orderListId;
	private Integer orderShopId;
	private String Month;
	private String Year;

	public Integer getOrderListId() {
		return orderListId;
	}

	public void setOrderListId(Integer orderListId) {
		this.orderListId = orderListId;
	}

	public Integer getOrderShopId() {
		return orderShopId;
	}

	public void setOrderShopId(Integer orderShopId) {
		this.orderShopId = orderShopId;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

}
