package com.szht.htfsweb.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.*;
import com.szht.htfsweb.R;
import com.szht.htfsweb.Welcome;
import com.szht.htfsweb.adapter.ZtAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.Zt;
import com.szht.htfsweb.sync.LRBSync;
import com.szht.htfsweb.sync.ZtAllSync;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.tools.YWDatePickerDialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QueryActivity extends ActivitySupport  {
    /**
     * Called when the activity is first created.
     */

    private Handler ztHandler;
    Intent intent;
    Zt zt;
    TextView ztmc;
    private Calendar calendar = null;
    private Dialog mdialog;
    private String year,month;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.query);
        ztmc = (TextView)findViewById(R.id.ztmc);
        intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        zt = (Zt)bunde.getSerializable("zt");

        ztmc.setText(zt.getZtmc());
        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                if (msg.arg1 == 1) {

                }
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }




            }

        };
    }

    private void syncZtList(){
        IUrlSync urlSync = new ZtAllSync();
        urlSync.setSyncTitle("获取账套信息");
        urlSync.setToastContentFa("没有账套");
        urlSync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(urlSync);
        urlTask.start();
//        showToast("正在获取账套列表……");
    }



    public void onQuery(View v) {
        final DatePickerDialogCustom localAlertDialogCustom = new DatePickerDialogCustom(context);
        String[] y=new String[1];
        y[0]=zt.getQysj().substring(0,4);
        String[] m= QYSJArrayUtil.getMonthStrArr(zt.getQysj());
        localAlertDialogCustom.setYearList(y);
        localAlertDialogCustom.setMonthList(m);
        localAlertDialogCustom.show();
        localAlertDialogCustom.setMessage("选择查询月份");
        localAlertDialogCustom.setOnOKListener("查询", new DatePickerDialogCustom.AlertDialogOKListener() {

            @Override
            public void onOKClick() {

                year = localAlertDialogCustom.getSelectedYear();
                month = localAlertDialogCustom.getSelectedMonth();
                IUrlSync sync=new LRBSync();
                sync.setModth(IUrlSync.POST);
                sync.addParm("kjnd",year);
                sync.addParm("kjqjs",month);
                sync.setSyncTitle("利润表");
                sync.setToastContentFa("查询失败");

                Intent mainIntent = new Intent(QueryActivity.this, QueryResultActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("zt", zt);
                extras.putSerializable("sync", sync);
                mainIntent.putExtras(extras);
                QueryActivity.this.startActivity(mainIntent);
            }
        });
        localAlertDialogCustom.setOnCancelListener("取消",new DatePickerDialogCustom.AlertDialogCancelListener(){


            @Override
            public void onCancelClick() {

            }
        });


    }

}
