package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.StoreServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderInfoPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderSubmitPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderInfoPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderSubmitPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderInfoView;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderSubmitView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderSubmitActivity extends BaseActivity implements IGoodsOrderSubmitView, IGoodsOrderInfoView, View.OnClickListener {

    @InjectView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @InjectView(R.id.tv_adviser_price)
    TextView tvAdviserPrice;
    @InjectView(R.id.tv_coupon_price)
    TextView tvCouponPrice;
    @InjectView(R.id.tv_freight_price)
    TextView tvFreightPrice;
    @InjectView(R.id.tv_integral)
    TextView tvIntegral;
    @InjectView(R.id.tv_pay_price)
    TextView tvPayPrice;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;

    private Long orderId;

    private IGoodsOrderSubmitPresenter goodsOrderSubmitPresenter;
    private IGoodsOrderInfoPresenter goodsOrderInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_submit);
        ButterKnife.inject(this);
        initView();
        initData();
    }


    private void initView() {
        setTitle("提交订单");
    }

    private void initData() {
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
        goodsOrderSubmitPresenter = new GoodsOrderSubmitPresenterImpl(this);
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
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void getGoodsOrderInfoFail(String msg) {
        ToastUtils.show(this, msg);
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
        tvAdviserPrice.setText("￥" + (price / 100f));
    }

    @Override
    public void setCoupon(Integer price) {
        tvCouponPrice.setText("-￥" + (price / 100f));
    }

    @Override
    public void setFreight(Integer price) {
        tvFreightPrice.setText("￥" + (price / 100f));
    }

    @Override
    public void setIntegral(Integer price) {
        tvIntegral.setText(price + "分");
    }

    @Override
    public void setPayPrice(Integer price) {
        tvPayPrice.setText("￥" + (price / 100f));
    }

    @Override
    public void submitGoodsOrderSuccess(GoodsOrderSubmitResultBean resultBean) {
        ToastUtils.show(this, "提交成功");
        StoreServiceActivity.isNeedRefresh = true;
        Intent intent = new Intent(this, GoodsOrderPayActivity.class);
        intent.putExtra(IntentName.INTENT_ORDERID, orderId);
        startActivity(intent);
        finish();
    }

    @Override
    public void submitGoodsOrderFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void onClick(View v) {
        goodsOrderSubmitPresenter.submitGoodsOrder();
    }
}
