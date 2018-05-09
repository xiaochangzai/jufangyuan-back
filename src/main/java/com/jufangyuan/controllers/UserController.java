package com.jufangyuan.controllers;

import com.jufangyuan.beans.UserBean;
import com.jufangyuan.services.UserServices;
import com.jufangyuan.util.ParamsUtil;
import com.jufangyuan.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    UserServices userServices;

    @RequestMapping("/addUser.do")
    //     参数验证
    public HashMap<String, Object> addUser(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> result = new ParamsUtil().put("userId","String",1,32,0,0)
                                                         .put("nickName","String",1,32,0,0)
                                                         .put("headImg","String",1,256,0,0)
                                                         .validate(request);
        if (result != null) return result;
        // 创建bean
        UserBean bean = new UserBean();
        bean.setNickName(request.getParameter("nickName"));
        bean.setCreateTime(Util.getNowTime());
        bean.setHeadImg(request.getParameter("headImg"));
        bean.setUserId(request.getParameter("userId"));

        System.out.println("用户" + bean.getUserId() + "进入 /addUser.do 接口"  );
        // 调用service层
        result = userServices.addUser(bean);
        return result;
    }
}
