package com.szht.htfsweb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.sync.LoginSync;
import com.szht.htfsweb.util.Convert;
import com.szht.htfsweb.util.IUrlSync;
import com.szht.htfsweb.util.UrlSync;
import com.szht.htfsweb.util.UrlTask;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

public class Welcome extends ActivitySupport {
    /**
     * Called when the activity is first created.
     */

    private EditText username,password;
    private Handler loginHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.onError(this);
        UmengUpdateAgent.update(this);

        setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);

        loginHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                Object m = msg.obj;

                showToast(m.toString(),3000);
                // // 接收子线程的消息
                if (msg.arg1 == 1) {
                    Intent mainIntent = new Intent(Welcome.this, MainActivity.class);
                    Welcome.this.startActivity(mainIntent);


                }
                if (msg.arg1 == 2) {


                }




            }

        };
    }

    public void login(View view){
        if(username.getText().toString().length()==0||password.getText().toString().length()==0){
            showToast("请填写用户名和密码",3000);
        }else{
            SharedPreferences p = context.getSharedPreferences(Convert.JSessionid,0);
            // 保存在线连接信息
            p.edit().clear().commit();
            IUrlSync urlSync = new LoginSync();
            urlSync.setSyncTitle("登录验证");
            urlSync.addParm("j_username",username.getText().toString());
            urlSync.addParm("j_password",password.getText().toString());
            urlSync.setHandler(loginHandler);
            urlSync.setToastContentFa("账号密码错误");
            UrlTask urlTask = new UrlTask(context);
            urlTask.setUrlSync(urlSync);
            urlTask.start();

//            showToast("正在登录……",3000);
        }
    }
}
