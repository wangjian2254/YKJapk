package com.szht.htfsweb.sync;

import android.os.Message;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.model.PzListItem;
import com.szht.htfsweb.model.ZzBB;
import com.szht.htfsweb.model.ZzItem;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PZListSync extends UrlSync {
    private    String uri="_302_PzLbAction.do?method=queryPzlb&limit=200";
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


        PzListItem item =null;
        PzListItem pzitem =null;

        String pzid = null;

        List<PzListItem> l =new ArrayList<PzListItem>();
        JSONArray r = result.getJSONArray("Pzlb312ShowVo");
        JSONObject b = null;
        for(int i=0;i<r.length();i++){
            b = r.getJSONObject(i);

            item = new PzListItem();

            item.pzrq = b.getString("pzrq");
            item.zh = b.getString("zh");
            item.jje = b.getString("jje");
            item.dje = b.getString("dje");
            item.dfhj = b.getString("dfhj");
            item.kminfo = b.getString("kminfo");

            item.jzf = b.optString("jzf","");
            item.fhf = b.optString("fhf","");
            item.ccf = b.optString("ccf","");
            if("Y".equalsIgnoreCase(item.jzf)){
                item.status="已记账";
            }
            if("N".equalsIgnoreCase(item.jzf)&&"Y".equalsIgnoreCase(item.fhf)){
                item.status="已审核";
            }
            if("N".equalsIgnoreCase(item.jzf)&&"N".equalsIgnoreCase(item.fhf)&&"Y".equalsIgnoreCase(item.ccf)){
                item.status="已标错";
            }
            if("N".equalsIgnoreCase(item.jzf)&&"N".equalsIgnoreCase(item.fhf)&&"N".equalsIgnoreCase(item.ccf)){
                item.status="未审核";
            }

            item.zy = b.getString("zy");

            if(!b.optString("pzid","").equals(pzid)){
                pzitem= new PzListItem();
                pzitem.pzinfo = "凭证编号："+item.zh+" 凭证日期："+item.pzrq+" 凭证状态："+item.status+" 合计："+item.dfhj;
                l.add(pzitem);
                pzid = b.optString("pzid","");
            }

            l.add(item);
        }


        Message hmsg = getHandler().obtainMessage();
        hmsg.obj = l;
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
