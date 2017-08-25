package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainBean;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;

import java.util.List;

/**
 * Created by zm.
 */

public interface IGoodsClassAttrMainModel {
    void getGoodsClassAttrMainData(Context context, GoodsClassAttrMainBean params, OnGetDataListener<List<GoodsClassAttrMainResultBean>> listener);
}
