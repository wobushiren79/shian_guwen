package com.shian.shianlife.provide.imp.impl;

import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.AddressManager;
import com.shian.shianlife.provide.params.HpLoadAddressParams;
import com.shian.shianlife.provide.result.HpLoadAddressResult;

import android.content.Context;

public class AddressManagerImpl implements AddressManager {
	private HttpRequestExecutor excutor;
	private static volatile AddressManagerImpl manager;

	private AddressManagerImpl() {
		excutor = new HttpRequestExecutor();
	}

	public static AddressManagerImpl getInstance() {
		if (manager == null) {
			synchronized (AddressManagerImpl.class) {
				if (manager == null) {
					manager = new AddressManagerImpl();
				}
			}
		}
		return manager;
	}

	@Override
	public void loadAddress(Context mContext, HpLoadAddressParams mParams,
			HttpResponseHandler<HpLoadAddressResult> handler) {
		excutor.requestPost(mContext, "address/load", HpLoadAddressResult.class, mParams, handler);
	}

}
