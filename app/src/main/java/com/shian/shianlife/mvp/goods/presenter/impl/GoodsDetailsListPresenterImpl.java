package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsDetailsListModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsDetailsListModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsDetailsListPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsDetailsListView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsDetailsListPresenterImpl implements IGoodsDetailsListPresenter {
    private IGoodsDetailsListModel goodsDetailsListModel;
    private IGoodsDetailsListView goodsDetailsListView;

    public GoodsDetailsListPresenterImpl(IGoodsDetailsListView goodsDetailsListView) {
        this.goodsDetailsListView = goodsDetailsListView;
        goodsDetailsListModel = new GoodsDetailsListModelImpl();
    }

    @Override
    public void getGoodsDetailsList() {
        if (goodsDetailsListView.getContext() == null) {
            goodsDetailsListView.showToast("数据错误");
            return;
        }
        GoodsDetailsListBean params = new GoodsDetailsListBean();

        String goodsIds = StringUtils.listToString(goodsDetailsListView.getGoodsIds(), ",");
        params.setGoodsId(goodsIds);
        String channelIds = StringUtils.listToString(goodsDetailsListView.getChannelIds(), ",");
        params.setChannelId(channelIds);
        String goodsSpecIds = StringUtils.listToString(goodsDetailsListView.getGoodsSpecIds(), ",");
        params.setGoodsSpecId(goodsSpecIds);


        goodsDetailsListModel.getGoodsDetailsList(goodsDetailsListView.getContext(), params, new OnGetDataListener<List<GoodsDetailsListResultBean>>() {

            @Override
            public void getDataSuccess(List<GoodsDetailsListResultBean> result) {
                goodsDetailsListView.getGoodsDetailsListDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsDetailsListView.getGoodsDetailsListDataFail(msg);
            }
        });
    }
}
