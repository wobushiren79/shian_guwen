package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.StoreServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.AnimUtils;
import com.shian.shianlife.common.utils.PayUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderInfoPresenter;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderOfflinePayPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderInfoPresenterImpl;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderOfflinePayPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderInfoView;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderOfflinePayView;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayResultBean;
import com.shian.shianlife.mvp.pay.presenter.IWeChatPrePayPresenter;
import com.shian.shianlife.mvp.pay.presenter.impl.WeChatPrePayPresenter;
import com.shian.shianlife.mvp.pay.view.IWeChatPrePayView;
import com.summerxia.dateselector.utils.DateUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderPayActivity extends BaseActivity implements IGoodsOrderInfoView, View.OnClickListener, IGoodsOrderOfflinePayView, IWeChatPrePayView {
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
    private IGoodsOrderOfflinePayPresenter goodsOrderOfflinePayPresenter;
    private IWeChatPrePayPresenter weChatPrePayPresenter;

    private Integer payPrice;
    private boolean isPayWeChat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_pay);
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
        goodsOrderOfflinePayPresenter = new GoodsOrderOfflinePayPresenterImpl(this);
        weChatPrePayPresenter = new WeChatPrePayPresenter(this);

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
        ToastUtils.show(this, msg);
    }

    @Override
    public void payOfflineSuccess(GoodsOrderOfflinePayResultBean resultBean) {
        StoreServiceActivity.isNeedRefresh = true;
        Intent intent = new Intent(this, GoodsOrderPayCallBackActivity.class);
        intent.putExtra(IntentName.INTENT_ISTURE, true);
        startActivity(intent);
        finish();
    }

    @Override
    public void payOfflineFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public Long getOrderId() {
        return orderId;
    }

    @Override
    public Integer getPayPrice() {
        return payPrice;
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
        payPrice = price;
        tvPayPrice.setText("￥" + (price / 100f));
    }


    @Override
    public void onClick(View v) {
        if (v == tvPayWechat) {
            payWechat();
        } else if (v == tvPayOffline) {
            payOffline();
        } else if (v == llContent) {
            AnimUtils.showPriceAnimShake(llContent, 500, null);
        }
    }

    /**
     * 微信支付
     */
    private void payWechat() {
        if (isPayWeChat) {
            ToastUtils.show(this, "支付中");
        }
        isPayWeChat = true;
        weChatPrePayPresenter.wechatPrePay();
    }

    /**
     * 线下支付
     */
    private void payOffline() {
        TipsDialog dialog = new TipsDialog(this);
        dialog.setTitle("点击确认后，使用线下支付方式，包括现金刷卡收取等");
        dialog.setTop("确认线下支付");
        dialog.setBottomButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goodsOrderOfflinePayPresenter.payOffline();
            }
        });
        dialog.setTopButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    @Override
    public void wechatPrePaySuccess(WeChatPrePayResultBean resultBean) {
        PayUtils.sendPayReq(this,
                AppContansts.WeChat_Pay_AppId,
                "partnerid",
                "prepayid",
                AppContansts.WeChat_Pay_Package,
                PayUtils.getUUIDString(),
                System.currentTimeMillis(),
                AppContansts.WeChat_Pay_Sign);
    }

    @Override
    public void wechatPrePayFail(String msg) {
        ToastUtils.show(this, msg);
        isPayWeChat = false;
    }
}
