package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCarBuildOrder;
import com.shian.shianlife.provide.result.HrGetCarDetails;

/**
 * Created by zm.
 */

public interface CarManager {

    /**
     * 保存派车单信息
     *
     * @param context
     * @param params
     * @param handler
     */
    void saveCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<Object> handler);

    /**
     * 获取订单详情
     *
     * @param context
     * @param params
     * @param handler
     */
    void getCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<HrGetCarDetails> handler);
}
