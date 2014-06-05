package com.szht.htfsweb.model;

import java.io.Serializable;

/**
 * Created by WangJian on 14-3-6.
 */
public class BBItem implements Serializable {
    private String xm;
    private String bqje;
    private String sqje;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getBqje() {
        return bqje;
    }

    public void setBqje(String bqje) {
        this.bqje = bqje;
    }

    public String getSqje() {
        return sqje;
    }

    public void setSqje(String sqje) {
        this.sqje = sqje;
    }
}
