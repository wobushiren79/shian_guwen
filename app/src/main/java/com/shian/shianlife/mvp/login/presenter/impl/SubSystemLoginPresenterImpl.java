package com.shian.shianlife.mvp.login.presenter.impl;


import com.shian.shianlife.common.contanst.AppContansts;
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
        subSystemLoginModel.subSystemStoreLogin(subSystemLoginView.getContext(), AppContansts.System_Ki4so_Client_Ec);
    }

    @Override
    public void loginCemeterySystem() {
        subSystemLoginModel.subSystemCemeteryLogin(subSystemLoginView.getContext(), AppContansts.System_Ki4so_Client_Ec);
    }
}
