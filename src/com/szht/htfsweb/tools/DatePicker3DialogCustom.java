package com.szht.htfsweb.tools;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.widget.ArrayWheelAdapter;
import com.szht.htfsweb.widget.WheelView;

public class DatePicker3DialogCustom extends Dialog
        implements View.OnClickListener {
    private Button cancel;
    private AlertDialogCancelListener cancelListener;
    private TextView message;
    private Button ok;
    private AlertDialogOKListener okListener;
    private TextView view;
    private WheelView year,month;
    private RadioButton yb,jb,nb;
    private String[] yeararr,montharr,jarr;

    public DatePicker3DialogCustom(Context paramContext) {
        super(paramContext, R.style.dialog);
    }

    public DatePicker3DialogCustom(Context paramContext, int paramInt) {
        super(paramContext, paramInt);
    }

    public void initUI(){
        year = (WheelView) findViewById(R.id.year);
        month = (WheelView) findViewById(R.id.month);

        yb = (RadioButton)findViewById(R.id.yb);
        yb.setOnClickListener(this);
        jb = (RadioButton)findViewById(R.id.jb);
        jb.setOnClickListener(this);
        nb = (RadioButton)findViewById(R.id.nb);
        nb.setOnClickListener(this);


        year.setVisibleItems(3);
        month.setVisibleItems(3);
        year.setAdapter(new ArrayWheelAdapter<String>(yeararr));
        month.setAdapter(new ArrayWheelAdapter<String>(montharr));
    }

    public void changeQueryType(View view){
        switch (view.getId()){
            case R.id.yb:
                month.setVisibleItems(View.VISIBLE);
                month.setAdapter(new ArrayWheelAdapter<String>(montharr));
                month.setVisibleItems(3);
                break;
            case R.id.jb:
                month.setVisibleItems(View.VISIBLE);
                month.setAdapter(new ArrayWheelAdapter<String>(jarr));
                month.setVisibleItems(3);
                break;
            case R.id.nb:
                month.setVisibleItems(View.GONE);
                break;

        }
    }

    public void setYearList(String[] yearList){
        yeararr=yearList;

    }
    public void setMonthList(String[] monthList){
        montharr=monthList;

        if(monthList.length<=3){
            jarr = new String[1];
            jarr[0]="第四季度";
        }else if(monthList.length<=6){
            jarr = new String[2];
            jarr[0]="第三季度";
            jarr[1]="第四季度";
        }else if(monthList.length<=9){
            jarr = new String[3];
            jarr[0]="第二季度";
            jarr[1]="第三季度";
            jarr[2]="第四季度";
        }else{
            jarr = new String[4];
            jarr[0]="第一季度";
            jarr[1]="第二季度";
            jarr[2]="第三季度";
            jarr[3]="第四季度";
        }

    }

    public String getSelectedYear(){
        return yeararr[year.getCurrentItem()];
    }
    public String getSelectedMonth(){
        if(jb.isChecked()){
            return String.valueOf(month.getCurrentItem()+1);
        }else{
            return montharr[month.getCurrentItem()];
        }
    }
    public String getYbJbNb(){
        if(yb.isChecked()){
            return "yb";
        }else if(jb.isChecked()){
            return "jb";
        }else {
            return "nb";
        }
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
            case R.id.yb:
                changeQueryType(paramView);
                break;
            case R.id.jb:
                changeQueryType(paramView);
                break;
            case R.id.nb:
                changeQueryType(paramView);
                break;

        }

    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        requestWindowFeature(1);
        setContentView(R.layout.date_picker3_dialog);
        Display localDisplay = getWindow().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.width = (int) (0.95D * localDisplay.getWidth());
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
