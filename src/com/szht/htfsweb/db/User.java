package com.szht.htfsweb.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangJian on 2014/7/14.
 */
@Table(name = "User")
public class User extends Model implements Serializable {
    @Column(name = "UserName")
    public String userName;

    @Column(name = "PassWord")
    public String passWord;

    @Column(name = "Sessionid")
    public String sessionid;

    @Column(name = "LashLoginTime")
    public long lashLoginTime;

    public List<ZtInfo> ztInfoList (){
        return getMany(ZtInfo.class,"User");
    }
}
