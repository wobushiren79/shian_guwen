package com.shian.shianlife.mvp.goods.model.impl;


import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsShoppingCartListModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsShoppingCartListModelImpl implements IGoodsShoppingCartListModel {


    @Override
    public void getShoppingCartList(Context context, GoodsShoppingCartListBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().getShoppingCartList(context, params, new HttpResponseHandler<GoodsShoppingCartListResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsShoppingCartListResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
