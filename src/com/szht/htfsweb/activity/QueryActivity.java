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
import com.szht.htfsweb.sync.ZtAllSync;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.util.IUrlSync;
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
    private String queryrq=null;
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
        DatePickerDialogCustom localAlertDialogCustom = new DatePickerDialogCustom(context);
        localAlertDialogCustom.show();
        localAlertDialogCustom.setMessage("选择查询月份");
        localAlertDialogCustom.setOnOKListener("查询", new DatePickerDialogCustom.AlertDialogOKListener() {

            @Override
            public void onOKClick() {
                Toast.makeText(context,"查询",Toast.LENGTH_LONG).show();
            }
        });
        localAlertDialogCustom.setOnCancelListener("取消", new DatePickerDialogCustom.AlertDialogCancelListener() {

            @Override
            public void onCancelClick() {

            }
        });

    }

}
