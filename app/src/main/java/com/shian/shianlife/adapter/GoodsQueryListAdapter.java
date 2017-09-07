package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsDetailsActivity;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;
import com.shian.shianlife.thisenum.OrderByEnum;

import java.util.Collections;
import java.util.Comparator;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsQueryListAdapter extends BaseRCAdapter<GoodsQueryListResultBean> {
    public static final int Order_Form_Sale = 0;
    public static final int Order_Form_Price = 1;

    private Long goodsClassId;
    private Long goodsClassAttrId;

    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsQueryListAdapter(Context context) {
        super(context, R.layout.item_goods_query_list_layout);
    }

    public void setGoodsClassId(Long goodsClassId) {
        this.goodsClassId = goodsClassId;
    }

    public void setGoodsClassAttrId(Long goodsClassAttrId) {
        this.goodsClassAttrId = goodsClassAttrId;
    }

    @Override
    public void convert(BaseViewHolder holder, final GoodsQueryListResultBean goodsQueryListResultBean, final int index) {
        final LinearLayout llContent = holder.getView(R.id.ll_content);
        ImageView ivIcon = holder.getView(R.id.iv_icon);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvDetails = holder.getView(R.id.tv_details);
        TextView tvPrice = holder.getView(R.id.tv_price);
        TextView tvSale = holder.getView(R.id.tv_sale);

        Utils.loadPic(mContext, ivIcon, AppContansts.Goods_PicUrl + "/" + goodsQueryListResultBean.getTitle_img(), R.drawable.zhy_pic_loading);

        String name = goodsQueryListResultBean.getName();
        if (!CheckUtils.isEmpty(name))
            tvName.setText(name);

        String details = goodsQueryListResultBean.getGoods_slogan();
        if (!CheckUtils.isEmpty(details))
            tvDetails.setText(details);

        Float price = goodsQueryListResultBean.getPrice();
        if (!CheckUtils.isEmpty(price))
            tvPrice.setText("￥" + price);

        Integer sale = goodsQueryListResultBean.getSale_amount();
        tvSale.setText("销售量：" + sale);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == llContent) {
                    Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
                    intent.putExtra(IntentName.INTENT_GOODS_ID, goodsQueryListResultBean.getId());
                    intent.putExtra(IntentName.INTENT_CLASS_ID, goodsClassId);
                    intent.putExtra(IntentName.INTENT_CLASSATTR_ID, goodsClassAttrId);
                    mContext.startActivity(intent);
                }
            }
        };

        llContent.setOnClickListener(onClickListener);
    }

    public void orderBy(OrderByEnum orderType, int orderForm) {
        if (orderForm == Order_Form_Sale) {
            Collections.sort(mDatas, new SaleComparator(orderType));
        } else if (orderForm == Order_Form_Price) {
            Collections.sort(mDatas, new PriceComparator(orderType));
        }
        this.notifyDataSetChanged();
    }

    class SaleComparator implements Comparator<GoodsQueryListResultBean> {
        OrderByEnum orderType;

        public SaleComparator(OrderByEnum orderType) {
            this.orderType = orderType;
        }

        @Override
        public int compare(GoodsQueryListResultBean lhs, GoodsQueryListResultBean rhs) {
            Integer lhsSale = lhs.getSale_amount();
            Integer rhsSale = rhs.getSale_amount();
            if (orderType == OrderByEnum.DESC) {
                return rhsSale.compareTo(lhsSale);
            } else {
                return lhsSale.compareTo(rhsSale);
            }
        }
    }

    class PriceComparator implements Comparator<GoodsQueryListResultBean> {
        OrderByEnum orderType;

        public PriceComparator(OrderByEnum orderType) {
            this.orderType = orderType;
        }

        @Override
        public int compare(GoodsQueryListResultBean lhs, GoodsQueryListResultBean rhs) {
            Float lhsPrice = lhs.getPrice();
            Float rhsPrice = rhs.getPrice();
            if (orderType == OrderByEnum.DESC) {
                return rhsPrice.compareTo(lhsPrice);
            } else {
                return lhsPrice.compareTo(rhsPrice);
            }
        }
    }
}
