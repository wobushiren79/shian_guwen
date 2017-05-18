package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpAcceptParams;
import com.shian.shianlife.provide.params.HpAddConsultParams;
import com.shian.shianlife.provide.params.HpAuditOrder;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpCemeteryStructureParams;
import com.shian.shianlife.provide.params.HpCetemeryAcceptParams;
import com.shian.shianlife.provide.params.HpCetemeryRejectParams;
import com.shian.shianlife.provide.params.HpChangeLocation;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpGetDictSelectParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.params.HpLoginParams;
import com.shian.shianlife.provide.params.HpOrderFeedback;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpOrderInvoic;
import com.shian.shianlife.provide.params.HpPageParams;
import com.shian.shianlife.provide.params.HpReadMessage;
import com.shian.shianlife.provide.params.HpRefundParams;
import com.shian.shianlife.provide.params.HpRejectParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryBuildData;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkData;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessAgentMan;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessContract;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessDeadMan;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessOne;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessThree;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkSuccessTwo;
import com.shian.shianlife.provide.params.HpSaveComment;
import com.shian.shianlife.provide.params.HpSaveContractData;
import com.shian.shianlife.provide.params.HpSaveCustomerAgentmanParams;
import com.shian.shianlife.provide.params.HpSaveCustomerCemeteryParams;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.params.HpSaveCustomerFuneral;
import com.shian.shianlife.provide.params.HpSaveCustomerTalkParams;
import com.shian.shianlife.provide.params.HpSaveCustomerUsageParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataFive;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataFour;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataOne;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataSeven;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataSix;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataThree;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataTwo;
import com.shian.shianlife.provide.params.HpSaveWaitServicePostData;
import com.shian.shianlife.provide.params.HpSkuIdParams;
import com.shian.shianlife.provide.params.HpTalkFailParams;
import com.shian.shianlife.provide.result.HrAddConsultResult;
import com.shian.shianlife.provide.result.HrCommentResult;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.provide.result.HrConsultCemetery;
import com.shian.shianlife.provide.result.HrConsultFuneral;
import com.shian.shianlife.provide.result.HrConsultUsageResult;
import com.shian.shianlife.provide.result.HrGetCemeteryBuildData;
import com.shian.shianlife.provide.result.HrGetCemeteryStructure;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkData;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessAgentMan;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessContract;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessDeadMan;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessOne;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessThree;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkSuccessTwo;
import com.shian.shianlife.provide.result.HrGetComment;
import com.shian.shianlife.provide.result.HrGetContractData;
import com.shian.shianlife.provide.result.HrGetCustomerContract;
import com.shian.shianlife.provide.result.HrGetCustomerFuneral;
import com.shian.shianlife.provide.result.HrGetCustomerFuneralOther;
import com.shian.shianlife.provide.result.HrGetCustomerPreready;
import com.shian.shianlife.provide.result.HrGetDictSelectData;
import com.shian.shianlife.provide.result.HrGetMsgNumberForUntreated;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.provide.result.HrGetOrderNote;
import com.shian.shianlife.provide.result.HrGetSKUDetails;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFive;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFour;
import com.shian.shianlife.provide.result.HrGetSendOrderDataOne;
import com.shian.shianlife.provide.result.HrGetSendOrderDataSeven;
import com.shian.shianlife.provide.result.HrGetSendOrderDataSix;
import com.shian.shianlife.provide.result.HrGetSendOrderDataThree;
import com.shian.shianlife.provide.result.HrGetSendOrderDataTwo;
import com.shian.shianlife.provide.result.HrGetTalkFail;
import com.shian.shianlife.provide.result.HrGetWaitServicePostData;
import com.shian.shianlife.provide.result.HrLoginResult;
import com.shian.shianlife.provide.result.HrMessageList;
import com.shian.shianlife.provide.result.HrOrderFeedback;
import com.shian.shianlife.provide.result.HrOrderIdResult;
import com.shian.shianlife.provide.result.HrOrderInvoic;
import com.shian.shianlife.provide.result.HrOrderItenList;
import com.shian.shianlife.provide.result.HrOrderItenNote;
import com.shian.shianlife.provide.result.HrUserInfo;

/**
 * 账户接口
 *
 * @author Administrator
 */
public interface MAccountManager extends HttpManager {
    public void login(Context context, HpLoginParams params,
                      HttpResponseHandler<HrLoginResult> handler);

    public void loginCemetery(Context context, HpLoginParams params,
                      HttpResponseHandler<HrLoginResult> handler);

    public void loginout(Context context,
                         HttpResponseHandler<Object> handler);

    public void loginoutCemetery(Context context,
                         HttpResponseHandler<Object> handler);
    /**
     * 字典查询
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getDictSelect(Context context, HpGetDictSelectParams params,
                       HttpResponseHandler<HrGetDictSelectData> handler);
    /****************** 顾问 ***********************/

    /**
     * 新建顾客信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void addConsult(Context context, HpAddConsultParams params,
                           HttpResponseHandler<HrAddConsultResult> handler);

    /**
     * 接单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void accept(Context context, HpAcceptParams params,
                       HttpResponseHandler<Object> handler);

    /**
     * 执行接单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void acceptZ(Context context, HpAcceptParams params,
                        HttpResponseHandler<Object> handler);

    /**
     * 拒单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void reject(Context context, HpRejectParams params,
                       HttpResponseHandler<Object> handler);

    /**
     * 执行拒单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void rejectZ(Context context, HpRejectParams params,
                        HttpResponseHandler<Object> handler);

    /**
     * 结束洽谈
     *
     * @param context
     * @param params
     * @param handler
     */
    public void talkFinish(Context context, HpConsultIdParams params,
                           HttpResponseHandler<Object> handler);

    /**
     * 切换为待服务
     *
     * @param context
     * @param params
     * @param handler
     */
    public void switch2waitService(Context context, HpConsultIdParams params,
                                   HttpResponseHandler<Object> handler);

    /**************************************************/
    /**
     * 获取洽谈信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerTalk(Context context, HpConsultIdParams params,
                                HttpResponseHandler<HrConsultFuneral> handler);

    /**
     * 保存洽谈信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerTalk(Context context,
                                 HpSaveCustomerTalkParams params, HttpResponseHandler<Object> handler);

    /**
     * 获取使用者信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerUsage(Context context, HpConsultIdParams params,
                                 HttpResponseHandler<HrConsultUsageResult> handler);

    /**
     * 保存使用者信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerUsage(Context context,
                                  HpSaveCustomerUsageParams params,
                                  HttpResponseHandler<Object> handler);

    /**
     * 获取经办人信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerAgentman(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrConsultAgentman> handler);

    /**
     * 保存经办人信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerAgentman(Context context,
                                     HpSaveCustomerAgentmanParams params,
                                     HttpResponseHandler<Object> handler);

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
     * 获取合同信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerContract(Context context,
                                    HpGetOrderDetailParams params,
                                    HttpResponseHandler<HrGetCustomerContract> handler);

    public void getCustomerContract(Context context, HpOrderIdParams params,
                                    HttpResponseHandler<HrGetCustomerContract> handler);

    /**
     * 保存合同信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerContract(Context context,
                                     HpSaveCustomerContract params, HttpResponseHandler<Object> handler);

    /**
     * 获取预备信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerPreready(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrGetCustomerPreready> handler);

    /**
     * 保存预备信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerPreready(Context context,
                                     HpSaveCustomerContract params, HttpResponseHandler<Object> handler);

    /**
     * 获取预备信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerFuneral(Context context, HpOrderIdParams params,
                                   HttpResponseHandler<HrGetCustomerFuneral> handler);
    /**
     * 获取预备信息2
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getCustomerFuneralOther(Context context, HpConsultIdParams params,
                                   HttpResponseHandler<HrGetCustomerFuneralOther> handler);
    /**
     * 保存预备信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveCustomerFuneral(Context context,
                                    HpSaveCustomerFuneral params, HttpResponseHandler<Object> handler);

    /**
     * 开始执行
     *
     * @param context
     * @param params
     * @param handler
     */
    public void startService(Context context, HpAcceptParams params,
                             HttpResponseHandler<Object> handler);

    /**
     * 结束派单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void endService(Context context, HpAcceptParams params,
                           HttpResponseHandler<Object> handler);

    /**
     * 获取服务派单列表
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getOrderItemList(Context context, HpAcceptParams params,
                                 HttpResponseHandler<HrOrderItenList> handler);

    /**
     * 申请派单
     *
     * @param context
     * @param params
     * @param handler
     */
    public void applyDispatch(Context context, HpAcceptParams params,
                              HttpResponseHandler<Object> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void getOrderComment(Context context, HpOrderIdParams params,
                                HttpResponseHandler<HrGetComment> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void saveOrderComment(Context context, HpSaveComment params,
                                 HttpResponseHandler<Object> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void getOrderNote(Context context, HpOrderIdParams params,
                             HttpResponseHandler<HrGetOrderNote> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void auditOrder(Context context, HpAuditOrder params,
                           HttpResponseHandler<Object> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void creatFrePay(Context context, HpOrderIdParams params,
                            HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void creatRestPay(Context context, HpOrderIdParams params,
                             HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void creatWeinxinEwm(Context context, HpOrderIdParams params,
                                HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void creatZhiFuBaoEwm(Context context, HpOrderIdParams params,
                                 HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void creatPosEwm(Context context, HpOrderIdParams params,
                            HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void saveOrderInvoic(Context context, HpOrderInvoic params,
                                HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void getOrderInvoic(Context context, HpOrderIdParams params,
                               HttpResponseHandler<HrOrderInvoic> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void getOrderFeedback(Context context, HpOrderIdParams params,
                                 HttpResponseHandler<HrOrderFeedback> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void saveOrderFeedback(Context context, HpOrderFeedback params,
                                  HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void payCrash(Context context, HpOrderIdParams params,
                         HttpResponseHandler<Object> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void saveItemNote(Context context, HpSaveCustomerContract params,
                             HttpResponseHandler<Object> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void getOrderItem(Context context, HpOrderIdParams params,
                             HttpResponseHandler<HrOrderItenNote> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void getMessageList(Context context, HpPageParams params,
                               HttpResponseHandler<HrMessageList> handler);

    /**
     * @param context
     * @param handler
     */
    public void getMessageCount(Context context,
                                HttpResponseHandler<HrCommentResult> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void readMessage(Context context, HpReadMessage params,
                            HttpResponseHandler<Object> handler);

    /**
     * @param context
     * @param params
     * @param handler
     */
    public void assignNotify(Context context, HpOrderIdParams params,
                             HttpResponseHandler<Object> handler);

    public void getRefundOrder(Context context, HpOrderIdParams params,
                               HttpResponseHandler<HrGetOrderDetailResult> handler);

    public void refundOrder(Context context, HpRefundParams params,
                            HttpResponseHandler<Object> handler);

    public void changeInfo(Context context, HpConsultIdParams params,
                           HttpResponseHandler<Object> handler);

    public void getUserInfo(Context context,
                            HttpResponseHandler<HrUserInfo> handler);

    public void changeCurAddress
            (Context context, HpConsultIdParams params,
             HttpResponseHandler<Object> handler);


    /**
     * 洽谈失败数据保存接口
     */
    public void saveTalkFailData(Context context, HpTalkFailParams params, HttpResponseHandler<Object> handler);


    /**
     * 获取洽谈失败数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getTalkFailData(Context context, HpConsultIdParams params,
                                HttpResponseHandler<HrGetTalkFail> handler);

    /**
     * 获取合同相关信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getContractData(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetContractData> handler);

    /**
     * 保存合同信息和截图
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveContractData(Context context, HpSaveContractData params, HttpResponseHandler<Object> handler);

    /**
     * 获取待服务提交信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getWaitServicePostData(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetWaitServicePostData> handler);

    /**
     * 保存待服务提交信息
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveWaitServicePostData(Context context, HpSaveWaitServicePostData params, HttpResponseHandler<Object> handler);

    /**
     * 获取处理临终现场数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataOne(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataOne> handler);

    /**
     * 保存处理临终现场数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataOne(Context context, HpSaveSendOrderDataOne params, HttpResponseHandler<Object> handler);

    /**
     * 获取治丧初期数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataTwo(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataTwo> handler);

    /**
     * 保存治丧初期数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataTwo(Context context, HpSaveSendOrderDataTwo params, HttpResponseHandler<Object> handler);

    /**
     * 获取开始服务（出殡前）数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataThree(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataThree> handler);

    /**
     * 保存开始服务（出殡前）数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataThree(Context context, HpSaveSendOrderDataThree params, HttpResponseHandler<Object> handler);

    /**
     * 获取出殡前服务数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataFour(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataFour> handler);

    /**
     * 保存出殡前服务数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataFour(Context context, HpSaveSendOrderDataFour params, HttpResponseHandler<Object> handler);

    /**
     * 获取开始服务出殡当天数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataFive(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataFive> handler);

    /**
     * 保存开始服务出殡当天数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataFive(Context context, HpSaveSendOrderDataFive params, HttpResponseHandler<Object> handler);

    /**
     * 获取出殡当天数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataSix(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataSix> handler);

    /**
     * 保存出殡当天数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataSix(Context context, HpSaveSendOrderDataSix params, HttpResponseHandler<Object> handler);


    /**
     * 获取殡仪馆服务数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void getSendOrderDataSeven(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataSeven> handler);

    /**
     * 保存殡仪馆服务数据
     *
     * @param context
     * @param params
     * @param handler
     */
    public void saveSendOrderDataSeven(Context context, HpSaveSendOrderDataSeven params, HttpResponseHandler<Object> handler);

    /**
     * 获取商品详情
     * @param context
     * @param params
     * @param handler
     */
    public void getSKUDetails(Context context, HpSkuIdParams params, HttpResponseHandler<HrGetSKUDetails> handler);


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
     *
     */
    public void getCemeteryStructure(Context context, HpCemeteryStructureParams params, HttpResponseHandler<HrGetCemeteryStructure> handler);

    /**
     * 订单待处理计数提示功能
     */
    public void getMsgNumberForUntreated(Context context, HttpResponseHandler<HrGetMsgNumberForUntreated> handler);

    /**
     * 改变地址
     */
    public void changeLocation(Context context, HpChangeLocation params, HttpResponseHandler<Object> handler);

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
}
