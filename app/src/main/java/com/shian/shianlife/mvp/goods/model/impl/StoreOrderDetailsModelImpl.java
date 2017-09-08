package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsResultBean;
import com.shian.shianlife.mvp.goods.model.IStoreOrderDetailsModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class StoreOrderDetailsModelImpl implements IStoreOrderDetailsModel {

    @Override
    public void getStoreOrderDetails(Context context, StoreOrderDetailsBean params, final OnGetDataListener<StoreOrderDetailsResultBean> listener) {
        MHttpManagerFactory.getGoodsOrderManager().getStoreOrderDetails(context, params, new HttpResponseHandler<StoreOrderDetailsResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(StoreOrderDetailsResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
