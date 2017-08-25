package com.shian.shianlife.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
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
    public void convert(BaseViewHolder holder, GoodsClassAttrMainResultBean goodsClassAttrMainResultBean, int index) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);

        if (goodsClassAttrMainResultBean.getPicUrl() == null) {
            Utils.loadPic(mContext, ivIcon, goodsClassAttrMainResultBean.getLocationPicId());
        } else {
            Utils.loadPic(mContext, ivIcon, goodsClassAttrMainResultBean.getPicUrl());
        }

        String name = goodsClassAttrMainResultBean.getClassAttrName();
        if (name != null && !name.isEmpty())
            tvName.setText(name);

    }
}
