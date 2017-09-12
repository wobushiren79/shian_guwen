package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderCreateModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsOrderCreateModelImpl implements IGoodsOrderCreateModel {

    @Override
    public void createGoodsOrder(Context context, GoodsOrderCreateBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().createGoodsOrder(context, params, new HttpResponseHandler<GoodsOrderCreateResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsOrderCreateResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
