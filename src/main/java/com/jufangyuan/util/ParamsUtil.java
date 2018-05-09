package com.jufangyuan.util;

import com.jufangyuan.beans.ParameterBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author 孙畅
 */
public class ParamsUtil {
    private ArrayList<ParameterBean> parms = new ArrayList<ParameterBean>();

    /**
     *
     * @param name 名字
     * @param type 类型
     * @param maxLen 最大差NGDU
     * @param minLen 最小长度
     * @param maxValue 最大值
     * @param minValue 最小值
     * @return
     */
    public synchronized ParamsUtil put(String name, String type,int minLen, int maxLen, float minValue,  float maxValue){
        ParameterBean bean = new ParameterBean();
        bean.setName(name);
        bean.setType(type);
        bean.setMaxLen(maxLen);
        bean.setMinLen(minLen);
        bean.setMaxValue(maxValue);
        bean.setMinValue(minValue);
        this.parms.add(bean);
        return this;
    }
    public HashMap<String,Object> validate(HttpServletRequest request){
        HashMap<String,Object> result = new HashMap<String, Object>();
        for(ParameterBean bean : this.parms){
            // 首先判断这个参数存不存在
            Object param = request.getParameter(bean.getName());
            if (param == null){
                result.put("flag",3);
                result.put("message","缺少参数： " + bean.getName());
                return result;
            }else {
                // 判断是不是字符串
                if (bean.getType().equals("String") || bean.getType().equals("string")){
                    try{
                        String parmStr = param.toString();
                        // 判断最大长度和最小长度
                        if(parmStr.length() < bean.getMinLen()){
                            result.put("flag",5);
                            result.put("message","参数：" + bean.getName() + "太短，最小长度为：" + bean.getMinLen());
                            return result;
                        }
                        if (parmStr.length() > bean.getMaxLen()){
                            result.put("flag",6);
                            result.put("message","参数" + bean.getName() + "太长，最大长度为：" + bean.getMaxLen());
                            return result;
                        }
                    }catch (Exception e){
                        result.put("flag",4);
                        result.put("message","参数： " + bean.getName() + " 类型错误，应是 " + bean.getType() + "类型");
                        return result;
                    }
                }else if(bean.getType().equals("Integer") || bean.getType().equals("int")){
                    try {
                        int paramValue = Integer.parseInt(param.toString());
                        // 判断最大值和最小值
                        if (paramValue < bean.getMinValue()){
                            result.put("flag",7);
                            result.put("message","参数：" + bean.getName() + "太小，最小值为：" + bean.getMinValue());
                            return result;
                        }
                        if (paramValue > bean.getMaxValue()){
                            result.put("flag",8);
                            result.put("message","参数：" + bean.getName() + "太大，最大值为：" + bean.getMaxValue());
                            return result;
                        }
                    }catch (Exception e){
                        result.put("flag",4);
                        result.put(":message","参数 " + bean.getName() + "类型错误，应是 " + bean.getType() +"类型");
                        return result;
                    }
                    // 浮点类型判断
                }else if (bean.getType().equals("Float") || bean.getType().equals("float")){
                    try {
                        float paramValue = Float.parseFloat(param.toString());
                        // 判断最大值和最小值
                        if (paramValue < bean.getMinValue()){
                            result.put("flag",7);
                            result.put("message","参数：" + bean.getName() + "太小，最小值为：" + bean.getMinValue());
                            return result;
                        }
                        if (paramValue > bean.getMaxValue()){
                            result.put("flag",8);
                            result.put("message","参数：" + bean.getName() + "太大，最大值为：" + bean.getMaxValue());
                            return result;
                        }
                    }catch (Exception e){
                        result.put("flag",4);
                        result.put("message","参数类型错误，应是 " + bean.getType() + "类型");
                        return result;
                    }
                }
            }
        }
        return null;
    }


}
