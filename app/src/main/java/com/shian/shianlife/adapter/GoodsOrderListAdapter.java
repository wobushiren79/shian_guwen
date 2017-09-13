package com.shian.shianlife.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsOrderPayActivity;
import com.shian.shianlife.activity.goods.GoodsOrderSubmitActivity;
import com.shian.shianlife.activity.goods.StoreOrderDetailsActivity;
import com.shian.shianlife.adapter.base.BaseRCAdapter;
import com.shian.shianlife.adapter.base.BaseViewHolder;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListResultBean;
import com.shian.shianlife.thisenum.GoodsOrderStatusEnum;
import com.shian.shianlife.thisenum.GoodsPayEnum;
import com.shian.shianlife.thisenum.GoodsServiceWayEnum;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsOrderListAdapter extends BaseRCAdapter<GoodsOrderListResultBean.Content> {


    /**
     * 单布局初始化
     *
     * @param context
     */
    public GoodsOrderListAdapter(Context context) {
        super(context, R.layout.item_goods_order_list);
    }

    @Override
    public void convert(BaseViewHolder holder, final GoodsOrderListResultBean.Content data, int index) {
        TextView tvOrderStatus = holder.getView(R.id.tv_order_status);
        TextView tvPayPrice = holder.getView(R.id.tv_pay_price);
        TextView tvServiceLocation = holder.getView(R.id.tv_service_location);
        TextView tvCustomerName = holder.getView(R.id.tv_customer_name);
        TextView tvServiceTime = holder.getView(R.id.tv_service_time);
        final LinearLayout llContent = holder.getView(R.id.ll_content);
        final LinearLayout llCustomerPhone = holder.getView(R.id.ll_customer_phone);
        final TextView tvOrderSubmit = holder.getView(R.id.tv_order_submit);
        final TextView tvOrderPay = holder.getView(R.id.tv_order_pay);
        final TextView tvOrderRemind = holder.getView(R.id.tv_order_remind);

        //价钱
        if (data.getTotal_price() != null)
            tvPayPrice.setText("￥" + (data.getTotal_price() / 100f));

        //订单状态
        String orderStatus = GoodsOrderStatusEnum.getValueText(data.getOrder_status());
        if (orderStatus != null)
            tvOrderStatus.setText(orderStatus);

        //服务地址
        if (data.getService_location() != null)
            tvServiceLocation.setText(data.getService_location());

        //客户名字
        if (data.getCustomer_name() != null)
            tvCustomerName.setText(data.getCustomer_name());

        //服务时间
        if (data.getService_way() != null)
            if (data.getService_way() == GoodsServiceWayEnum.now_service.getCode()) {
                tvServiceTime.setText(GoodsServiceWayEnum.now_service.getName());
            } else if (data.getService_way() == GoodsServiceWayEnum.plan_service.getCode()) {
                if (data.getBook_time() != null)
                    tvServiceTime.setText(data.getBook_time() + "进行服务");
            }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == llContent) {
                    clickDetails(data);
                }else if (v == tvOrderSubmit) {
                    clickSubmit(data);
                } else if (v == tvOrderPay) {
                    clickPay(data);
                } else if (v == tvOrderRemind) {

                }
            }
        };
        Utils.call(llCustomerPhone, data.getCustomer_phone());

        llContent.setOnClickListener(onClickListener);
        tvOrderSubmit.setOnClickListener(onClickListener);
        tvOrderPay.setOnClickListener(onClickListener);
        tvOrderRemind.setOnClickListener(onClickListener);

        tvOrderSubmit.setVisibility(View.GONE);
        tvOrderPay.setVisibility(View.GONE);
        tvOrderRemind.setVisibility(View.GONE);
        //支付功能
        if (data.getPayment_status() != null
                && data.getOrder_status() != null
                && data.getOrder_status() != GoodsOrderStatusEnum.waitpay.getCode()) {
            if (data.getPayment_status() == GoodsPayEnum.no_pay.getCode()) {
                tvOrderPay.setVisibility(View.VISIBLE);
            } else {
                tvOrderPay.setVisibility(View.GONE);
            }
        }

        //提交功能
        if (data.getOrder_status() != null) {
            if (data.getOrder_status() == GoodsOrderStatusEnum.waitpay.getCode()) {
                tvOrderSubmit.setVisibility(View.VISIBLE);
            } else {
                tvOrderSubmit.setVisibility(View.GONE);
            }
        }

    }

    /**
     * 支付
     *
     * @param data
     */
    private void clickPay(GoodsOrderListResultBean.Content data) {
        Intent intent = new Intent(mContext, GoodsOrderPayActivity.class);
        intent.putExtra(IntentName.INTENT_ORDERID, data.getId());
        mContext.startActivity(intent);
    }

    /**
     * 提交
     *
     * @param data
     */
    private void clickSubmit(GoodsOrderListResultBean.Content data) {
        Intent intent = new Intent(mContext, GoodsOrderSubmitActivity.class);
        intent.putExtra(IntentName.INTENT_ORDERID, data.getId());
        mContext.startActivity(intent);
    }

    /**
     * 订单详情
     *
     * @param data
     */
    private void clickDetails(GoodsOrderListResultBean.Content data) {
        Intent intent = new Intent(mContext, StoreOrderDetailsActivity.class);
        intent.putExtra(IntentName.INTENT_ORDERID, data.getId());
        mContext.startActivity(intent);
    }

}
