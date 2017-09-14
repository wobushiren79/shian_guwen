package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderOfflinePayResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderOfflinePayModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsOrderOfflinePayModelImpl implements IGoodsOrderOfflinePayModel {

    @Override
    public void offlinePay(Context context, GoodsOrderOfflinePayBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().payOfflineGoodsOrder(context, params, new HttpResponseHandler<GoodsOrderOfflinePayResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsOrderOfflinePayResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
