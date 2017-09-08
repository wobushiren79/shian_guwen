package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class GoodsServiceInfoLayout extends BaseLayout implements View.OnClickListener{
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
    @InjectView(R.id.ll_content)
    LinearLayout llContent;

    private String customerName;
    private String customerPhone;
    private String serviceLocation;
    private String serviceTime;

    public static final int Mode_Show = 0;
    public static final int Mode_Edit = 1;

    public GoodsServiceInfoLayout(Context context) {
        this(context, null);
    }

    public GoodsServiceInfoLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_store_order_serviceinfo, attrs);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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
                llContent.setOnClickListener(null);
                break;

            case Mode_Edit:
                ivMore.setVisibility(VISIBLE);
                llContent.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if(v==llContent){
            editServiceInfo();
        }
    }

    /**
     * 编辑服务信息
     */
    private void editServiceInfo(){

    }
}
