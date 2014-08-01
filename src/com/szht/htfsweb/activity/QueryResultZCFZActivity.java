package com.szht.htfsweb.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.LinearLayout;
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
        int screenWidth=getScreenWidth();
        TextView txt0 = (TextView) headerView.findViewById(R.id.txt0);
        TextView txt1 = (TextView) headerView.findViewById(R.id.txt1);
        TextView txt2 = (TextView) headerView.findViewById(R.id.txt2);
        TextView txt3 = (TextView) headerView.findViewById(R.id.txt3);
        TextView txt4 = (TextView) headerView.findViewById(R.id.txt4);
        TextView txt5 = (TextView) headerView.findViewById(R.id.txt5);
        if((txt0.getLayoutParams().width+txt1.getLayoutParams().width+txt2.getLayoutParams().width+txt3.getLayoutParams().width+txt4.getLayoutParams().width+txt5.getLayoutParams().width)<screenWidth){
            txt0.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(180/680.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
            txt1.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(80/680.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
            txt2.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(80/680.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
            txt3.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(180/680.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
            txt4.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(80/680.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
            txt5.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(80/680.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
        }


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
