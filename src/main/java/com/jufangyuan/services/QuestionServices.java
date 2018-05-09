package com.jufangyuan.services;

import com.jufangyuan.beans.QuestionsBean;

import java.util.HashMap;
import java.util.List;

public interface QuestionServices {
    public HashMap<String,Object> getQuestionById(int id);

    public HashMap<String,Object> getQuestions(QuestionsBean questionsBean);

    public HashMap<String,Object> getQuestionsAll();

    public HashMap<String,Object> addQuestion(QuestionsBean questionsBean);

    public HashMap<String,Object> alterQuestion(QuestionsBean questionsBean);

    public HashMap<String,Object> delQuestionById( int id);
}
