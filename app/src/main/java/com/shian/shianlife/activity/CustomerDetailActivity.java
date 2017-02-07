package com.shian.shianlife.activity;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.customerdetail.CemeteryDetailInfoView;
import com.shian.shianlife.common.view.customerdetail.ContractDetailInfoView;
import com.shian.shianlife.common.view.customerdetail.CustomerDeadInfoView;
import com.shian.shianlife.common.view.customerdetail.FunneralDetailInfoOtherView;
import com.shian.shianlife.common.view.customerdetail.FunneralDetailInfoView;
import com.shian.shianlife.common.view.customerdetail.ManagerDetailInfoView;
import com.shian.shianlife.common.view.customerdetail.PrereadyDetailInfoView;
import com.shian.shianlife.common.view.customerdetail.TalkDetailInfoView;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.provide.result.HrConsultCemetery;
import com.shian.shianlife.provide.result.HrConsultFuneral;
import com.shian.shianlife.provide.result.HrConsultUsageResult;
import com.shian.shianlife.provide.result.HrGetCustomerContract;
import com.shian.shianlife.provide.result.HrGetCustomerFuneral;
import com.shian.shianlife.provide.result.HrGetCustomerFuneralOther;
import com.shian.shianlife.provide.result.HrGetCustomerPreready;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CustomerDetailActivity extends BaseActivity {

    @InjectView(R.id.cdiv)
    CustomerDeadInfoView cdiv;
    @InjectView(R.id.mdiv)
    ManagerDetailInfoView mdiv;
    @InjectView(R.id.ctdiv)
    ContractDetailInfoView ctdiv;
    @InjectView(R.id.fdiv)
    FunneralDetailInfoOtherView fdiv;
    //            FunneralDetailInfoView fdiv;
    @InjectView(R.id.cediv)
    CemeteryDetailInfoView cediv;
    @InjectView(R.id.pdiv)
    PrereadyDetailInfoView pdiv;
    @InjectView(R.id.tdiv)
    TalkDetailInfoView tdiv;

    long consultId;
    long orderId;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_cutomerdetail);
        setTitle("客户详情");
        ButterKnife.inject(this);
        consultId = getIntent().getLongExtra("consultId", 0);
        orderId = getIntent().getLongExtra("orderId", 0);
        getCustomerDeatail();// 获取使用者信息（即逝者信息）
        getManagerDetail();// 获取经办人信息

        getCustomerContract();// 获取合同信息

        getCustomerFuneral();// 获取殡仪信息（即治丧信息）
        getCustomerPreready();// 获取提前准备
//		getCustomerCemetery();// 获取公墓信息
//		getCustomerTalk();// 获取洽谈信息
    }

//	private void getCustomerTalk() {
//		HpConsultIdParams params = new HpConsultIdParams();
//		params.setConsultId(consultId);
//		MHttpManagerFactory.getAccountManager().getCustomerTalk(this, params,
//				new HttpResponseHandler<HrConsultFuneral>() {
//
//					@Override
//					public void onSuccess(HrConsultFuneral result) {
//						tdiv.setData(result);
//					}
//
//					@Override
//					public void onStart() {
//
//					}
//
//					@Override
//					public void onError(String message) {
////						Toast.makeText(getApplicationContext(), "获取洽谈详情失败！", Toast.LENGTH_SHORT).show();
//					}
//				});
//
//	}

//	private void getCustomerCemetery() {
//		HpConsultIdParams params = new HpConsultIdParams();
//		params.setConsultId(consultId);
//		MHttpManagerFactory.getAccountManager().getCustomerCemetery(this, params,
//				new HttpResponseHandler<HrConsultCemetery>() {
//
//					@Override
//					public void onSuccess(HrConsultCemetery result) {
//						cediv.setData(result);
//					}
//
//					@Override
//					public void onStart() {
//
//					}
//
//					@Override
//					public void onError(String message) {
////						Toast.makeText(getApplicationContext(), "获取公墓详情失败！", Toast.LENGTH_SHORT).show();
//
//					}
//				});
//
//	}

    private void getCustomerPreready() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getCustomerPreready(this, params,
                new HttpResponseHandler<HrGetCustomerPreready>() {

                    @Override
                    public void onSuccess(HrGetCustomerPreready result) {
                        pdiv.setData(result);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//						Toast.makeText(getApplicationContext(), "获取预备详情失败！", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void getCustomerFuneral() {
//        HpOrderIdParams params = new HpOrderIdParams();
//        params.setOrderId(orderId);
//        MHttpManagerFactory.getAccountManager().getCustomerFuneral(this, params,
//                new HttpResponseHandler<HrGetCustomerFuneral>() {
//
//                    @Override
//                    public void onSuccess(HrGetCustomerFuneral result) {
//                        fdiv.setData(result);
//                    }
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onError(String message) {
////						Toast.makeText(getApplicationContext(), "获取殡仪详情失败！", Toast.LENGTH_SHORT).show();
//
//                    }
//                });

        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getCustomerFuneralOther(this, params,
                new HttpResponseHandler<HrGetCustomerFuneralOther>() {

                    @Override
                    public void onSuccess(HrGetCustomerFuneralOther result) {
                        Utils.LogVPrint( "getDeadTime:" + result.getDeadTime());
                        Utils.LogVPrint("getDeadLocation:" + result.getDeadLocation());
                        Utils.LogVPrint( "getZsLocation:" + result.getZsLocation());
                        Utils.LogVPrint( "getFuneralLocation:" + result.getFuneralLocation());
                        Utils.LogVPrint( "getFuneralTime:" + result.getFuneralTime());
                        Utils.LogVPrint( "getFireTime:" + result.getFireTime());
                        Utils.LogVPrint(  "getCrematorName:" + result.getCrematorName());
                        Utils.LogVPrint(  "getServiceWindows:" + result.getServiceWindows());
                        Utils.LogVPrint( "getBodiesPark:" + result.getBodiesPark());
                        Utils.LogVPrint( "getBodiesByeTime:" + result.getBodiesByeTime());
                        Utils.LogVPrint( "getAshDeal:" + result.getAshDeal());
                        fdiv.setData(result);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//						Toast.makeText(getApplicationContext(), "获取殡仪详情失败！", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void getCustomerContract() {
        HpGetOrderDetailParams params = new HpGetOrderDetailParams();
        params.setConsultId(consultId);
        params.setOrderId(orderId);
        MHttpManagerFactory.getAccountManager().getCustomerContract(this, params,
                new HttpResponseHandler<HrGetCustomerContract>() {
                    @Override
                    public void onSuccess(HrGetCustomerContract result) {
                        ctdiv.setData(result);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//						Toast.makeText(getApplicationContext(), "获取合同详情失败！", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void getManagerDetail() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getCustomerAgentman(this, params,
                new HttpResponseHandler<HrConsultAgentman>() {

                    @Override
                    public void onSuccess(HrConsultAgentman result) {
                        mdiv.setData(result);

                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
//						Toast.makeText(getApplicationContext(), "获取经办人详情失败！", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void getCustomerDeatail() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getCustomerUsage(this, params,
                new HttpResponseHandler<HrConsultUsageResult>() {

                    @Override
                    public void onSuccess(HrConsultUsageResult result) {
                        cdiv.setData(result);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//						Toast.makeText(getApplicationContext(), "获取用户详情失败！", Toast.LENGTH_SHORT).show();

                    }
                });

    }

}
