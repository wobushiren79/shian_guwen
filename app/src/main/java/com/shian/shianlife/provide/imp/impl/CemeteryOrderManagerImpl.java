package com.shian.shianlife.provide.imp.impl;

import android.content.Context;
import android.util.Log;

import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.CemeteryOrderManager;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;

/**
 * Created by Administrator on 2017/1/18.
 */

public class CemeteryOrderManagerImpl implements CemeteryOrderManager {

    private static volatile CemeteryOrderManagerImpl manager;

    private HttpRequestExecutor excutor;
    private String[] getOrderListMethod = { "cemetery/ordered/list/talk", "cemetery/ordered/list/afterMarket", "cemetery/ordered/list/afterMarket" };

    private CemeteryOrderManagerImpl() {
        excutor = new HttpRequestExecutor();
    }

    public static CemeteryOrderManagerImpl getInstance() {
        if (manager == null) {
            synchronized (AddressManagerImpl.class) {
                if (manager == null) {
                    manager = new CemeteryOrderManagerImpl();
                }
            }
        }
        return manager;
    }

    /**
     * 获取订单列表
     *
     * @param context
     * @param params
     * @param orderType
     *            0-洽谈列表 1-售后列表 2服务结束列表
     * @param response
     */
    @Override
    public void getOrderList(Context context, HpGetOrderListParams params, int orderType,
                             HttpResponseHandler<HrGetCemeteryListData> response) {
        excutor.requestPost(context, getOrderListMethod[orderType], HrGetCemeteryListData.class, params, response);
    }
}
