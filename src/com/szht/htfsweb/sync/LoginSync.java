package com.szht.htfsweb.sync;

import android.os.Message;
import com.szht.htfsweb.util.UrlSync;

public class LoginSync extends UrlSync {
    private      String uri="j_spring_security_check";


    public void doResult() throws Exception {
        if (!"true".equals(getResult())) {
            return;
        }else{
            if (getHandler() != null) {
                Message hmsg = getHandler().obtainMessage();
                hmsg.obj = "成功登录";
                hmsg.arg1 = 1;
                getHandler().sendMessage(hmsg);
                setHandler(null);
            }
        }

    }

    public void doFailureResult() throws Exception {
        if (getHandler() != null) {
            Message hmsg = getHandler().obtainMessage();
            hmsg.obj = getToastContentFa();
            hmsg.arg1 = 2;
            hmsg.sendToTarget();
            setHandler(null);
        }
    }

    @Override
    public String getUri() {
        return uri;
    }
}
