package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.FuneralOrderManager;
import com.shian.shianlife.provide.params.HpCreateOrderParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.provide.result.HrGetOrderListResult;
import com.shian.shianlife.provide.result.HrOderId;

public class FuneralOrderManagerImpl extends BaseManagerImpl implements FuneralOrderManager {
    private static  FuneralOrderManagerImpl manager;

    private String[] getOrderListMethod = {"order/list/talk", "order/list/waitService", "order/list/dispatch",
            "order/list/waitAudit", "order/list/waitMoney", "order/list/finish"};

    private FuneralOrderManagerImpl() {
        super();
        baseUrl = AppContansts.Funeral_BaseUrl;
    }

    public static FuneralOrderManagerImpl getInstance() {
        if (manager == null) {
            synchronized (FuneralOrderManagerImpl.class) {
                if (manager == null) {
                    manager = new FuneralOrderManagerImpl();
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
     * @param orderType 0-洽谈列表 1-待服务列表 2-派单中列表 3-待审核列表 4-待收款列表 5-服务结束列表
     * @param response
     */
    @Override
    public void getOrderList(Context context, HpGetOrderListParams params, int orderType,
                             HttpResponseHandler<HrGetOrderListResult> response) {
        requestPost(context, getOrderListMethod[orderType], HrGetOrderListResult.class, params, response);
    }

    @Override
    public void createOrder(Context context, HpCreateOrderParams params, HttpResponseHandler<HrOderId> response) {
        requestPost(context, "order/create", HrOderId.class, params, response);

    }

    @Override
    public void getOrderDetail(Context context, HpGetOrderDetailParams params,
                               HttpResponseHandler<HrGetOrderDetailResult> response) {
        requestPost(context, "order/view", HrGetOrderDetailResult.class, params, response);

    }

    @Override
    public void editOrder(Context context, HpCreateOrderParams params,
                          HttpResponseHandler<HrOderId> response) {
        // TODO Auto-generated method stub
        requestPost(context, "order/edit/save", HrOderId.class, params, response);
    }

    // @Override
    // public void getTalkList(Context context, HpGetTalkListParams params,
    // HttpResponseHandler<HrGetTalkListResult> response) {
    // requestPost(context, "order/list/talk",
    // HrGetTalkListResult.class, params, response);
    // }

    // @Override
    // public void getWaitServiceList(Context context,
    // HpGetWaitServiceListParams params,
    // HttpResponseHandler<HrGetWaitServiceListResult> response) {
    // requestPost(context, "order/list/waitService",
    // HrGetWaitServiceListResult.class, params, response);
    // }

}
