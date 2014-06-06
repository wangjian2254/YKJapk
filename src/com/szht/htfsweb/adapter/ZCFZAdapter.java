package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.LRBViewHolder;
import com.szht.htfsweb.adapter.holder.ZCFZViewHolder;
import com.szht.htfsweb.model.LRBItem;
import com.szht.htfsweb.model.ZCFZItem;

import java.util.List;

public class ZCFZAdapter extends BaseAdapter {
    private Context mContext;
    List<Object> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    float DownX = 0;
    float UpX = 0;

    public ZCFZAdapter(Context c, List<Object> itemContent) {
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
        ZCFZViewHolder holder;
        ZCFZItem item = (ZCFZItem)imgarrlist.get(position);
        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            //加载一级视图的布局文件
            localView = mLayoutInflater.inflate(R.layout.bb_list_item_zcfz, null);

            holder = new ZCFZViewHolder();
            holder.txt0 = (TextView) localView.findViewById(R.id.txt0);
            holder.txt1 = (TextView) localView.findViewById(R.id.txt1);
            holder.txt2 = (TextView) localView.findViewById(R.id.txt2);
            holder.txt3 = (TextView) localView.findViewById(R.id.txt3);
            holder.txt4 = (TextView) localView.findViewById(R.id.txt4);
            holder.txt5 = (TextView) localView.findViewById(R.id.txt5);

            localView.setTag(holder);
        } else {
            holder = (ZCFZViewHolder) localView.getTag();
        }

        holder.txt0.setText(item.getZc());
        holder.txt1.setText(item.getQmye1());
        holder.txt2.setText(item.getNcye1());
        holder.txt3.setText(item.getFz());
        holder.txt4.setText(item.getQmye2());
        holder.txt5.setText(item.getNcye2());
//        holder.ztmc.setText(item.getZtmc());
//        holder.kjzd.setText(item.getKjzd());
//        holder.qymc.setText(item.getQymc());
//        holder.qysj.setText(item.getQysj());

//        holder.item = item;


        return localView;
    }


}
