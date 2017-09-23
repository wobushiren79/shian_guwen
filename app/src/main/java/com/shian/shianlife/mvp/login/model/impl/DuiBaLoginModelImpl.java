package com.shian.shianlife.mvp.login.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginBean;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginResultBean;
import com.shian.shianlife.mvp.login.model.IDuiBaLoginModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class DuiBaLoginModelImpl implements IDuiBaLoginModel {
    @Override
    public void loginDuiBa(Context context, DuiBaLoginBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getWeChatManager().loginDuiBa(context, params, new HttpResponseHandler<DuiBaLoginResultBean>() {
            @Override
            public void onStart(Request request, int id) {
            }

            @Override
            public void onSuccess(DuiBaLoginResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
