package com.jufangyuan.servicesImpl;

import com.jufangyuan.beans.QuegiverBean;
import com.jufangyuan.beans.QuestionsBean;
import com.jufangyuan.daos.QuegiverDao;
import com.jufangyuan.daos.QuestionDao;
import com.jufangyuan.services.QuegiverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("QuegiverServices")
public class QuegiverServiceImpl implements QuegiverServices {
    @Autowired
    QuegiverDao quegiverDao;
    @Autowired
    QuestionDao questionDao;
    @Override
    public HashMap<String, Object> addQuegiver(QuegiverBean bean) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        int vrId = quegiverDao.addQuegiver(bean);
        System.out.println("出题id : " + bean.getVrId());
        if(vrId > 0){
            result.put("state",1);
            result.put("message","插入信息成功！");
            result.put("result",bean.getVrId());
        }else {
            result.put("state",-1);
            result.put("message","插入信息失败！");
        }
        return result;
    }

    @Override
    public HashMap<String, Object> getQuestionsByGiverId(String giverId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        QuegiverBean giverBean = quegiverDao.getQuegiverById(giverId);
        ArrayList arrayList = new ArrayList();
        // 遍历
        String[] arr = giverBean.getAnswers().split(",");
        for (String str : arr){
            String[] tempArr = str.split(":");
            arrayList.add(tempArr[0]);
        }
        List<QuestionsBean> quesList = questionDao.getGiverQuestions(arrayList);

        result.put("state",1);
        result.put("questions",quesList);
        result.put("answers",giverBean.getAnswers());
        result.put("message","获取出题人题目成功！");

        return result;
    }

    @Override
    public HashMap<String, Object> getGiverInfo(QuegiverBean bean) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        QuegiverBean bean1 = quegiverDao.getGiverInfo(bean);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("nickName",bean1.getNickName());
        map.put("headImg",bean1.getHeadImg());
        map.put("myscore",bean1.getMyscore());

        result.put("flag",1);
        result.put("message","获取信息成功！");
        result.put("result",map);
        return result;
    }

    @Override
    public HashMap<String, Object> getGiverListByUser(String userId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<QuegiverBean> list = quegiverDao.getGiverListByUser(userId);
        List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
        for (QuegiverBean bean : list){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vrId",bean.getVrId());
            map.put("createTime",bean.getCreateTime());
            map.put("maxScore",bean.getMaxScore());
            map.put("dealNum",bean.getDealNum());
            tempList.add(map);
        }
        result.put("flag",1);
        result.put("message","获取出题记录成功！");
        result.put("result",tempList);
        return result;
    }
}
