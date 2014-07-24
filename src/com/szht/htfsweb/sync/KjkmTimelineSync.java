package com.szht.htfsweb.sync;

import android.os.Message;
import com.activeandroid.query.Select;
import com.szht.htfsweb.db.DataTimeLine;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KjkmTimelineSync extends UrlSync {
    private    String uri="_113_KeMuAction.do?method=queryKmzdVersion";

    private ZtInfo zt;
    JSONObject result;
    public boolean doPerResult()throws Exception{
        result = new JSONObject(getResult());
        return true;

    }

    public void doResult() throws Exception {
        if (!doPerResult()) {
            return;
        }
        int version = result.optInt("kmversion");
        DataTimeLine dtl = new Select().from(DataTimeLine.class).where("Ztdm =? and DataType=?",zt.ztdm,"kjkm").executeSingle();
        int num = new Select().from(Kjkm.class).where("Ztdm = ?",zt.ztdm).count();
        if (dtl==null||dtl.timeflag!=version||num==0){
            if(dtl==null){
                dtl = new DataTimeLine();
            }
            dtl.ztdm=zt.ztdm;
            dtl.dataType="kjkm";
            dtl.timeflag = version;
            dtl.save();
            Message hmsg = getHandler().obtainMessage();

            hmsg.arg1 = 10;
            getHandler().sendMessage(hmsg);
            setHandler(null);
        }else{
            Message hmsg = getHandler().obtainMessage();

            hmsg.arg1 = 11;
            getHandler().sendMessage(hmsg);
            setHandler(null);
        }



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

//    public ZtInfo getZt() {
//        return zt;
//    }
//
//    public void setZt(ZtInfo zt) {
//        this.zt = zt;
//    }
}
