package com.szht.htfsweb.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WangJian on 2014/7/14.
 */
@Table(name = "BaseConfig")
public class BaseConfig extends Model {
    @Column(name = "Ztdm")
    public String ztdm;

    @Column(name = "Data")
    public String data;

    private JSONObject d;

    private void  initJson(){
        if(d==null){
            try {
                d = new JSONObject(data);
            } catch (JSONException e) {
                d = new JSONObject();
            }
        }
    }

    public String getBMGZ(){
        initJson();
        //bmgz.BMGZ_KMZD.bmgz
        try {
            if(d.has("bmgz")&&d.getJSONObject("bmgz").has("BMGZ_KMZD")&&d.getJSONObject("bmgz").getJSONObject("BMGZ_KMZD").has("bmgz")){
                return d.getJSONObject("bmgz").getJSONObject("BMGZ_KMZD").getString("bmgz");
            }else{
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }


    public String getJEJD(){
        initJson();
        //xtcs.XTCS_WBJEJD.val
        try {
            if(d.has("xtcs")&&d.getJSONObject("xtcs").has("XTCS_WBJEJD")&&d.getJSONObject("xtcs").getJSONObject("XTCS_WBJEJD").has("val")){
                return d.getJSONObject("xtcs").getJSONObject("XTCS_WBJEJD").getString("val");
            }else{
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }


    public String getSLJD(){
        initJson();
        //xtcs.XTCS_SLJD.val
        try {
            if(d.has("xtcs")&&d.getJSONObject("xtcs").has("XTCS_SLJD")&&d.getJSONObject("xtcs").getJSONObject("XTCS_SLJD").has("val")){
                return d.getJSONObject("xtcs").getJSONObject("XTCS_SLJD").getString("val");
            }else{
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }

    public String getDJJD(){
        initJson();
        //xtcs.XTCS_DJJD.val
        try {
            if(d.has("xtcs")&&d.getJSONObject("xtcs").has("XTCS_DJJD")&&d.getJSONObject("xtcs").getJSONObject("XTCS_DJJD").has("val")){
                return d.getJSONObject("xtcs").getJSONObject("XTCS_DJJD").getString("val");
            }else{
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }

    public String getHLJD(){
        initJson();
        //xtcs.XTCS_HLJD.val
        try {
            if(d.has("xtcs")&&d.getJSONObject("xtcs").has("XTCS_HLJD")&&d.getJSONObject("xtcs").getJSONObject("XTCS_HLJD").has("val")){
                return d.getJSONObject("xtcs").getJSONObject("XTCS_HLJD").getString("val");
            }else{
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }


}
