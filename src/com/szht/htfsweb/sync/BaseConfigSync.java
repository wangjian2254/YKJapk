package com.szht.htfsweb.sync;

import android.os.Message;
import com.activeandroid.query.Select;
import com.szht.htfsweb.db.BaseConfig;
import com.szht.htfsweb.db.DataTimeLine;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONObject;

public class BaseConfigSync extends UrlSync {
    private    String uri="_000_GetBaseConfigAction.do?method=getBaseConfig";

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
        BaseConfig dtl = new Select().from(BaseConfig.class).where("Ztdm =?", zt.ztdm).executeSingle();
        if (dtl==null) {
            dtl = new BaseConfig();
            dtl.ztdm = zt.ztdm;
        }
        dtl.data = getResult();
        dtl.save();

        Message hmsg = getHandler().obtainMessage();
        hmsg.arg1 = 4;
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

//    public ZtInfo getZt() {
//        return zt;
//    }
//
//    public void setZt(ZtInfo zt) {
//        this.zt = zt;
//    }
}
