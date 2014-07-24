package com.szht.htfsweb.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.XJLLAdapter;
import com.szht.htfsweb.adapter.ZCFZAdapter;
import com.szht.htfsweb.tools.DatePicker3DialogCustom;
import com.szht.htfsweb.tools.DatePickerDialogCustom;
import com.szht.htfsweb.util.QYSJArrayUtil;

public class QueryResultXJLLActivity extends QueryResultActivity  {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.queryresultxjll);

        querylist = (ListView)findViewById(R.id.querylist);
        lrbAdapter = new XJLLAdapter(context, list);
        headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.query_list_head_xjll,querylist,false);
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

    public void initBBHead(){
        if(sync.getParm().get("ybjbnb").equals("yb")){
            bbmonth.setText(sync.getParm().get("kjnd")+"年"+sync.getParm().get("kjqjs")+"月");
        }
        if(sync.getParm().get("ybjbnb").equals("jb")){
            bbmonth.setText(sync.getParm().get("kjnd")+"年"+(Integer.valueOf(sync.getParm().get("kjqjs"))*3-2)+"月~"+Integer.valueOf(sync.getParm().get("kjqjs"))*3+"月");
        }
        if(sync.getParm().get("ybjbnb").equals("nb")){
            bbmonth.setText(sync.getParm().get("kjnd")+"年");
        }

    }
}
