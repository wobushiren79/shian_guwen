package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsChannelResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassAttrModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsClassAttrModelImpl implements IGoodsClassAttrModel {

    @Override
    public void getGoodsClassAttrData(Context context, GoodsClassAttrBean params, final OnGetDataListener listener) {
        List<GoodsClassAttrResultBean> listData = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            GoodsClassAttrResultBean item = new GoodsClassAttrResultBean();
            item.setName("測試" + i);
            item.setTitle_img("http://img4.imgtn.bdimg.com/it/u=2003988139,3010807873&fm=26&gp=0.jpg");
            listData.add(item);
        }
        listener.getDataSuccess(listData);
//        MHttpManagerFactory.getGoodsManager().getGoodsClassAttr(context, params, new HttpResponseHandler<List<GoodsClassAttrResultBean>>() {
//            @Override
//            public void onStart(Request request, int id) {
//
//            }
//
//            @Override
//            public void onSuccess(List<GoodsClassAttrResultBean> result) {
//                listener.getDataSuccess(result);
//            }
//
//            @Override
//            public void onError(String message) {
//                listener.getDataFail(message);
//            }
//        });
    }
}
