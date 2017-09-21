package com.shian.shianlife.mvp.userinfo.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralResultBean;
import com.shian.shianlife.mvp.userinfo.model.IUserInfoIntegralModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class UserInfoIntegralModelImpl implements IUserInfoIntegralModel {
    @Override
    public void getUserInfoIntegral(Context context, UserInfoIntegralBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().getUserInfoIntegral(context, params, new HttpResponseHandler<UserInfoIntegralResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(UserInfoIntegralResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
