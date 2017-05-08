package com.shian.shianlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.shian.shianlife.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class MapSearchAdapter extends RecyclerView.Adapter<MapSearchAdapter.ViewHolder> {
    List<PoiInfo> searchListData = new ArrayList<>();
    Context context;

    CallBack callBack;

    public MapSearchAdapter(Context context, List<PoiInfo> searchListData) {
        this.searchListData = searchListData;
        this.context = context;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.view_map_data, null);
        view.setLayoutParams(layoutParams);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PoiInfo data = searchListData.get(position);
        holder.tvName.setText(data.name);
        holder.tvContent.setText(data.address);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null)
                    callBack.onItemClick(data);
            }
        });

    }


    @Override
    public int getItemCount() {
        return searchListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    public interface CallBack {
        void onItemClick(PoiInfo data);
    }
}
