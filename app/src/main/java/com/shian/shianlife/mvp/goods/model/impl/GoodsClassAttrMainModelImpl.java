package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.R;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassAttrMainModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class GoodsClassAttrMainModelImpl implements IGoodsClassAttrMainModel {
    @Override
    public void getGoodsClassAttrMainData(Context context, GoodsClassAttrMainBean params, final OnGetDataListener listener) {
        final GoodsClassAttrMainResultBean allData = new GoodsClassAttrMainResultBean();
        allData.setName("全部");
        allData.setPicId(R.drawable.zhy_goods_class_attr_all_icon);
        MHttpManagerFactory.getGoodsManager().getGoodClassAttrMain(context, params, new HttpResponseHandler<List<GoodsClassAttrMainResultBean>>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(List<GoodsClassAttrMainResultBean> result) {
                for (GoodsClassAttrMainResultBean itemResult : result) {
                    if (itemResult.getName() == null)
                        continue;
                    if (itemResult.getName().contains("骨灰")) {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_urn_icon);
                    } else if (itemResult.getName().contains("回礼")) {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_present_icon);
                    } else if (itemResult.getName().contains("车辆")) {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_car_icon);
                    } else if (itemResult.getName().contains("寿衣")) {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_shroud_icon);
                    } else if (itemResult.getName().contains("附属")) {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_affiliate_icon);
                    } else if (itemResult.getName().contains("纪念品")) {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_diamond_icon);
                    } else {
                        itemResult.setPicId(R.drawable.zhy_goods_class_attr_all_icon);
                    }
                }
                result.add(allData);
                listener.getDataSuccess(result);
            }


            @Override
            public void onError(String message) {
                List<GoodsClassAttrMainResultBean> tempData = new ArrayList<>();
                tempData.add(allData);
                listener.getDataSuccess(tempData);
            }
        });
    }
}
