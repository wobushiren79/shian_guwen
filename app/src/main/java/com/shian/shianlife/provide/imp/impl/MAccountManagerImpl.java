package com.shian.shianlife.provide.imp.impl;

import android.content.Context;

import com.shian.shianlife.provide.base.BaseHttpParams;
import com.shian.shianlife.provide.base.HttpRequestExecutor;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.MAccountManager;
import com.shian.shianlife.provide.params.HpAcceptParams;
import com.shian.shianlife.provide.params.HpAddConsultParams;
import com.shian.shianlife.provide.params.HpAuditOrder;
import com.shian.shianlife.provide.params.HpCarBuildOrder;
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
import com.shian.shianlife.provide.params.HpSaveTime;
import com.shian.shianlife.provide.params.HpSaveWaitServicePostData;
import com.shian.shianlife.provide.params.HpSkuIdParams;
import com.shian.shianlife.provide.params.HpTalkFailParams;
import com.shian.shianlife.provide.result.HrAddConsultResult;
import com.shian.shianlife.provide.result.HrCommentResult;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.provide.result.HrConsultCemetery;
import com.shian.shianlife.provide.result.HrConsultFuneral;
import com.shian.shianlife.provide.result.HrConsultUsageResult;
import com.shian.shianlife.provide.result.HrGetCarDetails;
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
 * 账户接口实现
 *
 * @author Administrator
 */
public class MAccountManagerImpl implements MAccountManager {
    public HttpRequestExecutor excutor = new HttpRequestExecutor();
    private static MAccountManager manager;

    private MAccountManagerImpl() {
    }


    public static MAccountManager getInstance() {
        if (manager == null) {
            manager = new MAccountManagerImpl();
        }
        return manager;
    }

    @Override
    public void login(Context context, HpLoginParams params,
                      HttpResponseHandler<HrLoginResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "doLogin", HrLoginResult.class, params,
                handler);
    }

    @Override
    public void loginCemetery(Context context, HpLoginParams params, HttpResponseHandler<HrLoginResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestCemeteryPost(context, "doLogin/marketing", HrLoginResult.class, params,
                handler);
    }

    @Override
    public void addConsult(Context context, HpAddConsultParams params,
                           HttpResponseHandler<HrAddConsultResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "consult/add", HrAddConsultResult.class,
                params, handler);
    }

    @Override
    public void getDictSelect(Context context, HpGetDictSelectParams params, HttpResponseHandler<HrGetDictSelectData> handler) {
        excutor.requestCemeteryPost(context, "marketing/dict/items/list", HrGetDictSelectData.class, params, handler);
    }

    /**
     * 接单
     */
    @Override
    public void accept(Context context, HpAcceptParams params,
                       HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "consult/accept", Object.class, params,
                handler);
    }

    /**
     * 拒单
     */
    @Override
    public void reject(Context context, HpRejectParams params,
                       HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "consult/reject", Object.class, params,
                handler);
    }

    /**
     * 结束洽谈
     */
    @Override
    public void talkFinish(Context context, HpConsultIdParams params,
                           HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "consult/talk/finish", Object.class,
                params, handler);
    }

    /**
     * 转为待服务
     */
    @Override
    public void switch2waitService(Context context, HpConsultIdParams params,
                                   HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "consult/switch2waitService",
                Object.class, params, handler);
    }

    @Override
    public void getCustomerTalk(Context context, HpConsultIdParams params,
                                HttpResponseHandler<HrConsultFuneral> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/talk/get",
                HrConsultFuneral.class, params, handler);
    }

    @Override
    public void saveCustomerTalk(Context context,
                                 HpSaveCustomerTalkParams params, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/talk/save", Object.class,
                params, handler);
    }

    @Override
    public void getCustomerUsage(Context context, HpConsultIdParams params,
                                 HttpResponseHandler<HrConsultUsageResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/usage/get",
                HrConsultUsageResult.class, params, handler);
    }

    @Override
    public void saveCustomerUsage(Context context,
                                  HpSaveCustomerUsageParams params,
                                  HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/usage/save", Object.class,
                params, handler);

    }

    @Override
    public void getCustomerAgentman(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrConsultAgentman> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/agentman/get",
                HrConsultAgentman.class, params, handler);
    }

    @Override
    public void saveCustomerAgentman(Context context,
                                     HpSaveCustomerAgentmanParams params,
                                     HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/agentman/save", Object.class,
                params, handler);

    }

    @Override
    public void getCustomerCemetery(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrConsultCemetery> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/cemetery/get",
                HrConsultCemetery.class, params, handler);

    }

    @Override
    public void saveCustomerCemetery(Context context,
                                     HpSaveCustomerCemeteryParams params,
                                     HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/cemetery/save", Object.class,
                params, handler);

    }

    @Override
    public void acceptZ(Context context, HpAcceptParams params,
                        HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/accept", Object.class, params,
                handler);
    }

    @Override
    public void startService(Context context, HpAcceptParams params,
                             HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/startService", Object.class,
                params, handler);
    }

    @Override
    public void getOrderItemList(Context context, HpAcceptParams params,
                                 HttpResponseHandler<HrOrderItenList> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/list", HrOrderItenList.class,
                params, handler);
    }

    @Override
    public void applyDispatch(Context context, HpAcceptParams params,
                              HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/dispatch/apply", Object.class,
                params, handler);
    }

    @Override
    public void getCustomerContract(Context context,
                                    HpGetOrderDetailParams params,
                                    HttpResponseHandler<HrGetCustomerContract> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/contract/get",
                HrGetCustomerContract.class, params, handler);
    }

    public void getCustomerContract(Context context, HpOrderIdParams params,
                                    HttpResponseHandler<HrGetCustomerContract> handler) {
        excutor.requestPost(context, "customer/contract/get",
                HrGetCustomerContract.class, params, handler);
    }

    ;

    @Override
    public void saveCustomerContract(Context context,
                                     HpSaveCustomerContract params, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/contract/save", Object.class,
                params, handler);
    }

    @Override
    public void getCustomerPreready(Context context, HpConsultIdParams params,
                                    HttpResponseHandler<HrGetCustomerPreready> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/preready/get",
                HrGetCustomerPreready.class, params, handler);
    }

    @Override
    public void saveCustomerPreready(Context context,
                                     HpSaveCustomerContract params, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/preready/save", Object.class,
                params, handler);
    }

    @Override
    public void getCustomerFuneral(Context context, HpOrderIdParams params,
                                   HttpResponseHandler<HrGetCustomerFuneral> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/funeral/get",
                HrGetCustomerFuneral.class, params, handler);
    }

    @Override
    public void getCustomerFuneralOther(Context context, HpConsultIdParams params,
                                        HttpResponseHandler<HrGetCustomerFuneralOther> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/funeral/get/other",
                HrGetCustomerFuneralOther.class, params, handler);
    }

    @Override
    public void saveCustomerFuneral(Context context,
                                    HpSaveCustomerFuneral params, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "customer/funeral/save", Object.class,
                params, handler);
    }

    @Override
    public void getOrderComment(Context context, HpOrderIdParams params,
                                HttpResponseHandler<HrGetComment> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/comment/get",
                HrGetComment.class, params, handler);
    }

    @Override
    public void saveOrderComment(Context context, HpSaveComment params,
                                 HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/comment/save", Object.class,
                params, handler);
    }

    @Override
    public void getOrderNote(Context context, HpOrderIdParams params,
                             HttpResponseHandler<HrGetOrderNote> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/view", HrGetOrderNote.class,
                params, handler);
    }

    @Override
    public void auditOrder(Context context, HpAuditOrder params,
                           HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/audit", Object.class, params,
                handler);
    }

    @Override
    public void creatFrePay(Context context, HpOrderIdParams params,
                            HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "pay/prepay/create",
                HrCommentResult.class, params, handler);
    }

    @Override
    public void creatRestPay(Context context, HpOrderIdParams params,
                             HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "pay/rest/create", HrCommentResult.class,
                params, handler);
    }

    @Override
    public void creatWeinxinEwm(Context context, HpOrderIdParams params,
                                HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "pay/weixin/createScanUrl",
                HrCommentResult.class, params, handler);
    }

    @Override
    public void creatZhiFuBaoEwm(Context context, HpOrderIdParams params,
                                 HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "/pay/alipay/createScanUrl",
                HrCommentResult.class, params, handler);
    }

    @Override
    public void creatPosEwm(Context context, HpOrderIdParams params,
                            HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "pay/pos/createScanUrl",
                HrCommentResult.class, params, handler);
    }

    @Override
    public void saveOrderInvoic(Context context, HpOrderInvoic params,
                                HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/invoice/save",
                HrCommentResult.class, params, handler);
    }

    @Override
    public void getOrderInvoic(Context context, HpOrderIdParams params,
                               HttpResponseHandler<HrOrderInvoic> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/invoice/view", HrOrderInvoic.class,
                params, handler);
    }

    @Override
    public void getOrderFeedback(Context context, HpOrderIdParams params,
                                 HttpResponseHandler<HrOrderFeedback> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/feedback/view",
                HrOrderFeedback.class, params, handler);
    }

    @Override
    public void saveOrderFeedback(Context context, HpOrderFeedback params,
                                  HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/feedback/save",
                HrCommentResult.class, params, handler);
    }

    @Override
    public void loginout(Context context, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "doLogout", Object.class,
                new BaseHttpParams(), handler);
    }

    @Override
    public void loginoutCemetery(Context context, HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestCemeteryPost(context, "doLogout", Object.class,
                new BaseHttpParams(), handler);
    }
    @Override
    public void endService(Context context, HpAcceptParams params,
                           HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/endService", Object.class, params,
                handler);
    }

    @Override
    public void payCrash(Context context, HpOrderIdParams params,
                         HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "pay/cash", Object.class, params, handler);
    }

    @Override
    public void saveItemNote(Context context, HpSaveCustomerContract params,
                             HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/note/save", Object.class,
                params, handler);
    }

    @Override
    public void rejectZ(Context context, HpRejectParams params,
                        HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/reject", Object.class, params,
                handler);
    }

    @Override
    public void getOrderItem(Context context, HpOrderIdParams params,
                             HttpResponseHandler<HrOrderItenNote> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "order/item/view", HrOrderItenNote.class,
                params, handler);
    }

    @Override
    public void getMessageList(Context context, HpPageParams params,
                               HttpResponseHandler<HrMessageList> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "push/msg/get", HrMessageList.class,
                params, handler);
    }

    @Override
    public void getMessageCount(Context context,
                                HttpResponseHandler<HrCommentResult> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "push/msg/unread/count",
                HrCommentResult.class, new BaseHttpParams(), handler);
    }

    @Override
    public void readMessage(Context context, HpReadMessage params,
                            HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "push/msg/read", Object.class, params,
                handler);
    }

    @Override
    public void assignNotify(Context context, HpOrderIdParams params,
                             HttpResponseHandler<Object> handler) {
        // TODO Auto-generated method stub
        excutor.requestPost(context, "consult/adviser/assign/notify", Object.class, params,
                handler);
    }

    @Override
    public void getRefundOrder(Context context, HpOrderIdParams params, HttpResponseHandler<HrGetOrderDetailResult> handler) {
        excutor.requestPost(context, "order/refund/view", HrGetOrderDetailResult.class, params,
                handler);
    }

    @Override
    public void refundOrder(Context context, HpRefundParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/refund", Object.class, params,
                handler);
    }

    @Override
    public void changeInfo(Context context, HpConsultIdParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "user/changeInfo", Object.class, params,
                handler);
    }

    @Override
    public void getUserInfo(Context context, HttpResponseHandler<HrUserInfo> handler) {
        excutor.requestPost(context, "user/info/get", HrUserInfo.class, new BaseHttpParams(),
                handler);
    }

    @Override
    public void changeCurAddress(Context context, HpConsultIdParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "user/changeCurAddress", Object.class, params,
                handler);
    }

    @Override
    public void saveTalkFailData(Context context, HpTalkFailParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "customer/talkfail/save", Object.class, params,
                handler);
    }

    @Override
    public void getTalkFailData(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetTalkFail> handler) {
        excutor.requestPost(context, "customer/talkfail/get", HrGetTalkFail.class, params,
                handler);
    }

    @Override
    public void getContractData(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetContractData> handler) {
        excutor.requestPost(context, "customer/talk/contract/get", HrGetContractData.class, params,
                handler);
    }

    @Override
    public void saveContractData(Context context, HpSaveContractData params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "customer/talk/contract/save", Object.class, params,
                handler);
    }

    @Override
    public void getWaitServicePostData(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetWaitServicePostData> handler) {
        excutor.requestPost(context, "customer/funeral/wait/service/get", HrGetWaitServicePostData.class, params,
                handler);
    }

    @Override
    public void saveWaitServicePostData(Context context, HpSaveWaitServicePostData params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "customer/funeral/wait/service/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataOne(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataOne> handler) {
        excutor.requestPost(context, "order/item/list/one/get", HrGetSendOrderDataOne.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataOne(Context context, HpSaveSendOrderDataOne params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/one/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataTwo(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataTwo> handler) {
        excutor.requestPost(context, "order/item/list/two/get", HrGetSendOrderDataTwo.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataTwo(Context context, HpSaveSendOrderDataTwo params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/two/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataThree(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataThree> handler) {
        excutor.requestPost(context, "order/item/list/three/get", HrGetSendOrderDataThree.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataThree(Context context, HpSaveSendOrderDataThree params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/three/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataFour(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataFour> handler) {
        excutor.requestPost(context, "order/item/list/four/get", HrGetSendOrderDataFour.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataFour(Context context, HpSaveSendOrderDataFour params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/four/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataFive(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataFive> handler) {
        excutor.requestPost(context, "order/item/list/five/get", HrGetSendOrderDataFive.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataFive(Context context, HpSaveSendOrderDataFive params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/five/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataSix(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataSix> handler) {
        excutor.requestPost(context, "order/item/list/six/get", HrGetSendOrderDataSix.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataSix(Context context, HpSaveSendOrderDataSix params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/six/save", Object.class, params,
                handler);
    }

    @Override
    public void getSendOrderDataSeven(Context context, HpConsultIdParams params, HttpResponseHandler<HrGetSendOrderDataSeven> handler) {
        excutor.requestPost(context, "order/item/list/seven/get", HrGetSendOrderDataSeven.class, params,
                handler);
    }

    @Override
    public void saveSendOrderDataSeven(Context context, HpSaveSendOrderDataSeven params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "order/item/list/seven/save", Object.class, params,
                handler);
    }

    @Override
    public void getSKUDetails(Context context, HpSkuIdParams params, HttpResponseHandler<HrGetSKUDetails> handler) {
        excutor.requestPost(context, "order/item/list/get/skudetails", HrGetSKUDetails.class, params,
                handler);
    }

    @Override
    public void getCemeteryBuildData(Context context, HttpResponseHandler<HrGetCemeteryBuildData> handler) {
        excutor.requestPost(context, "cemetery/bespeak/build/get", HrGetCemeteryBuildData.class, new BaseHttpParams(),
                handler);
    }

    @Override
    public void saveCemeteryBuildData(Context context, HpSaveCemeteryBuildData params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "marketing/bespeak/build/save", Object.class, params,
                handler);
    }

    @Override
    public void acceptCemetery(Context context, HpCetemeryAcceptParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "marketing/talk/accept", Object.class, params,
                handler);
    }


    @Override
    public void rejectCemetery(Context context, HpCetemeryRejectParams params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "cemetery/ordered/bespeak/reject", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkInfo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkData> handler) {
        excutor.requestCemeteryPost(context, "marketing/talk/getTalkPlan", HrGetCemeteryTalkData.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkInfo(Context context, HpSaveCemeteryTalkData params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "marketing/talk/saveTalkPlan", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessOne(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessOne> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/buycemetery/get", HrGetCemeteryTalkSuccessOne.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessOne(Context context, HpSaveCemeteryTalkSuccessOne params, HttpResponseHandler<HrOrderIdResult> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/buycemetery/save/or/update", HrOrderIdResult.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessTwo(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessTwo> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/deadinfo/get", HrGetCemeteryTalkSuccessTwo.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessTwo(Context context, HpSaveCemeteryTalkSuccessTwo params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/deadinfo/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessThree(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessThree> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/agentinfo/get", HrGetCemeteryTalkSuccessThree.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessThree(Context context, HpSaveCemeteryTalkSuccessThree params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/agentinfo/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryStructure(Context context, HpCemeteryStructureParams params, HttpResponseHandler<HrGetCemeteryStructure> handler) {
        excutor.requestCemeteryPost(context, "marketing/cemetery/structure/list", HrGetCemeteryStructure.class, params,
                handler);
    }

    @Override
    public void getMsgNumberForUntreated(Context context, HttpResponseHandler<HrGetMsgNumberForUntreated> handler) {
        excutor.requestPost(context, "order/list/wating/handle/count", HrGetMsgNumberForUntreated.class,
                new BaseHttpParams(), handler);
    }

    @Override
    public void changeLocation(Context context, HpChangeLocation params, HttpResponseHandler<Object> handler) {
        excutor.requestPost(context, "customer/address/position/change", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessContract(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessContract> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/buycemetery/get", HrGetCemeteryTalkSuccessContract.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessContract(Context context, HpSaveCemeteryTalkSuccessContract params, HttpResponseHandler<HrOrderIdResult> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/buycemetery/save/or/update", HrOrderIdResult.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessDeadMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessDeadMan> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/deadinfo/get", HrGetCemeteryTalkSuccessDeadMan.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessDeadMan(Context context, HpSaveCemeteryTalkSuccessDeadMan params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/deadinfo/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void getCemeteryTalkSuccessAgentMan(Context context, HpCemeteryIdParams params, HttpResponseHandler<HrGetCemeteryTalkSuccessAgentMan> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/agentinfo/get", HrGetCemeteryTalkSuccessAgentMan.class, params,
                handler);
    }

    @Override
    public void saveCemeteryTalkSuccessAgentMan(Context context, HpSaveCemeteryTalkSuccessAgentMan params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "marketing/order/agentinfo/save/or/update", Object.class, params,
                handler);
    }

    @Override
    public void saveCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<Object> handler) {
        excutor.requestCemeteryPost(context, "cars/apply/using", Object.class, params, handler, true);
    }

    @Override
    public void getCarBuildData(Context context, HpCarBuildOrder params, HttpResponseHandler<HrGetCarDetails> handler) {
        excutor.requestCemeteryPost(context, "cars/apply/handle/info", HrGetCarDetails.class, params, handler, true);
    }
}
