package com.shian.shianlife.provide.imp;

import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpGetAddedCtgListParams;
import com.shian.shianlife.provide.params.HpGetGoodsListParams;
import com.shian.shianlife.provide.result.HrGetAddedCtgListResult;
import com.shian.shianlife.provide.result.HrGetCemeteryResult;
import com.shian.shianlife.provide.result.HrGetFuneralSetmealResult;
import com.shian.shianlife.provide.result.HrGetGoodsListResult;
import com.shian.shianlife.provide.result.HrGetMainSetmealResult;

import android.content.Context;

public interface ProductManager extends HttpManager {

	/**
	 * 获取主套餐信息
	 * 
	 * @param context
	 * @param response
	 */
	public void getMainSetmeal(Context context, HttpResponseHandler<HrGetMainSetmealResult> response);

	/**
	 * 获取殡仪馆信息
	 * 
	 * @param context
	 * @param response
	 */
	public void getFuneralsSetmeal(Context context, HttpResponseHandler<HrGetFuneralSetmealResult> response);

	/**
	 * 获取公墓套餐列表
	 * 
	 * @param context
	 * @param repsone
	 */
	public void getCemeteryResult(Context context, HttpResponseHandler<HrGetCemeteryResult> repsone);

	/**
	 * 获取增值产品分类列表
	 * 
	 * @param context
	 * @param params
	 * @param response
	 */
	public void getAddedCtgList(Context context, HpGetAddedCtgListParams params,
			HttpResponseHandler<HrGetAddedCtgListResult> response);

	/**
	 * 获取增值产品下的商品列表
	 * 
	 * @param context
	 * @param params
	 * @param response
	 */
	public void getGoodsList(Context context, HpGetGoodsListParams params,
			HttpResponseHandler<HrGetGoodsListResult> response);

}
