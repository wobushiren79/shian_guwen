package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformResultBean;


/**
 * Created by zm.
 */

public interface IStoreOrderGetPerformModel {
    void getPerformInfo(Context context, StoreOrderGetPerformBean params, OnGetDataListener<StoreOrderGetPerformResultBean> listener);
}
