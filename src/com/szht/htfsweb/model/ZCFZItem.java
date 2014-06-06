package com.szht.htfsweb.model;

import java.io.Serializable;

/**
 * Created by WangJian on 14-3-6.
 */
public class ZCFZItem implements Serializable {
    private String zc;
    private String qmye1;
    private String ncye1;
    private String fz;
    private String qmye2;
    private String ncye2;

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getQmye1() {
        return qmye1;
    }

    public void setQmye1(String qmye1) {
        this.qmye1 = qmye1;
    }

    public String getNcye1() {
        return ncye1;
    }

    public void setNcye1(String ncye1) {
        this.ncye1 = ncye1;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public String getQmye2() {
        return qmye2;
    }

    public void setQmye2(String qmye2) {
        this.qmye2 = qmye2;
    }

    public String getNcye2() {
        return ncye2;
    }

    public void setNcye2(String ncye2) {
        this.ncye2 = ncye2;
    }
}
