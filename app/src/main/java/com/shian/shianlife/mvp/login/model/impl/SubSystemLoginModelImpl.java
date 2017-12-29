package com.shian.shianlife.mvp.login.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.model.ISubSystemLoginModel;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;


/**
 * Created by zm.
 */

public class SubSystemLoginModelImpl implements ISubSystemLoginModel {
    @Override
    public void subSystemStoreLogin(Context context, String loginKey, final OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().loginStoreSystem(context, loginKey, new HttpResponseHandler() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }

    @Override
    public void subSystemOrderCenterLogin(Context context, String loginKey, final OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().loginOrderCenterSystem(context, loginKey, new HttpResponseHandler() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }

    @Override
    public void subSystemCemeteryLogin(Context context, String loginKey, final OnGetDataListener listener) {
        MHttpManagerFactory.getSystemManager().loginCemeterySystem(context, loginKey, new HttpResponseHandler() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }
}
