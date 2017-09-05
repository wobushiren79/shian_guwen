package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.GoodsShoppingCartNumberBean;

/**
 * Created by zm.
 */

public interface IGoodsShoppingCartNumberModel {
    void getShoppingCartNumber(Context context, GoodsShoppingCartNumberBean params, OnGetDataListener listener);
}
