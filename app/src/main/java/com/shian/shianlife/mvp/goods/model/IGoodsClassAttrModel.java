package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrBean;

/**
 * Created by zm.
 */

public interface IGoodsClassAttrModel {
    void getGoodsClassAttrData(Context context, GoodsClassAttrBean params, OnGetDataListener listener);
}
