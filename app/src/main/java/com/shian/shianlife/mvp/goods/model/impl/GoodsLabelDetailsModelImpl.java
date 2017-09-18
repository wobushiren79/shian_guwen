package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsBean;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelDetailsResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsLabelDetailsModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsLabelDetailsModelImpl implements IGoodsLabelDetailsModel {

    @Override
    public void getGoodsLabelDetails(Context context, GoodsLabelDetailsBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodLabelDetails(context, params, new HttpResponseHandler<List<GoodsLabelDetailsResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsLabelDetailsResultBean> result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
