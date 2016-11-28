package com.shian.shianlife.provide.imp;

import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpLoadAddressParams;
import com.shian.shianlife.provide.result.HpLoadAddressResult;

import android.content.Context;

public interface AddressManager extends HttpManager {

	public void loadAddress(Context mContext, HpLoadAddressParams mParams,
			HttpResponseHandler<HpLoadAddressResult> handler);

}
