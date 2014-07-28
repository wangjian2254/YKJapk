package com.szht.htfsweb.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.*;
import com.activeandroid.query.Select;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.KjkmAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.db.BaseConfig;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.sync.*;
import com.szht.htfsweb.tools.DatePicker3DialogCustom;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.tools.DatePicker_KJKM_DialogCustom;
import com.szht.htfsweb.tools.DatePicker_PZList_DialogCustom;
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
    ZtInfo zt;
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
        zt = (ZtInfo)bunde.getSerializable("zt");

        ztmc.setText(zt.ztmc);
        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                if (msg.arg1 == 10) {
                    syncKjkmList();
                }
                if (msg.arg1 == 11) {

                }
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }
            }

        };
        syncKjkmSyncList();

    }

    private void syncKjkmSyncList(){
        //


        KjkmTimelineSync urlSync = new KjkmTimelineSync();
        urlSync.setZt(zt);
//        urlSync.setSyncTitle("获取会计科目");
        urlSync.setToastContentFa("获取会计科目失败");
        urlSync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(urlSync);
        urlTask.start();
    }
    private void syncKjkmList(){
        KjkmSync urlSync = new KjkmSync();
        urlSync.setZt(zt);
        urlSync.setSyncTitle("获取会计科目");
        urlSync.setToastContentFa("获取会计科目失败");
        urlSync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(urlSync);
        urlTask.start();
    }



    public void onQuery(View v) {
        switch (v.getId()){
            case R.id.mod_lrb:
                final DatePickerDialogCustom localAlertDialogCustom = new DatePickerDialogCustom(context);
                String[] y=new String[1];
                y[0]=zt.qysj.substring(0,4);
                String[] m= QYSJArrayUtil.getMonthStrArr(zt.qysj);
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
                y2[0]=zt.qysj.substring(0,4);
                String[] m2= QYSJArrayUtil.getMonthStrArr(zt.qysj);
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
                y3[0]=zt.qysj.substring(0,4);
                String[] m3= QYSJArrayUtil.getMonthStrArr(zt.qysj);
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

                        Intent mainIntent = new Intent(QueryActivity.this, QueryResultXJLLActivity.class);
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
            case R.id.mod_zz:
                final DatePicker_KJKM_DialogCustom datePicker4 = new DatePicker_KJKM_DialogCustom(context);
                String[] m4= QYSJArrayUtil.getMonthStrArr(zt.qysj);
                datePicker4.setMonthList(m4);
                datePicker4.setZt(zt);
                datePicker4.setBaseConfig((BaseConfig) new Select().from(BaseConfig.class).where("ztdm = ?",zt.ztdm).executeSingle());
                datePicker4.show();
                datePicker4.setMessage("总账查询");
                datePicker4.setKjkmSelected(new DatePicker_KJKM_DialogCustom.AlertDialogSelectListener() {
                    @Override
                    public void onSelectedClick(Kjkm kjkm) {
                        String param = datePicker4.getParam(kjkm.kmbh);
                        ZzSync sync=new ZzSync();
                        sync.setQjQ(datePicker4.getQ());
                        sync.setQjZ(datePicker4.getZ());
                        sync.setStyle(datePicker4.getZb_Style());
                        sync.setIsjson(true);
                        sync.setJsonparm(param);
                        sync.setModth(IUrlSync.POST);
                        sync.setSyncTitle("总账");
                        sync.setToastContentFa("查询失败");

                        Intent mainIntent = new Intent(QueryActivity.this, QueryZzActivity.class);
                        Bundle extras = new Bundle();
                        extras.putSerializable("zt", zt);
                        extras.putSerializable("sync", sync);
                        mainIntent.putExtras(extras);
                        QueryActivity.this.startActivity(mainIntent);
                    }
                });
                break;
            case R.id.mod_mx:
                final DatePicker_KJKM_DialogCustom datePicker5 = new DatePicker_KJKM_DialogCustom(context);
                String[] m5= QYSJArrayUtil.getMonthStrArr(zt.qysj);
                datePicker5.setMonthList(m5);
                datePicker5.setZt(zt);
                datePicker5.setBaseConfig((BaseConfig) new Select().from(BaseConfig.class).where("ztdm = ?",zt.ztdm).executeSingle());
                datePicker5.show();
                datePicker5.setMessage("明细账查询");
                datePicker5.setKjkmSelected(new DatePicker_KJKM_DialogCustom.AlertDialogSelectListener() {
                    @Override
                    public void onSelectedClick(Kjkm kjkm) {
                        String param = datePicker5.getParam(kjkm.kmbh);
                        MxSync sync=new MxSync();
                        sync.setQjQ(datePicker5.getQ());
                        sync.setQjZ(datePicker5.getZ());
                        sync.setStyle(datePicker5.getZb_Style());
                        sync.setIsjson(true);
                        sync.setJsonparm(param);
                        sync.setModth(IUrlSync.POST);
                        sync.setSyncTitle("明细账");
                        sync.setToastContentFa("查询失败");

                        Intent mainIntent = new Intent(QueryActivity.this, QueryMxActivity.class);
                        Bundle extras = new Bundle();
                        extras.putSerializable("zt", zt);
                        extras.putSerializable("sync", sync);
                        mainIntent.putExtras(extras);
                        QueryActivity.this.startActivity(mainIntent);
                    }
                });
                break;
            case R.id.mod_pzcx:
                final DatePicker_PZList_DialogCustom datePicker6 = new DatePicker_PZList_DialogCustom(context);
                String[] m6= QYSJArrayUtil.getMonthStrArr(zt.qysj);
                datePicker6.setMonthList(m6);
                datePicker6.setZt(zt);
                datePicker6.show();
                datePicker6.setMessage("明细账查询");
                datePicker6.setOnOKListener("查询", new DatePicker_PZList_DialogCustom.AlertDialogOKListener() {

                    @Override
                    public void onOKClick() {


                        IUrlSync sync=new PZListSync();
                        sync.setModth(IUrlSync.POST);
                        sync.setIsjson(true);
                        sync.setJsonparm(datePicker6.getParam());
                        sync.setSyncTitle("凭证查询");
                        sync.setToastContentFa("查询失败");

                        Intent mainIntent = new Intent(QueryActivity.this, QueryPZListActivity.class);
                        Bundle extras = new Bundle();
                        extras.putSerializable("zt", zt);
                        extras.putSerializable("sync", sync);
                        mainIntent.putExtras(extras);
                        QueryActivity.this.startActivity(mainIntent);
                    }
                });
                datePicker6.setOnCancelListener("取消",new DatePicker_PZList_DialogCustom.AlertDialogCancelListener(){


                    @Override
                    public void onCancelClick() {

                    }
                });
                break;
        }



    }

}
