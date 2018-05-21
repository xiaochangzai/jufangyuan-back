package com.jufangyuan.services;

import java.util.HashMap;

import com.jufangyuan.beans.OrdersBean;

public interface OrdersServices {
	public HashMap<String,Object> addOrder(OrdersBean bean);
}
