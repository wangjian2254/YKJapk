package com.szht.htfsweb.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by WangJian on 2014/7/14.
 * {"children":[],"cls":"","father":"","fatherid":"","fz1kh":"N","fz2gys":"N","fz3bm":"N","fz4ry":"N","fz5wl":"N","id":"4028818740a4b51a0140b80e90830003",
 * "jc":"1","jp":"kcxj","kmbh":"1001","kmlb":"1","kmmc":"库存现金","kmqc":"库存现金","kmsx":"1","leaf":true,"qp":"kucunxianjin","sfmx":"Y","sfnbwl":"N","sfqy":"Y",
 * "sfslhs":"N","sfss":"N","sfwbhs":"N","sfxjll":"Y","space":"","text":"1001:库存现金","uiProvider":"col","unit":"","wbid":"","wbmc":"","yefx":"1","yefxZnCh":"借",
 * "yhdm":"40288187402d3aec0140333f7f340778","zdyfz1":"N","zdyfz2":"N","zjm":"KCXJ","ztdm":"4028818740a4b51a0140b80e8fb80002"}

 */

@Table(name = "Kjkm",id="Kmid")
public class Kjkm extends Model {
    @Column(name = "Ztdm")
    public String ztdm;

    @Column(name = "Id",index = true,unique=true)
    public String id;

    @Column(name = "Father")
    public String father;

    @Column(name = "FatherId")
    public String fatherid;

    @Column(name = "Fz1kh")
    public String fz1kh;

    @Column(name = "Fz2gys")
    public String fz2gys;

    @Column(name = "Fz3bm")
    public String fz3bm;

    @Column(name = "Fz4ry")
    public String fz4ry;

    @Column(name = "Fz5wl")
    public String fz5wl;

    @Column(name = "Jc")
    public String jc;

    @Column(name = "Jp")
    public String jp;

    @Column(name = "Kmbh")
    public String kmbh;

    @Column(name = "Kmmc")
    public String kmmc;

    @Column(name = "Kmqc")
    public String kmqc;

    @Column(name = "Kmsx")
    public String kmsx;



    @Column(name = "Qp")
    public String qp;

    @Column(name = "Sfmx")
    public String sfmx;

    @Column(name = "Sfnbwl")
    public String sfnbwl;

    @Column(name = "Sfqy")
    public String sfqy;

    @Column(name = "Sfslhs")
    public String sfslhs;

    @Column(name = "Sfss")
    public String sfss;

    @Column(name = "Sfwbhs")
    public String sfwbhs;

    @Column(name = "Sfxjll")
    public String sfxjll;

    @Column(name = "Space")
    public String space;

    @Column(name = "Text")
    public String text;

    @Column(name = "Unit")
    public String unit;

    @Column(name = "Wbid")
    public String wbid;

    @Column(name = "Wbmc")
    public String wbmc;

    @Column(name = "Yefx")
    public String yefx;

    @Column(name = "YefxZnCh")
    public String yefxZnCh;

    @Column(name = "Yhdm")
    public String yhdm;

    @Column(name = "Zdyfz1")
    public String zdyfz1;

    @Column(name = "Zdyfz2")
    public String zdyfz2;

    @Column(name = "Zjm")
    public String zjm;

}
