package com.szht.htfsweb.tools;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.activeandroid.query.Select;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.KjkmAdapter;
import com.szht.htfsweb.db.BaseConfig;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;
import com.szht.htfsweb.widget.ArrayWheelAdapter;
import com.szht.htfsweb.widget.WheelView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DatePicker_PZList_DialogCustom extends Dialog
        implements View.OnClickListener {
//    private AlertDialogSelectListener selectListener;
    private AlertDialogCancelListener cancelListener;
    private Button ok,cancel;
    private TextView message;
    private EditText pzbhq;
    private EditText pzbhz;
    private AlertDialogOKListener okListener;
    private TextView view;
    private WheelView monthQ,monthQd,monthZ,monthZd;
    private String[] montharr;
    private String[] dayarr=null;
    private Spinner zb_style;
    private ZtInfo zt;
    private BaseConfig baseConfig;
//    private AdapterView.OnItemClickListener itemClickListener;


    public DatePicker_PZList_DialogCustom(Context paramContext) {
        super(paramContext, R.style.dialog);
    }

    public DatePicker_PZList_DialogCustom(Context paramContext, int paramInt) {
        super(paramContext, paramInt);
    }


    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        requestWindowFeature(1);
        setContentView(R.layout.date_picker_pzlist_dialog);
        DisplayMetrics metric = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(metric);
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.width = (int)(metric.density*320*0.95);
        getWindow().setAttributes(localLayoutParams);
        this.message = ((TextView) findViewById(R.id.message));

        this.ok = ((Button) findViewById(R.id.dialog_button_ok));
        this.cancel = ((Button) findViewById(R.id.dialog_button_cancel));
        this.view = ((TextView) findViewById(R.id.view));
        this.ok.setOnClickListener(this);
        this.cancel.setOnClickListener(this);

        setCanceledOnTouchOutside(false);
        initUI();
    }



    public void initUI(){
        monthQ = (WheelView) findViewById(R.id.monthQ);
        monthZ = (WheelView) findViewById(R.id.monthZ);
        monthQd = (WheelView) findViewById(R.id.monthQd);
        monthZd = (WheelView) findViewById(R.id.monthZd);
        zb_style = (Spinner) findViewById(R.id.zb_style);
        pzbhq = (EditText) findViewById(R.id.pzbhq);
        pzbhz = (EditText) findViewById(R.id.pzbhz);

/*
data : [['ALL', '全部'], ['YJZ', '已记账'],
										['YFH', '已审核'], ['WFH', '未审核'],
										['YBC', '已标错']]
 */
        String[] arr = new String[5];
        arr[0] = "全部";
        arr[1] = "已记账";
        arr[2] = "已审核";
        arr[3] = "未审核";
        arr[4] = "已标错";

        ArrayAdapter<String> zb_style_data = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, arr);
        zb_style_data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zb_style.setAdapter(zb_style_data);







        monthQ.setVisibleItems(3);
        monthZ.setVisibleItems(3);
        monthQ.setAdapter(new ArrayWheelAdapter<String>(montharr));
        monthZ.setAdapter(new ArrayWheelAdapter<String>(montharr));
        monthZ.setCurrentItem(montharr.length-1);
        monthQd.setVisibleItems(3);
        monthZd.setVisibleItems(3);
        monthQd.setAdapter(new ArrayWheelAdapter<String>(dayarr));
        monthZd.setAdapter(new ArrayWheelAdapter<String>(dayarr));
        monthZd.setCurrentItem(dayarr.length-1);
    }



    public void setMonthList(String[] monthList){
        montharr=monthList;
        dayarr = new String[31];
        dayarr[0]="01";
        dayarr[1]="02";
        dayarr[2]="03";
        dayarr[3]="04";
        dayarr[4]="05";
        dayarr[5]="06";
        dayarr[6]="07";
        dayarr[7]="08";
        dayarr[8]="09";
        dayarr[9]="10";
        dayarr[10]="11";
        dayarr[11]="12";
        dayarr[12]="13";
        dayarr[13]="14";
        dayarr[14]="15";
        dayarr[15]="16";
        dayarr[16]="17";
        dayarr[17]="18";
        dayarr[18]="19";
        dayarr[19]="20";
        dayarr[20]="21";
        dayarr[21]="22";
        dayarr[22]="23";
        dayarr[23]="24";
        dayarr[24]="25";
        dayarr[25]="26";
        dayarr[26]="27";
        dayarr[27]="28";
        dayarr[28]="29";
        dayarr[29]="30";
        dayarr[30]="31";

    }

    public String getQ(){
        return montharr[monthQ.getCurrentItem()]+dayarr[monthQd.getCurrentItem()];
    }


    public String getZ(){
        return montharr[monthZ.getCurrentItem()]+dayarr[monthZd.getCurrentItem()];
    }

    public String getZb_Style(){
        /*
        /*
data : [['ALL', '全部'], ['YJZ', '已记账'],
										['YFH', '已审核'], ['WFH', '未审核'],
										['YBC', '已标错']]
 */

        String s="ALL";
        switch (zb_style.getSelectedItemPosition()){
            case 0:
                s= "ALL";
                break;
            case 1:
                s= "YJZ";
                break;
            case 2:
                s= "YFH";
                break;
            case 3:
                s= "WFH";
                break;
            case 4:
                s= "YBC";
                break;
        }
        return s;
    }

    public String getParam(){
        /*
        {"pzrqStart":"",
        "pzrqEnd":"",
        "lxbh":"",
        "pzbhStart":"",
        "pzbhEnd":"",
        "kmbhStart":"",
        "kmbhEnd":"",
        "zy":"",
        "lybh":"",
        "jeStart":"",
        "jeEnd":"",
        "fzdms":["40288187402d3aec0140333f7f34077a"],
        "jzf":"",
        "fhf":"",
        "ccf":"",
        "limit":40}
         */
        JSONObject param = new JSONObject();
        try {
            param.put("pzrqStart",zt.qysj.substring(0,4)+getQ());
            param.put("pzrqEnd",zt.qysj.substring(0,4)+getZ());
            param.put("lxbh","");
            if(pzbhq.getText().toString().length()>0&&pzbhq.getText().toString().length()!=5){
                param.put("pzbhStart",("00000"+pzbhq.getText().toString()).substring(pzbhq.getText().toString().length()));
            }else{
                param.put("pzbhStart",pzbhq.getText().toString());
            }
            if(pzbhz.getText().toString().length()>0&&pzbhz.getText().toString().length()!=5){
                param.put("pzbhEnd",("00000"+pzbhz.getText().toString()).substring(pzbhz.getText().toString().length()));
            }else{
                param.put("pzbhEnd",pzbhq.getText().toString());
            }
            param.put("kmbhStart","");
            param.put("kmbhEnd","");
            param.put("zy","");
            param.put("lybh","");
            param.put("jeStart","");
            param.put("jeEnd","");
            JSONArray fzdm = new JSONArray();
            fzdm.put(zt.qyid);
            param.put("fzdms", fzdm);
            param.put("jzf","");
            param.put("ccf","");
            param.put("start","");
            param.put("limit","200");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return param.toString();
    }




    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.view:
                dismiss();
                break;
            case R.id.dialog_button_ok:
                dismiss();
                this.okListener.onOKClick();
                break;
            case R.id.dialog_button_cancel:
                dismiss();
                this.cancelListener.onCancelClick();
                break;


        }

    }


    public void setMessage(String paramString) {
        this.message.setText(paramString);
    }

//    public void setOnCancelListener(AlertDialogCancelListener paramAlertDialogCancelListener) {
//        this.cancelListener = paramAlertDialogCancelListener;
//
//    }



    public void setOnOKListener(AlertDialogOKListener paramAlertDialogOKListener) {
        this.okListener = paramAlertDialogOKListener;
    }

    public void setZt(ZtInfo zt) {
        this.zt = zt;
    }

    public void setBaseConfig(BaseConfig baseConfig) {
        this.baseConfig = baseConfig;
    }


    public void setOnCancelListener(AlertDialogCancelListener paramAlertDialogCancelListener) {
        this.cancelListener = paramAlertDialogCancelListener;
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams1.weight = 3.0F;
        this.ok.setLayoutParams(localLayoutParams1);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams2.weight = 5.0F;
        this.view.setLayoutParams(localLayoutParams2);
        LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams3.weight = 3.0F;
        this.cancel.setLayoutParams(localLayoutParams3);
    }

    public void setOnCancelListener(String paramString, AlertDialogCancelListener paramAlertDialogCancelListener) {
        this.cancel.setText(paramString);
        this.cancelListener = paramAlertDialogCancelListener;
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams1.weight = 3.0F;
        this.ok.setLayoutParams(localLayoutParams1);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams2.weight = 5.0F;
        this.view.setLayoutParams(localLayoutParams2);
        LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams3.weight = 3.0F;
        this.cancel.setLayoutParams(localLayoutParams3);
    }



    public void setOnOKListener(String paramString, AlertDialogOKListener paramAlertDialogOKListener) {
        this.ok.setText(paramString);
        this.okListener = paramAlertDialogOKListener;
    }

    public static abstract interface AlertDialogCancelListener {
        public abstract void onCancelClick();
    }

    public static abstract interface AlertDialogOKListener {
        public abstract void onOKClick();
    }
}
