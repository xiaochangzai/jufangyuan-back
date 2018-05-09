package com.jufangyuan.servicesImpl;

import com.jufangyuan.beans.SolveBean;
import com.jufangyuan.daos.SolveDao;
import com.jufangyuan.services.SolveServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("SolveServices")
public class SolveServiceImpl implements SolveServices {
    @Autowired SolveDao solveDao;
    @Override
    public HashMap<String, Object> getSolveById(int id) {
        HashMap<String,Object> result = new HashMap<String, Object>();

        SolveBean solveBean = solveDao.getSolveById(id);
        result.put("state",1);
        result.put("result",solveBean);
        return result;
    }

    @Override
    public HashMap<String, Object> addSolve(SolveBean solveBean) {
        int vrId = solveDao.addSolve(solveBean);
        System.out.println("-----------------  三八线  --------------------------");
        System.out.println("获得新插入的主键： " + solveBean.getVrId());
        HashMap<String,Object> result = new HashMap<String, Object>();
        if(vrId > 0){
//            result.put("state",1);
//            result.put("message","插入信息成功！");
            result.put("vrId",vrId);
        }else{
            result.put("state",-1);
            result.put("message","插入信息失败！");
        }
        return result;
    }

    @Override
    public HashMap<String, Object> alterSolve(SolveBean solveBean) {
        return null;
    }

    @Override
    public HashMap<String, Object> delSolveById(int id) {
        return null;
    }

    @Override
    public HashMap<String, Object> getSolves() {
        return null;
    }
}
