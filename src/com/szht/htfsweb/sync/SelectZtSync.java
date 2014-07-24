package com.szht.htfsweb.sync;

import android.os.Message;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectZtSync extends UrlSync {
    private    String uri="_005_SysLoginAcion.do?method=loginQyinfoZtlink";
    public boolean doPerResult()throws Exception{
        if(getResult().length()==0||getResult().indexOf("var mainpath")>-1){
            return true;
        }
        return false;

    }

    public void doResult() throws Exception {
        if (!doPerResult()) {
            return;
        }
        if (getResult().length()==0||getResult().indexOf("var mainpath")>-1) {
            if (getHandler() != null) {
                Message hmsg = getHandler().obtainMessage();

                hmsg.obj = null;
                hmsg.arg1 = 3;
                getHandler().sendMessage(hmsg);
                setHandler(null);
            }
            return;
        }else{
            if (getHandler() != null) {
                Message hmsg = getHandler().obtainMessage();
                hmsg.obj = "账套不存在";
                hmsg.arg1 = 2;
                hmsg.sendToTarget();
                setHandler(null);
            }
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
}
