package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsDetailsListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsDetailsListModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsDetailsListModelImpl implements IGoodsDetailsListModel {
    @Override
    public void getGoodsDetailsList(Context context, GoodsDetailsListBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodsDetailsList(context, params, new HttpResponseHandler<List<GoodsDetailsListResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsDetailsListResultBean> result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
