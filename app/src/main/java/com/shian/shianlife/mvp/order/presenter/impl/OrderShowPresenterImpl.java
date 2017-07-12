package com.shian.shianlife.mvp.order.presenter.impl;


import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;
import com.shian.shianlife.mvp.order.model.IOrderShowModel;
import com.shian.shianlife.mvp.order.model.impl.OrderShowModelImpl;
import com.shian.shianlife.mvp.order.presenter.IOrderShowPresenter;
import com.shian.shianlife.mvp.order.view.IOrderShowView;

/**
 * Created by zm.
 */

public class OrderShowPresenterImpl implements IOrderShowPresenter {
    IOrderShowModel orderShowModel;
    IOrderShowView orderShowView;

    public OrderShowPresenterImpl(IOrderShowView orderShowView) {
        this.orderShowView = orderShowView;
        orderShowModel = new OrderShowModelImpl();
    }


    @Override
    public void getOrderShowItem() {
        orderShowModel.getOrderShowItems(new OnGetDataListener<OrderShowResultBean>() {
            @Override
            public void getDataSuccess(OrderShowResultBean result) {
                orderShowView.showOrderItems(result);
            }

            @Override
            public void getDataFail(String msg) {

            }
        });
    }
}
