package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartChangeNumberModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsShoppingCartChangeNumberModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartChangeNumberPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartChangeNumberView;

/**
 * Created by zm.
 */

public class GoodsShoppingCartChangeNumberPresenterImpl implements IGoodsShoppingCartChangeNumberPresenter {
    private IGoodsShoppingCartChangeNumberView goodsShoppingCartChangeNumberView;
    private IGoodsShoppingCartChangeNumberModel goodsShoppingCartChangeNumberModel;

    public GoodsShoppingCartChangeNumberPresenterImpl(IGoodsShoppingCartChangeNumberView goodsShoppingCartChangeNumberView) {
        this.goodsShoppingCartChangeNumberView = goodsShoppingCartChangeNumberView;
        goodsShoppingCartChangeNumberModel = new GoodsShoppingCartChangeNumberModelImpl();
    }

    @Override
    public void changeGoodsShoppingCartNumber(final GoodsDetailsListResultBean data, final Integer changeNumber) {
        if (goodsShoppingCartChangeNumberView.getContext() == null) {
            goodsShoppingCartChangeNumberView.showToast("数据错误");
            return;
        }
        if (data.getShoppingCartId() == null) {
            goodsShoppingCartChangeNumberView.showToast("数据错误");
            return;
        }
        if (changeNumber <= 0 || changeNumber == null) {
            goodsShoppingCartChangeNumberView.showToast("商品数量不能再减少");
            return;
        }
        GoodsShoppingCartChangeNumberBean params = new GoodsShoppingCartChangeNumberBean();
        params.setSpecNum(data.getShoppingCartNumber());
        params.setId(data.getShoppingCartId());
        goodsShoppingCartChangeNumberModel.changeGoodsShoppingCartNumber(goodsShoppingCartChangeNumberView.getContext(), params, new OnGetDataListener<GoodsShoppingCartChangeNumberResultBean>() {


            @Override
            public void getDataSuccess(GoodsShoppingCartChangeNumberResultBean result) {
                data.setShoppingCartNumber(changeNumber);
                goodsShoppingCartChangeNumberView.changeShoppingCartNumberSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsShoppingCartChangeNumberView.changeShoppingCartNumberFail(msg);
            }
        });
    }
}
