package com.shian.shianlife.mvp.pay.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayBean;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayResultBean;
import com.shian.shianlife.mvp.pay.model.IWeChatPrePayModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class WeChatPrePayModelImpl implements IWeChatPrePayModel {
    @Override
    public void wechatPrePay(Context context, WeChatPrePayBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getPayManager().createPrePay(context, params, new HttpResponseHandler<WeChatPrePayResultBean>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(WeChatPrePayResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataSuccess(message);
            }

        });

    }
}
