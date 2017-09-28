package com.shian.shianlife.provide.imp;

import com.shian.shianlife.mvp.pay.bean.WeChatPrePayBean;
import com.shian.shianlife.mvp.pay.bean.WeChatPrePayResultBean;
import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCreatePrePayParams;
import com.shian.shianlife.provide.result.HrCreatePrePayResult;

import android.content.Context;

public interface PayManager extends HttpManager {

	public void createPrePay(Context context, WeChatPrePayBean params, HttpResponseHandler<WeChatPrePayResultBean> response);

}
