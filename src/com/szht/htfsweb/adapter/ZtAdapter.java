package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.ZtViewHolder;
import com.szht.htfsweb.model.Zt;

import java.util.List;

public class ZtAdapter extends BaseAdapter {
    private Context mContext;
    List<Zt> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    float DownX = 0;
    float UpX = 0;

    public ZtAdapter(Context c, List<Zt> itemContent) {
        mContext = c;
        imgarrlist = itemContent;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return imgarrlist.size();
    }

    public Zt getContactsItem(int position) {
        return imgarrlist.get(position);
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
        ZtViewHolder holder;
        Zt item = imgarrlist.get(position);
        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            //加载一级视图的布局文件
            localView = mLayoutInflater.inflate(R.layout.zt_list_item, null);

            holder = new ZtViewHolder();
            holder.ztmc = (TextView) localView.findViewById(R.id.ztmc);
//            holder.kjzd = (TextView) localView.findViewById(R.id.kjzd);
            holder.qysj = (TextView) localView.findViewById(R.id.qysj);
//            holder.qymc = (TextView) localView.findViewById(R.id.qymc);

            localView.setTag(holder);
        } else {
            holder = (ZtViewHolder) localView.getTag();
        }


        holder.ztmc.setText(item.getZtmc());
//        holder.kjzd.setText(item.getKjzd());
//        holder.qymc.setText(item.getQymc());
        holder.qysj.setText(item.getQysj());

        holder.item = item;


        return localView;
    }


}
