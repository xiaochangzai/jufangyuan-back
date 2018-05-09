package com.jufangyuan.services;

import com.jufangyuan.beans.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface UserServices {
    public HashMap<String,Object> getUserById(String id);

    public HashMap<String,Object> addUser(UserBean userBean);

    public HashMap<String,Object> delUserById(String id);
}
