package com.shian.shianlife.mvp.order.model.impl;


import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.order.bean.OrderShowResultBean;
import com.shian.shianlife.mvp.order.model.IOrderShowModel;
import com.shian.shianlife.thisenum.AppRolePermition;
import com.shian.shianlife.thisenum.OrderItemShowEnum;
import com.shian.shianlife.thisenum.RoleEnum;

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
        if (AppContansts.systemLoginInfo != null && AppContansts.systemLoginInfo.getResourceCodes() != null) {
            for (String roleCode : AppContansts.systemLoginInfo.getResourceCodes()) {
                if (roleCode.equals(RoleEnum.Funeral_Advisor.getCode())) {
                    listData.add(getItem(OrderItemShowEnum.funeral));
                } else if (roleCode.equals(RoleEnum.Cemetery_Advisor.getCode())) {
                    listData.add(getItem(OrderItemShowEnum.cemetery));
                } else if (roleCode.equals(RoleEnum.Goods_Advisor.getCode())) {
                    listData.add(getItem(OrderItemShowEnum.store));
                }
            }
        }

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
