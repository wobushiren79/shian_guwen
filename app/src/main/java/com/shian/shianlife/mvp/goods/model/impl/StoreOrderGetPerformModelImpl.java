package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;


import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformResultBean;
import com.shian.shianlife.mvp.goods.model.IStoreOrderGetPerformModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;


/**
 * Created by zm.
 */

public class StoreOrderGetPerformModelImpl implements IStoreOrderGetPerformModel {
    @Override
    public void getPerformInfo(Context context, StoreOrderGetPerformBean params, final OnGetDataListener<StoreOrderGetPerformResultBean> listener) {
        MHttpManagerFactory.getGoodsOrderManager().getPerformInfo(context, params, new HttpResponseHandler<StoreOrderGetPerformResultBean>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(StoreOrderGetPerformResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
