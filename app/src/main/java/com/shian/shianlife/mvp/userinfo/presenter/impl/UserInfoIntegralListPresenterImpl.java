package com.shian.shianlife.mvp.userinfo.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListResultBean;
import com.shian.shianlife.mvp.userinfo.model.IUserInfoIntegralListModel;
import com.shian.shianlife.mvp.userinfo.model.impl.UserInfoIntegralListModelImpl;
import com.shian.shianlife.mvp.userinfo.presenter.IUserInfoIntegralListPresenter;
import com.shian.shianlife.mvp.userinfo.view.IUserInfoIntegralListView;

/**
 * Created by zm.
 */

public class UserInfoIntegralListPresenterImpl implements IUserInfoIntegralListPresenter {
    private IUserInfoIntegralListView userInfoIntegralListView;
    private IUserInfoIntegralListModel userInfoIntegralListModel;

    public UserInfoIntegralListPresenterImpl(IUserInfoIntegralListView userInfoIntegralListView) {
        this.userInfoIntegralListView = userInfoIntegralListView;
        userInfoIntegralListModel = new UserInfoIntegralListModelImpl();
    }

    @Override
    public void getIntegralList() {
        if (userInfoIntegralListView.getContext() == null) {
            userInfoIntegralListView.showToast("数据错误");
            return;
        }

        if (userInfoIntegralListView.getPageNumber() == null) {
            userInfoIntegralListView.showToast("没有页数");
            return;
        }
        if (userInfoIntegralListView.getPageSize() == null) {
            userInfoIntegralListView.showToast("没有页大小");
            return;
        }
        UserInfoIntegralListBean params = new UserInfoIntegralListBean();
        params.setPageSize(userInfoIntegralListView.getPageSize());
        params.setPageNumber(userInfoIntegralListView.getPageNumber());
        userInfoIntegralListModel.getIntegralList(userInfoIntegralListView.getContext(), params, new OnGetDataListener<UserInfoIntegralListResultBean>() {
            @Override
            public void getDataSuccess(UserInfoIntegralListResultBean result) {
                userInfoIntegralListView.getIntegralListSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                userInfoIntegralListView.getIntegralListFail(msg);
            }
        });
    }
}
