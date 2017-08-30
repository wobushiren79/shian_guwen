package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsClassModelImpl implements IGoodsClassModel {
    @Override
    public void getGoodsClassData(Context context, GoodsClassBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getGoodsManager().getGoodsClass(context, params, new HttpResponseHandler<List<GoodsClassResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsClassResultBean> result) {
                listener.getDataSuccess(result);
            }


            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
