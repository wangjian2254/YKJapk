package com.szht.htfsweb.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.*;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.LRBAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.Zt;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QueryResultLRBActivity extends ActivitySupport  {
    /**
     * Called when the activity is first created.
     */

    private Handler ztHandler;
    Intent intent;
    Zt zt;
    TextView ztmc,qymc,bbmonth,bbdw;
    ListView querylist;
    Calendar calendar = null;
    Dialog mdialog;
    String year,month;
    IUrlSync sync;
    BaseAdapter lrbAdapter;
    View headerView;
    List<Object> list = new ArrayList<Object>();
    public static final int SEARCHPLUGIN = Menu.FIRST + 1;
    public static final int APPLIST = Menu.FIRST + 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.queryresultlrb);

        querylist = (ListView)findViewById(R.id.querylist);
        lrbAdapter = new LRBAdapter(context, list);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head,querylist,false);

        initUI();
    }
    public void initUI(){
        intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        zt = (Zt)bunde.getSerializable("zt");
        sync = (IUrlSync)bunde.getSerializable("sync");

        ztmc = (TextView)headerView.findViewById(R.id.querytxt);
        if(ztmc==null){
            ztmc = (TextView)findViewById(R.id.querytxt);
        }
        qymc = (TextView)headerView.findViewById(R.id.qymc);
        if(qymc==null){
            qymc = (TextView)findViewById(R.id.qymc);
        }
        qymc.setText(zt.getQymc());
        bbmonth = (TextView)headerView.findViewById(R.id.bbmonth);
        if(bbmonth==null){
            bbmonth = (TextView)findViewById(R.id.bbmonth);
        }
        bbdw = (TextView)headerView.findViewById(R.id.bbdw);
        if(bbdw==null){
            bbdw = (TextView)findViewById(R.id.bbdw);
        }
        querylist.addHeaderView(headerView);
        querylist.setAdapter(lrbAdapter);
        ztmc.setText(sync.getSyncTitle());
        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                if (msg.arg1 == 1) {
                    List<Object> m = (List)msg.obj;
                    list.clear();
                    list.addAll(m);
                    lrbAdapter.notifyDataSetChanged();
                }
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }




            }

        };
        sync.setHandler(ztHandler);
        syncURL();
    }

    private void syncURL(){
        initBBHead();
        sync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(sync);
        urlTask.start();
//        showToast("正在获取账套列表……");
    }

    public void initBBHead(){
        bbmonth.setText(sync.getParm().get("kjnd")+"年"+sync.getParm().get("kjqjs")+"月");
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
