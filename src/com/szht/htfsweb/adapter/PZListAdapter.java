package com.szht.htfsweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.szht.htfsweb.R;
import com.szht.htfsweb.adapter.holder.PZListViewHolder;
import com.szht.htfsweb.adapter.holder.ZZViewHolder;
import com.szht.htfsweb.base.ActivitySupport;
import com.szht.htfsweb.model.PzListItem;
import com.szht.htfsweb.model.ZzItem;

import java.util.List;

public class PZListAdapter extends BaseAdapter {
    private Context mContext;
    List<Object> imgarrlist;
    private LayoutInflater mLayoutInflater = null;
    private int screenWidth=0;

    public PZListAdapter(Context c, List<Object> itemContent) {
        mContext = c;
        imgarrlist = itemContent;
        mLayoutInflater = LayoutInflater.from(mContext);
        screenWidth = ((ActivitySupport)c).getScreenWidth();
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
        PZListViewHolder holder;
        PzListItem item = (PzListItem)imgarrlist.get(position);
        View localView = convertView;
        //判断当前view视图参数是否为null
        if (localView == null) {
            holder = new PZListViewHolder();
            //加载一级视图的布局文件

                localView = mLayoutInflater.inflate(R.layout.bb_list_item_pzlist, null);
                holder.zy = (TextView)localView.findViewById(R.id.pzlist_zy);
                holder.km = (TextView)localView.findViewById(R.id.pzlist_km);
                holder.jje = (TextView)localView.findViewById(R.id.pzlist_jje);
                holder.dje = (TextView)localView.findViewById(R.id.pzlist_dje);

                holder.pzinfo = (TextView)localView.findViewById(R.id.pzinfo);
                holder.linear_fl = (LinearLayout)localView.findViewById(R.id.linear_fl);
                holder.linear_pzinfo = (LinearLayout)localView.findViewById(R.id.linear_pzinfo);

            if((holder.zy.getLayoutParams().width+holder.km.getLayoutParams().width+holder.dje.getLayoutParams().width+holder.jje.getLayoutParams().width)<screenWidth){
                holder.zy.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(140/550.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                holder.km.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(170/550.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                holder.jje.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(120/550.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                holder.dje.setLayoutParams(new LinearLayout.LayoutParams((int)(screenWidth*(120/550.0)-0.5), ViewGroup.LayoutParams.WRAP_CONTENT));
                holder.pzinfo.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            }


            localView.setTag(holder);
        } else {
            holder = (PZListViewHolder) localView.getTag();
        }

        if(item.pzinfo==null){
            holder.linear_pzinfo.setVisibility(View.GONE);
            holder.linear_fl.setVisibility(View.VISIBLE);
            holder.zy.setText(item.zy);
            holder.km.setText(item.kminfo);
            holder.jje.setText(item.jje);
            holder.dje.setText(item.dje);
        }else{
            holder.linear_fl.setVisibility(View.GONE);
            holder.linear_pzinfo.setVisibility(View.VISIBLE);
            holder.pzinfo.setText(item.pzinfo);
        }


        return localView;
    }

}
