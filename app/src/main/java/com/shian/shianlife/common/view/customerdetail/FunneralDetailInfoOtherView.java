package com.shian.shianlife.common.view.customerdetail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.provide.result.HrGetCustomerFuneralOther;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/24.
 */

public class FunneralDetailInfoOtherView extends FrameLayout {
    @InjectView(R.id.tv_deadtime)
    TextView tv_deadtime;

    @InjectView(R.id.tv_deadlocation)
    TextView tv_deadlocation;

    @InjectView(R.id.tv_zslocation)
    TextView tv_zslocation;

    @InjectView(R.id.tv_funerallocation)
    TextView tv_funerallocation;

    @InjectView(R.id.tv_funeraltime)
    TextView tv_funeraltime;

    @InjectView(R.id.tv_firetime)
    TextView tv_firetime;

    @InjectView(R.id.tv_crematorname)
    TextView tv_crematorname;

    @InjectView(R.id.tv_windowservice)
    TextView tv_windowservice;

    @InjectView(R.id.tv_bodiespark)
    TextView tv_bodiespark;

    @InjectView(R.id.tv_bodiesbyetime)
    TextView tv_bodiesbyetime;

    @InjectView(R.id.tv_asldeal)
    TextView tv_asldeal;

    public FunneralDetailInfoOtherView(Context context) {
        super(context);
        init();
    }

    public FunneralDetailInfoOtherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_customer_deadinfoother, null);
        addView(view);
        ButterKnife.inject(this);
    }

    public void setData(HrGetCustomerFuneralOther result) {
        tv_crematorname.setText(result.getCrematorName());
        tv_windowservice.setText(result.getServiceWindows());
        tv_bodiespark.setText(result.getBodiesPark());
        tv_asldeal.setText(result.getAshDeal());
        tv_deadlocation.setText(result.getDeadLocation());
        tv_zslocation.setText(result.getZsLocation());
        tv_funerallocation.setText(result.getFuneralLocation());
        if (result.getDeadTime() != 0) {
            tv_deadtime.setText(TimeUtils.formatTime(result.getDeadTime()));
        }
        if (result.getFuneralTime() != 0) {
            tv_funeraltime.setText(TimeUtils.formatTime(result.getFuneralTime()));
        }
        if (result.getFireTime() != 0) {
            tv_firetime.setText(TimeUtils.formatTime(result.getFireTime()));
        }
        if (result.getBodiesByeTime() != 0) {
            tv_bodiesbyetime.setText(TimeUtils.formatTime(result.getBodiesByeTime()));
        }
    }
}
