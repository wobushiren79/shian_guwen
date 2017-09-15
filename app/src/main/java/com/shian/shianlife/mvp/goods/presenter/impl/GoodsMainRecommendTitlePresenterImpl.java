package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleBean;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsMainRecommendTitleModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsMainRecommendTitleModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsMainRecommendTitlePresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsMainRecommendTitleView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsMainRecommendTitlePresenterImpl implements IGoodsMainRecommendTitlePresenter {
    private IGoodsMainRecommendTitleView goodsMainRecommendTitleView;
    private IGoodsMainRecommendTitleModel goodsMainRecommendTitleModel;


    public GoodsMainRecommendTitlePresenterImpl(IGoodsMainRecommendTitleView goodsMainRecommendTitleView) {
        this.goodsMainRecommendTitleView = goodsMainRecommendTitleView;
        goodsMainRecommendTitleModel = new GoodsMainRecommendTitleModelImpl();
    }

    @Override
    public void getRecommendTitleData() {
        if (goodsMainRecommendTitleView.getContext() == null) {
            goodsMainRecommendTitleView.showToast("数据错误");
            return;
        }
        GoodsMainRecommendTitleBean params = new GoodsMainRecommendTitleBean();
        goodsMainRecommendTitleModel.getGoodsMainRecommendTitle(goodsMainRecommendTitleView.getContext(), params, new OnGetDataListener<List<GoodsMainRecommendTitleResultBean>>() {

            @Override
            public void getDataSuccess(List<GoodsMainRecommendTitleResultBean> result) {
                goodsMainRecommendTitleView.getGoodsMainRecommendTitleSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsMainRecommendTitleView.getGoodsMainRecommendTitleFail(msg);
            }
        });
    }
}
