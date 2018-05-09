package com.jufangyuan.daos;

import com.jufangyuan.beans.QuegiverBean;
import org.apache.ibatis.annotations.Param;

public interface QuegiverDao {
    public int addQuegiver(QuegiverBean bean);

    public  QuegiverBean getQuegiverById(@Param("vrId") String vrId);

    public QuegiverBean getGiverInfo(QuegiverBean bean);
}
