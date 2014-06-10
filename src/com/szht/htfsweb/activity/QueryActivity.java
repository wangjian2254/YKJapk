package com.szht.htfsweb.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.*;
import com.szht.htfsweb.R;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.Zt;
import com.szht.htfsweb.sync.LRBSync;
import com.szht.htfsweb.sync.XJLLSync;
import com.szht.htfsweb.sync.ZCFZSync;
import com.szht.htfsweb.sync.ZtAllSync;
import com.szht.htfsweb.tools.DatePicker3DialogCustom;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;

import java.util.Calendar;

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
        switch (v.getId()){
            case R.id.mod_lrb:
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
                        sync.addParm("json","1");
                        sync.addParm("kjnd",year);
                        sync.addParm("kjqjs",month);
                        sync.setSyncTitle("利润表");
                        sync.setToastContentFa("查询失败");

                        Intent mainIntent = new Intent(QueryActivity.this, QueryResultLRBActivity.class);
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
                break;
            case R.id.mod_zcfz:
                final DatePickerDialogCustom datePicker = new DatePickerDialogCustom(context);
                String[] y2=new String[1];
                y2[0]=zt.getQysj().substring(0,4);
                String[] m2= QYSJArrayUtil.getMonthStrArr(zt.getQysj());
                datePicker.setYearList(y2);
                datePicker.setMonthList(m2);
                datePicker.show();
                datePicker.setMessage("选择查询月份");
                datePicker.setOnOKListener("查询", new DatePickerDialogCustom.AlertDialogOKListener() {

                    @Override
                    public void onOKClick() {

                        year = datePicker.getSelectedYear();
                        month = datePicker.getSelectedMonth();
                        IUrlSync sync=new ZCFZSync();
                        sync.setModth(IUrlSync.POST);
                        sync.addParm("kjnd",year);
                        sync.addParm("json","1");
                        sync.addParm("kjqjs",month);
                        sync.setSyncTitle("资产负债表");
                        sync.setToastContentFa("查询失败");

                        Intent mainIntent = new Intent(QueryActivity.this, QueryResultZCFZActivity.class);
                        Bundle extras = new Bundle();
                        extras.putSerializable("zt", zt);
                        extras.putSerializable("sync", sync);
                        mainIntent.putExtras(extras);
                        QueryActivity.this.startActivity(mainIntent);
                    }
                });
                datePicker.setOnCancelListener("取消",new DatePickerDialogCustom.AlertDialogCancelListener(){


                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
            case R.id.mod_xjllb:
                final DatePicker3DialogCustom datePicker3 = new DatePicker3DialogCustom(context);
                String[] y3=new String[1];
                y3[0]=zt.getQysj().substring(0,4);
                String[] m3= QYSJArrayUtil.getMonthStrArr(zt.getQysj());
                datePicker3.setYearList(y3);
                datePicker3.setMonthList(m3);
                datePicker3.show();
                datePicker3.setMessage("选择查询月份");
                datePicker3.setOnOKListener("查询", new DatePicker3DialogCustom.AlertDialogOKListener() {

                    @Override
                    public void onOKClick() {

                        year = datePicker3.getSelectedYear();
                        month = datePicker3.getSelectedMonth();
                        IUrlSync sync=new XJLLSync();
                        sync.setModth(IUrlSync.POST);
                        sync.addParm("kjnd", year);
                        sync.addParm("json","1");
                        sync.addParm("ybjbnb",datePicker3.getYbJbNb());
                        sync.addParm("kjqjs",month);
                        sync.setSyncTitle("现金流量表");
                        sync.setToastContentFa("查询失败");

                        Intent mainIntent = new Intent(QueryActivity.this, QueryResultZCFZActivity.class);
                        Bundle extras = new Bundle();
                        extras.putSerializable("zt", zt);
                        extras.putSerializable("sync", sync);
                        mainIntent.putExtras(extras);
                        QueryActivity.this.startActivity(mainIntent);
                    }
                });
                datePicker3.setOnCancelListener("取消",new DatePicker3DialogCustom.AlertDialogCancelListener(){


                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
        }



    }

}
