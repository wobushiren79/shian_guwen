package com.shian.shianlife.mvp.login.model.impl;

import android.content.Context;


import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.SystemLoginBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.login.bean.UserLoginConfig;
import com.shian.shianlife.mvp.login.model.IUserLoginModel;
import com.shian.shianlife.mvp.login.presenter.ISubSystemLoginPresenter;
import com.shian.shianlife.mvp.login.presenter.impl.SubSystemLoginPresenterImpl;
import com.shian.shianlife.mvp.login.view.ISubSystemLoginView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;

import okhttp3.Request;

/**
 * Created by zm.
 */

public class UserLoginModelImpl implements IUserLoginModel, ISubSystemLoginView {
    private ISubSystemLoginPresenter subSystemLoginPresenter;
    private Context context;

    private boolean isLoginCemetery = false;
    private boolean isLoginGoods = false;
    private boolean isLoginOrderCenter = false;

    private OnGetDataListener<SystemLoginResultBean> listener;
    private SystemLoginResultBean result;

    @Override
    public void loginSystem(final Context context, SystemLoginBean params, final OnGetDataListener<SystemLoginResultBean> listener) {
        this.context = context;
        this.listener = listener;
        AppContansts.cookieStore.clear();
        MHttpManagerFactory.getSystemManager().loginSystem(context, params, new HttpResponseHandler<SystemLoginResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(SystemLoginResultBean result) {
                //登录子系统
                UserLoginModelImpl.this.result = result;
                subSystemLoginPresenter = new SubSystemLoginPresenterImpl(UserLoginModelImpl.this);
                subSystemLoginPresenter.loginStoreSystem();
                subSystemLoginPresenter.loginOrderCenterSystem();
                subSystemLoginPresenter.loginCemeterySystem();
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }

    @Override
    public void loginOutSystem(Context context, SystemLoginOutBean params, final OnGetDataListener<SystemLoginOutResultBean> listener) {
        MHttpManagerFactory.getSystemManager().loginOutSystem(context, params, new HttpResponseHandler<SystemLoginOutResultBean>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(SystemLoginOutResultBean result) {
                listener.getDataSuccess(result);
            }

            @Override
            public void onError(String message) {
                listener.getDataFail(message);
            }
        });
    }


    @Override
    public void saveLoginConfig(Context context, UserLoginConfig loginConfig) {
        SharePerfrenceUtils.setLoginShare(context, loginConfig.getUserName(), loginConfig.getPassWord(), loginConfig.isKeepAccount(), loginConfig.isAutoLogin());
    }

    @Override
    public UserLoginConfig getLoginConfig(Context context) {
        return SharePerfrenceUtils.getLoginShareSys(context);
    }
    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void loginCemeterySuccess() {
        isLoginCemetery = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginCemeteryFail() {
        isLoginCemetery = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginGoodsSuccess() {
        isLoginGoods = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginGoodsFail() {
        isLoginGoods = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginOrderCenterSuccess() {
        isLoginOrderCenter = true;
        checkLoginAllSystem();
    }

    @Override
    public void loginOrderCenterFail() {
        isLoginOrderCenter = true;
        checkLoginAllSystem();
    }

    private synchronized boolean checkLoginAllSystem() {
        if (isLoginCemetery && isLoginOrderCenter && isLoginGoods) {
            listener.getDataSuccess(result);
            return true;
        } else {
            return false;
        }
    }

}
