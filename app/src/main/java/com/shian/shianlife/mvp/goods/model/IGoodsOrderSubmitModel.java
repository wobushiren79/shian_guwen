package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderSubmitBean;

/**
 * Created by zm.
 */

public interface IGoodsOrderSubmitModel {
    void goodsOrderSubmit(Context context, GoodsOrderSubmitBean params, OnGetDataListener listener);
}
