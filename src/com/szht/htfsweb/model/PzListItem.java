package com.szht.htfsweb.model;

import java.io.Serializable;

/**
 * Created by WangJian on 2014/7/23.
 */
public class PzListItem implements Serializable {
       /*
    {
    "Pzlb312ShowVo":[
    {"zh":"记-00001","zy":"","jje":"","dje":"","dfhj":""}
    ]}




			{Pzlb312ShowVo: [
			{"ccf":"N","cn":"","cnTrueName":"","cnpz":"","count":1,
			"dch":"","dfdw":"","dffz":"","dfhj":100000,"dj":0,"djbh":"",
			"dje":"0.00","dsl":0,"dwb":0,"fhf":"Y","fhr":"40288187402d3aec0140333f7f340778",
			"fhrTrueName":"","fhrq":"20130819","fjzs":2,"flbh":0,"flid":"402881874094f676014095088c2e0699",
			"fpfs":0,"fz1kh":"","fz2gys":"","fz3bm":"","fz4ry":"","fz5wl":"","fz6fzh":"","fzdm":"40288187402d3aec0140333f7f34077a",
			"hl":0,"hsdwdm":"","jfhj":100000,"jje":"100000.00","jsbh":"","jsdh":"","jsl":0,"jwb":0,"jzf":"Y",
			"jzr":"40288187402d3aec0140333f7f340778","jzrTrueName":"","jzrq":"20130819","kjnd":"2013","kjqj":"07",
			"kjzg":"40288187402d3aec0140333f7f340778","kjzgTrueName":"","kmbh":"1002001","kminfo":"1002001: 银行存款-人民币",
			"kmqc":"","kzzbh":"","lxbh":"402881874094f67601409504a269013d","lybh":"1","pzbh":"00001","pzid":"402881874094f676014095088bff0697",
			"pzinfo":"<a style='display:none;'>20130701_00001<\/a>凭证日期: 20130701;\t\t<a href=javascript:util.getCurrentPage().gotoPZLR('402881874094f676014095088bff0697') style='text-decoration: underline;'><font color='red'>记-00001<\/font><\/a>;    制单人: ;    审核人: ;    记账人: ;    凭证状态: <font color='red'>已记账！<\/font>;\t合计: 100000.00元",
			"pzlymc":"","pzrq":"20130701","pzz":"","ssgz":"","status":"","unit":"","wbid":"","wzf":"Y","xxll":"402881874094f67601409504a1bd00ec","yhdz":"",
			"ywbh":"","ywrq":"","zdr":"40288187402d3aec0140333f7f340778","zdrTureName":"","zdyfz1":"","zdyfz2":"","zff":"N","zh":"记-00001","zrr":"",
			"ztdm":"402881874094f67601409504a0b40002","zy":"取得短期借款"},{"ccf":"N","cn":"","cnTrueName":"","cnpz":"","count":1,"dch":"","dfdw":"",
			"dffz":"","dfhj":100000,"dj":0,"djbh":"","dje":"100000.00","dsl":0,"dwb":0,"fhf":"Y","fhr":"40288187402d3aec0140333f7f340778","fhrTrueName":"",
			"fhrq":"20130819","fjzs":2,"flbh":1,"flid":"402881874094f676014095088c2e069a","fpfs":0,"fz1kh":"","fz2gys":"","fz3bm":"","fz4ry":"","fz5wl":"",
			"fz6fzh":"","fzdm":"40288187402d3aec0140333f7f34077a","hl":0,"hsdwdm":"","jfhj":100000,"jje":"0.00","jsbh":"","jsdh":"","jsl":0,"jwb":0,"jzf":"Y",
			"jzr":"40288187402d3aec0140333f7f340778","jzrTrueName":"","jzrq":"20130819","kjnd":"2013","kjqj":"07","kjzg":"40288187402d3aec0140333f7f340778",
			"kjzgTrueName":"","kmbh":"2001","kminfo":"2001: 短期借款","kmqc":"","kzzbh":"","lxbh":"402881874094f67601409504a269013d","lybh":"1","pzbh":"00001",
			"pzid":"402881874094f676014095088bff0697","pzinfo":"<a style='display:none;'>20130701_00001<\/a>凭证日期: 20130701;\t\t<a href=javascript:util.getCurrentPage().gotoPZLR('402881874094f676014095088bff0697') style='text-decoration: underline;'><font color='red'>记-00001<\/font><\/a>;    制单人: ;    审核人: ;    记账人: ;    凭证状态: <font color='red'>已记账！<\/font>;\t合计: 100000.00元","pzlymc":"","pzrq":"20130701","pzz":"","ssgz":"","status":"","unit":"","wbid":"","wzf":"Y","xxll":"","yhdz":"","ywbh":"","ywrq":"","zdr":"40288187402d3aec0140333f7f340778","zdrTureName":"","zdyfz1":"","zdyfz2":"","zff":"N","zh":"记-00001","zrr":"","ztdm":"402881874094f67601409504a0b40002","zy":"取得短期借款"}], totalNum:48}
     if("Y".equalsIgnoreCase(zwpzb.getJzf()))
			{
				zhuangtai = "<font color='red'>已记账！</font>";
			}
			if("N".equalsIgnoreCase(zwpzb.getJzf()) && "Y".equalsIgnoreCase(zwpzb.getFhf()))
			{
				zhuangtai = "<font color='red'>已审核</font>";
			}
			if("N".equalsIgnoreCase(zwpzb.getJzf()) && "N".equalsIgnoreCase(zwpzb.getFhf()) && "Y".equalsIgnoreCase(zwpzb.getCcf()))
			{
				zhuangtai = "已标错";
			}
			if("N".equalsIgnoreCase(zwpzb.getJzf()) && "N".equalsIgnoreCase(zwpzb.getFhf()) && "N".equalsIgnoreCase(zwpzb.getCcf()))
			{
				zhuangtai = "未审核";
			}
     */
    public String zh;
    public String zy;
    public String jje;
    public String dje;
    public String dfhj;
    public String kminfo;
    public String pzrq;
    public String jzf;
    public String fhf;
    public String ccf;
    public String status;
    public String pzinfo;



}
