package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
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

/**
 * Created by Administrator on 2017/1/18.
 */

public interface CemeteryManager extends HttpManager {
    public void loginCemetery(Context context, HpLoginParams params,
                              HttpResponseHandler<HrLoginResult> handler);

    public void loginoutCemetery(Context context,
                                 HttpResponseHandler<Object> handler);

    /**
     * 获取订单列表
     *
     * @param context
     * @param params
     * @param orderType 0-洽谈列表 1-售后列表 2服务结束列表
     * @param response
     */
    public void getOrderList(Context context, HpGetOrderListParams params, int orderType, HttpResponseHandler<HrGetCemeteryListData> response);


    /**
     * 字典查询
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getDictSelect(Context context, HpGetDictSelectParams params, HttpResponseHandler<HrGetDictSelectData> handler);

    /**
     * 获取公墓预约单信息
     *
     * @param context
     * @param handler
     */
    public void getCemeteryBuildData(Context context, HttpResponseHandler<HrGetCemeteryBuildData> handler);

    /**
     * 保存公墓预约单信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCemeteryBuildData(Context context, HpSaveCemeteryBuildData params, HttpResponseHandler<Object> handler);

    /**
     * 公墓系统接单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void acceptCemetery(Context context, HpCetemeryAcceptParams params,
                               HttpResponseHandler<Object> handler);

    /**
     * 公墓系统拒单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void rejectCemetery(Context context, HpCetemeryRejectParams params,
                               HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈信息
     *
     * @param context
     * @param handler
     */
    public void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler);

    /**
     * 保存公墓洽谈信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkData params, HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈成功第一步信息
     */
    public void getCemeteryTalkSuccessOne(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessOne> handler);

    /**
     * 保存公墓洽谈成功第一步信息
     */
    public void saveCemeteryTalkSuccessOne(Context context, HpSaveCemeteryTalkSuccessOne params, HttpResponseHandler<HrOrderIdResult> handler);

    /**
     * 获取公墓洽谈成功第二步信息
     */
    public void getCemeteryTalkSuccessTwo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessTwo> handler);

    /**
     * 保存公墓洽谈成功第二步信息
     */
    public void saveCemeteryTalkSuccessTwo(Context context, HpSaveCemeteryTalkSuccessTwo params, HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈成功第三步信息
     */
    public void getCemeteryTalkSuccessThree(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessThree> handler);

    /**
     * 保存公墓洽谈成功第三步信息
     */
    public void saveCemeteryTalkSuccessThree(Context context, HpSaveCemeteryTalkSuccessThree params, HttpResponseHandler<Object> handler);

    /**
     * 获取公墓墓位结构
     */
    public void getCemeteryStructure(Context context, HpCemeteryStructureParams params, HttpResponseHandler<HrGetCemeteryStructure> handler);

    /**
     * 获取公墓洽谈成功合同信息
     */
    void getCemeteryTalkSuccessContract(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessContract> handler);

    /**
     * 保存公墓洽谈成功合同信息
     */
    void saveCemeteryTalkSuccessContract(Context context, HpSaveCemeteryTalkSuccessContract params, HttpResponseHandler<HrOrderIdResult> handler);

    /**
     * 获取公墓洽谈成功往生者信息
     */
    void getCemeteryTalkSuccessDeadMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan> handler);

    /**
     * 保存公墓洽谈成功往生者信息
     */
    void saveCemeteryTalkSuccessDeadMan(Context context, HpSaveCemeteryTalkSuccessDeadMan params, HttpResponseHandler<Object> handler);

    /**
     * 获取公墓洽谈成功逝者信息
     */
    void getCemeteryTalkSuccessAgentMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan> handler);

    /**
     * 保存公墓洽谈成功逝者信息
     */
    void saveCemeteryTalkSuccessAgentMan(Context context, HpSaveCemeteryTalkSuccessAgentMan params, HttpResponseHandler<Object> handler);


    /**
     * 获取公墓信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerCemetery(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrConsultCemetery> handler);

    /**
     * 报错公墓信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerCemetery(Context context,
                                     HpSaveCustomerCemeteryParams params,
                                     HttpResponseHandler<Object> handler);

    /**
     * 取消公墓咨询单
     */
    void cancelCemeteryBeSpeak(Context context, HpCemeteryBeSpeakCancelParams params, HttpResponseHandler<Object> handler);
}
