package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.PHPManager;
import com.shian.shianlife.provide.phpresult.PHPHrGetAdvertisement;
import com.shian.shianlife.provide.phpresult.PHPHrGetDynamic;
import com.shian.shianlife.provide.phpresult.PHPHrGetHotIssue;
import com.shian.shianlife.provide.phpresult.PHPHrGetSiftListData;
import com.shian.shianlife.provide.phpresult.PHPHrGetVersion;

/**
 * Created by Administrator on 2017/3/4.
 */

public class PHPManagerImpl implements PHPManager {
    public HttpRequestExecutor excutor = new HttpRequestExecutor();
    private static PHPManager manager;

    private PHPManagerImpl() {
    }

    public static PHPManager getInstance() {
        if (manager == null) {
            manager = new PHPManagerImpl();
        }
        return manager;
    }


    @Override
    public void getAdvertisement(Context context, RequestParams params, HttpResponseHandler<PHPHrGetAdvertisement> handler) {
        excutor.requestPHPGet(context, "Home/index/advertising", PHPHrGetAdvertisement.class,
                params, handler, false);
    }

    @Override
    public void getDynamicInfo(Context context, RequestParams params, HttpResponseHandler<PHPHrGetDynamic> handler) {
        excutor.requestPHPGet(context, "Home/index/dynamic", PHPHrGetDynamic.class,
                params, handler, false);
    }

    @Override
    public void getSiftListData(Context context, RequestParams params, HttpResponseHandler<PHPHrGetSiftListData> handler) {
        excutor.requestPHPGet(context, "Home/index/sift", PHPHrGetSiftListData.class,
                params, handler, false);
    }

    @Override
    public void setSiftData(Context context, RequestParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPHPGet(context, "Home/index/siftuser", Object.class,
                params, handler, false);
    }

    @Override
    public void getHotIssue(Context context, RequestParams params, HttpResponseHandler<PHPHrGetHotIssue> handler) {
        excutor.requestPHPGet(context, "Home/index/help", PHPHrGetHotIssue.class,
                params, handler, false);
    }

    @Override
    public void setOpinion(Context context, RequestParams params, HttpResponseHandler<Object> handler, boolean isDialog) {
        excutor.requestPHPGet(context, "Home/index/opinion", Object.class,
                params, handler, isDialog);
    }

    @Override
    public void getVersion(Context context, RequestParams params, HttpResponseHandler<PHPHrGetVersion> handler, boolean isDialog) {

    }

}
