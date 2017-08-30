package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListBean;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsQueryListModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsQueryListModelImpl implements IGoodsQueryListModel {
    @Override
    public void getGoodsQueryData(Context context, GoodsQueryListBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodsQuery(context, params, new HttpResponseHandler<List<GoodsQueryListResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsQueryListResultBean> result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }

}
