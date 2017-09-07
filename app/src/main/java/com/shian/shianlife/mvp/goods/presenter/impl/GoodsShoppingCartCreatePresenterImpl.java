package com.shian.shianlife.mvp.goods.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartCreateModel;
import com.shian.shianlife.mvp.goods.model.impl.GoodsShoppingCartCreateModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IGoodsShoppingCartCreatePresenter;
import com.shian.shianlife.mvp.goods.view.IGoodsShoppingCartCreateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class GoodsShoppingCartCreatePresenterImpl implements IGoodsShoppingCartCreatePresenter {
    private IGoodsShoppingCartCreateView goodsShoppingCartCreateView;
    private IGoodsShoppingCartCreateModel goodsShoppingCartCreateModel;

    public GoodsShoppingCartCreatePresenterImpl(IGoodsShoppingCartCreateView goodsShoppingCartCreateView) {
        this.goodsShoppingCartCreateView = goodsShoppingCartCreateView;
        goodsShoppingCartCreateModel = new GoodsShoppingCartCreateModelImpl();
    }

    @Override
    public void createGoodsShoppingCartData() {
        if (goodsShoppingCartCreateView.getContext() == null) {
            goodsShoppingCartCreateView.showToast("数据错误");
            return;
        }
        if (goodsShoppingCartCreateView.getGoodsId() == null || goodsShoppingCartCreateView.getGoodsId() == -1) {
            goodsShoppingCartCreateView.showToast("商品ID为空");
            return;
        }
        if (goodsShoppingCartCreateView.getChannelId() == null) {
            goodsShoppingCartCreateView.showToast("商品渠道ID为空");
            return;
        }
        if (goodsShoppingCartCreateView.getClassifyAttrId() == null || goodsShoppingCartCreateView.getClassifyAttrId() == -1) {
            goodsShoppingCartCreateView.showToast("商品分类属性ID为空");
            return;
        }
        if (goodsShoppingCartCreateView.getClassifyId() == null || goodsShoppingCartCreateView.getClassifyId() == -1) {
            goodsShoppingCartCreateView.showToast("商品分类ID为空");
            return;
        }
        if (goodsShoppingCartCreateView.getGoodsSpecId() == null) {
            goodsShoppingCartCreateView.showToast("商品规格ID为空");
            return;
        }
        if (goodsShoppingCartCreateView.getSpecNum() == null || goodsShoppingCartCreateView.getSpecNum() <= 0) {
            goodsShoppingCartCreateView.showToast("商品数量为空");
            return;
        }
        List<GoodsShoppingCartCreateBean.Content> list = new ArrayList<>();
        GoodsShoppingCartCreateBean.Content data = new GoodsShoppingCartCreateBean.Content();
        data.setGoodsId(goodsShoppingCartCreateView.getGoodsId());
        data.setSpecNum(goodsShoppingCartCreateView.getSpecNum());
        data.setChannelId(goodsShoppingCartCreateView.getChannelId());
        data.setClassifyAttrId(goodsShoppingCartCreateView.getClassifyAttrId());
        data.setClassifyId(goodsShoppingCartCreateView.getClassifyId());
        data.setGoodsSpecId(goodsShoppingCartCreateView.getGoodsSpecId());
        list.add(data);

        GoodsShoppingCartCreateBean params = new GoodsShoppingCartCreateBean();
        params.setList(list);
        goodsShoppingCartCreateModel.createGoodsShoppingCart(goodsShoppingCartCreateView.getContext(), params, new OnGetDataListener<GoodsShoppingCartCreateResultBean>() {

            @Override
            public void getDataSuccess(GoodsShoppingCartCreateResultBean result) {
                goodsShoppingCartCreateView.createGoodsShoppingCartSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                goodsShoppingCartCreateView.createGoodsShoppingCartFail(msg);
            }
        });
    }
}
