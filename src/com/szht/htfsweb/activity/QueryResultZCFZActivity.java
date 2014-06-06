package com.szht.htfsweb.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.ListView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.ZCFZAdapter;

public class QueryResultZCFZActivity extends QueryResultLRBActivity  {
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

}
