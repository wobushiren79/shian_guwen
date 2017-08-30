package com.shian.shianlife.mvp.login.presenter.impl;


import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.SystemLoginBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginOutResultBean;
import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.login.bean.UserLoginConfig;
import com.shian.shianlife.mvp.login.model.IUserLoginModel;
import com.shian.shianlife.mvp.login.model.impl.UserLoginModelImpl;
import com.shian.shianlife.mvp.login.presenter.IUserLoginPresenter;
import com.shian.shianlife.mvp.login.view.IUserLoginOutView;
import com.shian.shianlife.mvp.login.view.IUserLoginView;

/**
 * Created by zm.
 */

public class UserLoginPresenterImpl implements IUserLoginPresenter {
    IUserLoginView userLoginView;
    IUserLoginOutView userLoginOutView;
    IUserLoginModel userLoginModel;

    public UserLoginPresenterImpl(IUserLoginView userLoginView, IUserLoginOutView userLoginOutView) {
        this.userLoginView = userLoginView;
        this.userLoginOutView = userLoginOutView;
        userLoginModel = new UserLoginModelImpl();
    }

    @Override
    public void loginSystem() {
        SystemLoginBean params = new SystemLoginBean();
        params.setUserName(userLoginView.getUserName());
        params.setUserPwd(userLoginView.getPassWord());
        userLoginModel.loginSystem(userLoginView.getContext(), params, new OnGetDataListener<SystemLoginResultBean>() {

            @Override
            public void getDataSuccess(SystemLoginResultBean result) {
                AppContansts.systemLoginInfo = result;
                userLoginView.loginSystemSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                userLoginView.loginSystemFail(msg);
            }

        });
    }

    @Override
    public void loginOutSystem() {
        SystemLoginOutBean params = new SystemLoginOutBean();
        userLoginModel.loginOutSystem(userLoginOutView.getContext(), params, new OnGetDataListener<SystemLoginOutResultBean>() {
            @Override
            public void getDataSuccess(SystemLoginOutResultBean result) {
                userLoginOutView.loginOutSystemSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                userLoginOutView.loginOutSystemFail(msg);
            }
        });
    }

    @Override
    public void saveLoginConfig() {
        UserLoginConfig loginConfig = new UserLoginConfig();
        loginConfig.setUserName(userLoginView.getUserName());
        loginConfig.setPassWord(userLoginView.getPassWord());
        loginConfig.setAutoLogin(userLoginView.getIsAutoLogin());
        loginConfig.setKeepAccount(userLoginView.getIsKeepAccount());
        userLoginModel.saveLoginConfig(userLoginView.getContext(), loginConfig);
    }

    @Override
    public void getLoginConfig() {
        UserLoginConfig loginConfig = userLoginModel.getLoginConfig(userLoginView.getContext());
        userLoginView.setUserName(loginConfig.getUserName());
        userLoginView.setPassWord(loginConfig.getPassWord());
        userLoginView.setIsKeepAccount(loginConfig.isKeepAccount());
        userLoginView.setIsAutoLogin(loginConfig.isAutoLogin());
    }
}
