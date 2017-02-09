package com.shian.shianlife.activity.cemetery;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpCemeteryIdParams;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryTalkData;
import com.shian.shianlife.provide.result.HrGetCemeteryTalkData;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;


public class CemeteryTalkFailActivity extends BaseActivity implements CetemeryTextSelectLayoutView.onSelectedListener {
    int inType = -1;
    long beSpeakId = -1;
    long orderId=-1;
    LinearLayout mLLDetails;

    EditText mETPlanToMoney;
    EditText mETTalkPoint;
    EditText mETTrafficWay;
    EditText mETPersonNum;
    EditText mETRemark;

    TextView mTVTime;
    Button mBTSubmit;

    CetemeryTextSelectLayoutView mSelectPlanToBuy;
    CetemeryTextSelectLayoutView mSelectState1;
    CetemeryTextSelectLayoutView mSelectState2;
    CetemeryTextSelectLayoutView mSelectRelation;
    CetemeryTextSelectLayoutView mSelectResult;

    MapSelectLayoutView mMapSelect1;
    MapSelectLayoutView mMapSelect2;


    List<String> planToBuyList = new ArrayList<>();
    List<String> stateList = new ArrayList<>();
    List<String> relationList = new ArrayList<>();
    List<String> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cemetery_talk_fail);

        initData();
        initView();
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        HpCemeteryIdParams params = new HpCemeteryIdParams();
        params.setBespeakId(beSpeakId);
        MHttpManagerFactory.getAccountManager().getCemeteryTalkInfo(CemeteryTalkFailActivity.this, params, new HttpResponseHandler<HrGetCemeteryTalkData>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryTalkData result) {
                Utils.LogVPrint( "getPlanBuyCemetery:" + result.getPlanBuyCemetery());
                Utils.LogVPrint( "getPlanBuyMoney:" + result.getPlanBuyMoney());
                Utils.LogVPrint( "getUserOneState:" + result.getUserOneState());
                Utils.LogVPrint( "getUserTwoState:" + result.getUserTwoState());
                Utils.LogVPrint( "getAshLocation:" + result.getAshLocation());
                Utils.LogVPrint( "getRelation:" + result.getRelation());
                Utils.LogVPrint( "getTalkPoint:" + result.getTalkPoint());
                Utils.LogVPrint( "isTalkResult:" + result.isTalkResult());
                Utils.LogVPrint( "getTrafficWay:" + result.getTrafficWay());
                Utils.LogVPrint( "getOrderTime:" + result.getOrderTime());
                Utils.LogVPrint( "getPersonNum:" + result.getPersonNum());
                Utils.LogVPrint( "getOrderLocation:" + result.getOrderLocation());
                Utils.LogVPrint( "getRemark:" + result.getRemark());


                mSelectPlanToBuy.setString(result.getPlanBuyCemetery());
                mETPlanToMoney.setText(result.getPlanBuyMoney() + "");
                mSelectState1.setString(result.getUserOneState());
                mSelectState2.setString(result.getUserTwoState());
                mMapSelect1.setLocation(result.getAshLocation());
                mSelectRelation.setString(result.getRelation());
                mETTalkPoint.setText(result.getTalkPoint());
                if (result.isTalkResult()) {
                    mSelectResult.setName("预约二次洽谈");
                } else {
                    mSelectResult.setName("未预约");
                }
                mETTrafficWay.setText(result.getTrafficWay());
                mTVTime.setText(result.getOrderTime());
                mETPersonNum.setText(result.getPersonNum());
                mMapSelect2.setLocation(result.getOrderLocation());
                mETRemark.setText(result.getRemark());
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    /**
     * 保存数据
     */
    private void saveData() {
        HpSaveCemeteryTalkData params = new HpSaveCemeteryTalkData();
        params.setBespeakId(beSpeakId);
        params.setOrderedId(orderId);
        params.setPlanBuyCemetery(mSelectPlanToBuy.getSelectedData());
        params.setPlanBuyMoney(mETPlanToMoney.getText().toString());
        params.setUserOneState(mSelectState1.getSelectedData());
        params.setUserTwoState(mSelectState2.getSelectedData());
        params.setAshLocation(mMapSelect1.getLocation());
        params.setRelation(mSelectRelation.getSelectedData());
        params.setTalkPoint(mETTalkPoint.getText().toString());
        if (mSelectResult.getSelectedData().equals("预约二次洽谈")) {
            params.setTalkResult(true);
        } else {
            params.setTalkResult(false);
        }
        params.setRemark(mETRemark.getText().toString());


        if (params.getBespeakId() == -1||params.getOrderedId()==-1) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "数据获取异常，请重新加载预订单");
            return;
        }
        if (params.getPlanBuyCemetery().isEmpty()) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "购买墓型不能为空");
            return;
        }
        if (params.getPlanBuyMoney().isEmpty()) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "购买价位不能为空");
            return;
        }
        if (params.getUserOneState().isEmpty()) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "使用者1现状不能为空");
            return;
        }
        if (params.getAshLocation().isEmpty()) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "骨灰当前所在地不能为空");
            return;
        }
        if (params.getRelation().isEmpty()) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "联系人与使用者的关系不能为空");
            return;
        }
        if (params.getTalkPoint().isEmpty()) {
            ToastUtils.show(CemeteryTalkFailActivity.this, "洽谈要点不能为空");
            return;
        }

        //如果预约二次洽谈
        if (params.isTalkResult()) {
            params.setTrafficWay(mETTrafficWay.getText().toString());
            params.setOrderTime(mTVTime.getText().toString());
            params.setPersonNum(mETPersonNum.getText().toString());
            params.setOrderLocation(mMapSelect2.getLocation());

            if (params.getTrafficWay().isEmpty()) {
                ToastUtils.show(CemeteryTalkFailActivity.this, "交通方式不能为空");
                return;
            }
            if (params.getOrderTime().isEmpty()) {
                ToastUtils.show(CemeteryTalkFailActivity.this, "预约时间不能为空");
                return;
            }
            if (params.getPersonNum().isEmpty()) {
                ToastUtils.show(CemeteryTalkFailActivity.this, "人数不能为空");
                return;
            }
            if (params.getOrderLocation().isEmpty()) {
                ToastUtils.show(CemeteryTalkFailActivity.this, "预约地点不能为空");
                return;
            }
        }
        Utils.LogVPrint( "BespeakId:" + params.getBespeakId());
        Utils.LogVPrint( "PlanBuyCemetery:" + params.getPlanBuyCemetery());
        Utils.LogVPrint( "PlanBuyMoney:" + params.getPlanBuyMoney());
        Utils.LogVPrint( "UserOneState:" + params.getUserOneState());
        Utils.LogVPrint( "UserTwoState:" + params.getUserTwoState());
        Utils.LogVPrint( "AshLocation:" + params.getAshLocation());
        Utils.LogVPrint( "Relation:" + params.getRelation());
        Utils.LogVPrint( "TalkPoint:" + params.getTalkPoint());
        Utils.LogVPrint( "TalkResult:" + params.isTalkResult());
        Utils.LogVPrint( "TrafficWay:" + params.getTrafficWay());
        Utils.LogVPrint( "OrderTime:" + params.getOrderTime());
        Utils.LogVPrint( "PersonNum:" + params.getPersonNum());
        Utils.LogVPrint( "OrderLocation:" + params.getOrderLocation());
        Utils.LogVPrint( "getRemark:" + params.getRemark());

        MHttpManagerFactory.getAccountManager().saveCemeteryTalkInfo(CemeteryTalkFailActivity.this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                CemeteryFragment.C_bOrder_isRefresh=true;
                ToastUtils.show(CemeteryTalkFailActivity.this, "提交数据成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(CemeteryTalkFailActivity.this, "提交数据失败");
            }
        });
    }

    private void initView() {
        mLLDetails = (LinearLayout) findViewById(R.id.ll_details);
        mETPlanToMoney = (EditText) findViewById(R.id.et_plantomoney);
        mETTalkPoint = (EditText) findViewById(R.id.et_talkpoint);
        mETRemark = (EditText) findViewById(R.id.et_remark);
        mETTrafficWay = (EditText) findViewById(R.id.et_au_note);
        mETPersonNum = (EditText) findViewById(R.id.et_personnum);

        mTVTime = (TextView) findViewById(R.id.tv_time);

        mSelectPlanToBuy = (CetemeryTextSelectLayoutView) findViewById(R.id.select_plantobuy);
        mSelectState1 = (CetemeryTextSelectLayoutView) findViewById(R.id.select_state_1);
        mSelectState2 = (CetemeryTextSelectLayoutView) findViewById(R.id.select_state_2);
        mSelectRelation = (CetemeryTextSelectLayoutView) findViewById(R.id.select_relation);
        mSelectResult = (CetemeryTextSelectLayoutView) findViewById(R.id.select_result);

        mMapSelect1 = (MapSelectLayoutView) findViewById(R.id.mapselect);
        mMapSelect2 = (MapSelectLayoutView) findViewById(R.id.mapselect1);

        mBTSubmit = (Button) findViewById(R.id.bt_submit);

        mSelectPlanToBuy.setName("计划购买墓型：");
        mSelectPlanToBuy.setData(planToBuyList, 0, this);

        mSelectState1.setName("使用者1现状：");
        mSelectState2.setName("使用者2现状：");
        mSelectState1.setData(stateList, 1, this);
        mSelectState2.setData(stateList, 2, this);


        mSelectRelation.setName("联系人是使用者的：");
        mSelectRelation.setData(relationList, 3, this);

        mSelectResult.setName("洽谈结果：");
        mSelectResult.setData(resultList, 4, this);

        mMapSelect1.setData(0, new ArrayList<String>());
        mMapSelect2.setData(1, new ArrayList<String>());

        mTVTime.setOnClickListener(onClickListener);
        mBTSubmit.setOnClickListener(onClickListener);

        if (inType == 0) {
            mBTSubmit.setVisibility(View.VISIBLE);
            mBTSubmit.setText("结束洽谈");
        } else {
            mBTSubmit.setVisibility(View.GONE);
            setStateShow();
        }

    }

    private void initData() {
        inType = getIntent().getIntExtra(CemeteryQTView.TALK_INFO_STATE, -1);
        beSpeakId = getIntent().getLongExtra(CemeteryQTView.TALK_INFO_ID, -1);
        orderId=getIntent().getLongExtra(CemeteryQTView.TALK_INFO_ORDER_ID,-1);
        setTitle("洽谈信息");
        planToBuyList = Utils.stringsToList(SelectData.CEMETERY_TYPE);
        stateList = Utils.stringsToList(SelectData.CEMETERY_STATE);
        relationList = Utils.stringsToList(SelectData.CEMETERY_RELATION);
        resultList = Utils.stringsToList(SelectData.CEMETERY_RESULT);
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {
        switch (num) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                if (i == 0) {
                    mLLDetails.setVisibility(View.VISIBLE);
                } else if (i == 1) {
                    mLLDetails.setVisibility(View.GONE);
                }
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                Utils.timeSelect(CemeteryTalkFailActivity.this, mTVTime);
            } else if (view == mBTSubmit) {
                saveData();
            }
        }
    };

    /**
     * 设置不可点击
     */
    public void setStateShow() {
        mETPlanToMoney.setFocusable(false);
        mETTalkPoint.setFocusable(false);
        mETTrafficWay.setFocusable(false);
        mETPersonNum.setFocusable(false);
        mETRemark.setFocusable(false);

        mTVTime.setClickable(false);

        mSelectPlanToBuy.setStateShow();
        mSelectState1.setStateShow();
        mSelectState2.setStateShow();
        mSelectRelation.setStateShow();
        mSelectResult.setStateShow();

        mMapSelect1.setStateShow();
        mMapSelect2.setStateShow();
    }
}
