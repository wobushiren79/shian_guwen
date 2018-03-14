package com.shian.shianlife.mvp.login.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginBean;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginResultBean;
import com.shian.shianlife.mvp.login.model.IDuiBaLoginModel;
import com.shian.shianlife.mvp.login.model.impl.DuiBaLoginModelImpl;
import com.shian.shianlife.mvp.login.presenter.IDuiBaLoginPresenter;
import com.shian.shianlife.mvp.login.view.IDuiBaLoginView;

/**
 * Created by zm.
 */

public class DuiBaLoginPresenterImpl implements IDuiBaLoginPresenter {
    private IDuiBaLoginModel duiBaLoginModel;
    private IDuiBaLoginView duiBaLoginView;

    public DuiBaLoginPresenterImpl(IDuiBaLoginView duiBaLoginView) {
        this.duiBaLoginView = duiBaLoginView;
        duiBaLoginModel = new DuiBaLoginModelImpl();
    }

    @Override
    public void loginDuiBa() {
        if (duiBaLoginView.getContext() == null) {
            duiBaLoginView.showToast("没有上下文对象");
            return;
        }
        if (AppContansts.systemLoginInfo == null || AppContansts.systemLoginInfo.getUserId() == null) {
            duiBaLoginView.showToast("用户ID为空，请重新登录");
            return;
        }
        if (duiBaLoginView.getIntegral() == null || duiBaLoginView.getIntegral() == 0) {
            duiBaLoginView.showToast("没有积分");
            return;
        }
        DuiBaLoginBean params = new DuiBaLoginBean();
        params.setUid(AppContansts.systemLoginInfo.getUserId());
        params.setCredits(duiBaLoginView.getIntegral());
        duiBaLoginModel.loginDuiBa(duiBaLoginView.getContext(), params, new OnGetDataListener<DuiBaLoginResultBean>() {
            @Override
            public void getDataSuccess(DuiBaLoginResultBean result) {
                duiBaLoginView.loginDuiBaSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                duiBaLoginView.loginDuiBaFail(msg);
            }
        });
    }
}
