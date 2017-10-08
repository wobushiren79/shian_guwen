package com.shian.shianlife.mvp.pay.presenter.impl;

import com.shian.shianlife.common.contanst.AppContansts;
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
        if(weChatPrePayView.wechatGetPayTotal()==null||weChatPrePayView.wechatGetPayTotal()<=0){
            weChatPrePayView.showToast("价格有误");
            return;
        }
        WeChatPrePayBean params = new WeChatPrePayBean();
        params.setAppid(AppContansts.WeChat_Pay_AppId);
        params.setTotal_fee(weChatPrePayView.wechatGetPayTotal());
        weChatPrePayModel.wechatPrePay(weChatPrePayView.getContext(), params, new OnGetDataListener<WeChatPrePayResultBean>() {
            @Override
            public void getDataSuccess(WeChatPrePayResultBean result) {
                if (result.getResult() == null || result.getResult().getReturn_code() == null) {
                    weChatPrePayView.wechatPrePayFail("没有return_code");
                    return;
                }
                if (result.getResult().getReturn_code().equals("SUCCESS")) {
                    String paypreId = result.getPrepayid();
                    String tempPaypreId = paypreId.replace("prepay_id=", "");
                    result.setPrepayid(tempPaypreId);
                    weChatPrePayView.wechatPrePaySuccess(result);
                } else {
                    weChatPrePayView.wechatPrePayFail(result.getResult().getReturn_msg());
                }

            }

            @Override
            public void getDataFail(String msg) {
                weChatPrePayView.wechatPrePayFail(msg);
            }
        });
    }
}
