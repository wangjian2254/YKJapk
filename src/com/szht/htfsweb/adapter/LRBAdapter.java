package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.LRBViewHolder;
import com.szht.htfsweb.model.LRBItem;

import java.util.List;

public class LRBAdapter extends BaseAdapter {
    private Context mContext;
    List<Object> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    float DownX = 0;
    float UpX = 0;

    public LRBAdapter(Context c, List<Object> itemContent) {
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
        LRBViewHolder holder;
        LRBItem item = (LRBItem)imgarrlist.get(position);
        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            //加载一级视图的布局文件
            localView = mLayoutInflater.inflate(R.layout.bb_list_item_lrb, null);

            holder = new LRBViewHolder();
            holder.txt0 = (TextView) localView.findViewById(R.id.txt0);
            holder.txt1 = (TextView) localView.findViewById(R.id.txt1);
            holder.txt2 = (TextView) localView.findViewById(R.id.txt2);

            localView.setTag(holder);
        } else {
            holder = (LRBViewHolder) localView.getTag();
        }

        holder.txt0.setText(item.getXm());
        holder.txt1.setText(item.getBqje());
        holder.txt2.setText(item.getSqje());
//        holder.ztmc.setText(item.getZtmc());
//        holder.kjzd.setText(item.getKjzd());
//        holder.qymc.setText(item.getQymc());
//        holder.qysj.setText(item.getQysj());

//        holder.item = item;


        return localView;
    }


}
