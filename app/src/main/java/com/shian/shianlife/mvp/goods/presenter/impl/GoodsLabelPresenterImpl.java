package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsLabelModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsLabelModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsLabelPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsLabelView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsLabelPresenterImpl implements IGoodsLabelPresenter {
    private IGoodsLabelView goodsMainRecommendTitleView;
    private IGoodsLabelModel goodsMainRecommendTitleModel;


    public GoodsLabelPresenterImpl(IGoodsLabelView goodsMainRecommendTitleView) {
        this.goodsMainRecommendTitleView = goodsMainRecommendTitleView;
        goodsMainRecommendTitleModel = new GoodsLabelModelImpl();
    }

    @Override
    public void getRecommendTitleData() {
        if (goodsMainRecommendTitleView.getContext() == null) {
            goodsMainRecommendTitleView.showToast("数据错误");
            return;
        }
        GoodsLabelBean params = new GoodsLabelBean();
        goodsMainRecommendTitleModel.getGoodsMainRecommendTitle(goodsMainRecommendTitleView.getContext(), params, new OnGetDataListener<List<GoodsLabelResultBean>>() {

            @Override
            public void getDataSuccess(List<GoodsLabelResultBean> result) {
                goodsMainRecommendTitleView.getGoodsMainRecommendTitleSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsMainRecommendTitleView.getGoodsMainRecommendTitleFail(msg);
            }
        });
    }
}
