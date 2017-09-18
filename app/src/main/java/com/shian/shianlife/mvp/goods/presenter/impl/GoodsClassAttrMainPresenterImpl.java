package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassAttrMainModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsClassAttrMainModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassAttrMainPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsClassAttrMainView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassAttrMainPresenterImpl implements IGoodsClassAttrMainPresenter {
    private IGoodsClassAttrMainView goodsClassAttrMainView;
    private IGoodsClassAttrMainModel goodsClassAttrMainModel;

    public GoodsClassAttrMainPresenterImpl(IGoodsClassAttrMainView goodsClassAttrMainView) {
        this.goodsClassAttrMainView = goodsClassAttrMainView;
        goodsClassAttrMainModel = new GoodsClassAttrMainModelImpl();
    }

    @Override
    public void getGoodsClassAttrMainData() {
        if (goodsClassAttrMainView.getContext() == null) {
            goodsClassAttrMainView.showToast("没有上下文对象");
            return;
        }
        if (AppContansts.goodsChannelId == null) {
            goodsClassAttrMainView.showToast("没有goodsChannelId");
            return;
        }
        GoodsClassAttrMainBean params = new GoodsClassAttrMainBean();
        params.setChannel_id(AppContansts.goodsChannelId);
        params.setType("APP");
        goodsClassAttrMainModel.getGoodsClassAttrMainData(goodsClassAttrMainView.getContext(), params, new OnGetDataListener<List<GoodsClassAttrMainResultBean>>() {
            @Override
            public void getDataSuccess(List<GoodsClassAttrMainResultBean> result) {
                goodsClassAttrMainView.getGoodsClassAttrMainDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsClassAttrMainView.getGoodsClassAttrMainDataFail(msg);
            }
        });
    }
}
