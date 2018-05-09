package com.jufangyuan.servicesImpl;

import com.jufangyuan.beans.QuedealBean;
import com.jufangyuan.daos.QuedealDao;
import com.jufangyuan.services.QuedealServices;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("QuedealServices")
public class QuedealServiceImpl implements QuedealServices {
    @Autowired
    QuedealDao quedealDao;
    @Override
    public HashMap<String, Object> addQuedeal(QuedealBean bean) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        int flag = quedealDao.addQuedeal(bean);
        if(flag > 0){
            result.put("flag",1);
            result.put("message","添加信息成功！");
        }else{
            result.put("flag",-1);
            result.put("message","添加信息失败！");
        }
        return result;
    }

    @Override
    public HashMap<String, Object> getQuedealByGivId(int vrId) {
        HashMap<String, Object> result = new HashMap<String, Object>();

        List<QuedealBean> list = quedealDao.getQuedealByGivId(vrId);
        List<HashMap<String,Object>> list1 = new ArrayList<HashMap<String, Object>>();
        for (QuedealBean bean : list){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("vrId",bean.getVrId());
            map.put("userId",bean.getUserId());
            map.put("nickName",bean.getNickName());
            map.put("headImg",bean.getHeadImg());
            map.put("dealTime",bean.getDealTime());
            map.put("score",bean.getScore());

            list1.add(map);
        }

        result.put("flag",1);
        result.put("message","获取信息成功！");
        result.put("result",list1);
        return result;
    }
}
