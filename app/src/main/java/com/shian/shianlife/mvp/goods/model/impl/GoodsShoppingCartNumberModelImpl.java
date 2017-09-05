package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartNumberModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsShoppingCartNumberModelImpl implements IGoodsShoppingCartNumberModel {

    @Override
    public void getShoppingCartNumber(Context context, GoodsShoppingCartNumberBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().getShoppingCartNumber(context, params, new HttpResponseHandler<GoodsShoppingCartNumberResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsShoppingCartNumberResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
