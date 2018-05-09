package com.jufangyuan.servicesImpl;

import com.jufangyuan.beans.QuestionsBean;
import com.jufangyuan.daos.QuestionDao;
import com.jufangyuan.services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("QuestionServices")
public class QuestionServiceImpl implements QuestionServices {
    @Autowired
    QuestionDao questionDao;

    @Override
    public HashMap<String, Object> getQuestionById(int id) {
        return null;
    }

    @Override
    public HashMap<String, Object> getQuestions(QuestionsBean questionsBean) {
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<QuestionsBean> list = questionDao.getQuestions(questionsBean);

        result.put("state",1);
        result.put("result",list);
        result.put("message","获得题目成功！");
        return result;
    }

    @Override
    public HashMap<String, Object> getQuestionsAll() {
        HashMap<String,Object> result = new HashMap<String, Object>();
        List<QuestionsBean> list = questionDao.getQuestionsAll();

        result.put("state",1);
        result.put("message","成功获取题目列表");
        result.put("result",list);

        return result;
    }

    @Override
    public HashMap<String, Object> addQuestion(QuestionsBean questionsBean) {
        return null;
    }

    @Override
    public HashMap<String, Object> alterQuestion(QuestionsBean questionsBean) {
        return null;
    }

    @Override
    public HashMap<String, Object> delQuestionById(int id) {
        return null;
    }
}
