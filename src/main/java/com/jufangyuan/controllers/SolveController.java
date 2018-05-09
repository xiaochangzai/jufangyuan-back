package com.jufangyuan.controllers;

import com.jufangyuan.beans.SolveBean;
import com.jufangyuan.services.SolveServices;
import com.jufangyuan.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class SolveController {
    @Autowired
    SolveServices solveServices;
    @RequestMapping("/getSolveId.do")
    public HashMap<String,Object> getSolveId(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> result = null;

        // 获取参数
        String userId = request.getParameter("userId");
        String startTime = Util.getNowTime();

        System.out.println("用户 " + userId + " 进入 /getSolveId.do 接口" );
        SolveBean solveBean = new SolveBean();
        solveBean.setUserId(userId);
        solveBean.setStartTime(startTime);
        // 调用service层
        result = solveServices.addSolve(solveBean);
        return result;
    }

    @RequestMapping("/getSolveById.do")
    public HashMap<String,Object> getSolveById(){
        HashMap<String,Object> result = solveServices.getSolveById(1);

        return result;
    }
}
