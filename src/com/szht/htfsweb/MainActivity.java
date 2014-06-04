package com.szht.htfsweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.szht.htfsweb.activity.QueryActivity;
import com.szht.htfsweb.adapter.ZtAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.Zt;
import com.szht.htfsweb.sync.ZtAllSync;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.UrlSync;
import com.szht.htfsweb.util.UrlTask;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActivitySupport {
    /**
     * Called when the activity is first created.
     */

    public static final int SEARCHPLUGIN = Menu.FIRST + 1;
    public static final int APPLIST = Menu.FIRST + 2;
    public static final int EXIT = Menu.FIRST + 3;

    ListView ztlist;
    ZtAdapter ztAdapter;
    List<Zt> ztArrayList =new ArrayList<Zt>();
    private Handler ztHandler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.onError(this);
        UmengUpdateAgent.update(this);
        FeedbackAgent agent = new FeedbackAgent(context);
        agent.sync();
        setContentView(R.layout.main);
        ztlist = (ListView)findViewById(R.id.ztlist);
        ztAdapter = new ZtAdapter(context, ztArrayList);
        View headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.zt_list_head,ztlist,false);
        ztlist.addHeaderView(headerView);
        ztlist.setAdapter(ztAdapter);
        ztlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Zt zt = ztArrayList.get(i-1);
                Intent mainIntent = new Intent(MainActivity.this, QueryActivity.class);
                Bundle extras = new Bundle();
                extras.putSerializable("zt", zt);
                mainIntent.putExtras(extras);
                MainActivity.this.startActivity(mainIntent);

//                showToast(ztArrayList.get(i-1).getZtmc(),3000);
            }
        });

        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                if (msg.arg1 == 1) {
                    List<Zt> m = (List)msg.obj;

                    ztArrayList.addAll(m);
                    ztAdapter.notifyDataSetChanged();

                }
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }




            }

        };
        syncZtList();
    }

    private void syncZtList(){
        IUrlSync urlSync = new ZtAllSync();
        urlSync.setSyncTitle("获取账套信息");
        urlSync.setToastContentFa("没有账套");
        urlSync.setHandler(ztHandler);
        UrlTask urlTask = new UrlTask(context);
        urlTask.setUrlSync(urlSync);
        urlTask.start();
//        showToast("正在获取账套列表……");
    }



    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        populateMenu(menu);
    }

    public void populateMenu(Menu menu) {
        menu.add(Menu.NONE, SEARCHPLUGIN, Menu.NONE, "联系我们");
        menu.add(Menu.NONE, APPLIST, Menu.NONE, "重新登录");
        menu.add(Menu.NONE, EXIT, Menu.NONE, "退出");
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
                FeedbackAgent agent = new FeedbackAgent(context);
                agent.startFeedbackActivity();
                return true;
            case APPLIST:
                Intent mainIntent = new Intent(this, Welcome.class);

                this.startActivity(mainIntent);
                return true;

            case EXIT:
                eimApplication.exit();
                return true;
        }
        return false;
    }
}
