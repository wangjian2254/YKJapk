package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.MXViewHolder;
import com.szht.htfsweb.adapter.holder.ZZViewHolder;
import com.szht.htfsweb.model.MxItem;
import com.szht.htfsweb.model.ZzItem;

import java.util.List;

public class MxAdapter extends BaseAdapter {
    private Context mContext;
    List<Object> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    private String zb_style;

    public MxAdapter(Context c, List<Object> itemContent) {
        mContext = c;
        imgarrlist = itemContent;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return imgarrlist.size();
    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MXViewHolder holder;
        MxItem item = (MxItem)imgarrlist.get(position);
        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            holder = new MXViewHolder();
            holder.style = zb_style;
            //加载一级视图的布局文件
            if("J".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx, null);
            }
            if("S".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx_s, null);
                holder.jsl = (TextView) localView.findViewById(R.id.jsl);
                holder.dsl = (TextView) localView.findViewById(R.id.dsl);
                holder.yesl = (TextView) localView.findViewById(R.id.yesl);
                holder.dj = (TextView) localView.findViewById(R.id.dj);
                holder.yedj = (TextView) localView.findViewById(R.id.yedj);
            }
            if("W".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx_w, null);
                holder.jwb = (TextView) localView.findViewById(R.id.jwb);
                holder.dwb = (TextView) localView.findViewById(R.id.dwb);
                holder.yewb = (TextView) localView.findViewById(R.id.yewb);
                holder.hl = (TextView) localView.findViewById(R.id.hl);
                holder.yehl = (TextView) localView.findViewById(R.id.yehl);
            }
            if("A".equals(zb_style)){
                localView = mLayoutInflater.inflate(R.layout.bb_list_item_mx_a, null);
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
            }



            holder.txt0 = (TextView) localView.findViewById(R.id.txt0);
            holder.txt1 = (TextView) localView.findViewById(R.id.txt1);
            holder.txt2 = (TextView) localView.findViewById(R.id.txt2);
            holder.txt3 = (TextView) localView.findViewById(R.id.txt3);
            holder.txt4 = (TextView) localView.findViewById(R.id.txt4);
            holder.txt5 = (TextView) localView.findViewById(R.id.txt5);
            holder.pzbh = (TextView) localView.findViewById(R.id.pzbh);

            localView.setTag(holder);
        } else {
            holder = (MXViewHolder) localView.getTag();
        }

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


        return localView;
    }


    public String getZb_style() {
        return zb_style;
    }

    public void setZb_style(String zb_style) {
        this.zb_style = zb_style;
    }
}
