package com.jufangyuan.daos;

import com.jufangyuan.beans.QuestionsBean;
import org.apache.ibatis.annotations.Param;


import java.util.ArrayList;
import java.util.List;

public interface QuestionDao {
    public QuestionsBean getQuestionById(@Param("id") int id);

    public List<QuestionsBean> getQuestions(QuestionsBean questionsBean);

    public List<QuestionsBean> getQuestionsAll();

    public List<QuestionsBean> getGiverQuestions(@Param("array")ArrayList array);

    public int addQuestion(QuestionsBean questionsBean);

    public int alterQuestion(QuestionsBean questionsBean);

    public int delQuestionById(@Param("id") int id);
}
