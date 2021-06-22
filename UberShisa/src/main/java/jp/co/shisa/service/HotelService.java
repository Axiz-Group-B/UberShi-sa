package jp.co.shisa.service;


import java.util.List;

import jp.co.shisa.entity.OrderInfo;
import jp.co.shisa.entity.Shop;

public interface HotelService {
	public List<Shop> shopFindAll();

	public List<OrderInfo> orderInfoFindAll();

}
