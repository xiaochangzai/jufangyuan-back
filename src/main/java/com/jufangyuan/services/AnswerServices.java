package com.jufangyuan.services;

import com.jufangyuan.beans.AnswerBean;

import java.util.HashMap;

public interface AnswerServices {
    public HashMap<String,Object> getAnswerById( int id);

    public HashMap<String,Object> addAnswer(AnswerBean answerBean);

    public HashMap<String,Object> alterAnswer(AnswerBean answerBean);

    public HashMap<String,Object> getAnswers();
}
