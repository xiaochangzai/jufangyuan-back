package com.jufangyuan.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jufangyuan.beans.OrdersBean;
import com.jufangyuan.services.OrdersServices;
import com.jufangyuan.util.ParamsUtil;
import com.jufangyuan.util.Util;

@RestController
public class OrdersController {
	@Autowired
	private OrdersServices ordersServices;
	@RequestMapping("/addOrder.do")
	public HashMap<String, Object> addOrder(HttpServletRequest request,HttpServletResponse response){
		HashMap<String, Object> result = new ParamsUtil()
				.put("userId", "String", 1, 32, 0, 0)
				.put("orderId", "String", 1, 32, 0, 0)
				.put("title","String",1,200,0,0)
				.put("price", "int", 1, 1000, 0, 100000000)
				.put("primkey", "int", 1, 32, 0, 10000000)
				.validate(request);
		if(result != null) {
			return result;
		}
		OrdersBean bean = new OrdersBean();
		bean.setCreateTime(Util.getNowTime());
		bean.setDetail(request.getParameter("detail"));
		bean.setOrderId(request.getParameter("orderId"));
		bean.setPrimkey(Integer.parseInt(request.getParameter("primkey")));
		bean.setPrice(Integer.parseInt(request.getParameter("price")));
		bean.setUserId(request.getParameter("userId"));
		
		result = ordersServices.addOrder(bean);
		
		return result;
	}

}
