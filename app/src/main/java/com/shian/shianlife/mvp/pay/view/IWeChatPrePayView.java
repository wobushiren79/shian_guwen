package com.shian.shianlife.mvp.pay.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayResultBean;

/**
 * Created by zm.
 */

public interface IWeChatPrePayView extends BaseMVPView {
    void wechatPrePaySuccess(WeChatPrePayResultBean resultBean);

    void wechatPrePayFail(String msg);

    Float wechatGetPayTotal();
}
