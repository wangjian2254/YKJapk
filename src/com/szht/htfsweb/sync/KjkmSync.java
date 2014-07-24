package com.szht.htfsweb.sync;

import android.os.Message;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.util.Convert;
import com.szht.htfsweb.util.UrlSync;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KjkmSync extends UrlSync {
    private    String uri="_301_PzAction.do?method=queryZwkmzdToCombo";


    private ZtInfo zt;
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
                List<Kjkm> list = new ArrayList<Kjkm>();
                JSONObject item=null;
                Kjkm ztitem=null;
                new Delete().from(Kjkm.class).where("ztdm = ?",zt.getId()).execute();
                for(int i=0;i<result.length();i++){
                    item = result.getJSONObject(i);
                    ztitem = new Select().from(Kjkm.class).where("id = ?",item.optString("id","")).executeSingle();
                    if(ztitem==null){
                        ztitem = new Kjkm();
                    }
//                    ztitem.ztdm = zt.ztdm;
                    ztitem.id=item.optString("id","");
                    ztitem.father=item.optString("father", "");
                    ztitem.fatherid=item.optString("fatherid","");
                    ztitem.fz1kh=item.optString("ztmc","");
                    ztitem.fz2gys=item.optString("fz2gys", "");
                    ztitem.fz3bm=item.optString("fz3bm", "");
                    ztitem.fz4ry=item.optString("fz4ry", "");
                    ztitem.fz5wl=item.optString("fz5wl", "");
                    ztitem.jc=item.optString("jc", "");
                    ztitem.jp=item.optString("jp", "");
                    ztitem.kmbh=item.optString("kmbh", "");
                    ztitem.kmmc=item.optString("kmmc", "");
                    ztitem.kmqc=item.optString("kmqc", "");
                    ztitem.kmsx=item.optString("kmsx", "");
                    ztitem.qp=item.optString("qp", "");
                    ztitem.sfmx=item.optString("sfmx", "");
                    ztitem.sfnbwl=item.optString("sfnbwl", "");
                    ztitem.sfqy=item.optString("sfqy", "");
                    ztitem.sfslhs=item.optString("sfslhs", "");
                    ztitem.sfss=item.optString("sfss", "");
                    ztitem.sfwbhs=item.optString("sfwbhs", "");
                    ztitem.sfxjll=item.optString("sfxjll", "");
                    ztitem.space=item.optString("space", "");
                    ztitem.text=item.optString("text", "");
                    ztitem.unit=item.optString("unit", "");
                    ztitem.wbid=item.optString("wbid", "");
                    ztitem.wbmc=item.optString("wbmc", "");
                    ztitem.yefx=item.optString("yefx", "");
                    ztitem.yefxZnCh=item.optString("yefxZnCh", "");
                    ztitem.yhdm=item.optString("yhdm", "");
                    ztitem.zdyfz1=item.optString("zdyfz1", "");
                    ztitem.zdyfz2=item.optString("zdyfz2", "");
                    ztitem.zjm=item.optString("zjm", "");
                    ztitem.ztdm=item.optString("ztdm", "");

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
                hmsg.obj = "获取会计科目失败";
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

    public ZtInfo getZt() {
        return zt;
    }

    public void setZt(ZtInfo zt) {
        this.zt = zt;
    }
}
