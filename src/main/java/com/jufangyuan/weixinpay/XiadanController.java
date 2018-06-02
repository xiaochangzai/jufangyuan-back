package com.jufangyuan.weixinpay;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jufangyuan.util.ParamsUtil;
import com.jufangyuan.util.Util;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
@RestController
public class XiadanController {
	@RequestMapping("/xiadan.do")
	public String xiadan(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> res = new ParamsUtil()
				.put("openId", "String", 1, 32, 0, 0)
				.put("title", "String", 1, 100, 0, 0)
				.put("price", "int", 1, 100, 0, 10000000)
				.validate(request);
		
		if(res != null) {
			return res.toString();
		}
		
		try {
//			String realIp = Util.getIpAddr(request);                                                                                                                                                                                             
			String outTradNo = RandomStringGenerator.getRandomStringByLength(32);
			String openid = request.getParameter("openId");
			int price = Integer.parseInt(request.getParameter("price"));
			String title = request.getParameter("title");
			OrderInfo order = new OrderInfo();
			order.setAppid(Configure.getAppID());
			order.setMch_id(Configure.getMch_id());
			order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
			order.setBody("sdsd");
			order.setOut_trade_no(outTradNo);
			order.setTotal_fee(price);
			order.setSpbill_create_ip("39.107.122.205");
			order.setNotify_url("https://qaq.jfy108.net/weixinpay/PayResult");
			order.setTrade_type("JSAPI");
			order.setOpenid(openid);
			order.setSign_type("MD5");
			//生成签名
			String sign = Signature.getSign(order);
			order.setSign(sign);
			
			
			String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
			System.out.println(result);
			XStream xStream = new XStream();
			xStream.alias("xml", OrderReturnInfo.class); 

			OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
			System.out.println("    return info     ");
			System.out.println(returnInfo.toString());
			System.out.println(returnInfo.getPrepay_id());
			JSONObject json = new JSONObject();
			json.put("prepay_id", returnInfo.getPrepay_id());
			json.put("outTradeNo", outTradNo);
			json.put("msg", returnInfo.getReturn_msg());
			json.put("code", returnInfo.getReturn_code());
			json.put("result_code", returnInfo.getResult_code());
			json.put("price", price);
			return json.toJSONString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "签名错误";
		}
	}
	
	@RequestMapping("/xiadan2.do")
	public String xiadan2(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> res = new ParamsUtil()
				.put("openid", "String", 1, 32, 0, 0)
				.put("price", "int", 1, 100, 0, 10000000)
				.validate(request);
		
		if(res != null) {
			return res.toString();
		}
		
		try {
			int price = Integer.parseInt(request.getParameter("price"));
			String realIp = Util.getIpAddr(request);
			String openid = request.getParameter("openid");
			String outTradNm = RandomStringGenerator.getRandomStringByLength(32);
			OrderInfo order = new OrderInfo();
			order.setAppid(Configure.getAppID());
			order.setMch_id(Configure.getMch_id());
			order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
			order.setBody("see answer ");
			order.setOut_trade_no(outTradNm);
			order.setTotal_fee(price);
			order.setSpbill_create_ip(realIp);
			order.setNotify_url("https://qaq.jfy108.net/weixinpay/PayResult");
			order.setTrade_type("JSAPI");
			order.setOpenid(openid);
			order.setSign_type("MD5");
			//生成签名
			String sign = Signature.getSign(order);
			order.setSign(sign);
			
			
			String result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
			System.out.println(result);
			XStream xStream = new XStream();
			xStream.alias("xml", OrderReturnInfo.class); 

			OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);
			JSONObject json = new JSONObject();
			json.put("prepay_id", returnInfo.getPrepay_id());
			json.put("out_trade_no", outTradNm);
			return json.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@RequestMapping("/sign.do")
	public String sign(HttpServletRequest request, HttpServletResponse response) {
		try {
			String repay_id = request.getParameter("repay_id");
			SignInfo signInfo = new SignInfo();
			signInfo.setAppId(Configure.getAppID());
			long time = System.currentTimeMillis()/1000;
			signInfo.setTimeStamp(String.valueOf(time));
			signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
			signInfo.setRepay_id("prepay_id="+repay_id);
			signInfo.setSignType("MD5");
			//生成签名
			String sign = Signature.getSign(signInfo);
			
			JSONObject json = new JSONObject();
			json.put("timeStamp", signInfo.getTimeStamp());
			json.put("nonceStr", signInfo.getNonceStr());
			json.put("package", signInfo.getRepay_id());
			json.put("signType", signInfo.getSignType());
			json.put("paySign", sign);
			
			return json.toJSONString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "再次签名失败！";
		}
	}

}
