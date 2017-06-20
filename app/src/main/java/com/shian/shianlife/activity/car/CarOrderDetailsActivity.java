package com.shian.shianlife.activity.car;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.CemeteryOrderModel;
import com.shian.shianlife.provide.params.HpCarBuildOrder;
import com.shian.shianlife.provide.result.HrGetCarDetails;
import com.shian.shianlife.thisenum.CarBusiTypeEnum;
import com.shian.shianlife.thisenum.DriverStateEnum;
import com.shian.shianlife.view.writeview.EditTextViewNormal;


public class CarOrderDetailsActivity extends BaseActivity {
    private CemeteryOrderModel data;

    EditTextViewNormal mOrderState;
    EditTextViewNormal mOrderTime;
    EditTextViewNormal mPersonNum;
    EditTextViewNormal mPurpose;
    EditTextViewNormal mUsePerson;
    EditTextViewNormal mUsePersonMobile;
    EditTextViewNormal mGoLocation;
    EditTextViewNormal mArriveLocation;
    EditTextViewNormal mRemark;
    EditTextViewNormal mCarNum;
    EditTextViewNormal mCarColor;
    EditTextViewNormal mCarType;
    EditTextViewNormal mCarSeat;
    EditTextViewNormal mCarDriver;
    EditTextViewNormal mCarPhone;


    EditTextViewNormal mCancelReason;
    LinearLayout mLLCancelReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_order_details);
        initView();
        initData();
    }

    private void initView() {
        setTitle("用车记录");
        mOrderState = (EditTextViewNormal) findViewById(R.id.driver_order_state);
        mOrderTime = (EditTextViewNormal) findViewById(R.id.driver_order_time);
        mPersonNum = (EditTextViewNormal) findViewById(R.id.driver_order_personnum);
        mPurpose = (EditTextViewNormal) findViewById(R.id.driver_order_purpose);
        mUsePerson = (EditTextViewNormal) findViewById(R.id.driver_order_useperson);
        mUsePersonMobile = (EditTextViewNormal) findViewById(R.id.driver_order_useperson_mobile);
        mGoLocation = (EditTextViewNormal) findViewById(R.id.driver_order_golocation);
        mArriveLocation = (EditTextViewNormal) findViewById(R.id.driver_order_arrivelocation);
        mRemark = (EditTextViewNormal) findViewById(R.id.driver_order_remark);
        mCancelReason = (EditTextViewNormal) findViewById(R.id.driver_cancel_reason);
        mLLCancelReason = (LinearLayout) findViewById(R.id.ll_cancel_reason);

        mCarNum = (EditTextViewNormal) findViewById(R.id.car_num);
        mCarColor = (EditTextViewNormal) findViewById(R.id.car_color);
        mCarType = (EditTextViewNormal) findViewById(R.id.car_type);
        mCarSeat = (EditTextViewNormal) findViewById(R.id.car_seat);
        mCarDriver = (EditTextViewNormal) findViewById(R.id.car_driver);
        mCarPhone = (EditTextViewNormal) findViewById(R.id.car_phone);


        mOrderState.setDisable(false);
        mOrderState.setTextColor(getResources().getColor(R.color.zhy_text_color_4));
        mOrderTime.setDisable(false);
        mPersonNum.setDisable(false);
        mPurpose.setDisable(false);
        mUsePerson.setDisable(false);
        mUsePersonMobile.setDisable(false);
        mGoLocation.setDisable(false);
        mArriveLocation.setDisable(false);
        mRemark.setDisable(false);

        mCarNum.setDisable(false);
        mCarColor.setDisable(false);
        mCarType.setDisable(false);
        mCarSeat.setDisable(false);
        mCarDriver.setDisable(false);
        mCarPhone.setDisable(false);
    }

    private void initData() {
        data = (CemeteryOrderModel) getIntent().getSerializableExtra("data");
        getData();
    }

    private void getData() {
        if (data == null) {
            ToastUtils.show(this, "数据错误");
            return;
        }
        HpCarBuildOrder params = new HpCarBuildOrder();
        params.setBusiId(data.getBespeakId());
        params.setBusiType(CarBusiTypeEnum.cemetery_bespeakid.getText());
        MHttpManagerFactory.getAccountManager().getCarBuildData(this, params, new HttpResponseHandler<HrGetCarDetails>() {

            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCarDetails result) {
                if (result.getOrder() != null) {
                    HrGetCarDetails.CarOrder carorder = result.getOrder();
                    if (carorder.getStatus() != null)
                        for (DriverStateEnum state : DriverStateEnum.values()) {
                            if (state.getCode() == carorder.getStatus()) {
                                mOrderState.setData(state.getName());
                            }
                        }
                    if (carorder.getAppointmentTime() != null)
                        mOrderTime.setData(carorder.getAppointmentTime());
                    if (carorder.getSeats() != null)
                        mPersonNum.setData(carorder.getSeats());
                    if (carorder.getPurpose() != null)
                        mPurpose.setData(carorder.getPurpose());
                    if (carorder.getConnecterName() != null)
                        mUsePerson.setData(carorder.getConnecterName());
                    if (carorder.getConnecterMobile() != null)
                        mUsePersonMobile.setData(carorder.getConnecterMobile());
                    if (carorder.getSource() != null)
                        mGoLocation.setData(carorder.getSource());
                    if (carorder.getTarget() != null)
                        mArriveLocation.setData(carorder.getTarget());
                    if (carorder.getRemark() != null)
                        mRemark.setData(carorder.getRemark());
                    if (carorder.getCancelReason() != null) {
                        mLLCancelReason.setVisibility(View.VISIBLE);
                        mCancelReason.setData(carorder.getCancelReason());
                    }
                }

                if (result.getAutocar() != null) {
                    HrGetCarDetails.Autocar autocar = result.getAutocar();
                    if (autocar.getNumber() != null)
                        mCarNum.setData(autocar.getNumber());
                    if (autocar.getColor() != null)
                        mCarColor.setData(autocar.getColor());
                    if (autocar.getStyle() != null)
                        if (autocar.getStyle() == 0) {
                            mCarType.setData("商务面包");
                        } else if (autocar.getStyle() == 0) {
                            mCarType.setData("高级商务");
                        } else if (autocar.getStyle() == 0) {
                            mCarType.setData("轿车");
                        }
                    if (autocar.getSeats() != null)
                        mCarSeat.setData(autocar.getSeats() + "");
                }

                if (result.getDriver() != null) {
                    HrGetCarDetails.Driver driver = result.getDriver();
                    if (driver.getName() != null)
                        mCarDriver.setData(driver.getName());
                    if (driver.getMobile() != null)
                        mCarPhone.setData(driver.getMobile());
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
