package com.shian.shianlife.activity.goods;

import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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

    protected boolean isSuccessPay;//是否成功支付

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_pay_call_back);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        setTitle("支付");
        isSuccessPay = getIntent().getBooleanExtra(IntentName.INTENT_ISTURE, false);
        if (isSuccessPay) {
            getDataSuccess();
        }
        tvSubmit.setOnClickListener(this);
        Utils.call(tvPhone, AppContansts.Shianlife_Phone);
    }

    private void initData() {
        final int payBgId;
        final int payIcon;
        final int payOther1;
        final int payOther2;
        final int payOther3;
        final int payOther4;
        final int payOther5;
        if (isSuccessPay) {
            tvTitle.setText("您已付款成功");
            payBgId = R.drawable.zhy_pay_success_bg;
            payIcon = R.drawable.zhy_pay_success_icon;
            payOther1 = R.drawable.zhy_pay_success_other_icon_2;
            payOther2 = R.drawable.zhy_pay_success_other_icon_1;
            payOther3 = R.drawable.zhy_pay_success_other_icon_2;
            payOther4 = R.drawable.zhy_pay_success_other_icon_1;
            payOther5 = R.drawable.zhy_pay_success_other_icon_2;
        } else {
            tvTitle.setText("付款失败，请重新支付");
            payBgId = R.drawable.zhy_pay_fail_bg;
            payIcon = R.drawable.zhy_pay_fail_icon;
            payOther1 = R.drawable.zhy_pay_fail_other_icon_2;
            payOther2 = R.drawable.zhy_pay_fail_other_icon_1;
            payOther3 = R.drawable.zhy_pay_fail_other_icon_2;
            payOther4 = R.drawable.zhy_pay_fail_other_icon_1;
            payOther5 = R.drawable.zhy_pay_fail_other_icon_2;
        }
        RequestListener<Integer, GlideDrawable> listener = new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                if (payBgId == model) {
                    AnimUtils.payPriceAnim(ivPayBg, 2000);
                } else if (payIcon == model) {
                    AnimUtils.payPriceAnimIcon(ivPayIcon, 3000);
                } else if (payOther1 == model) {
                    AnimUtils.payPriceAnimOther(ivPayOther1, 2000, -0.7f);
                } else if (payOther2 == model) {
                    AnimUtils.payPriceAnimOther(ivPayOther2, 3000, 1f);
                } else if (payOther3 == model) {
                    AnimUtils.payPriceAnimOther(ivPayOther3, 3500, -2f);
                } else if (payOther4 == model) {
                    AnimUtils.payPriceAnimOther(ivPayOther4, 4000, -0.7f);
                } else if (payOther5 == model) {
                    AnimUtils.payPriceAnimOther(ivPayOther5, 5000, -0.7f);
                }
                return false;
            }
        };
        Utils.loadPic(this, ivPayBg, payBgId, listener);
        Utils.loadPic(this, ivPayIcon, payIcon, listener);
        Utils.loadPic(this, ivPayOther1, payOther1, listener);
        Utils.loadPic(this, ivPayOther2, payOther2, listener);
        Utils.loadPic(this, ivPayOther3, payOther3, listener);
        Utils.loadPic(this, ivPayOther4, payOther4, listener);
        Utils.loadPic(this, ivPayOther5, payOther5, listener);
    }


    protected void getDataSuccess() {
        initData();
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
