package com.szht.htfsweb.adapter.holder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.szht.htfsweb.db.ZtInfo;

public class ZtViewHolder implements OnClickListener {
    public TextView ztmc;
    public TextView qymc;
    public TextView kjzd;
    public TextView qysj;

    public ZtInfo item;
    public Context mContext;

    @Override
    public void onClick(View v) {
        delAddress(item);
    }

    public void delAddress(ZtInfo item) {
        TextView eDeleteW = new TextView(mContext);
        LinearLayout.LayoutParams eLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        eDeleteW.setPadding(10, 10, 10, 10);
        eDeleteW.setText("是否进入 \"" + item.ztmc + "\"");
        eDeleteW.setTextColor(Color.WHITE);
        eDeleteW.setTextSize(14);
        eDeleteW.setLayoutParams(eLayoutParam);


        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mContext);
        AlertDialog myDialog = builder.create();
        myDialog.setMessage(eDeleteW.getText());
        myDialog.setTitle("提示");
        myDialog.setCancelable(false);

        myDialog.setButton2("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        myDialog.setButton("进入",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        return;
                    }
                });
        myDialog.show();
    }
}
