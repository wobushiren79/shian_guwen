package com.shian.shianlife.activity.updata;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.customer.PicImageView;
import com.shian.shianlife.common.view.finger.DrawView;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.imp.impl.ProductManagerImpl;
import com.shian.shianlife.provide.model.CemeteryModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.provide.result.HrGetCemeteryResult;
import com.shian.shianlife.provide.result.HrGetContractData;
import com.shian.shianlife.provide.result.HrGetFuneralSetmealResult;
import com.shian.shianlife.provide.result.HrGetMainSetmealResult;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.provide.result.HrOrderFeedback;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.shian.shianlife.view.ScreenShot;

import java.util.List;

public class ContractDataActivity extends BaseActivity {

    ScrollView theScrollView;

    TextView mTVNext;
    TextView mTVBack;
    TextView mTVComplete;

    TextView mTVAllMoney;
    TextView mTVFirstMoney;
    TextView mTVLastMoney;

    TextView mTVContractId;

    ImageView dvPay;
    long consultId;
    long orderId;

    TextView mTVFirstName;
    TextView mTVDeadName;
    TextView mTVDeadSex;
    TextView mTVDeadState;
    TextView mTVDeadCardId;
    TextView mTVDeadAge;
    TextView mTVDeadShoes;
    TextView mTVAgentManName;
    TextView mTVAgentManPhone;
    TextView mTVAgentManEmail;
    TextView mTVAgentManCardId;
    TextView mTVAgentManLocation;
    TextView mTVAgentManRelation;

    TextView mTVZSLocation;
    TextView mTVDLLocation;

    DisplayMetrics metrics = new DisplayMetrics();
    /**
     * 用户之前提交的订单详情内容
     */
    List<ProjectItemModel> projectItems;

    private List<SetmealModel> mainSetmeals;// 系统主套餐
    private List<SetmealModel> funeralSetmeals;// 系统殡仪馆信息
    private List<CemeteryModel> cemeteries;// 系统公墓信息

    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_data);
        consultId = getIntent().getLongExtra("consultId", 0);
        orderId = getIntent().getLongExtra("orderId", 0);
        setTitle("合同信息");


        initView();
        initData();
    }


    private void initView() {
        mTVNext = (TextView) findViewById(R.id.tv_next);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVComplete = (TextView) findViewById(R.id.tv_complete);
        mTVContractId = (TextView) findViewById(R.id.tv_contractId);

        mTVAllMoney = (TextView) findViewById(R.id.tv_allmoney);
        mTVFirstMoney = (TextView) findViewById(R.id.tv_firstmoney);
        mTVLastMoney = (TextView) findViewById(R.id.tv_lastmoney);

        dvPay = (ImageView) findViewById(R.id.dv_pay);
        theScrollView = (ScrollView) findViewById(R.id.activity_contract_data);

        mTVFirstName = (TextView) findViewById(R.id.tv_name);
        mTVDeadName = (TextView) findViewById(R.id.tv_deadmanname);
        mTVDeadSex = (TextView) findViewById(R.id.tv_deadmansex);
        mTVDeadState = (TextView) findViewById(R.id.tv_deadmanstate);
        mTVDeadCardId = (TextView) findViewById(R.id.tv_deadmancardid);
        mTVDeadAge = (TextView) findViewById(R.id.tv_deadmanage);
        mTVDeadShoes = (TextView) findViewById(R.id.tv_deadmanshoes);
        mTVAgentManName = (TextView) findViewById(R.id.tv_agentmanname);
        mTVAgentManPhone = (TextView) findViewById(R.id.tv_agentmanphone);
        mTVAgentManEmail = (TextView) findViewById(R.id.tv_agentmanemail);
        mTVAgentManCardId = (TextView) findViewById(R.id.tv_agentmancardid);
        mTVAgentManLocation = (TextView) findViewById(R.id.tv_agentmanlocation);
        mTVAgentManRelation = (TextView) findViewById(R.id.tv_agentmanrelation);
        mTVZSLocation= (TextView) findViewById(R.id.tv_zslocation);
        mTVDLLocation= (TextView) findViewById(R.id.tv_dlman);


        mTVNext.setOnClickListener(onClickListener);
        mTVBack.setOnClickListener(onClickListener);
        mTVComplete.setOnClickListener(onClickListener);
        dvPay.setOnClickListener(onClickListener);
    }

    private void initData() {

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        getMainSetmeals();
        getMoney();
//        getData();


    }

    private void getData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId((ContractDataActivity.this).getIntent().getLongExtra("consultId", 0));
        MHttpManagerFactory.getAccountManager().getContractData(ContractDataActivity.this, params,
                new HttpResponseHandler<HrGetContractData>() {

                    @Override
                    public void onSuccess(HrGetContractData result) {
                        // TODO Auto-generated method stub

                        String deadmanName = result.getDeadmanName();
                        String deadmanSex = result.getDeadmanSex();
                        String deadmanState = result.getDeadmanState();
                        String deadmanCardId = result.getDeadmanCardId();
                        String deadmanAge = result.getDeadmanAge();
                        String deadmanShoes = result.getDeadmanShoes();

                        String agentmanName = result.getAgentmanName();
                        String agentmanPhone = result.getAgentmanPhone();
                        String agentmanEmail = result.getAgentmanEmail();
                        String agentmanCardId = result.getAgentmanCardId();
                        String agentmanLocation = result.getAgentmanLocation();
                        String agentmanRelation = result.getAgentmanRelation();

                        String zsLocation = result.getZsLocation();
                        String adviserName = result.getAdviserName();


                        Log.v("this", "deadmanName" + deadmanName);
                        Log.v("this", "deadmanSex" + deadmanSex);
                        Log.v("this", "deadmanState" + deadmanState);
                        Log.v("this", "deadmanCardId" + deadmanCardId);
                        Log.v("this", "deadmanAge" + deadmanAge);
                        Log.v("this", "deadmanShoes" + deadmanShoes);
                        Log.v("this", "agentmanName" + agentmanName);
                        Log.v("this", "agentmanPhone" + agentmanPhone);
                        Log.v("this", "agentmanEmail" + agentmanEmail);
                        Log.v("this", "agentmanCardId" + agentmanCardId);
                        Log.v("this", "agentmanLocation" + agentmanLocation);
                        Log.v("this", "agentmanRelation" + agentmanRelation);

                        Log.v("this", "zsLocation" + zsLocation);
                        Log.v("this", "adviserName" + adviserName);

                        mTVFirstName.setText(agentmanName);
                        mTVDeadName.setText(deadmanName);
                        mTVDeadSex.setText(deadmanSex);
                        mTVDeadState.setText(deadmanState);
                        mTVDeadCardId.setText(deadmanCardId);
                        mTVDeadAge.setText(deadmanAge);
                        mTVDeadShoes.setText(deadmanShoes);
                        mTVAgentManName.setText(agentmanName);
                        mTVAgentManPhone.setText(agentmanPhone);
                        mTVAgentManEmail.setText(agentmanEmail);
                        mTVAgentManCardId.setText(agentmanCardId);
                        mTVAgentManLocation.setText(agentmanLocation);
                        mTVAgentManRelation.setText(agentmanRelation);

                        mTVZSLocation.setText(zsLocation);
                        mTVDLLocation.setText(adviserName);
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void getMoney() {
        HpOrderIdParams params = new HpOrderIdParams();
        params.setOrderId(getIntent().getLongExtra("orderId", 0));
        MHttpManagerFactory.getAccountManager().getOrderFeedback(this, params,
                new HttpResponseHandler<HrOrderFeedback>() {

                    @Override
                    public void onSuccess(HrOrderFeedback result) {
                        // TODO Auto-generated method stub
                        mTVFirstMoney.setText(result.getPrepayAmount() + "元");
                        mTVAllMoney.setText(result.getTotalAmount() + "元");
                        mTVLastMoney.setText(result.getRestAmount() + "元");
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVNext) {
                Intent intent = new Intent(ContractDataActivity.this, TQZBDataActivity.class);
                intent.putExtra("consultId", consultId);
                startActivity(intent);
            } else if (view == mTVBack) {
                Intent intent = new Intent(ContractDataActivity.this, JBRDataActivity.class);
                intent.putExtra("consultId", consultId);
                startActivity(intent);
                finish();
            } else if (view == mTVComplete) {
                complete();
            } else if (view == dvPay) {
                drawName();
            }
        }
    };

    private void complete() {
        final Bitmap bitmap = ScreenShot.getBitmapByView(theScrollView);
        new Thread() {
            @Override
            public void run() {
//                String fName = ScreenShot.savePic(ScreenShot.compressImage(bitmap));
                String fName = ScreenShot.savePic(bitmap);
//                uploadFile(consultId + "", fName);
                if (customDialog != null) {
                    customDialog.cancel();
                }
            }
        }.start();

        customDialog = new CustomDialog(ContractDataActivity.this);
        customDialog.show();
    }

    private void uploadFile(final String file, String path) {
        MHttpManagerFactory.getFileManager().upLoadFile(ContractDataActivity.this, file,
                path, new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(ContractDataActivity.this, "上传成功");
                        HpSaveCustomerContract.AddAddition add = new HpSaveCustomerContract.AddAddition();
                        add.setFileName(file);
                        add.setFileUrl(t.getNameMap().get(file).toString());
                        String FileUrl = AppContansts.OSSURL + add.getFileUrl();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long total, long current,
                                           boolean isUploading) {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    private void drawName() {


        AlertDialog alertDialog;
        final DrawView drawView = new DrawView(ContractDataActivity.this);
        drawView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.dimen_40dp)));
        drawView.changeColour(7);
        drawView.requestFocus();
        drawView.changeColour(5);
        alertDialog = new AlertDialog.Builder(ContractDataActivity.this)
                .setTitle("请绘制签名")
                .setView(drawView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bitmap bitmapName = drawView.getBitamp();
                        dvPay.setImageBitmap(bitmapName);
                    }
                })
                .create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(metrics.widthPixels, metrics.heightPixels / 2);
    }


    /**
     * 获取套餐信息
     */
    private void getMainSetmeals() {
        ProductManagerImpl.getInstance().getMainSetmeal(this,
                new HttpResponseHandler<HrGetMainSetmealResult>() {

                    @Override
                    public void onSuccess(HrGetMainSetmealResult result) {
                        mainSetmeals = result.getMains();
                        getFuneralSetmeals();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
                    }
                });

    }

    /**
     * 获取殡仪馆信息
     */
    protected void getFuneralSetmeals() {
        ProductManagerImpl.getInstance().getFuneralsSetmeal(this,
                new HttpResponseHandler<HrGetFuneralSetmealResult>() {

                    @Override
                    public void onSuccess(HrGetFuneralSetmealResult result) {
                        funeralSetmeals = result.getFunerals();
                        getCemeterys();
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
                    }
                });
    }

    /**
     * 获取公墓信息
     */
    protected void getCemeterys() {
        ProductManagerImpl.getInstance().getCemeteryResult(this,
                new HttpResponseHandler<HrGetCemeteryResult>() {

                    @Override
                    public void onSuccess(HrGetCemeteryResult result) {
                        cemeteries = result.getRetCemeteries();
                        if (orderId != -1) {
                            getOrderDetail();
                        } else {
//                            initOrderView();
                        }
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
                    }
                });

    }

    /**
     * 获取订单详情
     */
    protected void getOrderDetail() {
        HpGetOrderDetailParams params = new HpGetOrderDetailParams();
        params.setOrderId(orderId);
        OrderManagerImpl.getInstance().getOrderDetail(this, params,
                new HttpResponseHandler<HrGetOrderDetailResult>() {

                    @Override
                    public void onSuccess(HrGetOrderDetailResult result) {
                        projectItems = result.getProjectItems();
//                        initOrderView(result);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }


}
