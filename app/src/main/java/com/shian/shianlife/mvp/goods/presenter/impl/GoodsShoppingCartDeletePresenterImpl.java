package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartDeleteModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsShoppingCartDeleteModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartDeletePresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartDeleteView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class GoodsShoppingCartDeletePresenterImpl implements IGoodsShoppingCartDeletePresenter {
    private IGoodsShoppingCartDeleteModel goodsShoppingCartDeleteModel;
    private IGoodsShoppingCartDeleteView goodsShoppingCartDeleteView;

    public GoodsShoppingCartDeletePresenterImpl(IGoodsShoppingCartDeleteView goodsShoppingCartDeleteView) {
        this.goodsShoppingCartDeleteView = goodsShoppingCartDeleteView;
        goodsShoppingCartDeleteModel = new GoodsShoppingCartDeleteModelImpl();
    }

    @Override
    public void deleteGoodsShoppingCart(final GoodsShoppingCartListChildBean itemAllData, final List<GoodsShoppingCartListChildBean> listData) {
        final GoodsDetailsListResultBean data = itemAllData.getResultBean();

        if (goodsShoppingCartDeleteView.getContext() == null) {
            goodsShoppingCartDeleteView.showToast("数据错误");
            return;
        }

        if (data.getShoppingCartId() == null) {
            goodsShoppingCartDeleteView.showToast("数据错误");
            return;
        }
        List<Long> listId = new ArrayList<>();
        listId.add(data.getShoppingCartId());

        GoodsShoppingCartDeleteBean params = new GoodsShoppingCartDeleteBean();
        params.setShoppingCartIds(listId);
        goodsShoppingCartDeleteModel.deleteGoodsShoppingCart(goodsShoppingCartDeleteView.getContext(), params, new OnGetDataListener<GoodsShoppingCartDeleteResultBean>() {

            @Override
            public void getDataSuccess(GoodsShoppingCartDeleteResultBean result) {
                listData.remove(itemAllData);
                goodsShoppingCartDeleteView.deleteGoodsShoppingCartSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsShoppingCartDeleteView.deleteGoodsShoppingCartFail(msg);
            }

        });
    }
}
