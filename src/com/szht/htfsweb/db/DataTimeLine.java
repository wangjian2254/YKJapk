package com.szht.htfsweb.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by WangJian on 2014/7/14.
 */
@Table(name = "DataTimeLine")
public class DataTimeLine extends Model {
    @Column(name = "Ztdm")
    public String ztdm;

    @Column(name = "DataType")
    public String dataType;

    @Column(name = "TimeFlag")
    public int timeflag;
}
