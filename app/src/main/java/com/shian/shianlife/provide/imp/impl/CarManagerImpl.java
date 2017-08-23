package com.shian.shianlife.provide.imp.impl;


import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.CarManager;
import com.shian.shianlife.provide.params.HpCarBuildOrder;
import com.shian.shianlife.provide.result.HrGetCarDetails;

/**
 * Created by zm.
 */

public class CarManagerImpl extends BaseManagerImpl implements CarManager {
    private static CarManagerImpl manager;

    private CarManagerImpl() {
        super();
        baseUrl = AppContansts.Cemetery_BaseUrl;
    }

    public static CarManagerImpl getInstance() {
        if (manager == null) {
            manager = new CarManagerImpl();
        }
        return manager;
    }

    @Override
    public void saveCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<Object> handler) {
        requestPost(context, "cars/apply/using", Object.class, params, handler, true);
    }

    @Override
    public void getCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<HrGetCarDetails> handler) {
        requestPost(context, "cars/apply/handle/info", HrGetCarDetails.class, params, handler, true);
    }

}
