package com.szht.htfsweb.tools;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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
import com.szht.htfsweb.util.Convert;
import com.szht.htfsweb.widget.ArrayWheelAdapter;
import com.szht.htfsweb.widget.WheelView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DatePicker_KJKM_DialogCustom extends Dialog
        implements View.OnClickListener {
    private AlertDialogSelectListener selectListener;
    private TextView message;
    private AlertDialogOKListener okListener;
    private TextView view;
    private WheelView monthQ,monthZ;
    private String[] montharr;
    private ListView kjkm;
    private Spinner zb_style;
    private CheckBox wjz;
    private ZtInfo zt;
    private BaseConfig baseConfig;
    private List<Kjkm> listkjkm;
//    private AdapterView.OnItemClickListener itemClickListener;


    public DatePicker_KJKM_DialogCustom(Context paramContext) {
        super(paramContext, R.style.dialog);
    }

    public DatePicker_KJKM_DialogCustom(Context paramContext, int paramInt) {
        super(paramContext, paramInt);
    }


    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        requestWindowFeature(1);
        setContentView(R.layout.date_picker_kjkm_dialog);
        Display localDisplay = getWindow().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.width = (int) (0.95D * localDisplay.getWidth());
        getWindow().setAttributes(localLayoutParams);
        this.message = ((TextView) findViewById(R.id.message));

        this.view = ((TextView) findViewById(R.id.view));

        setCanceledOnTouchOutside(false);
        initUI();
    }

    public void setKjkmSelected(AlertDialogSelectListener clickListener){
        selectListener = clickListener;

    }

    public void initUI(){
        monthQ = (WheelView) findViewById(R.id.monthQ);
        monthZ = (WheelView) findViewById(R.id.monthZ);
        zb_style = (Spinner) findViewById(R.id.zb_style);


        String[] arr = new String[4];
        arr[0] = "金额";
        arr[1] = "金额数量";
        arr[2] = "金额外币";
        arr[3] = "金额数量外币";

        ArrayAdapter<String> zb_style_data = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, arr);
        zb_style_data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zb_style.setAdapter(zb_style_data);

        kjkm = (ListView) findViewById(R.id.kjkmlist);
        listkjkm = new Select().from(Kjkm.class).where("ztdm = ?", zt.ztdm).execute();
        KjkmAdapter adapter = new KjkmAdapter(this.getContext(), listkjkm);
        kjkm.setAdapter(adapter);
        kjkm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                selectListener.onSelectedClick(listkjkm.get(position));
            }
        });

        wjz = (CheckBox)findViewById(R.id.cb_wjz);




        monthQ.setVisibleItems(3);
        monthZ.setVisibleItems(3);
        monthQ.setAdapter(new ArrayWheelAdapter<String>(montharr));
        monthZ.setAdapter(new ArrayWheelAdapter<String>(montharr));
    }



    public void setMonthList(String[] monthList){
        montharr=monthList;
    }

    public String getQ(){
        return montharr[monthQ.getCurrentItem()];
    }


    public String getZ(){
        return montharr[monthZ.getCurrentItem()];
    }

    public String getZb_Style(){
        String s="J";
        switch (zb_style.getSelectedItemPosition()){
            case 0:
                s= "J";
                break;
            case 1:
                s= "S";
                break;
            case 2:
                s= "W";
                break;
            case 3:
                s= "A";
                break;
        }
        return s;
    }

    public String getParam(String kmbh){
        /*
        var zzParams = {
				kjnd : this.ui.ZongZhangCxForm.ui.kjndComb.getValue(),// 会计年度
				kjqjQ : this.ui.ZongZhangCxForm.ui.kjqjqComb.getValue(),// 会计区间起
				kjqjZ : this.ui.ZongZhangCxForm.ui.kjqjzComb.getValue(),// 会计区间止
				ztdm : g.user.bmgz.BMGZ_BMZD.ztdm,// 账套代码
				kmbh : this.temp_kmbh(this.ui.ZongZhangCxForm), // 科目编号
				bmgz : g.user.bmgz.BMGZ_KMZD.bmgz, // 编码规则
				jejd : g.user.xtcs.XTCS_WBJEJD.val, // 金额精度
				sljd : g.user.xtcs.XTCS_SLJD.val, // 数量精度
				djjd : g.user.xtcs.XTCS_DJJD.val, // 单价精度
				hljd : g.user.xtcs.XTCS_HLJD.val, // 汇率精度
				fzdm : this.temp_fzdm(this.ui.ZongZhangCxForm), // 分支代码
				jzbz : this.temp_jzbz(this.ui.ZongZhangCxForm),// 记账标志
				zbxs : this.ui.ZongZhangCxForm.ui.styleComb.getValue(), // 账薄显示格式
				qymc : g.user.qymc
				// 企业名称
			};
         */
        JSONObject param = new JSONObject();
        try {
            param.put("kjnd",zt.qysj.substring(0,4));
            param.put("kjqjQ",getQ());
            param.put("kjqjZ",getZ());
            param.put("ztdm",zt.ztdm);
            param.put("kmbh",kmbh);
            param.put("bmgz",baseConfig.getBMGZ());
            param.put("jejd",baseConfig.getJEJD());
            param.put("sljd",baseConfig.getSLJD());
            param.put("djjd",baseConfig.getDJJD());
            param.put("hljd",baseConfig.getHLJD());
            JSONArray fzdm = new JSONArray();
            fzdm.put(zt.qyid);
            param.put("fzdm", fzdm);
            param.put("jzbz",wjz.isSelected()?"Y":"N");
            param.put("zbxs",getZb_Style());
            param.put("qymc",zt.qymc);
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
//                this.cancelListener.onCancelClick();
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

    public static abstract interface AlertDialogSelectListener {
        public abstract void onSelectedClick(Kjkm kjkm);
    }

    public static abstract interface AlertDialogCancelListener {
        public abstract void onCancelClick();
    }

    public static abstract interface AlertDialogOKListener {
        public abstract void onOKClick();
    }
}
