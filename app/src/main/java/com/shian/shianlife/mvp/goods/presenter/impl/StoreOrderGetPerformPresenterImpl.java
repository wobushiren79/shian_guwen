package com.shian.shianlife.mvp.goods.presenter.impl;


import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformBean;
import com.shian.shianlife.mvp.goods.bean.StoreOrderGetPerformResultBean;
import com.shian.shianlife.mvp.goods.model.IStoreOrderGetPerformModel;
import com.shian.shianlife.mvp.goods.model.impl.StoreOrderGetPerformModelImpl;
import com.shian.shianlife.mvp.goods.presenter.IStoreOrderGetPerformPresenter;
import com.shian.shianlife.mvp.goods.view.IStoreOrderGetPerformView;

/**
 * Created by zm.
 */

public class StoreOrderGetPerformPresenterImpl implements IStoreOrderGetPerformPresenter {
    IStoreOrderGetPerformView storeOrderGetPerformView;
    IStoreOrderGetPerformModel storeOrderGetPerformModel;

    public StoreOrderGetPerformPresenterImpl(IStoreOrderGetPerformView storeOrderGetPerformView) {
        this.storeOrderGetPerformView = storeOrderGetPerformView;
        storeOrderGetPerformModel = new StoreOrderGetPerformModelImpl();
    }


    @Override
    public void getPerformInfo(Long performId) {
        StoreOrderGetPerformBean params = new StoreOrderGetPerformBean();
        params.setPerformId(performId);
        storeOrderGetPerformModel.getPerformInfo(storeOrderGetPerformView.getContext(), params, new OnGetDataListener<StoreOrderGetPerformResultBean>() {
            @Override
            public void getDataSuccess(StoreOrderGetPerformResultBean result) {
                storeOrderGetPerformView.getPerformInfoSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                storeOrderGetPerformView.getPerformInfoFail(msg);
            }
        });
    }
}
