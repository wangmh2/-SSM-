package com.demo.Utils;

import java.sql.Timestamp;
import java.util.Date;

public class Timetools {
    
    public String time(){
        Date date = new Date();
        
        Timestamp ts = new Timestamp(date.getTime());
        String a = ts.toString();
       
        String []b = a.split(" ");
        
        String []c = b[1].split(":");
        
        int time = Integer.parseInt(c[0]);
        String d = null;
        if(0 <= time && time <=5 ){
            d = "凌晨好";
        }else if (6 <= time && time <=8){
            d = "早上好";
        }else if (9 <= time && time <=11){
            d = "上午好";
        }else if(12 <= time && time <= 14){
            d = "中午好";
        }else if(15 <= time && time <=17){
            d = "下午好";
        }else if(18 <= time && time <=22){
            d = "晚上好";
        }else{
            d = "深夜好";
        }
        return d;
    }
    
}
