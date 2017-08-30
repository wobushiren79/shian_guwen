package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassAttrModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsClassAttrModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassAttrPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsClassAttrView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassAttrPresenterImpl implements IGoodsClassAttrPresenter {
    private IGoodsClassAttrView goodsClassAttrView;
    private IGoodsClassAttrModel goodsClassAttrModel;

    public GoodsClassAttrPresenterImpl(IGoodsClassAttrView goodsClassAttrView) {
        this.goodsClassAttrView = goodsClassAttrView;
        goodsClassAttrModel = new GoodsClassAttrModelImpl();
    }

    @Override
    public void getGoodsClassAttrData() {
        if (goodsClassAttrView.getContext() == null) {
            goodsClassAttrView.showToast("数据错误");
            return;
        }
        if(CheckUtils.isEmpty(goodsClassAttrView.getGoodsClassId())){
            goodsClassAttrView.showToast("数据错误");
            return;
        }
        GoodsClassAttrBean params = new GoodsClassAttrBean();
        params.setId(goodsClassAttrView.getGoodsClassId());
        goodsClassAttrModel.getGoodsClassAttrData(goodsClassAttrView.getContext(), params, new OnGetDataListener<List<GoodsClassAttrResultBean>>() {

            @Override
            public void getDataSuccess(List<GoodsClassAttrResultBean> result) {
                goodsClassAttrView.getGoodsClassAttrDataSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsClassAttrView.getGoodsClassAttrDataFail(msg);
            }
        });
    }

}
