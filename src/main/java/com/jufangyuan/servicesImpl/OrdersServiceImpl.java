package com.jufangyuan.servicesImpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jufangyuan.beans.OrdersBean;
import com.jufangyuan.daos.OrdersDao;
import com.jufangyuan.services.OrdersServices;
@Service("OrdersServices")
public class OrdersServiceImpl implements OrdersServices{
@Autowired
private OrdersDao ordersDao;
	@Override
	public HashMap<String, Object> addOrder(OrdersBean bean) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		int flag = ordersDao.addOrder(bean);
		if(flag > 0) {
			result.put("state", 1);
			result.put("message", "添加订单成功！");
		}else {
			result.put("state", 0);
			result.put("message","添加订单失败！");
		}
		return result;
	}

}
