package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.ImagePreviewActivity;
import com.shian.shianlife.activity.PicShowActivity;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsResultBean;
import com.shian.shianlife.view.piccorner.RoundCornerImageView;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsPackageListAdapter extends BaseRCAdapter<GoodsDetailsResultBean.SpecGoods> {

    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsPackageListAdapter(Context context) {
        super(context, R.layout.item_goods_package);
    }

    @Override
    public void convert(BaseViewHolder holder, final GoodsDetailsResultBean.SpecGoods specGoods, final int index) {
        ImageView ivAdd = holder.getView(R.id.iv_add);
        RoundCornerImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvNumber = holder.getView(R.id.tv_number);
        TextView tvSpecName = holder.getView(R.id.tv_spec_name);
        if (index == 0) {
            ivAdd.setVisibility(View.GONE);
        } else {
            ivAdd.setVisibility(View.VISIBLE);
        }

        //设置名字
        if (specGoods.getName() != null)
            tvName.setText(specGoods.getName());
        if (specGoods.getSpec_name() != null)
            tvSpecName.setText(specGoods.getSpec_name());
        if (specGoods.getGoods_spec_number() != null)
            tvNumber.setText("x" + specGoods.getGoods_spec_number());
        //设置图片
//        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + specGoods.getTitle_img());
        Utils.loadPic(mContext, ivIcon,specGoods.getTitle_img());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePreviewActivity.class);
//                intent.putExtra(IntentName.INTENT_URL, AppContansts.Goods_PicUrl + "/" + specGoods.getTitle_img());
                intent.putExtra(IntentName.INTENT_URL, specGoods.getTitle_img());
                mContext.startActivity(intent);
            }
        });
    }
}
