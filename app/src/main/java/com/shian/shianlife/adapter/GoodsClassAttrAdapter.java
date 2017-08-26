package com.shian.shianlife.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;

/**
 * Created by zm.
 */

public class GoodsClassAttrAdapter extends BaseRCAdapter<GoodsClassAttrResultBean> {
    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsClassAttrAdapter(Context context) {
        super(context, R.layout.item_goods_class_attr_list);
    }

    @Override
    public void convert(BaseViewHolder holder, GoodsClassAttrResultBean goodsClassAttrResultBean, int index) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);

        String name = goodsClassAttrResultBean.getName();
        String picUrl = goodsClassAttrResultBean.getTitle_img();
        if (!StringUtils.isEmpty(name))
            tvName.setText(name);
        else
            tvName.setText("未知");

        Utils.loadPic(mContext, ivIcon, picUrl);
    }
}
