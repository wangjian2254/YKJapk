package com.szht.htfsweb.tools;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.widget.ArrayWheelAdapter;
import com.szht.htfsweb.widget.OnWheelChangedListener;
import com.szht.htfsweb.widget.WheelView;

public class YWDatePickerDialogCustom extends Dialog
        implements View.OnClickListener {
    private Button cancel;
    private AlertDialogCancelListener cancelListener;
    private TextView message;
    private Button ok;
    private AlertDialogOKListener okListener;
    private TextView view;
    private WheelView year,month,day;
    private String[] yeararr,montharr;

    public YWDatePickerDialogCustom(Context paramContext) {
        super(paramContext, R.style.dialog);

    }

    public YWDatePickerDialogCustom(Context paramContext, int paramInt) {
        super(paramContext, paramInt);
    }

    public void initUI(){
        year = (WheelView) findViewById(R.id.year);
        month = (WheelView) findViewById(R.id.month);
        day = (WheelView) findViewById(R.id.day);

        year.setVisibleItems(3);
        month.setVisibleItems(3);
        day.setVisibleItems(3);
        year.setAdapter(new ArrayWheelAdapter<String>(yeararr));
        month.setAdapter(new ArrayWheelAdapter<String>(montharr));
        day.setAdapter(new ArrayWheelAdapter<String>(initDays()));
        month.addChangingListener(new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String[] dayarr=initDays();

                day.setAdapter(new ArrayWheelAdapter<String>(dayarr));
            }
        });



    }

    private String[] initDays(){
        String[] dayarr=null;
        int m=Integer.valueOf(getSelectedMonth());
        int y = Integer.valueOf(getSelectedYear());

        boolean r=false;
        if(y%100==0){
            if(y%400==0){
                r=true;
            }
        }else{
            if(y%4==0){
                r=true;
            }
        }
        if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
            dayarr=new String[31];
            for(int i=1;i<=31;i++){
                if(i<10){
                    dayarr[i-1]="0"+i;
                }else{
                    dayarr[i-1]=""+i;
                }
            }
        }else if(m==2){
            if(r){
                dayarr=new String[29];
                for(int i=1;i<=29;i++){
                    if(i<10){
                        dayarr[i-1]="0"+i;
                    }else{
                        dayarr[i-1]=""+i;
                    }
                }
            }else{
                dayarr=new String[28];
                for(int i=1;i<=28;i++){
                    if(i<10){
                        dayarr[i-1]="0"+i;
                    }else{
                        dayarr[i-1]=""+i;
                    }
                }
            }


        }else{
            dayarr=new String[30];
            for(int i=1;i<=30;i++){
                if(i<10){
                    dayarr[i-1]="0"+i;
                }else{
                    dayarr[i-1]=""+i;
                }
            }
        }
        return dayarr;
    }
    public void setYearList(String[] yearList){
        yeararr=yearList;

    }
    public void setMonthList(String[] monthList){
        montharr=monthList;

    }

    public String getSelectedYear(){
        return yeararr[year.getCurrentItem()];
    }
    public String getSelectedMonth(){
        return montharr[month.getCurrentItem()];
    }
    public String getSelectedDay(){
        if(day.getCurrentItem()<9){
            return "0"+day.getCurrentItem();
        }
        return ""+day.getCurrentItem();
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

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        requestWindowFeature(1);
        setContentView(R.layout.select_date_picker_dialog);
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

    public void setMessage(String paramString) {
        this.message.setText(paramString);
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

    public void setOnOKListener(AlertDialogOKListener paramAlertDialogOKListener) {
        this.okListener = paramAlertDialogOKListener;
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
