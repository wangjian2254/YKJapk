package com.szht.htfsweb.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.Welcome;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.Zt;
import com.szht.htfsweb.sync.ZtAllSync;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;
import com.umeng.fb.FeedbackAgent;

import java.util.Calendar;

public class QueryResultActivity extends ActivitySupport  {
    /**
     * Called when the activity is first created.
     */

    private Handler ztHandler;
    Intent intent;
    Zt zt;
    TextView ztmc,qymc,bbmonth,bbdw;
    ListView querylist;
    private Calendar calendar = null;
    private Dialog mdialog;
    private String year,month;
    private IUrlSync sync;

    public static final int SEARCHPLUGIN = Menu.FIRST + 1;
    public static final int APPLIST = Menu.FIRST + 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.queryresult);

        querylist = (ListView)findViewById(R.id.querylist);
        intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        zt = (Zt)bunde.getSerializable("zt");
        sync = (IUrlSync)bunde.getSerializable("sync");
        syncURL();
        View headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head,querylist,false);
        ztmc = (TextView)headerView.findViewById(R.id.querytxt);
        qymc = (TextView)headerView.findViewById(R.id.qymc);
        qymc.setText(zt.getQymc());
        bbmonth = (TextView)headerView.findViewById(R.id.bbmonth);
        bbdw = (TextView)headerView.findViewById(R.id.bbdw);
        querylist.addHeaderView(headerView);

        ztmc.setText(sync.getSyncTitle());
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

    private void syncURL(){

        sync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(sync);
        urlTask.start();
//        showToast("正在获取账套列表……");
    }



    public void onQuery(View v) {
        final DatePickerDialogCustom localAlertDialogCustom = new DatePickerDialogCustom(context);
        String[] y={zt.getQysj().substring(0,4)};
        String[] m= QYSJArrayUtil.getMonthStrArr(zt.getQysj());
        localAlertDialogCustom.setYearList(y);
        localAlertDialogCustom.setMonthList(m);
        localAlertDialogCustom.show();
        localAlertDialogCustom.setMessage("选择查询月份");
        localAlertDialogCustom.setOnOKListener("查询", new DatePickerDialogCustom.AlertDialogOKListener() {

            @Override
            public void onOKClick() {

                bbmonth.setText(localAlertDialogCustom.getSelectedYear()+"年"+localAlertDialogCustom.getSelectedMonth()+"月");
                sync.addParm("kjnd",localAlertDialogCustom.getSelectedYear());
                sync.addParm("kjqjs",localAlertDialogCustom.getSelectedMonth());
                syncURL();
            }
        });
        localAlertDialogCustom.setOnCancelListener("取消", new DatePickerDialogCustom.AlertDialogCancelListener() {

            @Override
            public void onCancelClick() {

            }
        });

    }


    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        populateMenu(menu);
    }

    public void populateMenu(Menu menu) {
        menu.add(Menu.NONE, SEARCHPLUGIN, Menu.NONE, "刷新");
        menu.add(Menu.NONE, APPLIST, Menu.NONE, "修改查询条件");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        populateMenu(menu);
        return (super.onCreateOptionsMenu(menu));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }

    public boolean onContextItemSelected(MenuItem item) {
        return (applyMenuChoice(item) || super.onContextItemSelected(item));
    }
    public boolean applyMenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case SEARCHPLUGIN:
                syncURL();
                return true;
            case APPLIST:
                onQuery(null);
                return true;


        }
        return false;
    }

}
