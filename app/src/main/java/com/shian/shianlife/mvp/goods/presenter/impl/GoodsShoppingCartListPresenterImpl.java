package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartListModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsShoppingCartListModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartListPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartListView;

/**
 * Created by zm.
 */

public class GoodsShoppingCartListPresenterImpl implements IGoodsShoppingCartListPresenter {
    private IGoodsShoppingCartListView goodsShoppingCartListView;
    private IGoodsShoppingCartListModel goodsShoppingCartListModel;

    public GoodsShoppingCartListPresenterImpl(IGoodsShoppingCartListView goodsShoppingCartListView) {
        this.goodsShoppingCartListView = goodsShoppingCartListView;
        goodsShoppingCartListModel = new GoodsShoppingCartListModelImpl();
    }

    @Override
    public void getShoppingCartListData() {
        if (goodsShoppingCartListView.getContext() == null) {
            goodsShoppingCartListView.showToast("数据错误");
            return;
        }
        if (goodsShoppingCartListView.getShoppingCartPageSize() == null
                || goodsShoppingCartListView.getShoppingCartPageNumber() == null) {
            goodsShoppingCartListView.showToast("数据错误");
            return;
        }
        if (AppContansts.goodsChannelId == null) {
            goodsShoppingCartListView.showToast("商品渠道ID为空");
            return;
        }
        GoodsShoppingCartListBean params = new GoodsShoppingCartListBean();
        params.setPageSize(goodsShoppingCartListView.getShoppingCartPageSize());
        params.setPageNumber(goodsShoppingCartListView.getShoppingCartPageNumber());

        GoodsShoppingCartListBean.Content content = new GoodsShoppingCartListBean.Content();
        content.setChannelId(AppContansts.goodsChannelId);

        params.setContent(content);
        goodsShoppingCartListModel.getShoppingCartList(goodsShoppingCartListView.getContext(), params, new OnGetDataListener<GoodsShoppingCartListResultBean>() {
            @Override
            public void getDataSuccess(GoodsShoppingCartListResultBean result) {
                goodsShoppingCartListView.getShoppingCartListDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsShoppingCartListView.getShoppingCartListDataFail(msg);
            }
        });
    }
}
