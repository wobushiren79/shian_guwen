package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderSubmitModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsOrderSubmitModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderSubmitPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderSubmitView;

/**
 * Created by zm.
 */

public class GoodsOrderSubmitPresenterImpl implements IGoodsOrderSubmitPresenter {
    private IGoodsOrderSubmitView goodsOrderSubmitView;
    private IGoodsOrderSubmitModel goodsOrderSubmitModel;

    public GoodsOrderSubmitPresenterImpl(IGoodsOrderSubmitView goodsOrderSubmitView) {
        this.goodsOrderSubmitView = goodsOrderSubmitView;
        goodsOrderSubmitModel = new GoodsOrderSubmitModelImpl();
    }

    @Override
    public void submitGoodsOrder() {
        if (goodsOrderSubmitView.getContext() == null) {
            goodsOrderSubmitView.showToast("数据错误");
            return;
        }
        if (goodsOrderSubmitView.getOrderId() == null || goodsOrderSubmitView.getOrderId() == -1) {
            goodsOrderSubmitView.showToast("没有订单id");
            return;
        }
        GoodsOrderSubmitBean params = new GoodsOrderSubmitBean();
        params.setOrderId(goodsOrderSubmitView.getOrderId());
        goodsOrderSubmitModel.goodsOrderSubmit(goodsOrderSubmitView.getContext(), params, new OnGetDataListener<GoodsOrderSubmitResultBean>() {
            @Override
            public void getDataSuccess(GoodsOrderSubmitResultBean result) {
                goodsOrderSubmitView.submitGoodsOrderSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsOrderSubmitView.submitGoodsOrderFail(msg);
            }
        });
    }
}
