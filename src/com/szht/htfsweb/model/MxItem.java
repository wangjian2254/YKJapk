package com.szht.htfsweb.model;

import java.io.Serializable;

/**
 * Created by WangJian on 2014/7/23.
 */
public class MxItem implements Serializable {
       /*
    {"bbrq":"2014-07-23","kmbh":"1004","kmmc":"备用金","pageSize":"",
    "result":[
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013-08","type":"ZZMXZ_QCYE","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"期初余额"},
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013-08","type":"ZZMXZ_BNLJ","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"本月合计"},
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013","type":"ZZMXZ_BNLJ","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"本年累计"}]}
     */
    public String pzbh;
    public String dj;
    public String hl;
    public String yedj;
    public String yehl;
    public String dfje;
    public String dfsl;
    public String dfwb;
//    public String fx;
    public String jfje;
    public String jfsl;
    public String jfwb;
//    public String kjnd;
//    public String kjqj;
//    public String kmbh;
    public String pzrq;
    public String type;
    public String yefx;
    public String yefxBz;
    public String yeje;
    public String yesl;
    public String yewb;
    public String zy;
}
