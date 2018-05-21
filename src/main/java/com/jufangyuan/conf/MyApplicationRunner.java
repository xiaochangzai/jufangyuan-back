package com.jufangyuan.conf;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jufangyuan.beans.TockenBean;
import com.jufangyuan.services.TockenServices;
import com.jufangyuan.util.HttpRequest;
import com.jufangyuan.util.Util;

@Component
public class MyApplicationRunner implements ApplicationRunner {

	 @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.secret}")
    private String secret;
    
    @Autowired
    TockenServices tockenServices;
    @Override
    public void run(ApplicationArguments var1) throws Exception{
        System.out.println("MyApplicationRunner class will be execute when the project was started!");
        getTocken();
    }
    @Scheduled(cron="0/7000 * * * * ? ")
    public String getTocken(){
    	System.out.println("启动，即将获取tocken");
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String params ="grant_type=client_credential&appid="+ appId +"&secret=" + secret;
        String result = HttpRequest.sendGet(url,params);
        ObjectMapper mapper = new ObjectMapper();

        try {
            TockenBean bean = mapper.readValue(result,TockenBean.class);
            bean.setCreateTime(Util.getNowTime());
            
            int flag = tockenServices.setTocken(bean);
            
            System.out.println("----------------------启动---------------- ");
            System.out.println("存入tocken: " + bean.getAccess_token());
            
            // 存入tocken
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void test() {
    	System.out.println("-----------------------    任务测试！！！");
    }
   

}