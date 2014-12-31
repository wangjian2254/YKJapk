package com.szht.htfsweb.util;

import com.szht.htfsweb.db.User;

/**
 * Created by WangJian on 14-3-6.
 */
public class Convert {
    public static String LOGIN_SET="username";
    public static String JSessionid="jsessionid";//cookie 中session键
//    public static String hosturl="http://192.168.101.18:8088/htfsweb/";
    public static String hosturl="http://www.htykj.com.cn/";
    public static String login = "j_spring_security_check";
    public static String allZts = "_002_QiYeAction.do?method=findComplyIOSByGrouping";
    public static String selectZt = "_005_SysLoginAcion.do?method=loginQyinfoZtlink";
    public static String baseConfig = "_000_GetBaseConfigAction.do?method=getBaseConfig&_dc=1355295979374";

    public static String currentYear=null;//当前年
    public static String currentMonth=null;//当前月
    public static String currentDay=null;//当前日

    public static User currentUser;


    /*
    http://www.htykj.com.cn/htfsweb/j_spring_security_check
http://www.htykj.com.cn/htfsweb/_002_QiYeAction.do?method=findComplyIOSByGrouping
http://www.htykj.com.cn/htfsweb/_005_SysLoginAcion.do?method=loginQyinfoZtlink
http://www.htykj.com.cn/htfsweb/_000_GetBaseConfigAction.do?method=getBaseConfig&_dc=1355295979374
     */
}
