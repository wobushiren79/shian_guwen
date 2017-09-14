package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderOfflinePayModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsOrderOfflinePayModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderOfflinePayPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderOfflinePayView;

/**
 * Created by zm.
 */

public class GoodsOrderOfflinePayPresenterImpl implements IGoodsOrderOfflinePayPresenter {
    private IGoodsOrderOfflinePayView goodsOrderOfflinePayView;
    private IGoodsOrderOfflinePayModel goodsOrderOfflinePayModel;

    public GoodsOrderOfflinePayPresenterImpl(IGoodsOrderOfflinePayView goodsOrderOfflinePayView) {
        this.goodsOrderOfflinePayView = goodsOrderOfflinePayView;
        goodsOrderOfflinePayModel = new GoodsOrderOfflinePayModelImpl();
    }

    @Override
    public void payOffline() {
        if (goodsOrderOfflinePayView.getContext() == null) {
            goodsOrderOfflinePayView.showToast("数据错误");
            return;
        }
        if (goodsOrderOfflinePayView.getOrderId() == null) {
            goodsOrderOfflinePayView.showToast("没有订单ID");
            return;
        }
        if (goodsOrderOfflinePayView.getPayPrice() == null || goodsOrderOfflinePayView.getPayPrice() <= 0) {
            goodsOrderOfflinePayView.showToast("支付总价价格有误");
            return;
        }
        GoodsOrderOfflinePayBean params = new GoodsOrderOfflinePayBean();
        params.setOrderId(goodsOrderOfflinePayView.getOrderId());
        params.setActualPayment(goodsOrderOfflinePayView.getPayPrice());
        goodsOrderOfflinePayModel.offlinePay(goodsOrderOfflinePayView.getContext(), params, new OnGetDataListener<GoodsOrderOfflinePayResultBean>() {
            @Override
            public void getDataSuccess(GoodsOrderOfflinePayResultBean result) {
                goodsOrderOfflinePayView.payOfflineSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsOrderOfflinePayView.payOfflineFail(msg);
            }
        });
    }
}
