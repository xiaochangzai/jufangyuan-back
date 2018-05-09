package com.jufangyuan.daos;

import com.jufangyuan.beans.AnswerBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerDao {
    public AnswerBean getAnswerById(@Param("id") int id);

    public int addAnswer(AnswerBean answerBean);

    public int alterAnswer(AnswerBean answerBean);

    public List<AnswerBean> getAnswers();

}
