package com.jufangyuan.daos;

import com.jufangyuan.beans.QuegiverBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuegiverDao {
    public int addQuegiver(QuegiverBean bean);

    public  QuegiverBean getQuegiverById(@Param("vrId") String vrId);

    public QuegiverBean getGiverInfo(QuegiverBean bean);

    public List<QuegiverBean> getGiverListByUser(@Param("userId") String userId);

    public int updateQuedeal(QuegiverBean bean);
}
