package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderCreateModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsOrderCreateModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderCreatePresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderCreateView;

/**
 * Created by zm.
 */

public class GoodsOrderCreatePresenterImpl implements IGoodsOrderCreatePresenter {
    private IGoodsOrderCreateView goodsOrderCreateView;
    private IGoodsOrderCreateModel goodsOrderCreateModel;

    public GoodsOrderCreatePresenterImpl(IGoodsOrderCreateView goodsOrderCreateView) {
        this.goodsOrderCreateView = goodsOrderCreateView;
        goodsOrderCreateModel = new GoodsOrderCreateModelImpl();
    }

    @Override
    public void createGoodsOrder() {
        if (goodsOrderCreateView == null) {
            goodsOrderCreateView.showToast("数据错误");
            return;
        }
        if(goodsOrderCreateView.getGoodsServiceInfo()==null){
            goodsOrderCreateView.showToast("还没有填写服务信息");
            return;
        }
        GoodsOrderCreateBean params = new GoodsOrderCreateBean();
        params.setGoodsOrder(goodsOrderCreateView.getGoodsOrder());
        params.setGoodsInvoice(goodsOrderCreateView.getGoodsInvoice());
        params.setGoodsServiceInfo(goodsOrderCreateView.getGoodsServiceInfo());
        params.setGoodsOrderItem(goodsOrderCreateView.getGoodsOrderItem());
        goodsOrderCreateModel.createGoodsOrder(goodsOrderCreateView.getContext(), params, new OnGetDataListener<GoodsOrderCreateResultBean>() {

            @Override
            public void getDataSuccess(GoodsOrderCreateResultBean result) {
                goodsOrderCreateView.createGoodsOrderSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsOrderCreateView.createGoodsOrderFail(msg);
            }
        });
    }
}
