package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartNumberModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsShoppingCartNumberModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartNumberPresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartNumberView;

/**
 * Created by zm.
 */

public class GoodsShoppingCartNumberPresenterImpl implements IGoodsShoppingCartNumberPresenter {
    private IGoodsShoppingCartNumberView goodsShoppingCartNumberView;
    private IGoodsShoppingCartNumberModel goodsShoppingCartNumberModel;

    public GoodsShoppingCartNumberPresenterImpl(IGoodsShoppingCartNumberView goodsShoppingCartNumberView) {
        this.goodsShoppingCartNumberView = goodsShoppingCartNumberView;
        this.goodsShoppingCartNumberModel = new GoodsShoppingCartNumberModelImpl();
    }

    @Override
    public void getShoppingCartNumber() {
        if (goodsShoppingCartNumberView.getContext() == null) {
            goodsShoppingCartNumberView.showToast("数据错误");
            return;
        }
        GoodsShoppingCartNumberBean params = null;
        goodsShoppingCartNumberModel.getShoppingCartNumber(goodsShoppingCartNumberView.getContext(), params, new OnGetDataListener<GoodsShoppingCartNumberResultBean>() {
            @Override
            public void getDataSuccess(GoodsShoppingCartNumberResultBean result) {
                goodsShoppingCartNumberView.getShoppingCartNumberSuccess(result);
                StringBuffer number = new StringBuffer();
                if (result.getShoppingTotalNumber() != null && result.getShoppingTotalNumber() > 0) {
                    if (result.getShoppingTotalNumber() > 99) {
                        number.append("99+");
                    } else {
                        number.append(result.getShoppingTotalNumber());
                    }
                }

                goodsShoppingCartNumberView.setShoppingCartNumber(number.toString());
            }

            @Override
            public void getDataFail(String msg) {
                goodsShoppingCartNumberView.getShoppingCartNumberFail(msg);
            }
        });
    }
}
