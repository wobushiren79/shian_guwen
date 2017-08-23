package com.shian.shianlife.provide.imp;

import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCreateOrderParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.provide.result.HrGetOrderListResult;
import com.shian.shianlife.provide.result.HrOderId;

import android.content.Context;

public interface FuneralOrderManager extends HttpManager {

	// /**
	// * 获取洽谈列表
	// *
	// * @param context
	// * @param params
	// * @param response
	// */
	// public void getTalkList(Context context, HpGetTalkListParams params,
	// HttpResponseHandler<HrGetTalkListResult> response);

	// /**
	// * 获取待服务订单列表
	// *
	// * @param context
	// * @param params
	// * @param response
	// */
	// public void getWaitServiceList(Context context,
	// HpGetWaitServiceListParams params,
	// HttpResponseHandler<HrGetWaitServiceListResult> response);
	//
	// /**
	// * 获取派单中的订单列表
	// *
	// * @param context
	// * @param params
	// * @param response
	// */
	// public void getDispatchList(Context context, HpGetDispatchListParams
	// params,
	// HttpResponseHandler<HrGetDispatchListResult> response);

	/**
	 * 获取订单列表
	 * 
	 * @param context
	 * @param params
	 * @param orderType
	 *            0-洽谈列表 1-待服务列表 2-派单中列表 3-待审核列表 4-待收款列表 5-服务结束列表
	 * @param response
	 */
	public void getOrderList(Context context, HpGetOrderListParams params, int orderType,
			HttpResponseHandler<HrGetOrderListResult> response);

	/**
	 * 新建订单
	 * 
	 * @param context
	 * @param params
	 * @param response
	 */
	public void createOrder(Context context, HpCreateOrderParams params, HttpResponseHandler<HrOderId> response);

	/**
	 * 编辑订单
	 * 
	 * @param context
	 * @param params
	 * @param response
	 */
	public void editOrder(Context context, HpCreateOrderParams params, HttpResponseHandler<HrOderId> response);

	/**
	 * 获取订单详情
	 * @param context
	 * @param params
	 * @param response
	 */
	public void getOrderDetail(Context context, HpGetOrderDetailParams params,
			HttpResponseHandler<HrGetOrderDetailResult> response);

}
