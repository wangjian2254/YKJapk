package com.szht.htfsweb.sync;

import android.os.Message;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.model.MxBB;
import com.szht.htfsweb.model.MxItem;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

public class MxSync extends UrlSync {
    private    String uri="_316_MingXiZhangAction.do?method=getMxzGrid";
    /*
    {"bbrq":"2014-07-23","kmbh":"1004","kmmc":"备用金","pageSize":"",
    "result":[
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013-08","type":"ZZMXZ_QCYE","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"期初余额"},
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013-08","type":"ZZMXZ_BNLJ","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"本月合计"},
    {"dfje":"","dfsl":"","dfwb":"","fx":"","jfje":"","jfsl":"","jfwb":"","kjnd":"","kjqj":"","kmbh":"","pzrq":"2013","type":"ZZMXZ_BNLJ","yefx":"平","yefxBz":"0","yeje":"","yesl":"","yewb":"","zy":"本年累计"}]}
     */

    private ZtInfo zt;
    private String style;
    private String qjQ;
    private String qjZ;
    JSONObject result;
    public boolean doPerResult()throws Exception{
        result = new JSONObject(getResult());
        return true;

    }

    public void doResult() throws Exception {
        if (!doPerResult()) {
            return;
        }
        MxBB zzBB = new MxBB();
        zzBB.bbrq = result.getString("bbrq");
        zzBB.kmbh = result.getString("kmbh");
        zzBB.kmmc = result.getString("kmmc");

        MxItem item =null;

        JSONArray r = result.getJSONArray("result");
        JSONObject b = null;
        for(int i=0;i<r.length();i++){
            b = r.getJSONObject(i);
            item = new MxItem();
            item.pzbh = b.getString("pzbh");
            item.dj = b.getString("dj");
            item.yedj = b.getString("yedj");
            item.hl = b.getString("hl");
            item.yehl = b.getString("yehl");
            item.dfje = b.getString("jedffs");
            item.dfsl = b.getString("sldffs");
            item.dfwb = b.getString("wbdffs");
//            item.fx = b.getString("fx");
            item.jfje = b.getString("jejffs");
            item.jfsl = b.getString("sljffs");
            item.jfwb = b.getString("wbjffs");
//            item.kjnd = b.getString("kjnd");
//            item.kjqj = b.getString("kjqj");
//            item.kmbh = b.getString("kmbh");
            item.pzrq = b.getString("pzrq");
            item.type = b.getString("type");
            item.yefx = b.getString("yefx");
            item.yefxBz = b.getString("yefxbz");
            item.yeje = b.getString("ye");
            item.yesl = b.getString("yesl");
            item.yewb = b.getString("yewb");
            item.zy = b.getString("zy");
            zzBB.list.add(item);
        }


        Message hmsg = getHandler().obtainMessage();
        hmsg.obj = zzBB;
        hmsg.arg1 = 1;
        getHandler().sendMessage(hmsg);
        setHandler(null);




    }

    public void doFailureResult() throws Exception {
        if (getHandler() != null) {
            Message hmsg = getHandler().obtainMessage();
            hmsg.obj = getToastContentFa();
            hmsg.arg1 = 2;
            hmsg.sendToTarget();
            setHandler(null);
        }
    }

    @Override
    public String getUri() {
        return uri;
    }

    public ZtInfo getZt() {
        return zt;
    }

    public void setZt(ZtInfo zt) {
        this.zt = zt;
    }

    public String getQjQ() {
        return qjQ;
    }

    public void setQjQ(String qjQ) {
        this.qjQ = qjQ;
    }

    public String getQjZ() {
        return qjZ;
    }

    public void setQjZ(String qjZ) {
        this.qjZ = qjZ;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
