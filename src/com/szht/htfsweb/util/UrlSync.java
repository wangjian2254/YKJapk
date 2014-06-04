package com.szht.htfsweb.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlSync implements IUrlSync {
	private int connectNum;
	public final    String uri="";
	private Context mainContext;
	private Context pluginContext;
	private String pluginActionStr;
	private String modth=GET;
	private String urlparam="";
	private List<NameValuePair> prarm=null;
	private String resultType=null;
	private String syncType=INFOSEND;
	private String appcode="";
	private String code=null;
	private boolean isNotice=false;
	private boolean needNotice=true;//是否设定
	
	private boolean isSync=false;
	
	private String syncTitle="";

	private String noticeTitle="";
	private String noticeContent="";
	private String noticeCode="";
	
	private String toastContentSu=null;
	private String toastContentFa=null;
	private boolean isToast=true;
	
	private String result=null;
	private String userinfoparam=null;
	
	private Handler handler=null;
	private JSONObject jsonobj=null;
	
	private UserInfo user;

    private Map<String,String> parm = new HashMap<String,String>();
    public  void addParm(String k,String v){
        parm.put(k,v);
    }

    public String getParmString(){
        StringBuffer p=null;
        if(GET.equals(modth)){
            if (getUri().indexOf("?")<0){
                p=new StringBuffer("?");
            }else{
                p=new StringBuffer();
            }

        }else{
            p=new StringBuffer();
        }
        for(String k :parm.keySet()){
            p.append(k);
            p.append("=");
            try {
                p.append(URLEncoder.encode(parm.get(k), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            p.append("&");
        }
        return p.toString();
    }

	// 通知列表
	

	public void setSync(){
		this.isSync=true;
	}
	@Override
	public String getResult() {
		return result;
	}
	@Override
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public boolean isGet(){
		if(GET.equals(this.modth)){
			return true;
		}
		return false;
	}

    public String getUri() {
        return uri;
    }


    @Override
	public String getModth() {
		return modth;
	}
	@Override
	public void setModth(String modth) {
		this.modth = modth;
	}

	
	@Override
	public String getResultType() {
		return resultType;
	}
	@Override
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	@Override
	public String getSyncType() {
		return syncType;
	}
	@Override
	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}

	
	@Override
	public String getCode() {
		return code;
	}
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	
	
	public UrlSync() {
	}
	@Override
	public boolean isNotice() {
		return isNotice;
	}
	@Override
	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}
	
	@Override
	public String getNoticeTitle() {
		return noticeTitle;
	}
	@Override
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	@Override
	public String getNoticeContent() {
		return noticeContent;
	}
	@Override
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	@Override
	public String getUrlparam() {
		return urlparam;
	}
	@Override
	public void setUrlparam(String urlparam) {
		if(urlparam==null){
			return;
		}
		this.urlparam = urlparam;
	}
	@Override
	public String getNoticeCode() {
		return noticeCode;
	}
	@Override
	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	@Override
	public String getToastContentSu() {
		return toastContentSu;
	}
	@Override
	public void setToastContentSu(String toastContentSu) {
		this.toastContentSu = toastContentSu;
	}
	@Override
	public String getToastContentFa() {
		return toastContentFa;
	}
	@Override
	public void setToastContentFa(String toastContentFa) {
		this.toastContentFa = toastContentFa;
	}
	
	@Override
	public boolean isToast() {
		return isToast;
	}
	@Override
	public void setToast(boolean isToast) {
		this.isToast = isToast;
	}
	@Override
	public Handler getHandler() {
		return handler;
	}
	@Override
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	@Override
	public List<NameValuePair> getPrarm() {
		return prarm;
	}
	@Override
	public void setPrarm(List<NameValuePair> prarm) {
		this.prarm = prarm;
	}

	@Override
	public boolean isNeedNotice() {
		return needNotice;
	}
	@Override
	public void setNeedNotice(boolean needNotice) {
		this.needNotice = needNotice;
	}

	@Override
	public void doResult() throws Exception {

	}


	@Override
	public String getAppcode() {
		return appcode;
	}
	@Override
	public void setAppcode(String appcode) {
		this.appcode = appcode;
	}

	@Override
	public void setMainContext(Context context) {
		this.mainContext=context;
	}
	@Override
	public Context getMainContext() {
		return this.mainContext;
	}

	@Override
	public void setPluginContext(Context context) {
		this.pluginContext=context;
	}
	@Override
	public Context getPluginContext() {
		return this.pluginContext;
	}
	@Override
	public String getUserinfoparam() {
		return userinfoparam;
	}
	@Override
	public void setUserinfoparam(String userinfoparam) {
		this.userinfoparam = userinfoparam;
	}
	@Override
	public String getPluginActionStr() {
		return pluginActionStr;
	}
	@Override
	public void setPluginActionStr(String pluginActionStr) {
		this.pluginActionStr = pluginActionStr;
	}
	@Override
	public void doFailureResult() throws Exception {
	}
	
	public boolean isNeedConnect(){
		if(this.connectNum>3){
			return false;
		}else{
			return true;
		}
	}
	public void addConnectNum(){
		this.connectNum++;
	}
	
	public boolean doPerResult()throws Exception{
		jsonobj=new JSONObject(getResult());
		int status=jsonobj.getInt("status");
		if(status==200){
			return true;
		}else{
			if(getHandler()!=null){
				
				Message hmsg=getHandler().obtainMessage();
				hmsg.arg1=404;
				hmsg.obj=jsonobj.getString("message");
				Log.e("login_error", jsonobj.getString("message"));
				
				getHandler().sendMessage(hmsg);
				setHandler(null);
			}
			return false;
		}
	}
	public JSONObject getJsonobj() {
		return jsonobj;
	}
	public void setJsonobj(JSONObject jsonobj) {
		this.jsonobj = jsonobj;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}


    public String getSyncTitle() {
        return syncTitle;
    }

    public void setSyncTitle(String syncTitle) {
        this.syncTitle = syncTitle;
    }
}
