package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsClassAttrDetailsActivity;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsClassAttrMainAdapter extends BaseRCAdapter<GoodsClassAttrMainResultBean> {
    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsClassAttrMainAdapter(Context context) {
        super(context, R.layout.item_goods_class_attr_main_list);
    }

    @Override
    public void convert(BaseViewHolder holder, final GoodsClassAttrMainResultBean data, int index) {
        LinearLayout llContent = holder.getView(R.id.ll_content);
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);

        if (data.getPicId() != null)
            Utils.loadPic(mContext, ivIcon, data.getPicId());

        if (!data.getName().isEmpty())
            tvName.setText(data.getName());

        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsClassAttrDetailsActivity.class);
                intent.putExtra(IntentName.INTENT_CLASS_ID, data.getId());
                mContext.startActivity(intent);
            }
        });
    }


}
