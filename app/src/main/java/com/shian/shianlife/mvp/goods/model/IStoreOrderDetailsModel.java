package com.shian.shianlife.mvp.goods.model;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsResultBean;


/**
 * Created by zm.
 */

public interface IStoreOrderDetailsModel {

    void getStoreOrderDetails(Context context, StoreOrderDetailsBean params, OnGetDataListener<StoreOrderDetailsResultBean> listener);
}
