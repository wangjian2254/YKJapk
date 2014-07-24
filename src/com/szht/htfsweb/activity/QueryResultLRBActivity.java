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
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.QYSJArrayUtil;
import com.szht.htfsweb.util.UrlTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QueryResultLRBActivity extends QueryResultActivity  {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.queryresultlrb);

        querylist = (ListView)findViewById(R.id.querylist);
        lrbAdapter = new LRBAdapter(context, list);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head,querylist,false);
//        ztmc = (TextView)headerView.findViewById(R.id.querytxt);
//        if(ztmc==null){
//            ztmc = (TextView)findViewById(R.id.querytxt);
//        }
//        qymc = (TextView)headerView.findViewById(R.id.qymc);
//        if(qymc==null){
//            qymc = (TextView)findViewById(R.id.qymc);
//        }
//        qymc.setText(zt.ztmc);
//        bbmonth = (TextView)headerView.findViewById(R.id.bbmonth);
//        if(bbmonth==null){
//            bbmonth = (TextView)findViewById(R.id.bbmonth);
//        }
//        bbdw = (TextView)headerView.findViewById(R.id.bbdw);
//        if(bbdw==null){
//            bbdw = (TextView)findViewById(R.id.bbdw);
//        }
        initUI();
    }


}
