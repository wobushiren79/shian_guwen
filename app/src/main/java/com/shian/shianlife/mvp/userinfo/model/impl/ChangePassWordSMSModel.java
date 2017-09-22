package com.shian.shianlife.mvp.userinfo.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSBean;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSResultBean;
import com.shian.shianlife.mvp.userinfo.model.IChangePassWordSMSModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class ChangePassWordSMSModel implements IChangePassWordSMSModel {
    @Override
    public void passWordSMS(Context context, ChangePassWordSMSBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().changePassWordSMS(context, params, new HttpResponseHandler<ChangePassWordSMSResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(ChangePassWordSMSResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
