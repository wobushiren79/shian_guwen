package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsDescribeApplyLayout extends BaseLayout {
    @InjectView(R.id.tv_apply_bury)
    TextView tvApplyBury;
    @InjectView(R.id.tv_apply_person)
    TextView tvApplyPerson;
    @InjectView(R.id.tv_apply_phase)
    TextView tvApplyPhase;
    @InjectView(R.id.tv_apply_age)
    TextView tvApplyAge;
    @InjectView(R.id.tv_apply_location)
    TextView tvApplyLocation;

    public GoodsDescribeApplyLayout(Context context) {
        this(context, null);
    }

    public GoodsDescribeApplyLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_describle_apply, attrs);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void setApplyBury(String applyBury) {
        tvApplyBury.setText(applyBury);
    }

    public void setApplyPerson(String applyPerson) {
        tvApplyPerson.setText(applyPerson);
    }

    public void setApplyPhase(String applyPhase) {
        tvApplyPhase.setText(applyPhase);
    }

    public void setApplyAge(String applyAge) {
        tvApplyAge.setText(applyAge);
    }

    public void setApplyLocation(String applyLocation) {
        tvApplyLocation.setText(applyLocation);
    }
}

