package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderInfoResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderInfoModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsOrderInfoModelImpl implements IGoodsOrderInfoModel {
    @Override
    public void getGoodsOrderInfo(Context context, GoodsOrderInfoBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().getGoodsOrderInfo(context, params, new HttpResponseHandler<GoodsOrderInfoResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsOrderInfoResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
