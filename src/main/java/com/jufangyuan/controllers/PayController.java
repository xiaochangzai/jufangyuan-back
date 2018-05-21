package com.jufangyuan.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jufangyuan.beans.PaymentPo;
import com.jufangyuan.util.MessageUtil;
import com.jufangyuan.util.PayCommonUtil;
import com.jufangyuan.util.PayUtil;
import com.jufangyuan.util.UUIDHexGenerator;


@RestController
public class PayController {

	 private String total_fee = "10";//总金额   
	    private String body = "商品描述";//商品描述   
	    private String detail = "商品详情";//商品详情      
	    private String attach = "{title:\"附加数据\",id:1}";//附加数据   
	    private String time_start;//交易起始时间   
	    private String time_expire;//交易结束时间   
	    private String openid;//用户标识   
	    @Value("${weixin.appId}")
	    private String appId;
	    @Value("${weixin.secret}")
	    private String secret;
	    @Value("${weixin.serverIp}")
	    private String ip;
	    @Value("${weixin.mch_id}")
	    private String mchId;
	    @Value("${weixin.paySecret}")
	    private String paySecret;
	@RequestMapping("/pay.do")
	public String pay(HttpServletRequest request, HttpServletResponse response) throws DocumentException, UnsupportedEncodingException, JSONException {
//		HashMap<String, Object> result = null;
//		result = new ParamsUtil()
//				.put("openid", "String", 1, 32, 0, 0)
//				.put(name, type, minLen, maxLen, minValue, maxValue)
		body = new String(body.getBytes("UTF-8"),"ISO-8859-1");    
        String appid = appId;//小程序ID   
        String mch_id = mchId;//商户号   
        String nonce_str = UUIDHexGenerator.generate();//随机字符串   
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());   
        String code = PayUtil.createCode(8);   
        String out_trade_no = mch_id+today+code;//商户订单号   
        String spbill_create_ip = ip;//终端IP   
        String notify_url = "http://www.weixin.qq.com/wxpay/pay.php";//通知地址   
        String trade_type = "JSAPI";//交易类型    
        String openid="ot14n4yt0DOLBd_qrYmVbtvNpplU";//用户标识   
        /**/   
        PaymentPo paymentPo = new PaymentPo();   
        paymentPo.setAppid(appid);   
        paymentPo.setMch_id(mch_id);   
        paymentPo.setNonce_str(nonce_str);   
        String newbody=new String(body.getBytes("ISO-8859-1"),"UTF-8");//以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码   
        paymentPo.setBody(newbody);   
        paymentPo.setOut_trade_no(out_trade_no);   
        paymentPo.setTotal_fee(total_fee);   
        paymentPo.setSpbill_create_ip(spbill_create_ip);   
        paymentPo.setNotify_url(notify_url);   
        paymentPo.setTrade_type(trade_type);   
        paymentPo.setOpenid(openid);   
        
        
        //--------------------
        
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", appId);
        packageParams.put("mch_id",mch_id);
        packageParams.put("nonce_str", today);//时间戳
        packageParams.put("body", newbody);//支付主体
        packageParams.put("out_trade_no", out_trade_no);//编号
        packageParams.put("total_fee", total_fee);//价格
        // packageParams.put("spbill_create_ip", getIp2(request));这里之前加了ip，但是总是获取sign失败，原因不明，之后就注释掉了
        packageParams.put("notify_url", notify_url);//支付返回地址，不用纠结这个东西，我就是随便写了一个接口，内容什么都没有
        packageParams.put("trade_type", "JSAPI");//这个api有，固定的
        packageParams.put("openid", openid);//openid
        //获取sign
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, paySecret);//最后这个是自己设置的32位密钥
        
        
        packageParams.put("sign", sign);
        //转成XML
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        System.out.println("转成xml ---------");
        System.out.println(requestXML);
        
        //----------------------
        
        // 把请求参数打包成数组   
        Map<String, String> sParaTemp = new HashMap();
        sParaTemp.put("appid", paymentPo.getAppid());
        sParaTemp.put("mch_id", paymentPo.getMch_id());
        sParaTemp.put("nonce_str", paymentPo.getNonce_str());   
        sParaTemp.put("body",  paymentPo.getBody());   
        sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());   
        sParaTemp.put("total_fee",paymentPo.getTotal_fee());
        sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
        sParaTemp.put("notify_url",paymentPo.getNotify_url());
        sParaTemp.put("trade_type", paymentPo.getTrade_type());
        sParaTemp.put("openid", paymentPo.getOpenid());
        // 除去数组中的空值和签名参数   
        Map sPara = PayUtil.paraFilter(sParaTemp);   
        String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串   
        String key = "&key=" + paySecret; // 商户支付密钥   
        //MD5运算生成签名   
        String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();   
        paymentPo.setSign(mysign);   
        //打包要发送的xml
        String respXml = MessageUtil.messageToXML(paymentPo);   
        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”   
        respXml = respXml.replace("__", "_");   
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接   
        String param = respXml;
        //String result = SendRequestForUrl.sendRequest(url, param);//发起请求  
        System.out.println("==================== param =====================");
        System.out.println(param);
        System.out.println("==========================================");
        String result =PayUtil.httpRequest(url, "POST", requestXML);
        System.out.println("--------- 返回信息 ---------->");
        System.out.println(result);
        System.out.println("-----------------------");
        // 将解析结果存储在HashMap中   
        Map map = new HashMap();
         InputStream in=new ByteArrayInputStream(result.getBytes());
        // 读取输入流   
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        // 得到xml根元素   
        Element root = document.getRootElement();   
        // 得到根元素的所有子节点   
        @SuppressWarnings("unchecked")   
        List elementList = root.elements();   
        for (Object element : elementList) {   
            map.put(((Node) element).getName(), ((Node) element).getText());   
        }   
        // 返回信息   
        String return_code = (String) map.get("return_code");//返回状态码   
        String return_msg = (String) map.get("return_msg");//返回信息   
        System.out.println("return_msg"+return_msg);   
        JSONObject JsonObject=new JSONObject() ; 
        
        if(return_code=="SUCCESS"||return_code.equals(return_code)){   
            // 业务结果   
            String prepay_id = (String) map.get("prepay_id");//返回的预付单信息   
            String nonceStr=UUIDHexGenerator.generate();   
            JsonObject.put("nonceStr", nonceStr);   
            JsonObject.put("package", "prepay_id="+prepay_id);   
            Long timeStamp= System.currentTimeMillis()/1000;   
            JsonObject.put("timeStamp", timeStamp+"");   
            String stringSignTemp = "appId="+appid+"&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;   
            //再次签名   
            String paySign=PayUtil.sign(stringSignTemp, "&key=" + secret, "utf-8").toUpperCase();   
            JsonObject.put("paySign", paySign);   
        }   
		return JsonObject.toString();
	}
}
