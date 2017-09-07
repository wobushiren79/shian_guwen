package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartCreateResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartCreateModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsShoppingCartCreateModelImpl implements IGoodsShoppingCartCreateModel {
    @Override
    public void createGoodsShoppingCart(Context context, GoodsShoppingCartCreateBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().createShoppingCart(context, params, new HttpResponseHandler<GoodsShoppingCartCreateResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsShoppingCartCreateResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
