package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsOrderListModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsOrderListModelImpl implements IGoodsOrderListModel {
    @Override
    public void getGoodsOrderListData(Context context, GoodsOrderListBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsOrderManager().getGoodsOrderList(context, params, new HttpResponseHandler<GoodsOrderListResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(GoodsOrderListResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
