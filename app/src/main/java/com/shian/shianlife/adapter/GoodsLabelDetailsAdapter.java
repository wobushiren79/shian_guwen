package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsDetailsActivity;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsResultBean;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsLabelDetailsAdapter extends BaseRCAdapter<GoodsLabelDetailsResultBean> {
    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsLabelDetailsAdapter(Context context) {
        super(context, R.layout.item_goods_label_details_layout);
    }

    @Override
    public void convert(BaseViewHolder holder, final GoodsLabelDetailsResultBean data, int index) {
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvGoodsName = holder.getView(R.id.goods_name);
        TextView tvGoodsShowPrice = holder.getView(R.id.goods_show_price);
        TextView tvAdviserPriceTitle = holder.getView(R.id.tv_adviser_price_title);
        TextView tvAdviserPrice = holder.getView(R.id.tv_adviser_price);
        TextView tvSubmit = holder.getView(R.id.tv_submit);

//        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + data.getTitle_img(), R.drawable.zhy_pic_loading);
        Utils.loadPic(mContext, ivIcon,  data.getTitle_img(), R.drawable.zhy_pic_loading);

        //用户名设置
        StringBuffer goodsName = new StringBuffer();
        if (data.getName() != null)
            goodsName.append(data.getName());
        if (data.getGoods_slogan() != null)
            goodsName.append(data.getGoods_slogan());
        tvGoodsName.setText(goodsName.toString());

        //推荐价设置
        if (data.getPrice() != null)
            tvGoodsShowPrice.setText("推荐价：￥" + data.getPrice());

        //顾问价设置
        if (data.getAdviser_price() != null)
            tvAdviserPrice.setText("" + data.getAdviser_price());

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                intent.putExtra(IntentName.INTENT_GOODS_ID, data.getId());
                mContext.startActivity(intent);
            }
        });
    }
}
