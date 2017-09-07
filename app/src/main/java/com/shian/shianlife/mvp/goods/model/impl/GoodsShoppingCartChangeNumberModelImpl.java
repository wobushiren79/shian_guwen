package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartChangeNumberResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartChangeNumberModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsShoppingCartChangeNumberModelImpl implements IGoodsShoppingCartChangeNumberModel {
    @Override
    public void changeGoodsShoppingCartNumber(Context context, GoodsShoppingCartChangeNumberBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().changeShoppingCartNumber(context, params, new HttpResponseHandler<GoodsShoppingCartChangeNumberResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsShoppingCartChangeNumberResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
