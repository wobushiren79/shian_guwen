package com.shian.shianlife.mvp.userinfo.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignResultBean;
import com.shian.shianlife.mvp.userinfo.model.IUserInfoSignModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class UserInfoSignModelImpl implements IUserInfoSignModel {
    @Override
    public void userInfoSign(Context context, UserInfoSignBean params, final OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().userInfoSign(context, params, new HttpResponseHandler<UserInfoSignResultBean>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(UserInfoSignResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }

        });
    }
}
