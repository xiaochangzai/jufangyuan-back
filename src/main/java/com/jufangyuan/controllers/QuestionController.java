package com.jufangyuan.controllers;

import com.jufangyuan.beans.QuestionsBean;
import com.jufangyuan.services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class QuestionController {
    @Autowired
    QuestionServices questionServices;
    @RequestMapping("/getQuestion.do")
    public HashMap<String, Object> getQuestion(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> result = null;
        QuestionsBean questionsBean = new QuestionsBean();

        System.out.println("用户 " + request.getParameter("userId") + " 进入 /getQuestion.do 接口");

        String notStr = request.getParameter("notStr");
        if (notStr != null && !"".equals(notStr)){
            // 如果有排除的题目，则处理下排除的列表
            String[] tempArr  = notStr.split(",");
            int[] notArr  = new int[tempArr.length];
            for (int i = 0; i < tempArr.length; i++){
                notArr[i] = Integer.parseInt(tempArr[i]);
            }
            questionsBean.setNotArr(notArr);
        }

        result = questionServices.getQuestions(questionsBean);
        return result;
    }

    @RequestMapping("/getQuestionsAll.do")
    public HashMap<String,Object> getQuestionsAll(){
        HashMap<String,Object> result = null;
        System.out.println("用户进入 /getQuestionsAll.do 接口");
        result = questionServices.getQuestionsAll();

        return result;
    }
}
