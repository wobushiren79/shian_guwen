package com.shian.shianlife.mvp.login.presenter.impl;


import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.model.ISubSystemLoginModel;
import com.shian.shianlife.mvp.login.model.impl.SubSystemLoginModelImpl;
import com.shian.shianlife.mvp.login.presenter.ISubSystemLoginPresenter;
import com.shian.shianlife.mvp.login.view.ISubSystemLoginView;

/**
 * Created by zm.
 */

public class SubSystemLoginPresenterImpl implements ISubSystemLoginPresenter {

    ISubSystemLoginView subSystemLoginView;
    ISubSystemLoginModel subSystemLoginModel;

    public SubSystemLoginPresenterImpl(ISubSystemLoginView subSystemLoginView) {
        this.subSystemLoginView = subSystemLoginView;
        subSystemLoginModel = new SubSystemLoginModelImpl();
    }

    @Override
    public void loginStoreSystem() {
        subSystemLoginModel.subSystemStoreLogin(subSystemLoginView.getContext(), AppContansts.System_Ki4so_Client_Ec, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                subSystemLoginView.loginGoodsSuccess();
            }

            @Override
            public void getDataFail(String msg) {
                subSystemLoginView.loginGoodsFail();
            }
        });
    }

    @Override
    public void loginOrderCenterSystem() {
        subSystemLoginModel.subSystemOrderCenterLogin(subSystemLoginView.getContext(), AppContansts.System_Ki4so_Client_Ec, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                subSystemLoginView.loginOrderCenterSuccess();
            }

            @Override
            public void getDataFail(String msg) {
                subSystemLoginView.loginOrderCenterFail();
            }
        });
    }

    @Override
    public void loginCemeterySystem() {
        subSystemLoginModel.subSystemCemeteryLogin(subSystemLoginView.getContext(), AppContansts.System_Ki4so_Client_Ec, new OnGetDataListener() {
            @Override
            public void getDataSuccess(Object result) {
                subSystemLoginView.loginCemeterySuccess();
            }

            @Override
            public void getDataFail(String msg) {
                subSystemLoginView.loginCemeteryFail();
            }
        });
    }
}
