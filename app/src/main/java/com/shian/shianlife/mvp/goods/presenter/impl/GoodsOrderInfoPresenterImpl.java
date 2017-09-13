package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderInfoModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsOrderInfoModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderInfoPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderInfoView;

/**
 * Created by zm.
 */

public class GoodsOrderInfoPresenterImpl implements IGoodsOrderInfoPresenter {

    private IGoodsOrderInfoModel goodsOrderInfoModel;
    private IGoodsOrderInfoView goodsOrderInfoView;

    public GoodsOrderInfoPresenterImpl(IGoodsOrderInfoView goodsOrderInfoView) {
        this.goodsOrderInfoView = goodsOrderInfoView;
        goodsOrderInfoModel = new GoodsOrderInfoModelImpl();
    }

    @Override
    public void getGoodsOrderInfo() {
        if (goodsOrderInfoView.getContext() == null) {
            goodsOrderInfoView.showToast("数据错误");
            return;
        }
        if (goodsOrderInfoView.getOrderId() == null || goodsOrderInfoView.getOrderId() == -1) {
            goodsOrderInfoView.showToast("没有订单id");
            return;
        }
        GoodsOrderInfoBean params = new GoodsOrderInfoBean();
        params.setOrderId(goodsOrderInfoView.getOrderId());
        goodsOrderInfoModel.getGoodsOrderInfo(goodsOrderInfoView.getContext(), params, new OnGetDataListener<GoodsOrderInfoResultBean>() {
            @Override
            public void getDataSuccess(GoodsOrderInfoResultBean result) {
                Integer adviserPrice = result.getTotalPrice();
                if (adviserPrice == null)
                    return;
                Integer coupon = 0;
                Integer freight = 0;
                Integer integral = 0;

                Integer payPrice = adviserPrice - coupon + freight;
                goodsOrderInfoView.getGoodsOrderInfoSuccess(result);
                goodsOrderInfoView.setAdviserPrice(adviserPrice);
                goodsOrderInfoView.setCoupon(coupon);
                goodsOrderInfoView.setFreight(freight);
                goodsOrderInfoView.setIntegral(integral);
                goodsOrderInfoView.setPayPrice(payPrice);

                if (result.getOrderNumber() != null)
                    goodsOrderInfoView.setOrderNumber(result.getOrderNumber());
            }

            @Override
            public void getDataFail(String msg) {
                goodsOrderInfoView.getGoodsOrderInfoFail(msg);
            }
        });
    }
}
