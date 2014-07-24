package com.szht.htfsweb.sync;

import android.os.Message;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.util.Convert;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZtAllSync extends UrlSync {
    private    String uri="_002_QiYeAction.do?method=findComplyIOSByGrouping";
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
                List<ZtInfo> list = new ArrayList<ZtInfo>();
                JSONObject item=null;
                ZtInfo ztitem=null;
                new Delete().from(ZtInfo.class).where("User = ?",Convert.currentUser.getId()).execute();
                for(int i=0;i<result.length();i++){
                    item = result.getJSONObject(i);
                    ztitem = new Select().from(ZtInfo.class).where("ztdm = ?",item.optString("ztdm","")).executeSingle();
                    if(ztitem==null){
                        ztitem = new ZtInfo();
                    }
                    ztitem.user = Convert.currentUser;
                    ztitem.ztdm=item.optString("ztdm","");
                    ztitem.ztmc=item.optString("ztmc","");
                    ztitem.kjzd=item.optString("kjzd", "");
                    ztitem.qyid=item.optString("qyid","");
                    ztitem.qymc=item.optString("ztmc","");
                    ztitem.qysj=item.optString("qysj", "");
                    ztitem.save();
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
