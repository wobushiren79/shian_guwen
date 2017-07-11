package com.shian.shianlife.mvp.userinfo.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoResultBean;
import com.shian.shianlife.mvp.userinfo.model.IUserInfoModel;
import com.shian.shianlife.mvp.userinfo.model.impl.UserInfoModelImpl;
import com.shian.shianlife.mvp.userinfo.presenter.IUserInfoPresenter;
import com.shian.shianlife.mvp.userinfo.view.IUserInfoView;

/**
 * Created by zm.
 */

public class UserInfoPresenterImpl implements IUserInfoPresenter {
    IUserInfoModel userInfoModel;
    IUserInfoView userInfoView;


    public UserInfoPresenterImpl(IUserInfoView userInfoView) {
        this.userInfoView = userInfoView;
        userInfoModel = new UserInfoModelImpl();
    }

    @Override
    public void getUserInfoData() {
        UserInfoBean params = new UserInfoBean();
        userInfoModel.getUserInfoData(userInfoView.getContext(), params, new OnGetDataListener<UserInfoResultBean>() {
            @Override
            public void getDataSuccess(UserInfoResultBean result) {
                AppContansts.userInfoData = result;
                userInfoView.ChangeHeadImage(AppContansts.OSSURL + result.getHeadImg());
                userInfoView.ChangeName(result.getName());
                userInfoView.ChangePhone(result.getMobile());
                userInfoView.ChangePoint(result.getAvgSatis() + "");
            }

            @Override
            public void getDataFail(String msg) {

            }
        });
    }
}
