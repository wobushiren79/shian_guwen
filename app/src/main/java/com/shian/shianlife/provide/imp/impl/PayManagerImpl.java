package com.shian.shianlife.provide.imp.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayBean;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayResultBean;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.PayManager;
import com.shian.shianlife.provide.params.HpCreatePrePayParams;
import com.shian.shianlife.provide.result.HrCreatePrePayResult;

import android.content.Context;

public class PayManagerImpl extends BaseManagerImpl implements PayManager {
    private static PayManagerImpl manager;

    private PayManagerImpl() {
        super();
        baseUrl = "http://192.168.0.89/shian_goods";
    }

    public static PayManagerImpl getInstance() {
        if (manager == null) {
            synchronized (PayManagerImpl.class) {
                if (manager == null) {
                    manager = new PayManagerImpl();
                }
            }
        }
        return manager;
    }

    @Override
    public void createPrePay(Context context, WeChatPrePayBean params, HttpResponseHandler<WeChatPrePayResultBean> response) {
        requestPostFormToListForObj(context, "api/weixing/app_unified_order", WeChatPrePayResultBean.class, params, response, true);
    }

}
