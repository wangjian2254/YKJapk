package com.szht.htfsweb.sync;

import android.os.Message;
import com.szht.htfsweb.model.XJLLItem;
import com.szht.htfsweb.model.ZCFZItem;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XJLLSync extends UrlSync {
    private    String uri="_505_XianJinLiuLiangAction.do?method=showZwxjllDataShowVo_Phone";
    JSONArray result;
    public boolean doPerResult()throws Exception{
        result = new JSONArray(getResult());
        return true;

    }

    public void doResult() throws Exception {
        if (!doPerResult()) {
            return;
        }
        if (result.length()>0) {
            if (getHandler() != null) {
                Message hmsg = getHandler().obtainMessage();
                List<XJLLItem> list = new ArrayList<XJLLItem>();
                JSONObject item=null;
                JSONArray bb=null;
                XJLLItem ztitem=null;

                for(int i=0;i<result.length();i++){
                    item = result.getJSONObject(i);
                    ztitem = new XJLLItem();
                    ztitem.setXmmc(item.optString("xmmc").replace("&nbsp;&nbsp;&nbsp;", " ").replace("&nbsp;", " "));
                    ztitem.setSqje(item.optString("sqje"));
                    ztitem.setBqje(item.getString("bqje")); 
//                    ztitem.setId(item.optString("ztdm",""));
//                    ztitem.setZtmc(item.optString("ztmc",""));
//                    ztitem.setKjzd(item.optString("kjzd", ""));
//                    ztitem.setQyid(item.optString("qyid",""));
//                    ztitem.setQymc(item.optString("ztmc",""));
//                    ztitem.setQysj(item.optString("qysj", ""));
                    list.add(ztitem);
                }
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
