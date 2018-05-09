package com.jufangyuan.servicesImpl;

import com.jufangyuan.beans.UserBean;
import com.jufangyuan.daos.UserDao;
import com.jufangyuan.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("UserServices")
public class UserServiceImpl implements UserServices {
    @Autowired
    UserDao userDao;
    @Override
    public HashMap<String, Object> getUserById(String id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        UserBean bean = userDao.getUserById(id);

        if(bean == null){
            result.put("flag",-1);
            result.put("message","该用户不存在！");
        }else{
            result.put("flag",1);
            result.put("message","成功获取该用户信息!");
            result.put("result",bean);
        }
        return result;
    }

    @Override
    public HashMap<String, Object> addUser(UserBean userBean) {
        HashMap<String,Object> map = getUserById(userBean.getUserId());
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (Integer.parseInt(map.get("flag").toString()) != 1){
            // 没有此人信息
            int flag = userDao.addUser(userBean);
            result.put("flag",1);
            result.put("message","添加该用户信息成功！");

        }else{
            result.put("flag",2);
            result.put("message","该用户已存在！");
        }
        return result;
    }

    @Override
    public HashMap<String, Object> delUserById(String id) {
        return null;
    }
}
