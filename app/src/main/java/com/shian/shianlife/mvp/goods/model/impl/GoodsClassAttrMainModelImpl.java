package com.shian.shianlife.mvp.goods.model.impl;

import android.content.Context;

import com.shian.shianlife.R;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;
import com.shian.shianlife.mvp.goods.model.IGoodsClassAttrMainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassAttrMainModelImpl implements IGoodsClassAttrMainModel {
    @Override
    public void getGoodsClassAttrMainData(Context context, GoodsClassAttrMainBean params, OnGetDataListener<List<GoodsClassAttrMainResultBean>> listener) {
        GoodsClassAttrMainResultBean allData = new GoodsClassAttrMainResultBean();
        allData.setClassAttrName("全部");
        allData.setLocationPicId(R.drawable.zhy_goods_class_attr_all_icon);

        List<GoodsClassAttrMainResultBean> tempData = new ArrayList<>();
        tempData.add(allData);

        listener.getDataSuccess(tempData);
    }
}
