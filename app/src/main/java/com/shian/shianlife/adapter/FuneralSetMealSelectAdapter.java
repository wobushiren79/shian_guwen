package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.model.AddedCtgModel;
import com.shian.shianlife.provide.model.ProductItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class FuneralSetMealSelectAdapter extends BaseAdapter {

    Context context;
    private List<ProductItemModel> productItems = new ArrayList<>();


    public FuneralSetMealSelectAdapter(Context context, List<ProductItemModel> productItems) {
        this.context = context;
        this.productItems = productItems;
    }

    @Override
    public int getCount() {
        return productItems.size();
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
        FuneralSetMealSelectAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_setmealselect_item, null);
            viewHolder = new FuneralSetMealSelectAdapter.ViewHolder();
            viewHolder.select = (CheckBox) convertView.findViewById(R.id.cb_check);
            viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FuneralSetMealSelectAdapter.ViewHolder) convertView.getTag();
        }
        final ProductItemModel data = productItems.get(position);
        viewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data.setCheck(isChecked);
                notifyDataSetChanged();
            }
        });
        viewHolder.select.setChecked(data.isCheck());
        viewHolder.content.setText(data.getName());

        return convertView;
    }

    class ViewHolder {
        CheckBox select;
        TextView content;
    }
}

