package com.jufangyuan.servicesImpl;

import com.jufangyuan.beans.QuedealBean;
import com.jufangyuan.beans.QuegiverBean;
import com.jufangyuan.beans.QuestionsBean;
import com.jufangyuan.daos.QuedealDao;
import com.jufangyuan.daos.QuegiverDao;
import com.jufangyuan.daos.QuestionDao;
import com.jufangyuan.services.QuedealServices;
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
    @Autowired
    QuegiverDao quegiverDao;
    @Autowired
    QuestionDao questionDao;
    @Override
    public HashMap<String, Object> addQuedeal(QuedealBean bean) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        int flag = quedealDao.addQuedeal(bean);

        // 修改出题记录的做题人数和最高分
        // 先获得当前分数
        QuegiverBean givBean = quegiverDao.getQuegiverById(""+bean.getGiveId());
        if(bean.getScore() > givBean.getMaxScore()){
            givBean.setMaxScore((int)bean.getScore());
        }else {
            givBean.setMaxScore(-1);
        }
        givBean.setVrId(bean.getGiveId());
        givBean.setDealNum(givBean.getDealNum() + 1);

        quegiverDao.updateQuedeal(givBean);

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

    @Override
    public HashMap<String, Object> getDealByUser(String userId) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<QuedealBean> list = quedealDao.getDealByUser(userId);
        List<Map<String,Object>> tempList = new ArrayList<Map<String, Object>>();
        for (QuedealBean bean : list){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("vrId",bean.getVrId());
            map.put("givId",bean.getGiveId());
            map.put("dealTime",bean.getDealTime());
            map.put("giverId",bean.getGiveId());
            map.put("nickName",bean.getNickName());
            map.put("headImg",bean.getHeadImg());
            map.put("score",bean.getScore());
            tempList.add(map);
        }

        result.put("state",1);
        result.put("message","获取信息成功！");
        result.put("result",tempList);
        return result;
    }

    @Override
    public HashMap<String, Object> getDealById(String id) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        QuedealBean bean = quedealDao.getDealById(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("answers",bean.getAnswers());
        map.put("score",bean.getScore());
        map.put("dealTime",bean.getDealTime());
        map.put("givAnswers",bean.getGivAnswers());
        map.put("isBuy", bean.getIsBuy());
        String answers = bean.getGivAnswers();
        String[] tempArr = answers.split(",");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String tempStr : tempArr){
            // 添加题目索引
            list.add(Integer.parseInt(tempStr.split(":")[0]));
        }

        List<QuestionsBean> questionsBeans = questionDao.getGiverQuestions(list);

        result.put("questions", questionsBeans);
        result.put("state",1);
        result.put("message","获得答题信息成功！");
        result.put("result",map);
        return result;
    }

	@Override
	public HashMap<String, Object> updateDeal(QuedealBean bean) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result = new HashMap<String,Object>();
		int flag = quedealDao.updateDeal(bean);
		result.put("state", flag);
		if(flag > 0) {
			result.put("message","修改信息成功！");
			
		}else {
			result.put("message", "修改信息失败！");
		}
		return result;
	}

}
