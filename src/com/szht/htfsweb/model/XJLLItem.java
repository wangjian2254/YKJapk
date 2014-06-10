package com.szht.htfsweb.model;

import java.io.Serializable;

/**
 * Created by WangJian on 14-3-6.
 */
public class XJLLItem implements Serializable {
    private String xmmc;
    private String bqje;
    private String sqje;


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

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }
}
