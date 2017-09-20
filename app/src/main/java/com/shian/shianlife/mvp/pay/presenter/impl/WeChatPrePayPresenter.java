package com.shian.shianlife.mvp.pay.presenter.impl;

import com.shian.shianlife.mvp.base.OnGetDataListener;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayBean;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayResultBean;
import com.shian.shianlife.mvp.pay.model.IWeChatPrePayModel;
import com.shian.shianlife.mvp.pay.model.impl.WeChatPrePayModelImpl;
import com.shian.shianlife.mvp.pay.presenter.IWeChatPrePayPresenter;
import com.shian.shianlife.mvp.pay.view.IWeChatPrePayView;

/**
 * Created by zm.
 */

public class WeChatPrePayPresenter implements IWeChatPrePayPresenter {
    private IWeChatPrePayModel weChatPrePayModel;
    private IWeChatPrePayView weChatPrePayView;

    public WeChatPrePayPresenter(IWeChatPrePayView weChatPrePayView) {
        this.weChatPrePayView = weChatPrePayView;
        weChatPrePayModel = new WeChatPrePayModelImpl();
    }

    @Override
    public void wechatPrePay() {
        if (weChatPrePayView.getContext() == null) {
            weChatPrePayView.showToast("数据错误");
            return;
        }
        WeChatPrePayBean params = new WeChatPrePayBean();
        weChatPrePayModel.wechatPrePay(weChatPrePayView.getContext(), params, new OnGetDataListener<WeChatPrePayResultBean>() {
            @Override
            public void getDataSuccess(WeChatPrePayResultBean result) {
                weChatPrePayView.wechatPrePaySuccess(result);
            }

            @Override
            public void getDataFail(String msg) {
                weChatPrePayView.wechatPrePayFail(msg);
            }
        });
    }
}
