package com.jufangyuan.controllers;

import com.jufangyuan.beans.QuegiverBean;
import com.jufangyuan.services.QuegiverServices;
import com.jufangyuan.util.ParamsUtil;
import com.jufangyuan.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class QuegiverController {
    @Autowired
    QuegiverServices quegiverServices;

    @RequestMapping("/addQuegiver.do")
    public HashMap<String,Object> addQuegiver(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> result = null;
        result = new ParamsUtil()
                    .put("answers","String",1,256,0,0)
                    .put("giverId","String",1,32,0,0)
                    .validate(request);
        if(result != null){
            return result;
        }
        QuegiverBean quegiverBean = new QuegiverBean();
        quegiverBean.setGiverId(request.getParameter("giverId").toString());
        quegiverBean.setCreateTime(Util.getNowTime());
        quegiverBean.setAnswers(request.getParameter("answers").toString());

        result = quegiverServices.addQuegiver(quegiverBean);
        return result;
    }

    @RequestMapping("/getQuestionsByGiverId.do")
    public HashMap<String, Object> getQuestionsByGiverId(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = null;
        System.out.println("用户进入 /getQuestionsByGiverId.do 接口");
        result = new ParamsUtil()
                    .put("giverId","String",1,32,0,0)
                    .validate(request);
        if (result != null) return result;

        result = quegiverServices.getQuestionsByGiverId(request.getParameter("giverId"));

        return result;
    }

    @RequestMapping("/getGiverInfo.do")
    public HashMap<String, Object> getGiverInfo(HttpServletRequest request, HttpServletResponse response){
        System.out.println("用户进入: /getGiverInfo.do 接口" );
        HashMap<String,Object> result = new ParamsUtil()
                    .put("giveId","Integer",1,32,0,100000000)
                    .put("userId","String",1,32,0,0)
                    .validate(request);
        if (result != null) return  result;
        // 调用services层
        QuegiverBean bean = new QuegiverBean();
        bean.setVrId(Integer.parseInt(request.getParameter("giveId")));
        bean.setUserId(request.getParameter("userId"));
        result = quegiverServices.getGiverInfo(bean);
        return result;
    }
}
