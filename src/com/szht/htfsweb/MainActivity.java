package com.szht.htfsweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.activeandroid.query.Select;
import com.szht.htfsweb.activity.QueryActivity;
import com.szht.htfsweb.adapter.ZtAdapter;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.sync.BaseConfigSync;
import com.szht.htfsweb.sync.SelectZtSync;
import com.szht.htfsweb.sync.ZtAllSync;
import com.szht.htfsweb.tools.YWDatePickerDialogCustom;
import com.szht.htfsweb.util.*;
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
    List<ZtInfo> ztArrayList =new ArrayList<ZtInfo>();
    ZtInfo selectzt=null;
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
        if(Convert.currentUser==null){
            Intent mainIntent = new Intent(this, Welcome.class);

            this.startActivity(mainIntent);
            finish();
            return;
        }
        ztArrayList = new Select().from(ZtInfo.class).where("User = ?",Convert.currentUser.getId()).execute();
        ztAdapter = new ZtAdapter(context, ztArrayList);
        View headerView = ((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.zt_list_head,ztlist,false);
        ztlist.addHeaderView(headerView);
        ztlist.setAdapter(ztAdapter);
        //账套列表的点击事件
        ztlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // qyid，ztdm，ywrq


                selectzt = ztArrayList.get(i-1);
                final YWDatePickerDialogCustom localAlertDialogCustom = new YWDatePickerDialogCustom(context);
                String[] y=new String[1];

                y[0]=selectzt.qysj.substring(0,4);
                String[] m= QYSJArrayUtil.getMonthStrArr(selectzt.qysj);
                localAlertDialogCustom.setYearList(y);
                localAlertDialogCustom.setMonthList(m);
                localAlertDialogCustom.show();
                localAlertDialogCustom.setMessage("选择业务日期");
                //点击账套 产生的 日期选择
                localAlertDialogCustom.setOnOKListener("确定", new YWDatePickerDialogCustom.AlertDialogOKListener() {

                    @Override
                    public void onOKClick() {

                        Convert.currentYear = localAlertDialogCustom.getSelectedYear();
                        Convert.currentMonth = localAlertDialogCustom.getSelectedMonth();
                        Convert.currentDay = localAlertDialogCustom.getSelectedDay();

                        IUrlSync urlSync = new SelectZtSync();
                        urlSync.setSyncTitle("选择账套");
                        urlSync.setModth(IUrlSync.POST);
                        urlSync.addParm("qyid", selectzt.qyid);
                        urlSync.addParm("ztid",selectzt.ztdm);
                        urlSync.addParm("ywrq",Convert.currentYear+Convert.currentMonth+Convert.currentDay);
                        urlSync.setToastContentFa("没有账套");
                        urlSync.setHandler(ztHandler);
                        UrlTask urlTask = new UrlTask(context);
                        urlTask.setUrlSync(urlSync);
                        urlTask.start();
                    }
                });
                localAlertDialogCustom.setOnCancelListener("取消",new YWDatePickerDialogCustom.AlertDialogCancelListener(){


                    @Override
                    public void onCancelClick() {

                    }
                });

//                showToast(ztArrayList.get(i-1).getZtmc(),3000);
            }
        });

        //账套相关的异步处理
        ztHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                // // 接收子线程的消息
                // 刷新账套列表
                if (msg.arg1 == 1) {
                    List<ZtInfo> m = (List)msg.obj;
                    ztArrayList.clear();
                    ztArrayList.addAll(m);
                    ztAdapter.notifyDataSetChanged();

                }
                //请求失败，按照失败信息显示给用户。
                if (msg.arg1 == 2) {

                    showToast(msg.obj.toString());
                }
                //选择了账套，发出获取账套系统信息
                if(msg.arg1 == 3){
                    BaseConfigSync urlSync = new BaseConfigSync();
                    urlSync.setSyncTitle("获取账套系统信息");
                    urlSync.setZt(selectzt);
                    urlSync.setToastContentFa("没有账套");
                    urlSync.setHandler(ztHandler);
                    UrlTask urlTask = new UrlTask(context);
                    urlTask.setUrlSync(urlSync);
                    urlTask.start();
                }
                //获取账套系统信息成功，跳转界面至查询主界面
                if(msg.arg1 == 4){
                    Intent mainIntent = new Intent(MainActivity.this, QueryActivity.class);
                    Bundle extras = new Bundle();
                    extras.putSerializable("zt", selectzt);
                    mainIntent.putExtras(extras);
                    MainActivity.this.startActivity(mainIntent);
                }




            }

        };

        syncZtList();
    }

    /**
     * 同步账套列表，一般在进入系统后，如果没有缓存账套列表，则显示loading界面，否则后台默认运行请求
     */
    private void syncZtList(){
        IUrlSync urlSync = new ZtAllSync();
        if(ztArrayList.size()==0){
            urlSync.setSyncTitle("获取账套信息");
        }

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

    /**
     * 自定义菜单
     * @param menu
     */
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

    /**
     * 定义菜单的处理逻辑
     * @param item
     * @return
     */
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
