package com.szht.htfsweb.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangJian on 2014/7/23.
 */
public class ZzBB implements Serializable {
     /*
    {"bbrq":"2014-07-23","kmbh":"1004","kmmc":"备用金","pageSize":"",
    "result":[
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013-08","type":"ZZMXZ_QCYE","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"期初余额"},
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013-08","type":"ZZMXZ_BNLJ","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"本月合计"},
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013","type":"ZZMXZ_BNLJ","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"本年累计"}]}
     */

    public String bbrq;
    public String kmbh;
    public String kmmc;

    public List<ZzItem> list =new ArrayList<ZzItem>();
}
