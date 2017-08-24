package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.base.BaseManagerImpl;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.CemeteryManager;
import com.shian.shianlife.provide.params.HpCarBuildOrder;
import com.shian.shianlife.provide.params.HpCemeteryBeSpeakCancelParams;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpCemeteryStructureParams;
import com.shian.shianlife.provide.params.HpCetemeryAcceptParams;
import com.shian.shianlife.provide.params.HpCetemeryRejectParams;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpGetDictSelectParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryBuildData;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkData;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessAgentMan;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessContract;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessDeadMan;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessOne;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessThree;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessTwo;
import com.shian.shianlife.provide.params.HpSaveCustomerCemeteryParams;
import com.shian.shianlife.provide.result.HrConsultCemetery;
import com.shian.shianlife.provide.result.HrGetCarDetails;
import com.shian.shianlife.provide.result.HrGetCemeteryBuildData;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;
import com.shian.shianlife.provide.result.HrGetCemeteryStructure;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkData;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessAgentMan;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessContract;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessDeadMan;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessOne;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessThree;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessTwo;
import com.shian.shianlife.provide.result.HrGetDictSelectData;
import com.shian.shianlife.provide.result.HrLoginResult;
import com.shian.shianlife.provide.result.HrOrderIdResult;
import com.shian.shianlife.thisenum.CemeteryBuildListTypeEnum;

/**
 * Created by Administrator on 2017/1/18.
 */

public class CemeteryManagerImpl extends BaseManagerImpl implements CemeteryManager {
    private static volatile CemeteryManagerImpl manager;

    private CemeteryManagerImpl() {
        super();
        baseUrl = AppContansts.Cemetery_BaseUrl;
    }

    public static CemeteryManagerImpl getInstance() {
        if (manager == null) {
            synchronized (CemeteryManagerImpl.class) {
                if (manager == null) {
                    manager = new CemeteryManagerImpl();
                }
            }
        }
        return manager;
    }

    @Override
    public void loginCemetery(Context context, HpLoginParams params, HttpResponseHandler<HrLoginResult> handler) {
        // TODO Auto-generated method stub
        requestPost(context, "doLogin/marketing", HrLoginResult.class, params,
                handler);
    }

    @Override
    public void loginoutCemetery(Context context, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        requestPost(context, "doLogout", Object.class,
                new BaseHttpParams(), handler);
    }

    /**
     * 获取订单列表
     *
     * @param context
     * @param params
     * @param orderType 0-洽谈列表 1-新建列表
     * @param response
     */
    @Override
    public void getOrderList(Context context, HpGetOrderListParams params, int orderType,
                             HttpResponseHandler<HrGetCemeteryListData> response) {
        String url = CemeteryBuildListTypeEnum.getUrlFromCode(orderType);
        requestPost(context, url, HrGetCemeteryListData.class, params, response, false);
    }


    @Override
    public void getDictSelect(Context context, HpGetDictSelectParams params, HttpResponseHandler<HrGetDictSelectData> handler) {
        requestPost(context, "marketing/dict/items/list", HrGetDictSelectData.class, params, handler);
    }

    @Override
    public void getCemeteryTalkSuccessContract(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessContract> handler) {
        requestPost(context, "marketing/order/buycemetery/get", HrGetCemeteryTalkSuccessContract.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessContract(Context context, HpSaveCemeteryTalkSuccessContract params, HttpResponseHandler<HrOrderIdResult> handler) {
        requestPost(context, "marketing/order/buycemetery/save/or/update", HrOrderIdResult.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessDeadMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan> handler) {
        requestPost(context, "marketing/order/dead/get", HrGetCemeteryTalkSuccessDeadMan.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessDeadMan(Context context, HpSaveCemeteryTalkSuccessDeadMan params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/order/dead/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessAgentMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan> handler) {
        requestPost(context, "marketing/order/agentinfo/get", HrGetCemeteryTalkSuccessAgentMan.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessAgentMan(Context context, HpSaveCemeteryTalkSuccessAgentMan params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/order/agentinfo/save/or/update", Object.class, params,
                handler);
    }


    @Override
    public void cancelCemeteryBeSpeak(Context context, HpCemeteryBeSpeakCancelParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/bespeak/build/cancel", Object.class, params, handler, true);
    }


    @Override
    public void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler) {
        requestPost(context, "marketing/talk/getTalkPlan", HrGetCemeteryTalkData.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkData params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/talk/saveTalkPlan", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessOne(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessOne> handler) {
        requestPost(context, "marketing/order/buycemetery/get", HrGetCemeteryTalkSuccessOne.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessOne(Context context, HpSaveCemeteryTalkSuccessOne params, HttpResponseHandler<HrOrderIdResult> handler) {
        requestPost(context, "marketing/order/buycemetery/save/or/update", HrOrderIdResult.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessTwo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessTwo> handler) {
        requestPost(context, "marketing/order/deadinfo/get", HrGetCemeteryTalkSuccessTwo.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessTwo(Context context, HpSaveCemeteryTalkSuccessTwo params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/order/deadinfo/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessThree(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessThree> handler) {
        requestPost(context, "marketing/order/agentinfo/get", HrGetCemeteryTalkSuccessThree.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessThree(Context context, HpSaveCemeteryTalkSuccessThree params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/order/agentinfo/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryStructure(Context context, HpCemeteryStructureParams params, HttpResponseHandler<HrGetCemeteryStructure> handler) {
        requestPost(context, "marketing/cemetery/structure/list", HrGetCemeteryStructure.class, params,
                handler);
    }

    @Override
    public void saveCemeteryBuildData(Context context, HpSaveCemeteryBuildData params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/bespeak/build/save", Object.class, params,
                handler, true);
    }

    @Override
    public void getCemeteryBuildData(Context context, HttpResponseHandler<HrGetCemeteryBuildData> handler) {
        requestPost(context, "cemetery/bespeak/build/get", HrGetCemeteryBuildData.class, new BaseHttpParams(),
                handler);
    }


    @Override
    public void acceptCemetery(Context context, HpCetemeryAcceptParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "marketing/talk/accept", Object.class, params,
                handler);
    }


    @Override
    public void rejectCemetery(Context context, HpCetemeryRejectParams params, HttpResponseHandler<Object> handler) {
        requestPost(context, "cemetery/ordered/bespeak/reject", Object.class, params,
                handler);
    }

    @Override
    public void getCustomerCemetery(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrConsultCemetery> handler) {
        // TODO Auto-generated method stub
        requestPost(context, "customer/cemetery/get",
                HrConsultCemetery.class, params, handler);

    }

    @Override
    public void saveCustomerCemetery(Context context,
                                     HpSaveCustomerCemeteryParams params,
                                     HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        requestPost(context, "customer/cemetery/save", Object.class,
                params, handler);

    }
}
