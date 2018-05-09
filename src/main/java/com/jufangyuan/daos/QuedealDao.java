package com.jufangyuan.daos;

import com.jufangyuan.beans.QuedealBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuedealDao {
    public int addQuedeal(QuedealBean bean);

    public List<QuedealBean> getQuedealByGivId(@Param("vrId") int vrId);
}
