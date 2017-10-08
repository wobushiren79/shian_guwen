package com.shian.shianlife.mvp.pay.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderBean;
import com.shian.shianlife.mvp.pay.bean.BindGoodsOrderResultBean;
import com.shian.shianlife.mvp.pay.model.IBindGoodsOrderModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class BindGoodsOrderModelImpl implements IBindGoodsOrderModel {

    @Override
    public void bindGoodsOrder(Context context, BindGoodsOrderBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().bindGoodsOrder(context, params, new HttpResponseHandler<BindGoodsOrderResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(BindGoodsOrderResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
