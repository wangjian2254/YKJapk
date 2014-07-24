package com.szht.htfsweb;

/**
 * Created by WangJian on 14-3-6.
 */

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * 完整的退出应用.
 *
 * @author WangJian
 */
public class EimApplication extends com.activeandroid.app.Application {
    private List<Activity> activityList = new LinkedList<Activity>();

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}
