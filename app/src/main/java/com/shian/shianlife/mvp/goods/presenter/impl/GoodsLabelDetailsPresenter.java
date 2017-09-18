package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsLabelDetailsModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsLabelDetailsModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsLabelDetailsPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsLabelDetailsView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsLabelDetailsPresenter implements IGoodsLabelDetailsPresenter {
    private IGoodsLabelDetailsView goodsLabelDetailsView;
    private IGoodsLabelDetailsModel goodsLabelDetailsModel;

    public GoodsLabelDetailsPresenter(IGoodsLabelDetailsView goodsLabelDetailsView) {
        this.goodsLabelDetailsView = goodsLabelDetailsView;
        goodsLabelDetailsModel = new GoodsLabelDetailsModelImpl();
    }

    @Override
    public void getGoodsLabelDetails() {
        if (goodsLabelDetailsView.getContext() == null) {
            goodsLabelDetailsView.showToast("数据错误");
            return;
        }
        if (AppContansts.goodsChannelId == null) {
            goodsLabelDetailsView.showToast("数据异常");
            return;
        }
        if (goodsLabelDetailsView.getLabelId() == null) {
            goodsLabelDetailsView.showToast("数据异常");
            return;
        }
        GoodsLabelDetailsBean params = new GoodsLabelDetailsBean();
        params.setLobel_id(goodsLabelDetailsView.getLabelId());
        params.setChannel_id(AppContansts.goodsChannelId);
        goodsLabelDetailsModel.getGoodsLabelDetails(goodsLabelDetailsView.getContext(), params, new OnGetDataListener<List<GoodsLabelDetailsResultBean>>() {
            @Override
            public void getDataSuccess(List<GoodsLabelDetailsResultBean> result) {
                goodsLabelDetailsView.getGoodsLabelDetailsSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsLabelDetailsView.getGoodsLabelDetailsFail(msg);
            }
        });
    }
}
