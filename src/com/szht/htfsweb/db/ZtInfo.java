package com.szht.htfsweb.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangJian on 2014/7/14.
 */
@Table(name = "ZtInfo")
public class ZtInfo extends Model implements Serializable {
    @Column(name = "Ztdm")
    public String ztdm;

    @Column(name = "Ztmc")
    public String ztmc;

    @Column(name = "Qysj")
    public String qysj;

    @Column(name = "Qymc")
    public String qymc;

    @Column(name = "Kjzd")
    public String kjzd;

    @Column(name = "Qyid")
    public String qyid;


    @Column(name = "Ywrq")
    public String ywrq;

    @Column(name = "User")
    public User user;

    public List<Kjkm> kjkmList (){
        return getMany(Kjkm.class,"Zt");
    }
}
