package com.jufangyuan.util;

import com.jufangyuan.beans.ParameterBean;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Util {
    public static String getNowTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(d);
        return nowTime;
    }
    public static int ByteArrayToInt(byte[] bArr) {    
        if(bArr.length!=4){    
            return -1;    
        }    
        return (int) ((((bArr[3] & 0xff) << 24)      
                   | ((bArr[2] & 0xff) << 16)      
                   | ((bArr[1] & 0xff) << 8)  
| ((bArr[0] & 0xff) << 0)));     
}  

}
