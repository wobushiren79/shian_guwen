package com.shian.shianlife.adapter;

import android.content.Context;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListResultBean;

/**
 * Created by zm.
 */

public class IntegralListAdapter extends BaseRCAdapter<UserInfoIntegralListResultBean.Content> {

    /**
     * 单布局初始化
     *
     * @param context
     */
    public IntegralListAdapter(Context context) {
        super(context, R.layout.view_details_items);
    }

    @Override
    public void convert(BaseViewHolder holder, UserInfoIntegralListResultBean.Content data, int index) {
        TextView tvTime = holder.getView(R.id.tv_time);
        TextView tvPoint = holder.getView(R.id.tv_point);
        TextView tvTitle = holder.getView(R.id.tv_title);

        if (data.getCreated_at() != null)
            tvTime.setText(data.getCreated_at());
        if (data.getTrans_type() != null)
            tvTitle.setText(data.getTrans_type());
        if (data.getCredit_amount() != null)
            if (data.getCredit_amount() >= 0)
                tvPoint.setText("+" + data.getCredit_amount());
            else
                tvPoint.setText("-" + Math.abs(data.getCredit_amount()));
    }

}
