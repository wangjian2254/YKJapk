package com.szht.htfsweb.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.activeandroid.query.Select;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.ZzAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.db.BaseConfig;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.sync.ZzSync;
import com.szht.htfsweb.tools.DatePicker_KJKM_DialogCustom;
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
    public LinearLayout j_head,s_head,w_head,a_head;
    public  IUrlSync sync;
    public BaseAdapter baseAdapter;
    public View headerView;
    public List<Object> list = new ArrayList<Object>();
    public static final int SEARCHPLUGIN = Menu.FIRST + 1;
    public static final int APPLIST = Menu.FIRST + 2;

    protected String zb_style;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.queryresultzz);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head_zz,querylist,false);


    }
    public void initUI(){
        intent = this.getIntent();
        Bundle bunde = intent.getExtras();
        querylist = (ListView)findViewById(R.id.querylist);
        zt = (ZtInfo)bunde.getSerializable("zt");

        j_head = (LinearLayout)headerView.findViewById(R.id.j_head);
        s_head = (LinearLayout)headerView.findViewById(R.id.s_head);
        w_head = (LinearLayout)headerView.findViewById(R.id.w_head);
        a_head = (LinearLayout)headerView.findViewById(R.id.a_head);
        bbmc = (TextView)findViewById(R.id.querytitle);

        kjkm = (TextView)findViewById(R.id.kjkm);
        kjqj = (TextView)findViewById(R.id.kjqj);

        querylist.addHeaderView(headerView);
        querylist.setAdapter(baseAdapter);

        sync.setHandler(ztHandler);
        syncURL();
    }

    protected void setHead(String style){
        if("J".equals(style)){
            j_head.setVisibility(View.VISIBLE);
            s_head.setVisibility(View.GONE);
            w_head.setVisibility(View.GONE);
            a_head.setVisibility(View.GONE);
        }
        if("S".equals(style)){
            s_head.setVisibility(View.VISIBLE);
            j_head.setVisibility(View.GONE);
            w_head.setVisibility(View.GONE);
            a_head.setVisibility(View.GONE);
        }
        if("W".equals(style)){
            w_head.setVisibility(View.VISIBLE);
            s_head.setVisibility(View.GONE);
            j_head.setVisibility(View.GONE);
            a_head.setVisibility(View.GONE);
        }
        if("A".equals(style)){
            a_head.setVisibility(View.VISIBLE);
            s_head.setVisibility(View.GONE);
            w_head.setVisibility(View.GONE);
            j_head.setVisibility(View.GONE);
        }
    }

     public void initHead(String style){

        setHead(style);
        ZzAdapter zzAdapter  = new ZzAdapter(context, list);
        zzAdapter.setZb_style(style);
        querylist.setAdapter(zzAdapter);
        baseAdapter = zzAdapter;
    }

    protected void syncURL(){
        initHead(zb_style);
        sync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(sync);
        urlTask.start();
    }




    protected void onQuery(View v) {
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
