package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderSubmitModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsOrderSubmitModelImpl implements IGoodsOrderSubmitModel {

    @Override
    public void goodsOrderSubmit(Context context, GoodsOrderSubmitBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().submitGoodsOrder(context, params, new HttpResponseHandler<GoodsOrderSubmitResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsOrderSubmitResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
