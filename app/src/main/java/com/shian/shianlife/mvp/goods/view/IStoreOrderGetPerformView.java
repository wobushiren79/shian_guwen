package com.shian.shianlife.mvp.goods.view;

import android.content.Context;

import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformResultBean;


/**
 * Created by zm.
 */

public interface IStoreOrderGetPerformView {
    Context getContext();

    void getPerformInfoSuccess(StoreOrderGetPerformResultBean resultBean);

    void getPerformInfoFail(String msg);

}
