package com.shian.shianlife.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsQueryActivity;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.CheckUtils;
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
    public void convert(BaseViewHolder holder, final GoodsClassAttrResultBean goodsClassAttrResultBean, int index) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);
        final LinearLayout llContent = holder.getView(R.id.ll_content);

        String name = goodsClassAttrResultBean.getName();
        String picUrl = goodsClassAttrResultBean.getTitle_img();
        if (!CheckUtils.isEmpty(name))
            tvName.setText(name);
        else
            tvName.setText("未知");

//        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + picUrl, R.drawable.zhy_pic_loading);
        Utils.loadPic(mContext, ivIcon,  picUrl, R.drawable.zhy_pic_loading);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == llContent) {
                    jumpQuery(goodsClassAttrResultBean.getApec_id(), goodsClassAttrResultBean.getId());
                }
            }
        };

        llContent.setOnClickListener(onClickListener);
    }


    private void jumpQuery(Long classId, Long classAttId) {
        Intent intent = new Intent(mContext, GoodsQueryActivity.class);
        intent.putExtra(IntentName.INTENT_CLASS_ID, classId);
        intent.putExtra(IntentName.INTENT_CLASSATTR_ID, classAttId);
        mContext.startActivity(intent);

        Activity activity = (Activity) mContext;
        activity.finish();
    }
}
