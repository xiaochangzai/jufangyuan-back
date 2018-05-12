package com.jufangyuan.services;

import com.jufangyuan.beans.QuegiverBean;

import java.util.HashMap;

public interface QuegiverServices {
    public HashMap<String,Object> addQuegiver(QuegiverBean bean);

    public HashMap<String,Object> getQuestionsByGiverId(String giverId);

    public HashMap<String, Object> getGiverInfo(QuegiverBean bean);

    public HashMap<String, Object> getGiverListByUser(String userId);
}
