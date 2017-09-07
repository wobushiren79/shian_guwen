package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartDeleteResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartDeleteModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsShoppingCartDeleteModelImpl implements IGoodsShoppingCartDeleteModel {

    @Override
    public void deleteGoodsShoppingCart(Context context, GoodsShoppingCartDeleteBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().deleteShoppingCart(context, params, new HttpResponseHandler<GoodsShoppingCartDeleteResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsShoppingCartDeleteResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
