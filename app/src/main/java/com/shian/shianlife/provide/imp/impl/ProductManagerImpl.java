package com.shian.shianlife.provide.imp.impl;

import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.ProductManager;
import com.shian.shianlife.provide.params.HpGetAddedCtgListParams;
import com.shian.shianlife.provide.params.HpGetGoodsListParams;
import com.shian.shianlife.provide.result.HrGetAddedCtgListResult;
import com.shian.shianlife.provide.result.HrGetCemeteryResult;
import com.shian.shianlife.provide.result.HrGetFuneralSetmealResult;
import com.shian.shianlife.provide.result.HrGetGoodsListResult;
import com.shian.shianlife.provide.result.HrGetMainSetmealResult;

import android.content.Context;

public class ProductManagerImpl implements ProductManager {

	private static volatile ProductManagerImpl manager;

	private HttpRequestExecutor excutor;

	private ProductManagerImpl() {
		excutor = new HttpRequestExecutor();
	}

	public static ProductManagerImpl getInstance() {
		if (manager == null) {
			synchronized (ProductManagerImpl.class) {
				if (manager == null) {
					manager = new ProductManagerImpl();
				}
			}
		}
		return manager;
	}

	@Override
	public void getMainSetmeal(Context context, HttpResponseHandler<HrGetMainSetmealResult> response) {
		BaseHttpParams params = new BaseHttpParams();
		excutor.requestPost(context, "setmeal/main/get", HrGetMainSetmealResult.class, params, response);
	}

	@Override
	public void getFuneralsSetmeal(Context context, HttpResponseHandler<HrGetFuneralSetmealResult> response) {
		BaseHttpParams params = new BaseHttpParams();
		excutor.requestPost(context, "setmeal/funeral/get", HrGetFuneralSetmealResult.class, params, response);
	}

	@Override
	public void getCemeteryResult(Context context, HttpResponseHandler<HrGetCemeteryResult> respsone) {
		BaseHttpParams params = new BaseHttpParams();
		excutor.requestPost(context, "setmeal/cemetery/get", HrGetCemeteryResult.class, params, respsone);

	}

	@Override
	public void getAddedCtgList(Context context, HpGetAddedCtgListParams params,
			HttpResponseHandler<HrGetAddedCtgListResult> response) {
		excutor.requestPost(context, "sku/category/get", HrGetAddedCtgListResult.class, params, response);
	}

	@Override
	public void getGoodsList(Context context, HpGetGoodsListParams params,
			HttpResponseHandler<HrGetGoodsListResult> response) {
		excutor.requestPost(context, "sku/product/get", HrGetGoodsListResult.class, params, response);
	}

}
