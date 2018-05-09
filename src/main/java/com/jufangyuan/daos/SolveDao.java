package com.jufangyuan.daos;

import com.jufangyuan.beans.SolveBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SolveDao {
    public SolveBean getSolveById(@Param("id") int id);

    public int addSolve(SolveBean solveBean);

    public int alterSolve(SolveBean solveBean);

    public int delSolveById(@Param("id") int id);


    public List<SolveBean> getSolves();
}
