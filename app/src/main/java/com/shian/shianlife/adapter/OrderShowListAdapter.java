package com.shian.shianlife.adapter;

import android.content.Context;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;



/**
 * Created by zm.
 */

public class OrderShowListAdapter extends BaseRCAdapter<OrderShowResultBean.Item> {

    /**
     * 单布局初始化
     *
     * @param context
     */
    public OrderShowListAdapter(Context context) {
        super(context, R.layout.layout_order_show);
    }


    @Override
    public void convert(BaseViewHolder holder, OrderShowResultBean.Item orderShowItemBean, int index) {
        View view = holder.itemView;
        TextView tvTitle = holder.getView(R.id.tv_title);
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        if (orderShowItemBean.getName() != null)
            tvTitle.setText(orderShowItemBean.getName());
        if (orderShowItemBean.getPicId() != 0)
            ivIcon.setImageResource(orderShowItemBean.getPicId());
    }
}
