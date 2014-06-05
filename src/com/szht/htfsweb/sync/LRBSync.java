package com.szht.htfsweb.sync;

import android.os.Message;
import com.szht.htfsweb.model.BBItem;
import com.szht.htfsweb.model.Zt;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LRBSync extends UrlSync {
    private    String uri="_504_ZiDingYiBaoBiaoAction.do?method=showLrbDataShowVo_Phone";
    JSONObject result;
    public boolean doPerResult()throws Exception{
        result = new JSONObject(getResult());
        return true;

    }

    public void doResult() throws Exception {
        if (!doPerResult()) {
            return;
        }
        if (result.length()>0) {
            if (getHandler() != null) {
                Message hmsg = getHandler().obtainMessage();
                List<BBItem> list = new ArrayList<BBItem>();
                JSONObject item=null;
                JSONArray bb=null;
                BBItem ztitem=null;

//                for(int i=0;i<result.length();i++){
//                    item = result.getJSONObject(i);
//                    ztitem = new Zt();
//                    ztitem.setId(item.optString("ztdm",""));
//                    ztitem.setZtmc(item.optString("ztmc",""));
//                    ztitem.setKjzd(item.optString("kjzd", ""));
//                    ztitem.setQyid(item.optString("qyid",""));
//                    ztitem.setQymc(item.optString("ztmc",""));
//                    ztitem.setQysj(item.optString("qysj", ""));
//                    list.add(ztitem);
//                }
                hmsg.obj = list;
                hmsg.arg1 = 1;
                getHandler().sendMessage(hmsg);
                setHandler(null);
            }
            return;
        }else{
            if (getHandler() != null) {
                Message hmsg = getHandler().obtainMessage();
                hmsg.obj = "没有账套";
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
