package com.szht.htfsweb.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.CookieSyncManager;


import java.io.*;
import java.net.*;



public  class UrlTask {

	private IUrlSync urlSync=null;

    private Thread th2;
    private String pathName,appcode;
    private boolean stop=false;

    private String method=get;
    private Context context;
    private static String get = "GET";
    private static String post = "POST";


    public void handleResult(String result) {

    }

    public UrlTask(Context con) {
        this.context = con;
        th2 = new Thread() {
            public void run() {


                String httpUrl = Convert.hosturl+urlSync.getUri();


                HttpURLConnection con = null;
                InputStream inputStream = null;
                    try {
                        if(method.equals(get)){

                            httpUrl+=urlSync.getParmString();
                        }
                        con = (HttpURLConnection) (new URL(httpUrl)).openConnection();

                        setHeader(con);
                        con.setAllowUserInteraction(true);
                        // 设置连接超时时间为10000ms
                        con.setConnectTimeout(30000);
                        // 设置读取数据超时时间为10000ms
                        con.setReadTimeout(30000);
                        if(method.equals(post)){

                            con.setRequestMethod(post);
                            // http正文内，因此需要设为true
                            con.setDoOutput(true);
                            // Read from the connection. Default is true.
                            con.setDoInput(true);
                            // Post 请求不能使用缓存
                            con.setUseCaches(false);

                            con.setInstanceFollowRedirects(true);
                            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
                            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
                            // 进行编码
                            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                            con.connect();
                            DataOutputStream out = new DataOutputStream(con
                                    .getOutputStream());
                            // The URL-encoded contend
                            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致



                            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
                            out.writeBytes(urlSync.getParmString());

                            out.flush();
                            out.close();

                        }else{
                            con.setRequestMethod(get);
                            con.connect();
                        }


                        //判断http status是否为HTTP/1.1 206 Partial Content或者200 OK
                        //如果不是以上两种状态，把status改为STATUS_HTTPSTATUS_ERROR
                        if (con.getResponseCode() != HttpURLConnection.HTTP_OK
                                && con.getResponseCode() != HttpURLConnection.HTTP_PARTIAL) {

                            //失败
//                                failDownload(failStr);
                            con.disconnect();

                        }
                        if(httpUrl.indexOf(Convert.login)>-1){
                            if (con.getURL().toString().indexOf("jsessionid")>=-1){
                                String s=con.getURL().toString().substring(con.getURL().toString().indexOf(";")+1);
                                SharedPreferences p = context.getSharedPreferences(Convert.JSessionid,0);
                                // 保存在线连接信息
                                p.edit().putString(Convert.JSessionid, s.toUpperCase()).commit();
                            }
                        }

                        inputStream = con.getInputStream();
                        byte[] data = StreamTool.readInputStream(inputStream);
                        String json = new String(data);
                        urlSync.setResult(json);
                        urlSync.doResult();


                        try{
                            inputStream.close();
                            con.disconnect();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }




                    } catch (IOException e) {
                        if(con!=null){
                            con.disconnect();
                        }
                        try {
                            urlSync.doFailureResult();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }catch (Exception e) {
                        try {
                            urlSync.doFailureResult();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

            }
            };


    }

    public void start() {
        th2.start();
    }

    private void successDownload(){
        //下载游戏统计

    }

    private void failDownload(String msg){

    }





    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }


    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public boolean isStop() {
        return stop;
    }




    private void setHeader(URLConnection con) {
        con.setRequestProperty("User-Agent", "iphone2request");
        con.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
        con.setRequestProperty("Referer", "http://www.htykj.com.cn/login.jsp");
        SharedPreferences preference = context.getSharedPreferences(Convert.JSessionid,0);
        String sessionId =preference.getString(Convert.JSessionid,null);
        if(sessionId!=null){
            con.setRequestProperty("Cookie", sessionId);
        }


    }

//    String getCookie(Context context){
//        CookieSyncManager.createInstance(context);
//        CookieManager cookieManager = CookieManager.getInstance();
//        String        cookie = cookieManager.getCookie("cookie");
//        if(cookie != null){
//            return cookie;
//        }else{
//            cookie= “XXX”;
//            cookieManager.setCookie("cookie", cookie);
//            return cookie;
//        }
//    }
    public void setGet() {
        this.method = get;
    }
    public void setPost() {
        this.method = post;
    }

    public void setUrlSync(IUrlSync urlSync) {
        this.urlSync = urlSync;
    }
}
