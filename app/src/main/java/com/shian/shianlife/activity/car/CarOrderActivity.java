package com.shian.shianlife.activity.car;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.CemeteryServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.SelectDictCode;
import com.shian.shianlife.common.utils.StringUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.CemeteryOrderModel;
import com.shian.shianlife.provide.params.HpCarBuildOrder;
import com.shian.shianlife.thisenum.CarBusiTypeEnum;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;


public class CarOrderActivity extends BaseActivity implements View.OnClickListener {
    TimeSelectViewNormal mUseTime;
    EditTextViewNormal mSubmitPerson;
    EditTextViewNormal mSubmitPersonPhone;
    EditTextViewNormal mUsePerson;
    EditTextViewNormal mUsePersonPhone;
    EditTextViewNormal mPersonNum;
    MapSelectViewNormal mGetLocation;
    MapSelectViewNormal mArriveLocation;
    EditTextViewNormal mRemark;
    SpinnerViewNormal mUseReason;
    TextView mSubmit;
    private CemeteryOrderModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_order);
        initView();
        initData();
    }

    private void initData() {
        data = (CemeteryOrderModel) getIntent().getSerializableExtra("data");
        setTitle("申请用车");
        if (data != null) {
            mUsePerson.setData(data.getCustomerName());
            mUsePersonPhone.setData(data.getCustomerMobile());
        }
        if (AppContansts.systemLoginInfo != null && AppContansts.systemLoginInfo.getUserObj() != null) {
            mSubmitPerson.setData(AppContansts.systemLoginInfo.getUserObj().getName());
            mSubmitPersonPhone.setData(AppContansts.systemLoginInfo.getUserObj().getPhone());
        }
    }

    private void initView() {
        mUseTime = (TimeSelectViewNormal) findViewById(R.id.car_use_time);
        mSubmitPerson = (EditTextViewNormal) findViewById(R.id.car_send_person);
        mSubmitPersonPhone = (EditTextViewNormal) findViewById(R.id.car_send_person_phone);
        mUsePerson = (EditTextViewNormal) findViewById(R.id.car_user_person);
        mUsePersonPhone = (EditTextViewNormal) findViewById(R.id.car_user_person_phone);
        mUseReason = (SpinnerViewNormal) findViewById(R.id.car_use_reason);
        mPersonNum = (EditTextViewNormal) findViewById(R.id.car_personnum);
        mGetLocation = (MapSelectViewNormal) findViewById(R.id.car_get_location);
        mArriveLocation = (MapSelectViewNormal) findViewById(R.id.car_arrive_location);
        mRemark = (EditTextViewNormal) findViewById(R.id.car_remark);

        mSubmit = (TextView) findViewById(R.id.tv_submit);

        mSubmit.setOnClickListener(this);
        mSubmitPersonPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPersonNum.setInputType(InputType.TYPE_CLASS_NUMBER);
        mUsePersonPhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        mUseReason.initSpinner(SelectDictCode.USE_CAR_REASON);

        mGetLocation.setNumView(0);
        mArriveLocation.setNumView(1);
    }


    @Override
    public void onClick(View v) {
        if (v == mSubmit) {
            submit();
        }
    }

    /**
     * 提交
     */
    private void submit() {
        if (AppContansts.systemLoginInfo == null || AppContansts.systemLoginInfo.getUserId() == null) {
            ToastUtils.show(this, "账号信息错误，请重新登录");
            Utils.jumpLogin(this);
            return;
        }
        if (data == null) {
            ToastUtils.show(this, "数据错误请重新登录");
            return;
        }
        if (mUseTime.getData().isEmpty()) {
            ToastUtils.show(this, "预约用车时间不能为空");
            return;
        }
        if (mSubmitPerson.getData().isEmpty()) {
            ToastUtils.show(this, "申请人不能为空");
            return;
        }
        if (mSubmitPersonPhone.getData().isEmpty()) {
            ToastUtils.show(this, "申请人电话不能为空");
            return;
        }
        if (!Utils.isPhoneNumber(mSubmitPersonPhone.getData())) {
            ToastUtils.show(this, "申请人电话格式错误");
            return;
        }
        if (mUsePerson.getData().isEmpty()) {
            ToastUtils.show(this, "用车人不能为空");
            return;
        }
        if (mUsePersonPhone.getData().isEmpty()) {
            ToastUtils.show(this, "用车人电话不能为空");
            return;
        }
        if (!Utils.isPhoneNumber(mUsePersonPhone.getData())) {
            ToastUtils.show(this, "用车人电话格式错误");
            return;
        }
        if (mUseReason.getData().isEmpty()) {
            ToastUtils.show(this, "用车理由不能为空");
            return;
        }
        if (mPersonNum.getData().isEmpty()) {
            ToastUtils.show(this, "用车人数不能为空");
            return;
        }
        if (mGetLocation.getData().isEmpty()) {
            ToastUtils.show(this, "预约地不能为空");
            return;
        }
        if (mArriveLocation.getData().isEmpty()) {
            ToastUtils.show(this, "目的地不能为空");
            return;
        }

        HpCarBuildOrder params = new HpCarBuildOrder();
        params.setBusiType(CarBusiTypeEnum.cemetery_bespeakid.getText());
        params.setBusiId(data.getBespeakId());
        params.setProposerId(AppContansts.systemLoginInfo.getUserId());
        params.setProposerName(mSubmitPerson.getData());
        params.setProposerMobile(mSubmitPersonPhone.getData());
        params.setConnecterName(mUsePerson.getData());
        params.setConnecterMobile(mUsePersonPhone.getData());
        params.setPurpose(mUseReason.getData());
        params.setSeats(mPersonNum.getData());
        params.setSource(mGetLocation.getData());
        params.setSourceLongitude(mGetLocation.getLongitude());
        params.setSourceLatitude(mGetLocation.getLatitude());
        params.setTarget(mArriveLocation.getData());
        params.setTargetLongitude(mArriveLocation.getLongitude());
        params.setTargetLatitude(mArriveLocation.getLatitude());
        params.setAppointmentTime(mUseTime.getData() + ":00");
        params.setRemark(mRemark.getData());

        //增加备份数据
        Map<String, String> backUpData = new HashMap<>();
        if (data != null && data.getConsultNumber() != null)
            backUpData.put("consultNumber", data.getConsultNumber());
        String backUpString = StringUtils.getStringFromMap(backUpData);
        params.setBackupData(backUpString);

        MHttpManagerFactory.getCarManager().saveCarBuildData(this, params, new HttpResponseHandler<Object>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(CarOrderActivity.this, "申请成功");
                CemeteryServiceActivity.C_bOrder_isRefresh = true;
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(CarOrderActivity.this, "申请失败");
            }
        });
    }
}
