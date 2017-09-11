package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfoBean;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsServiceInfoPresenter;
import com.shian.shianlife.mvp.shared.presenter.impl.SharedGoodsServiceInfoPresenterImpl;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsServiceInfoView;
import com.shian.shianlife.thisenum.GoodsServiceWayEnum;
import com.shian.shianlife.view.goods.StoreEditNormalView;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.summerxia.dateselector.widget.LocationSelectorDialogBuilder;

import butterknife.InjectView;
import butterknife.OnClick;

public class GoodsOrderServiceInfoActivity extends BaseActivity implements ISharedGoodsServiceInfoView, StoreEditNormalView.CallBack, RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.layout_customer_name)
    StoreEditNormalView layoutCustomerName;
    @InjectView(R.id.layout_customer_phone)
    StoreEditNormalView layoutCustomerPhone;
    @InjectView(R.id.rb_service_way_1)
    RadioButton rbServiceWay1;
    @InjectView(R.id.rb_service_way_2)
    RadioButton rbServiceWay2;
    @InjectView(R.id.rg_service_way)
    RadioGroup rgServiceWay;
    @InjectView(R.id.layout_service_location)
    StoreEditNormalView layoutServiceLocation;
    @InjectView(R.id.layout_service_details_location)
    StoreEditNormalView layoutServiceDetailsLocation;
    @InjectView(R.id.layout_service_time)
    StoreEditNormalView layoutServiceTime;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;

    private ISharedGoodsServiceInfoPresenter goodsServiceInfoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_service_info);
        initView();
        initData();
    }

    private void initView() {
        setTitle("服务信息");
        layoutServiceLocation.setMode(StoreEditNormalView.Mode_Check);
        layoutServiceTime.setMode(StoreEditNormalView.Mode_Check);
        layoutServiceLocation.setCallBack(this);
        layoutServiceTime.setCallBack(this);
        rgServiceWay.setOnCheckedChangeListener(this);
    }

    private void initData() {
        goodsServiceInfoPresenter = new SharedGoodsServiceInfoPresenterImpl(this);
        goodsServiceInfoPresenter.getServiceInfoData();
    }


    @OnClick({R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                goodsServiceInfoPresenter.setServiceInfoData();
                break;
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void getSharedGoodsServiceInfoSuccess(SharedGoodsServiceInfoBean result) {
    }

    @Override
    public void getSharedGoodsServiceInfoFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void setSharedGoodsServiceInfoSuccess(String msg) {
        ToastUtils.show(this, msg);
        GoodsOrderSettlementActivity.isSaveServiceInfo = true;
        finish();
    }

    @Override
    public void setSharedGoodsServiceInfoFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void setServiceInfoCustomerName(String name) {
        layoutCustomerName.setData(name);
    }

    @Override
    public void setServiceInfoCustomerPhone(String phone) {
        layoutCustomerPhone.setData(phone);
    }

    @Override
    public void setServiceInfoServiceWay(Integer serviceWay) {
        if (serviceWay == GoodsServiceWayEnum.now_service.getCode()) {
            rbServiceWay1.setChecked(true);
        } else if (serviceWay == GoodsServiceWayEnum.plan_service.getCode()) {
            rbServiceWay2.setChecked(true);
        }
    }

    @Override
    public void setServiceInfoServiceLocation(String serviceLocation) {
        layoutServiceLocation.setData(serviceLocation);
    }

    @Override
    public void setServiceInfoServiceDetailsLocation(String serviceDetailsLocation) {
        layoutServiceDetailsLocation.setData(serviceDetailsLocation);
    }

    @Override
    public void setServiceInfoServiceTime(String serviceTime) {
        layoutServiceTime.setData(serviceTime);
    }

    @Override
    public String getServiceInfoCustomerName() {
        return layoutCustomerName.getData();
    }

    @Override
    public String getServiceInfoCustomerPhone() {
        return layoutCustomerPhone.getData();
    }

    @Override
    public Integer getServiceInfoServiceWay() {
        Integer serviceWay = -1;
        if (rbServiceWay1.isChecked()) {
            serviceWay = 0;
        } else if (rbServiceWay2.isChecked()) {
            serviceWay = 1;
        }
        return serviceWay;
    }

    @Override
    public String getServiceInfoServiceLocation() {
        return layoutServiceLocation.getData();
    }

    @Override
    public String getServiceInfoServiceDetailsLocation() {
        return layoutServiceDetailsLocation.getData();
    }

    @Override
    public String getServiceInfoServiceTime() {
        return layoutServiceTime.getData();
    }

    @Override
    public void onCheck(View view) {
        if (view == layoutServiceLocation) {
            getLocationSelect();
        } else if (view == layoutServiceTime) {
            getTimeSelect();
        }
    }


    public void getLocationSelect() {
        ViewUtils.getLocationSelectDialog(this, new LocationSelectorDialogBuilder.OnSaveLocationLister() {
            @Override
            public void onSaveLocation(String location, String provinceId, String cityId) {
                layoutServiceLocation.setData(location);
            }
        });
    }


    public void getTimeSelect() {
        ViewUtils.getTimeSelectDialog(this, new DateTimeSelectorDialogBuilder.OnSaveListener() {
            @Override
            public void onSaveSelectedDate(String selectedDate) {
                layoutServiceTime.setData(selectedDate);
            }
        }, true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == rbServiceWay1.getId()) {
            layoutServiceTime.setVisibility(View.GONE);
            layoutServiceTime.setData("");
        } else if (checkedId == rbServiceWay2.getId()) {
            layoutServiceTime.setVisibility(View.VISIBLE);
        }
    }
}
