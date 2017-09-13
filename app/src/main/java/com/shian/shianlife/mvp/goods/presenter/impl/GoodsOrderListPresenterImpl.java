package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderListModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsOrderListModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderListPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderListView;

/**
 * Created by zm.
 */

public class GoodsOrderListPresenterImpl implements IGoodsOrderListPresenter {
    private IGoodsOrderListView goodsOrderListView;
    private IGoodsOrderListModel goodsOrderListModel;

    public GoodsOrderListPresenterImpl(IGoodsOrderListView goodsOrderListView) {
        this.goodsOrderListView = goodsOrderListView;
        goodsOrderListModel = new GoodsOrderListModelImpl();
    }

    @Override
    public void getGoodsOrderListData() {
        if (goodsOrderListView == null) {
            goodsOrderListView.showToast("数据错误");
            return;
        }
        GoodsOrderListBean.Content content = new GoodsOrderListBean.Content();
        content.setOrderStatus(goodsOrderListView.getOrderStatus());
        content.setPayStatus(goodsOrderListView.getPayStatus());

        GoodsOrderListBean params = new GoodsOrderListBean();
        params.setPageNumber(goodsOrderListView.getPageNumber());
        params.setPageSize(goodsOrderListView.getPageSize());
        params.setContent(content);

        goodsOrderListModel.getGoodsOrderListData(goodsOrderListView.getContext(), params, new OnGetDataListener<GoodsOrderListResultBean>() {
            @Override
            public void getDataSuccess(GoodsOrderListResultBean result) {
                goodsOrderListView.getGoodsOrderListDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsOrderListView.getGoodsOrderListDataFail(msg);
            }
        });
    }
}
