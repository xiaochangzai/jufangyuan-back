package com.jufangyuan.daos;

import com.jufangyuan.beans.UserBean;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public UserBean getUserById(@Param("id")  String id);

    public int addUser(UserBean userBean);

    public int delUserById(@Param("id") String id);
}
