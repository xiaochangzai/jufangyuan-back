package com.jufangyuan.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jufangyuan.beans.TockenBean;
import com.jufangyuan.beans.WxTockenBean;
import com.jufangyuan.services.TockenServices;
import com.jufangyuan.util.HttpRequest;
import com.jufangyuan.util.ParamsUtil;

@RestController
public class SystemController {
    @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.secret}")
    private String secret;
    @Autowired
    private TockenServices tockenServices;
    @RequestMapping("/getWxTocken.do")
    public String getTocken(HttpServletRequest request, HttpServletResponse response){
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String params ="grant_type=client_credential&appid="+ appId +"&secret=" + secret;
        String result = HttpRequest.sendGet(url,params);
        ObjectMapper mapper = new ObjectMapper();

        try {
            WxTockenBean bean = mapper.readValue(result,WxTockenBean.class);
            HttpSession session = request.getSession();
            session.setAttribute("tocken",bean.getAccess_tocken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @RequestMapping("/getTocken.do")
    public TockenBean getTocken(){
    	TockenBean bean = tockenServices.getTocken();
    	
    	return bean;
    }
    
    @RequestMapping("/getUserOpenId.do")
    public HashMap<String, Object> getOpenId(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = new ParamsUtil().put("code","String",1,32,0,0).validate(request);
        if (result != null) return result;
        String code = request.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String params = "appid="+ appId + "&secret="+ secret +"&js_code="+ code +"&grant_type=authorization_code";
        String resultStr = HttpRequest.sendPost(url,params);
        result = new HashMap<String, Object>();
        result.put("flag",1);
        result.put("result",resultStr);
        return result;
    }
   
   /*
    * 获取二维码
　　* 这里的 post 方法 为 json post【重点】
    */
   @RequestMapping("/getWxCode.do")
   public HashMap<String,Object> getCodeM(HttpServletRequest request) throws Exception {
	   HashMap<String,Object> rs = new ParamsUtil().put("id", "String", 1, 100, 0, 0).validate(request);
	   if(rs != null ) {
		   return rs;
	   }
	   rs = new HashMap<String, Object>();
	   String id = request.getParameter("id");
       String token =tockenServices.getTocken().getAccess_token();
       String imei ="867186032552993";
       String name = imei+"code_"+ request.getParameter("id") +".png";
       String path = "C:\\img\\";
       File file = new File(path + name);
       if(file.exists()) {
    	   rs.put("path", name);
    	   rs.put("flag", 1);
    	   rs.put("message", "获取二维码成功！");
    	   return rs;
       }
       
       file = null;
       Map<String, Object> params = new HashMap<>();
       params.put("scene", id);  //参数
       params.put("page", "pages/friendAnswer/friendAnswer"); //位置
       params.put("width", 430);

       CloseableHttpClient  httpClient = HttpClientBuilder.create().build();

       HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token);  // 接口
       httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
       String body = JSON.toJSONString(params);           //必须是json模式的 post      
       StringEntity entity;
       entity = new StringEntity(body);
       entity.setContentType("image/png");

       httpPost.setEntity(entity);
       HttpResponse response;

       response = httpClient.execute(httpPost);
       InputStream inputStream = response.getEntity().getContent();
       
       int flag = saveToImgByInputStream(inputStream,"C:\\img\\",name);  //保存图片
       rs = new HashMap<String,Object>();
       rs.put("path", name);
       rs.put("state", flag);
       return rs;
   }
   
   

   /**
   * 将二进制转换成文件保存
   * @param instreams 二进制流
   * @param imgPath 图片的保存路径
   * @param imgName 图片的名称
   * @return 
   *      1：保存正常
   *      0：保存失败
   */
  public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
      int stateInt = 1;
      System.out.println("开始读取图片");
      if(instreams != null){
          try {
              File file=new File(imgPath + imgName);//可以是任何图片格式.jpg,.png等
              FileOutputStream fos=new FileOutputStream(file);
              byte[] b = new byte[1024];
              int nRead = 0;
              while ((nRead = instreams.read(b)) != -1) {
                  fos.write(b, 0, nRead);
              }
              fos.flush();
              fos.close();                
          } catch (Exception e) {
              stateInt = 0;
              e.printStackTrace();
              System.out.println("读取图片错误！");
          } finally {
        	  System.out.println("读取图片完毕！");
          }
      }
     
      return stateInt;
  }
    
    }
