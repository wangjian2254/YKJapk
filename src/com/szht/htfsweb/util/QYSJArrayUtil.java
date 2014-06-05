package com.szht.htfsweb.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by WangJian on 14-6-5.
 */
public class QYSJArrayUtil {

    public static String[] getMonthStrArr(String date){
        Date now =new Date();
        String[] m=new String[12];
        int index=0;
        if(Integer.valueOf(date.substring(0,4))<now.getYear()+1900){
            for(int i=Integer.valueOf(date.substring(4,6));i<=12;i++){
                if(i<10){
                    m[index]="0"+i;
                }else{
                    m[index]=""+i;
                }
               index++;
            }
        }else if(Integer.valueOf(date.substring(0,4))==now.getYear()+1900){
            for(int i=Integer.valueOf(date.substring(4,6));i<=now.getMonth();i++){
                if(i<10){
                    m[index]="0"+i;
                }else{
                    m[index]=""+i;
                }
                index++;
            }
        }
        String[] m2=new String[index];
        for(int j=0;j<index;j++){
            m2[j]=m[j];
        }
        return m2;
    }
}
