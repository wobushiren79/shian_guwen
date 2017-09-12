package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shian.shianlife.R;
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

public class GoodsOrderSubmitActivity extends BaseActivity implements IGoodsOrderSubmitView, IGoodsOrderInfoView {

    private Long orderId;

    private IGoodsOrderSubmitPresenter goodsOrderSubmitPresenter;
    private IGoodsOrderInfoPresenter goodsOrderInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_submit);
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
    public void submitGoodsOrderSuccess(GoodsOrderSubmitResultBean resultBean) {
        ToastUtils.show(this, "提交成功");
        finish();
    }

    @Override
    public void submitGoodsOrderFail(String msg) {
        ToastUtils.show(this, msg);
    }
}
