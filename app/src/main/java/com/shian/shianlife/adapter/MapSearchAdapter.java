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

    public MapSearchAdapter(Context context, List<PoiInfo> searchListData) {
        this.searchListData = searchListData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_map_data, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PoiInfo data = searchListData.get(position);
        holder.tvName.setText(data.address);
    }

    @Override
    public int getItemCount() {
        return searchListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
