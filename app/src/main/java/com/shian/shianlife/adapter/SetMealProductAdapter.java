package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shian.shianlife.R;

/**
 * Created by Administrator on 2017/3/21.
 */

public class SetMealProductAdapter extends BaseAdapter {
    Context context;

    public SetMealProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_setmeal_product_item, null);
        }
        return convertView;
    }

    class ViewHolder {

    }

}
