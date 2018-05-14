package com.jufangyuan.jufangyuan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jufangyuan.beans.User;
import com.jufangyuan.beans.WxTockenBean;
import com.jufangyuan.util.HttpRequest;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void httpTest(){
        String appid = "wx43b4a325dfcf1409";
        String secret = "fed2da2df958cecefef92dc311c70133";
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String params ="grant_type=client_credential&appid="+ appid +"&secret=" + secret;

        String result = HttpRequest.sendGet(url,params);
        System.out.println("---->");
        System.out.println(result);

    }
}
