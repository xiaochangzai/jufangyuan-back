package com.jufangyuan.jufangyuan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jufangyuan.beans.User;
import com.jufangyuan.beans.WxTockenBean;
import com.jufangyuan.util.HttpRequest;
import net.minidev.json.JSONObject;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JufangyuanApplicationTests {
    @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.secret}")
    private String secret;
    @Test
    public void contextLoads() {
        System.out.println("--------------------");
        System.out.println(appId);
        System.out.println(secret);
    }
    @Test
    public void jsonTest(){
        /**
         * {"access_token":"9_ijAympXKu0OPYcRGkp6ao0h17yy0LY-OxtxUC5sMc_FnJV3wqH9fv3F2HM157_PYVv2kg2hiD1OwhJAvtYIRFWci1kvRN888Vub0mvbNZC4E8quy0XOYQo1eFYasegNq3cZgOycH7Q9gXr9wSBYhACAGPM","expires_in":7200}
         */
        String jsonString ="{\"name\":\"zhangsan\",\"password\":\"zhangsan123\",\"email\":\"10371443@qq.com\"}";
        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        ObjectMapper mapper = new ObjectMapper();
        try {
            User bean = mapper.readValue(jsonString, User.class);
            System.out.println("---------------------");
            System.out.println(bean.getEmail());
            System.out.println(bean.getName());
            System.out.println(bean.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getCode() {
    	String tockenStr = "9_i28fndwqpfeozapxbg-rf-vjkpl6hirkyd7mdm6_ejxqyf4yit4mfwlgbgnjdpkbod8rqu0ros3tbcpcbbgi4zcdff-lydqg9tnyi-kqoc_fxpx9x4d331ymfbccawbaeakie";
    	String Codeurl = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=" + tockenStr;
        
        
        
        System.out.println(Codeurl);
        String codeParms = "page=/pages/index/index&width=200&scene=1&is_hyaline=false&auto_color=false&line_color={“r”:”0”,”g”:”0”,”b”:”0”}";
        String codeRes = HttpRequest.sendPost(Codeurl, codeParms);
        System.out.println("获取二维码成功！");
        System.out.println(codeRes);
    }
    @Test
    public void httpTest() throws JSONException{
    	
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String params ="grant_type=client_credential&appid="+ appId +"&secret=" + secret;
        String result = HttpRequest.sendGet(url,params);
        
        
        System.out.println("---->");
        String tocken = result.toLowerCase();
        org.json.JSONObject json = new org.json.JSONObject(tocken);
        String tockenStr = json.getString("access_token");
        System.out.println("----------------------");
        System.out.println(tockenStr);
        
        
    }
    @Test
    public void fileTest() {
    	File file = new File("D:\\test_file.txt");
    	boolean exit = file.exists();
    	System.out.println("存在或者不存在： " + exit);
    			
    }
}
