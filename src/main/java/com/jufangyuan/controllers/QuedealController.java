package com.jufangyuan.controllers;

import com.jufangyuan.beans.QuedealBean;
import com.jufangyuan.services.QuedealServices;
import com.jufangyuan.util.ParamsUtil;
import com.jufangyuan.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class QuedealController {
    @Autowired
    QuedealServices quedealServices;
    @RequestMapping("/addQuedealer.do")
    public HashMap<String, Object> addQuedealer(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = new ParamsUtil()
                .put("giveId","String",1,32,0,0)
                .put("userId", "String",1,32,0,0)
                .put("answers","String",1,256,0,0)
                .put("score","String",1,4,0,100)
                .validate(request);
        if (result != null){
            return result;
        }
        QuedealBean bean = new QuedealBean();
        bean.setAnswers(request.getParameter("answers"));
                bean.setDealTime(Util.getNowTime());
                bean.setGiveId(Integer.parseInt(request.getParameter("giveId")));
                bean.setScore(Float.parseFloat(request.getParameter("score")));
                bean.setUserId(request.getParameter("userId"));

                result = quedealServices.addQuedeal(bean);
                return result;
    }

    @RequestMapping("/getQuedealByGivId.do")
    public HashMap<String, Object> getQuedealByGivId(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = new ParamsUtil().put("giveId","String",1,32,0,0).validate(request);
        if (result != null) return  result;
        // 调用service层
        result = quedealServices.getQuedealByGivId(Integer.parseInt(request.getParameter("giveId")));
        return result;
    }


    @RequestMapping("/getDealByUser.do")
    public HashMap<String, Object> getDealByUser(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = new ParamsUtil().put("userId","String",1,32,0,0).validate(request);
        if (result != null) return result;
        result = quedealServices.getDealByUser(request.getParameter("userId"));
        return result;
    }


    @RequestMapping("/getDealById.do")
    public HashMap<String, Object> getDealById(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = new ParamsUtil().put("id","String",1,32,0,0).validate(request);
        if (result != null) return result;
        // 调用service层
        result = quedealServices.getDealById(request.getParameter("id"));
        return result;
    }
    
    @RequestMapping("/updateDeal.do")
    public HashMap<String, Object> updateDeal(HttpServletRequest request, HttpServletResponse response){
    	HashMap<String, Object> result ;
    	ParamsUtil paramsUtil = new ParamsUtil();
    	paramsUtil.put("vrId", "String", 1, 100, 0, 1000000000);
    	String answers = request.getParameter("answers");
    	String score = request.getParameter("score");
    	String isBuy = request.getParameter("isBuy");
    	if(answers != null && !"".equals(answers)) 
    		paramsUtil.put("answers", "String", 1, 520, 0, 0);
    	if(score != null && !"".equals(score))
    		paramsUtil.put("score", "int", 1, 3, 0, 100);
    	if(isBuy != null && !"".equals(isBuy))
    		paramsUtil.put("isBuy", "int", 1, 1, 0, 1);
    	
    	result = paramsUtil.validate(request);
    	if(result != null) {
    		return result;
    	}
    	
    	// 调用service层
    	QuedealBean bean = new QuedealBean();
    	bean.setVrId(Integer.parseInt(request.getParameter("vrId")));
    	if(answers != null && !"".equals(answers))
    		bean.setAnswers(request.getParameter("answers"));
    	if(score != null && !"".equals(score))
    		bean.setScore(Integer.parseInt(request.getParameter("score")));
    	if(isBuy != null && !"".equals(isBuy))
    		bean.setIsBuy(Integer.parseInt(request.getParameter("isBuy")));
    	
    	result = quedealServices.updateDeal(bean);
    	
    	return result;
    }

}
