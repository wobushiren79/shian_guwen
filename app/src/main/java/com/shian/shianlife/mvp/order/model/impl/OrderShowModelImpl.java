package com.shian.shianlife.mvp.order.model.impl;


import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;
import com.shian.shianlife.mvp.order.model.IOrderShowModel;
import com.shian.shianlife.thisenum.AppRolePermition;
import com.shian.shianlife.thisenum.OrderItemShowEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class OrderShowModelImpl implements IOrderShowModel {

    @Override
    public void getOrderShowItems(OnGetDataListener<OrderShowResultBean> listener) {
        OrderShowResultBean resultBean = new OrderShowResultBean();
        List<OrderShowResultBean.Item> listData = new ArrayList<>();

        //是否有公墓权限
        if (AppContansts.userCemetery != null) {
            for (int i = 0; i < AppContansts.userCemetery.getPermitionCodes().size(); i++) {
                if (AppContansts.userCemetery.getPermitionCodes().get(i).equals(AppRolePermition.ADVISOR.getCode())) {
                    listData.add(getItem(OrderItemShowEnum.cemetery));
                }
            }
        }

        listData.add(getItem(OrderItemShowEnum.funeral));
//        listData.add(getItem(OrderItemShowEnum.store));

        
        resultBean.setList(listData);
        listener.getDataSuccess(resultBean);
    }

    private OrderShowResultBean.Item getItem(OrderItemShowEnum itemShowEnum) {
        OrderShowResultBean.Item item = new OrderShowResultBean.Item();
        item.setName(itemShowEnum.getName());
        item.setPicId(itemShowEnum.getItemPic());
        item.setIntentClass(itemShowEnum.getIntentClass());
        return item;
    }
}
