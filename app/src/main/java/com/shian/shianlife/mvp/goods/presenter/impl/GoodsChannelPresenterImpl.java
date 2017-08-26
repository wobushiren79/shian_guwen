package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelBean;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsChannelModel;
import com.shian.shianlife.mvp.goods.model.IGoodsClassModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsChannelModelImpl;
import com.shian.shianlife.mvp.goods.model.impl.GoodsClassModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsChannelPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsChannelView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsChannelPresenterImpl implements IGoodsChannelPresenter {
    private IGoodsChannelView goodsChannelView;
    private IGoodsChannelModel goodsChannelModel;

    public GoodsChannelPresenterImpl(IGoodsChannelView goodsChannelView) {
        this.goodsChannelView = goodsChannelView;
        goodsChannelModel = new GoodsChannelModelImpl();
    }

    @Override
    public void getGoodsChannelData() {
        if (goodsChannelView.getContext() == null) {
            goodsChannelView.showToast("数据错误");
            return;
        }
        GoodsChannelBean params = new GoodsChannelBean();
        goodsChannelModel.getGoodsChannel(goodsChannelView.getContext(), params, new OnGetDataListener<List<GoodsChannelResultBean>>() {


            @Override
            public void getDataSuccess(List<GoodsChannelResultBean> result) {
                for (GoodsChannelResultBean item : result) {
                    if (item.getName() != null && item.getName().contains("单项")) {
                        goodsChannelView.setChannelId(item.getId());
                    }
                }
                goodsChannelView.getGoodsChannelDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsChannelView.getGoodsChannelDataFail(msg);
            }
        });
    }
}
