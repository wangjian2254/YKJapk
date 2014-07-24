package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.KjkmViewHolder;
import com.szht.htfsweb.adapter.holder.ZtViewHolder;
import com.szht.htfsweb.db.Kjkm;
import com.szht.htfsweb.db.ZtInfo;

import java.util.List;

public class KjkmAdapter extends BaseAdapter {
    private Context mContext;
    List<Kjkm> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    float DownX = 0;
    float UpX = 0;

    public KjkmAdapter(Context c, List<Kjkm> itemContent) {
        mContext = c;
        imgarrlist = itemContent;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return imgarrlist.size();
    }

    public Kjkm getContactsItem(int position) {
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
        KjkmViewHolder holder;
        Kjkm item = imgarrlist.get(position);
        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            //加载一级视图的布局文件
            localView = mLayoutInflater.inflate(R.layout.kjkm_list_item, null);

            holder = new KjkmViewHolder();
            holder.text = (TextView) localView.findViewById(R.id.kmqc);
            localView.setTag(holder);
        } else {
            holder = (KjkmViewHolder) localView.getTag();
        }
        holder.text.setText(item.text);
        holder.item = item;
        return localView;
    }


}
