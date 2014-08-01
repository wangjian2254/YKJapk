package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.MXViewHolder;
import com.szht.htfsweb.adapter.holder.ZZViewHolder;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.MxItem;
import com.szht.htfsweb.model.ZzItem;

import java.util.List;

public class MxAdapter extends BaseAdapter {
    private Context mContext;
    List<Object> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    private String zb_style;
    private int screenWidth=0;

    public MxAdapter(Context c, List<Object> itemContent) {
        mContext = c;
        imgarrlist = itemContent;
        mLayoutInflater = LayoutInflater.from(mContext);
        screenWidth = ((ActivitySupport)c).getScreenWidth();
    }

    @Override
    public int getCount() {
        return imgarrlist.size()+1;
    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void initItemView(MXViewHolder holder,View localView){
        holder.txt0 = (TextView) localView.findViewById(R.id.txt0);
        holder.txt1 = (TextView) localView.findViewById(R.id.txt1);
        holder.txt2 = (TextView) localView.findViewById(R.id.txt2);
        holder.txt3 = (TextView) localView.findViewById(R.id.txt3);
        holder.txt4 = (TextView) localView.findViewById(R.id.txt4);
        holder.txt5 = (TextView) localView.findViewById(R.id.txt5);
        holder.pzbh = (TextView) localView.findViewById(R.id.pzbh);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MXViewHolder holder;

        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            holder = new MXViewHolder();
            holder.style = zb_style;
            //加载一级视图的布局文件
            if("J".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx, null);
                initItemView(holder,localView);
                if((holder.pzbh.getLayoutParams().width+holder.txt0.getLayoutParams().width+holder.txt1.getLayoutParams().width+holder.txt2.getLayoutParams().width+holder.txt3.getLayoutParams().width+holder.txt4.getLayoutParams().width+holder.txt5.getLayoutParams().width)<screenWidth){
                    holder.pzbh.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt0.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt1.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt2.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt3.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt4.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(40/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt5.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/540.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                }
            }
            if("S".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx_s, null);
                initItemView(holder,localView);
                holder.jsl = (TextView) localView.findViewById(R.id.jsl);
                holder.dsl = (TextView) localView.findViewById(R.id.dsl);
                holder.yesl = (TextView) localView.findViewById(R.id.yesl);
                holder.dj = (TextView) localView.findViewById(R.id.dj);
                holder.yedj = (TextView) localView.findViewById(R.id.yedj);

                if((holder.dj.getLayoutParams().width+holder.yedj.getLayoutParams().width+holder.jsl.getLayoutParams().width+holder.dsl.getLayoutParams().width+holder.yesl.getLayoutParams().width+holder.pzbh.getLayoutParams().width+holder.txt0.getLayoutParams().width+holder.txt1.getLayoutParams().width+holder.txt2.getLayoutParams().width+holder.txt3.getLayoutParams().width+holder.txt4.getLayoutParams().width+holder.txt5.getLayoutParams().width)<screenWidth){
                    holder.jsl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.dsl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yesl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.dj.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yedj.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));

                    holder.pzbh.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt0.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt1.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt2.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt3.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt4.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(40/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt5.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                }

            }
            if("W".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx_w, null);
                initItemView(holder,localView);
                holder.jwb = (TextView) localView.findViewById(R.id.jwb);
                holder.dwb = (TextView) localView.findViewById(R.id.dwb);
                holder.yewb = (TextView) localView.findViewById(R.id.yewb);
                holder.hl = (TextView) localView.findViewById(R.id.hl);
                holder.yehl = (TextView) localView.findViewById(R.id.yehl);

                if((holder.jwb.getLayoutParams().width+holder.dwb.getLayoutParams().width+holder.yewb.getLayoutParams().width+holder.hl.getLayoutParams().width+holder.yehl.getLayoutParams().width+holder.pzbh.getLayoutParams().width+holder.txt0.getLayoutParams().width+holder.txt1.getLayoutParams().width+holder.txt2.getLayoutParams().width+holder.txt3.getLayoutParams().width+holder.txt4.getLayoutParams().width+holder.txt5.getLayoutParams().width)<screenWidth){
                    holder.jwb.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.dwb.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yewb.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.hl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yehl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));

                    holder.pzbh.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt0.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt1.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt2.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt3.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt4.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(40/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt5.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/890.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                }
            }
            if("A".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx_a, null);
                initItemView(holder,localView);
                holder.jsl = (TextView) localView.findViewById(R.id.jsl);
                holder.dsl = (TextView) localView.findViewById(R.id.dsl);
                holder.yesl = (TextView) localView.findViewById(R.id.yesl);
                holder.jwb = (TextView) localView.findViewById(R.id.jwb);
                holder.dwb = (TextView) localView.findViewById(R.id.dwb);
                holder.yewb = (TextView) localView.findViewById(R.id.yewb);
                holder.dj = (TextView) localView.findViewById(R.id.dj);
                holder.yedj = (TextView) localView.findViewById(R.id.yedj);
                holder.hl = (TextView) localView.findViewById(R.id.hl);
                holder.yehl = (TextView) localView.findViewById(R.id.yehl);

                if((holder.dj.getLayoutParams().width+holder.yedj.getLayoutParams().width+holder.jsl.getLayoutParams().width+holder.dsl.getLayoutParams().width+holder.yesl.getLayoutParams().width+holder.jwb.getLayoutParams().width+holder.dwb.getLayoutParams().width+holder.yewb.getLayoutParams().width+holder.hl.getLayoutParams().width+holder.yehl.getLayoutParams().width+holder.pzbh.getLayoutParams().width+holder.txt0.getLayoutParams().width+holder.txt1.getLayoutParams().width+holder.txt2.getLayoutParams().width+holder.txt3.getLayoutParams().width+holder.txt4.getLayoutParams().width+holder.txt5.getLayoutParams().width)<screenWidth){
                    holder.jsl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.dsl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yesl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.dj.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yedj.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));

                    holder.jwb.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.dwb.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yewb.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.hl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.yehl.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));

                    holder.pzbh.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt0.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(70/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt1.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt2.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt3.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt4.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(40/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                    holder.txt5.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(90/1240.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                }
            }






            localView.setTag(holder);
        } else {
            holder = (MXViewHolder) localView.getTag();
        }


        if(position>0){
            MxItem item = (MxItem)imgarrlist.get(position-1);
            holder.txt0.setText(item.pzrq);
            holder.txt1.setText(item.zy);
            holder.txt2.setText(item.jfje);
            holder.txt3.setText(item.dfje);
            holder.txt4.setText(item.yefx);
            holder.txt5.setText(item.yeje);
            holder.pzbh.setText(item.pzbh);
            if("S".equals(holder.style)||"A".equals(holder.style)){
                holder.jsl.setText(item.jfsl);
                holder.dsl.setText(item.dfsl);
                holder.yesl.setText(item.yesl);
                holder.dj.setText(item.dj);
                holder.yedj.setText(item.yedj);
            }
            if("W".equals(holder.style)||"A".equals(holder.style)){
                holder.jwb.setText(item.jfwb);
                holder.dwb.setText(item.dfwb);
                holder.yewb.setText(item.yewb);
                holder.hl.setText(item.hl);
                holder.yehl.setText(item.yehl);
            }
            localView.setBackgroundResource(R.drawable.zt_list_item_selector);
        }else{
            holder.txt0.setText("凭证日期");
            holder.txt1.setText("摘要");
            holder.txt2.setText("借金额");
            holder.txt3.setText("贷金额");
            holder.txt4.setText("方向");
            holder.txt5.setText("余额");
            holder.pzbh.setText("凭证号");
            if("S".equals(holder.style)||"A".equals(holder.style)){
                holder.jsl.setText("数量");
                holder.dsl.setText("数量");
                holder.yesl.setText("数量");
                holder.dj.setText("单价");
                holder.yedj.setText("单价");
            }
            if("W".equals(holder.style)||"A".equals(holder.style)){
                holder.jwb.setText("外币");
                holder.dwb.setText("外币");
                holder.yewb.setText("外币");
                holder.hl.setText("汇率");
                holder.yehl.setText("汇率");
            }
            localView.setBackgroundResource(R.color.headback);
        }



        return localView;
    }


    public String getZb_style() {
        return zb_style;
    }

    public void setZb_style(String zb_style) {
        this.zb_style = zb_style;
    }
}
