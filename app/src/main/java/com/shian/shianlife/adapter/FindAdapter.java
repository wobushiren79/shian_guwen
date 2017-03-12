package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/11.
 */

public class FindAdapter extends BaseAdapter {
    Context context;

    public FindAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
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
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_find_items, null);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tvCollection = (TextView) convertView.findViewById(R.id.tv_collection);
            holder.tvPraise = (TextView) convertView.findViewById(R.id.tv_praise);
            holder.ivCollection = (ImageView) convertView.findViewById(R.id.iv_collection);
            holder.ivPraise = (ImageView) convertView.findViewById(R.id.iv_praise);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   holder.ivCollection.setImageResource(R.drawable.zhy_find_collection_2);
            }
        });
        holder.ivPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivPraise.setImageResource(R.drawable.zhy_find_praise_2);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView ivPic;
        TextView tvTitle;
        TextView tvTime;

        TextView tvCollection;
        TextView tvPraise;
        ImageView ivCollection;
        ImageView ivPraise;
    }
}
