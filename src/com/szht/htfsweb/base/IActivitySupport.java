package com.szht.htfsweb.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import com.szht.htfsweb.EimApplication;

/**
 * Created by WangJian on 14-3-6.
 */
public interface IActivitySupport {
    public abstract EimApplication getEimApplication();


    /**
     * 校验网络-如果没有网络就弹出设置,并返回true.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:03:56
     */
    public abstract boolean validateInternet();

    /**
     * 校验网络-如果没有网络就返回true.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:05:15
     */
    public abstract boolean hasInternetConnected();

    /**
     * 退出应用.
     *
     * @author shimiso
     * @update 2012-7-6 上午9:06:42
     */
    public abstract void isExit();

    /**
     * 判断GPS是否已经开启.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:04:07
     */
    public abstract boolean hasLocationGPS();

    /**
     * 判断基站是否已经开启.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:07:34
     */
    public abstract boolean hasLocationNetWork();

    /**
     * 检查内存卡.
     *
     * @author shimiso
     * @update 2012-7-6 上午9:07:51
     */
    public abstract void checkMemoryCard();

    /**
     * 显示toast.
     *
     * @param text    内容
     * @param longint 内容显示多长时间
     * @author shimiso
     * @update 2012-7-6 上午9:12:02
     */
    public abstract void showToast(String text, int longint);

    /**
     * 短时间显示toast.
     *
     * @param text
     * @author shimiso
     * @update 2012-7-6 上午9:12:46
     */
    public abstract void showToast(String text);

    /**
     * 获取进度条.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:14:38
     */
    public abstract ProgressDialog getProgressDialog();

    /**
     * 返回当前Activity上下文.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:19:54
     */
    public abstract Context getContext();

    /**
     * 获取当前登录用户的SharedPreferences配置.
     *
     * @return
     * @author shimiso
     * @update 2012-7-6 上午9:23:02
     */
    public SharedPreferences getLoginUserSharedPre();

    /**
     * 发出Notification的method.
     *
     * @param iconId       图标
     * @param contentTitle 标题
     * @param contentText  你内容
     * @param activity
     * @author shimiso
     * @update 2012-5-14 下午12:01:55
     */
    public void setNotiType(int iconId, String contentTitle,
                            String contentText, Class activity, String from);
}
