package com.jufangyuan.services;

import com.jufangyuan.beans.QuedealBean;

import java.util.HashMap;

public interface QuedealServices {
    public HashMap<String, Object> addQuedeal(QuedealBean bean);

    public HashMap<String, Object> getQuedealByGivId(int vrId);
}
