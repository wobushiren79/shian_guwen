package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsQueryListModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsQueryListModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsQueryListPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsQueryListView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsQueryListPresenterImpl implements IGoodsQueryListPresenter {
    private IGoodsQueryListModel goodsQueryListModel;
    private IGoodsQueryListView goodsQueryListView;

    public GoodsQueryListPresenterImpl(IGoodsQueryListView goodsQueryListView) {
        this.goodsQueryListView = goodsQueryListView;
        goodsQueryListModel = new GoodsQueryListModelImpl();
    }

    @Override
    public void getGoodsQueryListData() {
        if (goodsQueryListView.getContext() == null) {
            goodsQueryListView.showToast("数据错误");
            return;
        }
        if (goodsQueryListView.getClassAttrId() == null) {
            goodsQueryListView.showToast("缺少ClassAttrId");
            return;
        }
        if (CheckUtils.isEmpty(goodsQueryListView.getChannelId())) {
            goodsQueryListView.showToast("缺少ChannelId");
            return;
        }
        if (goodsQueryListView.getPageNumber() == null) {
            goodsQueryListView.showToast("缺少查询页数");
            return;
        }
        if (goodsQueryListView.getPageSize() == null) {
            goodsQueryListView.showToast("缺少查询数量");
            return;
        }
        GoodsQueryListBean params = new GoodsQueryListBean();
        params.setId(goodsQueryListView.getClassAttrId());
        params.setChannel_id(goodsQueryListView.getChannelId());
        params.setPageNumber(goodsQueryListView.getPageNumber());
        params.setPageSize(goodsQueryListView.getPageSize());
        params.setOrder(goodsQueryListView.getRankOrder());
        if (goodsQueryListView.getGoodsName() == null || goodsQueryListView.getGoodsName().isEmpty())
            params.setGoods_name(goodsQueryListView.getGoodsName());
        goodsQueryListModel.getGoodsQueryData(goodsQueryListView.getContext(), params, new OnGetDataListener<List<GoodsQueryListResultBean>>() {

            @Override
            public void getDataSuccess(List<GoodsQueryListResultBean> result) {
                goodsQueryListView.getGoodsQueryListDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsQueryListView.getGoodsQueryListDataFail(msg);
            }

        });
    }
}
