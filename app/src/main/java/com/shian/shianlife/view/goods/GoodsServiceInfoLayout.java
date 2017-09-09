package com.shian.shianlife.view.goods;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsOrderServiceInfoActivity;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsServiceInfo;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsServiceInfoPresenter;
import com.shian.shianlife.mvp.shared.presenter.impl.SharedGoodsServiceInfoPresenterImpl;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsServiceInfoView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class GoodsServiceInfoLayout extends BaseLayout implements View.OnClickListener, ISharedGoodsServiceInfoView {
    @InjectView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @InjectView(R.id.tv_customer_phone)
    TextView tvCustomerPhone;
    @InjectView(R.id.tv_service_location)
    TextView tvServiceLocation;
    @InjectView(R.id.tv_service_time)
    TextView tvServiceTime;

    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.ll_edit)
    LinearLayout llEdit;
    @InjectView(R.id.ll_show)
    LinearLayout llShow;

    private String customerName;
    private String customerPhone;
    private String serviceLocation;
    private String serviceTime;

    public static final int Mode_Show = 0;
    public static final int Mode_Edit = 1;

    private SharedGoodsServiceInfo serviceInfoData;

    private ISharedGoodsServiceInfoPresenter sharedGoodsServiceInfoPresenter;

    public GoodsServiceInfoLayout(Context context) {
        this(context, null);
    }

    public GoodsServiceInfoLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_store_order_serviceinfo, attrs);
    }

    @Override
    protected void initView() {
        setMode(Mode_Show);
        setHasData(false);
    }

    @Override
    protected void initData() {
        sharedGoodsServiceInfoPresenter = new SharedGoodsServiceInfoPresenterImpl(this);
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
        tvCustomerName.setText(customerName);
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
        tvCustomerPhone.setText(customerPhone);
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
        tvServiceLocation.setText(serviceLocation);
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
        tvServiceTime.setText(serviceTime);
    }


    public void setMode(int mode) {
        switch (mode) {
            case Mode_Show:
                ivMore.setVisibility(GONE);
                llEdit.setOnClickListener(null);
                llShow.setOnClickListener(null);
                break;

            case Mode_Edit:
                ivMore.setVisibility(VISIBLE);
                llEdit.setOnClickListener(this);
                llShow.setOnClickListener(this);
                break;
        }
    }

    public void getDataForShared() {
        sharedGoodsServiceInfoPresenter.getServiceInfoData();
    }

    public void setHasData(Boolean hasData) {
        if (hasData) {
            llEdit.setVisibility(GONE);
            llShow.setVisibility(VISIBLE);
        } else {
            llEdit.setVisibility(VISIBLE);
            llShow.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == llEdit || v == llShow) {
            editServiceInfo();
        }
    }

    /**
     * 编辑服务信息
     */
    private void editServiceInfo() {
        Intent intent = new Intent(getContext(), GoodsOrderServiceInfoActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getSharedGoodsServiceInfoSuccess(SharedGoodsServiceInfo result) {
        serviceInfoData = result;
        setHasData(true);
    }

    @Override
    public void getSharedGoodsServiceInfoFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void setSharedGoodsServiceInfoSuccess(String msg) {

    }

    @Override
    public void setSharedGoodsServiceInfoFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void setServiceInfoCustomerName(String name) {
        tvCustomerName.setText("客户：" + name);
    }

    @Override
    public void setServiceInfoCustomerPhone(String phone) {
        tvCustomerPhone.setText(phone);
    }

    @Override
    public void setServiceInfoServiceWay(Integer serviceWay) {

    }

    @Override
    public void setServiceInfoServiceLocation(String serviceLocation) {
    }

    @Override
    public void setServiceInfoServiceDetailsLocation(String serviceDetailsLocation) {
        tvServiceLocation.setText("地址：" + serviceDetailsLocation);
    }

    @Override
    public void setServiceInfoServiceTime(String serviceTime) {
        if (serviceTime != null && !serviceTime.isEmpty())
            tvServiceTime.setText("服务时间：" + serviceTime);
    }

    @Override
    public String getServiceInfoCustomerName() {
        return null;
    }

    @Override
    public String getServiceInfoCustomerPhone() {
        return null;
    }

    @Override
    public Integer getServiceInfoServiceWay() {
        return null;
    }

    @Override
    public String getServiceInfoServiceLocation() {
        return null;
    }

    @Override
    public String getServiceInfoServiceDetailsLocation() {
        return null;
    }

    @Override
    public String getServiceInfoServiceTime() {
        return null;
    }
}
