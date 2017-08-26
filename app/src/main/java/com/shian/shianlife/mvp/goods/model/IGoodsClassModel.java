package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsClassBean;

/**
 * Created by zm.
 */

public interface IGoodsClassModel {
    void getGoodsClassData(Context context, GoodsClassBean params, OnGetDataListener listener);
}
