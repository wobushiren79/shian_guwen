package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.AnimUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderInfoPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderInfoPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderInfoView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderPayActivity extends BaseActivity implements IGoodsOrderInfoView, View.OnClickListener {
    @InjectView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @InjectView(R.id.tv_pay_price)
    TextView tvPayPrice;
    @InjectView(R.id.ll_content)
    LinearLayout llContent;
    @InjectView(R.id.tv_pay_wechat)
    TextView tvPayWechat;
    @InjectView(R.id.tv_pay_offline)
    TextView tvPayOffline;

    private Long orderId;
    private IGoodsOrderInfoPresenter goodsOrderInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_pay);
        ButterKnife.inject(this);
        initView();
        initData();
    }


    private void initView() {
        setTitle("支付");
        llContent.setVisibility(View.GONE);

    }

    private void initData() {
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
        goodsOrderInfoPresenter = new GoodsOrderInfoPresenterImpl(this);
        goodsOrderInfoPresenter.getGoodsOrderInfo();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void getGoodsOrderInfoSuccess(GoodsOrderInfoResultBean resultBean) {
        llContent.setVisibility(View.VISIBLE);
        llContent.setOnClickListener(this);
        tvPayWechat.setOnClickListener(this);
        tvPayOffline.setOnClickListener(this);
        AnimUtils.showPriceAnim(llContent, 500, null);
    }

    @Override
    public void getGoodsOrderInfoFail(String msg) {

    }

    @Override
    public Long getOrderId() {
        return orderId;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        tvOrderNumber.setText(orderNumber);
    }

    @Override
    public void setAdviserPrice(Integer price) {

    }

    @Override
    public void setCoupon(Integer price) {

    }

    @Override
    public void setFreight(Integer price) {

    }

    @Override
    public void setIntegral(Integer price) {

    }

    @Override
    public void setPayPrice(Integer price) {
        tvPayPrice.setText("￥" + price);
    }


    @Override
    public void onClick(View v) {
        if (v == tvPayWechat) {

        } else if (v == tvPayOffline) {

        } else if (v == llContent) {
            AnimUtils.showPriceAnimShake(llContent, 500, null);
        }
    }
}
