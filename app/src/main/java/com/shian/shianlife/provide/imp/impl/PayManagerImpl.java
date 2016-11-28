package com.shian.shianlife.provide.imp.impl;

import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.PayManager;
import com.shian.shianlife.provide.params.HpCreatePrePayParams;
import com.shian.shianlife.provide.result.HrCreatePrePayResult;

import android.content.Context;

public class PayManagerImpl implements PayManager {

	private static volatile PayManagerImpl manager;

	private HttpRequestExecutor excutor;

	private PayManagerImpl() {
		excutor = new HttpRequestExecutor();
	}

	public static PayManagerImpl getInstance() {
		if (manager == null) {
			synchronized (AddressManagerImpl.class) {
				if (manager == null) {
					manager = new PayManagerImpl();
				}
			}
		}
		return manager;
	}

	@Override
	public void createPrePay(Context context, HpCreatePrePayParams params,
			HttpResponseHandler<HrCreatePrePayResult> response) {
		excutor.requestPost(context, "pay/prepay/create", HrCreatePrePayResult.class, params, response);
	}

}
