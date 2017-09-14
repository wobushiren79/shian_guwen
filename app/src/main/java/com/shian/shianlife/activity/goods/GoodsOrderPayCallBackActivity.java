package com.shian.shianlife.activity.goods;

import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MainActivity;
import com.shian.shianlife.activity.order.StoreServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.base.SaBaseApplication;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.AnimUtils;
import com.shian.shianlife.common.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderPayCallBackActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.rl_content)
    RelativeLayout rlContent;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;


    @InjectView(R.id.iv_pay_bg)
    ImageView ivPayBg;
    @InjectView(R.id.iv_pay_icon)
    ImageView ivPayIcon;
    @InjectView(R.id.iv_pay_other_1)
    ImageView ivPayOther1;
    @InjectView(R.id.iv_pay_other_2)
    ImageView ivPayOther2;
    @InjectView(R.id.iv_pay_other_3)
    ImageView ivPayOther3;
    @InjectView(R.id.iv_pay_other_4)
    ImageView ivPayOther4;
    @InjectView(R.id.iv_pay_other_5)
    ImageView ivPayOther5;

    private boolean isSuccessPay;//是否成功支付

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_pay_call_back);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("支付");
        tvSubmit.setOnClickListener(this);
    }

    private void initData() {
        Utils.call(tvPhone, AppContansts.Shianlife_Phone);
        isSuccessPay = getIntent().getBooleanExtra(IntentName.INTENT_ISTURE, false);

        if (isSuccessPay) {
            tvTitle.setText("您已付款成功");
            ivPayBg.setImageResource(R.drawable.zhy_pay_success_bg);
            ivPayIcon.setImageResource(R.drawable.zhy_pay_success_icon);
            ivPayOther1.setImageResource(R.drawable.zhy_pay_success_other_icon_2);
            ivPayOther2.setImageResource(R.drawable.zhy_pay_success_other_icon_1);
            ivPayOther3.setImageResource(R.drawable.zhy_pay_success_other_icon_2);
            ivPayOther4.setImageResource(R.drawable.zhy_pay_success_other_icon_1);
            ivPayOther5.setImageResource(R.drawable.zhy_pay_success_other_icon_2);
        } else {
            tvTitle.setText("付款失败，请重新支付");
            ivPayBg.setImageResource(R.drawable.zhy_pay_fail_bg);
            ivPayIcon.setImageResource(R.drawable.zhy_pay_fail_icon);
            ivPayOther1.setImageResource(R.drawable.zhy_pay_fail_other_icon_2);
            ivPayOther2.setImageResource(R.drawable.zhy_pay_fail_other_icon_1);
            ivPayOther3.setImageResource(R.drawable.zhy_pay_fail_other_icon_2);
            ivPayOther4.setImageResource(R.drawable.zhy_pay_fail_other_icon_1);
            ivPayOther5.setImageResource(R.drawable.zhy_pay_fail_other_icon_2);
        }
        AnimUtils.payPriceAnim(ivPayBg, 2000);
        AnimUtils.payPriceAnimIcon(ivPayIcon, 3000);
        AnimUtils.payPriceAnimOther(ivPayOther1, 2000, -0.7f);
        AnimUtils.payPriceAnimOther(ivPayOther2, 3000, 1f);
        AnimUtils.payPriceAnimOther(ivPayOther3, 3500, -2f);
        AnimUtils.payPriceAnimOther(ivPayOther4, 4000, -0.7f);
        AnimUtils.payPriceAnimOther(ivPayOther5, 5000, -0.7f);
    }

    @Override
    public void onClick(View v) {
        if (v == tvSubmit) {
            List<Class> listClass = new ArrayList<>();
            listClass.add(MainActivity.class);
            listClass.add(StoreServiceActivity.class);
            ((SaBaseApplication) getApplicationContext()).finshAllExceptActivity(listClass);
        }
    }
}
