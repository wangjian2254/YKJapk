package com.szht.htfsweb.activity;

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
import com.szht.htfsweb.adapter.PZListAdapter;
import com.szht.htfsweb.adapter.ZzAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.db.BaseConfig;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.model.ZzBB;
import com.szht.htfsweb.sync.ZzSync;
import com.szht.htfsweb.tools.DatePicker_KJKM_DialogCustom;
import com.szht.htfsweb.tools.DatePicker_PZList_DialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;

import java.util.ArrayList;
import java.util.List;

public class QueryPZListActivity extends ActivitySupport  {
    /**
     * Called when the activity is first created.
     */
    protected Handler ztHandler;
    Intent intent;
    public ZtInfo zt;
    public TextView bbmc,kjkm,kjqj;
    public ListView querylist;
    public  IUrlSync sync;
    public BaseAdapter baseAdapter;
    public View headerView;
    public List<Object> list = new ArrayList<Object>();
    public static final int SEARCHPLUGIN = Menu.FIRST + 1;
    public static final int APPLIST = Menu.FIRST + 2;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryresultpzlist);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head_pzlist,querylist,false);
        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                if (msg.arg1 == 1) {
                    list.clear();
                    list.addAll((List)msg.obj);
                    baseAdapter.notifyDataSetChanged();
                }
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }




            }

        };
        initUI();

    }
    public void initUI(){
        intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        querylist = (ListView)findViewById(R.id.querylist);
        zt = (ZtInfo)bunde.getSerializable("zt");
        sync = (IUrlSync)bunde.getSerializable("sync");
        baseAdapter = new PZListAdapter(context,list);
        bbmc = (TextView)findViewById(R.id.querytitle);


        querylist.addHeaderView(headerView);
        querylist.setAdapter(baseAdapter);

        sync.setHandler(ztHandler);
        syncURL();
    }




    protected void syncURL(){
        sync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(sync);
        urlTask.start();
    }




    protected void onQuery(View v) {
        final DatePicker_PZList_DialogCustom datePicker4 = new DatePicker_PZList_DialogCustom(context);
        String[] m4= QYSJArrayUtil.getMonthStrArr(zt.qysj);
        datePicker4.setMonthList(m4);
        datePicker4.setZt(zt);
        datePicker4.show();
        datePicker4.setMessage("凭证列表查询");
        datePicker4.setOnOKListener("查询", new DatePicker_PZList_DialogCustom.AlertDialogOKListener() {
            @Override
            public void onOKClick() {
                sync.setModth(IUrlSync.POST);
                sync.setIsjson(true);
                sync.setJsonparm(datePicker4.getParam());
                sync.setModth(IUrlSync.POST);
                sync.setSyncTitle("凭证列表查询");
                sync.setToastContentFa("查询失败");

                syncURL();
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
