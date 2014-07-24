package com.szht.htfsweb.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.XJLLAdapter;
import com.szht.htfsweb.adapter.ZCFZAdapter;
import com.szht.htfsweb.tools.DatePicker3DialogCustom;
import com.szht.htfsweb.util.QYSJArrayUtil;

public class QueryResultZCFZActivity extends QueryResultActivity  {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.queryresultzcfz);

        querylist = (ListView)findViewById(R.id.querylist);
        lrbAdapter = new ZCFZAdapter(context, list);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head_zcfz,querylist,false);


        initUI();
    }





    public void onQuery(View v) {
        final DatePicker3DialogCustom localAlertDialogCustom = new DatePicker3DialogCustom(context);
        String[] y={zt.qysj.substring(0,4)};
        String[] m= QYSJArrayUtil.getMonthStrArr(zt.qysj);
        localAlertDialogCustom.setYearList(y);
        localAlertDialogCustom.setMonthList(m);
        localAlertDialogCustom.show();
        localAlertDialogCustom.setMessage("选择查询月份");
        localAlertDialogCustom.setOnOKListener("查询", new DatePicker3DialogCustom.AlertDialogOKListener() {

            @Override
            public void onOKClick() {


                sync.addParm("kjnd",localAlertDialogCustom.getSelectedYear());
                sync.addParm("kjqjs",localAlertDialogCustom.getSelectedMonth());
                sync.addParm("ybjbnb",localAlertDialogCustom.getYbJbNb());

                syncURL();
            }
        });
        localAlertDialogCustom.setOnCancelListener("取消", new DatePicker3DialogCustom.AlertDialogCancelListener() {

            @Override
            public void onCancelClick() {

            }
        });

    }

}
