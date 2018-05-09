package com.jufangyuan.services;

import com.jufangyuan.beans.SolveBean;

import java.util.HashMap;

public interface SolveServices {
    public HashMap<String,Object> getSolveById(int id);

    public HashMap<String,Object> addSolve(SolveBean solveBean);

    public HashMap<String,Object> alterSolve(SolveBean solveBean);

    public HashMap<String,Object> delSolveById(int id);


    public HashMap<String,Object> getSolves();
}
