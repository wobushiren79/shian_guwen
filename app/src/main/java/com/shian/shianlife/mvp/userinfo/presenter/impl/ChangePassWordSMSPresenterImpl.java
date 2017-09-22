package com.shian.shianlife.mvp.userinfo.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSBean;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSResultBean;
import com.shian.shianlife.mvp.userinfo.model.IChangePassWordSMSModel;
import com.shian.shianlife.mvp.userinfo.model.impl.ChangePassWordSMSModel;
import com.shian.shianlife.mvp.userinfo.presenter.IChangePassWordSMSPresenter;
import com.shian.shianlife.mvp.userinfo.view.IChangePassWordSMSView;

/**
 * Created by zm.
 */

public class ChangePassWordSMSPresenterImpl implements IChangePassWordSMSPresenter {
    private IChangePassWordSMSModel changePassWordSMSModel;
    private IChangePassWordSMSView changePassWordSMSView;

    public ChangePassWordSMSPresenterImpl(IChangePassWordSMSView changePassWordSMSView) {
        this.changePassWordSMSView = changePassWordSMSView;
        changePassWordSMSModel = new ChangePassWordSMSModel();
    }


    @Override
    public void changePassWordSMS() {
        if (changePassWordSMSView.getContext() == null) {
            changePassWordSMSView.showToast("数据错误");
            return;
        }
        if (changePassWordSMSView.getUserPhone().isEmpty()) {
            changePassWordSMSView.showToast("还未输入手机号码");
            return;
        }

        if (changePassWordSMSView.getNewPassWord().isEmpty()) {
            changePassWordSMSView.showToast("还未输入新密码");
            return;
        }
        int smsCode;
        try {
            if (changePassWordSMSView.getMsgCode().isEmpty()) {
                changePassWordSMSView.showToast("还未输入验证码");
                return;
            }
            smsCode = Integer.valueOf(changePassWordSMSView.getMsgCode());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        ChangePassWordSMSBean params = new ChangePassWordSMSBean();
        params.setMobile(changePassWordSMSView.getUserPhone());
        params.setKeys(changePassWordSMSView.getNewPassWord());
        params.setMsgCode(smsCode);
        changePassWordSMSModel.passWordSMS(changePassWordSMSView.getContext(), params, new OnGetDataListener<ChangePassWordSMSResultBean>() {
            @Override
            public void getDataSuccess(ChangePassWordSMSResultBean result) {
                changePassWordSMSView.changePassWordSMSSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                changePassWordSMSView.changePassWordSMSFail(msg);
            }
        });
    }

    @Override
    public void getSMSCode() {
        if (changePassWordSMSView.getContext() == null) {
            changePassWordSMSView.showToast("数据错误");
            return;
        }
        if (changePassWordSMSView.getUserPhone().isEmpty()) {
            changePassWordSMSView.showToast("还未输入手机号码");
            return;
        }
        if (!Utils.isPhoneNumber(changePassWordSMSView.getUserPhone())) {
            changePassWordSMSView.showToast("输入手机格式不对");
            return;
        }
        ChangePassWordSMSBean params = new ChangePassWordSMSBean();
        params.setMobile(changePassWordSMSView.getUserPhone());
        changePassWordSMSModel.passWordSMS(changePassWordSMSView.getContext(), params, new OnGetDataListener<ChangePassWordSMSResultBean>() {
            @Override
            public void getDataSuccess(ChangePassWordSMSResultBean result) {
                changePassWordSMSView.getSMSCodeSuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                changePassWordSMSView.getSMSCodeFail(msg);
            }
        });
    }
}
