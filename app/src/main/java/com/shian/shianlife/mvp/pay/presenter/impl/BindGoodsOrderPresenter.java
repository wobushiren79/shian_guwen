package com.shian.shianlife.mvp.pay.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderBean;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderResultBean;
import com.shian.shianlife.mvp.pay.model.IBindGoodsOrderModel;
import com.shian.shianlife.mvp.pay.model.impl.BindGoodsOrderModelImpl;
import com.shian.shianlife.mvp.pay.presenter.IBindGoodsOrderPresenter;
import com.shian.shianlife.mvp.pay.view.IBindGoodsOrderView;

/**
 * Created by zm.
 */

public class BindGoodsOrderPresenter implements IBindGoodsOrderPresenter {
    private IBindGoodsOrderModel bindGoodsOrderModel;
    private IBindGoodsOrderView bindGoodsOrderView;

    public BindGoodsOrderPresenter(IBindGoodsOrderView bindGoodsOrderView) {
        this.bindGoodsOrderView = bindGoodsOrderView;
        bindGoodsOrderModel = new BindGoodsOrderModelImpl();
    }

    @Override
    public void bindGoodsOrder() {
        if (bindGoodsOrderView.getContext() == null) {
            bindGoodsOrderView.showToast("数据错误");
            return;
        }
        if (bindGoodsOrderView.getOrderId() == null) {
            bindGoodsOrderView.showToast("没有订单ID");
            return;
        }
        if (bindGoodsOrderView.getOutTradeNo().isEmpty()) {
            bindGoodsOrderView.showToast("没有支付编号");
            return;
        }
        BindGoodsOrderBean params = new BindGoodsOrderBean();
        params.setOrderId(bindGoodsOrderView.getOrderId());
        params.setOutTradeNo(bindGoodsOrderView.getOutTradeNo());
        bindGoodsOrderModel.bindGoodsOrder(bindGoodsOrderView.getContext(), params, new OnGetDataListener<BindGoodsOrderResultBean>() {
            @Override
            public void getDataSuccess(BindGoodsOrderResultBean result) {
                bindGoodsOrderView.bindGoodsOrderSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                bindGoodsOrderView.bindGoodsOrderFail(msg);
            }
        });
    }
}
