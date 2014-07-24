package com.szht.htfsweb.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.activeandroid.query.Select;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.LRBAdapter;
import com.szht.htfsweb.adapter.ZzAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.db.BaseConfig;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.model.ZzBB;
import com.szht.htfsweb.sync.ZzSync;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.tools.DatePicker_KJKM_DialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QueryZzActivity extends QueryZBActivity  {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryresultzz);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head_zz,querylist,false);
        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                if (msg.arg1 == 1) {
                    ZzBB m = (ZzBB)msg.obj;
                    list.clear();
                    list.addAll(m.list);
                    bbmc.setText(m.kmmc + "总账");
                    kjkm.setText("科目:"+m.kmbh+":"+m.kmmc);
                    ZzSync s = (ZzSync)sync;
                    kjqj.setText(zt.qysj.substring(0,4)+"."+s.getQjQ()+"-"+s.getQjZ());


                    baseAdapter.notifyDataSetChanged();
                }
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }




            }

        };
        sync = (IUrlSync)this.getIntent().getExtras().getSerializable("sync");
        ZzSync zzSync = (ZzSync)sync;
        zb_style =zzSync.getStyle();

        initUI();
    }

////    @Override
//    public void initHead(String style) {
//        setHead(style);
//        ZzAdapter zzAdapter  = new ZzAdapter(context, list);
//        zzAdapter.setZb_style(style);
//        querylist.setAdapter(zzAdapter);
//        baseAdapter = zzAdapter;
//    }


    public void onQuery(View v) {
        final DatePicker_KJKM_DialogCustom datePicker4 = new DatePicker_KJKM_DialogCustom(context);
        String[] m4= QYSJArrayUtil.getMonthStrArr(zt.qysj);
        datePicker4.setMonthList(m4);
        datePicker4.setZt(zt);
        datePicker4.setBaseConfig((BaseConfig) new Select().from(BaseConfig.class).where("ztdm = ?", zt.ztdm).executeSingle());
        datePicker4.show();
        datePicker4.setMessage("总账查询");
        datePicker4.setKjkmSelected(new DatePicker_KJKM_DialogCustom.AlertDialogSelectListener() {
            @Override
            public void onSelectedClick(Kjkm kjkm) {
                String param = datePicker4.getParam(kjkm.kmbh);
                ZzSync zzsync = new ZzSync();
                zzsync.setQjQ(datePicker4.getQ());
                zzsync.setQjZ(datePicker4.getZ());
                zzsync.setStyle(datePicker4.getZb_Style());
                zzsync.setIsjson(true);
                zzsync.setJsonparm(param);
                zzsync.setModth(IUrlSync.POST);
                zzsync.setSyncTitle("总账");
                zzsync.setToastContentFa("查询失败");
                sync = zzsync;
                zb_style = zzsync.getStyle();
                syncURL();
            }
        });

    }


}
