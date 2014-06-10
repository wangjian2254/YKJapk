package com.szht.htfsweb.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.XJLLAdapter;
import com.szht.htfsweb.adapter.ZCFZAdapter;

public class QueryResultZCFZActivity extends QueryResultLRBActivity  {
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
