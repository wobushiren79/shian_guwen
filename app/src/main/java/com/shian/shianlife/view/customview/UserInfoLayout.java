package com.shian.shianlife.view.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.UserInfoIntegralActivity;
import com.shian.shianlife.activity.UserInfoMoneyActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.result.HrUserInfo;

/**
 * Created by Administrator
 */

public class UserInfoLayout extends LinearLayout {
    View view;
    TextView mTVName;
    TextView mTVStatus;
    TextView mTVScore;
    TextView mTVSignName;

    ImageView mIVUserPic;
    ImageView mIVSignIcon;

    LinearLayout mLLSign;

//    UserInfoPointLayout mUserInfoPointLayoutIntegral;
//    UserInfoPointLayout mUserInfoPointLayoutMoney;
//    UserInfoPointLayout mUserInfoPointLayoutOrder;

    TextView tvMoney;
    TextView tvIntegral;
    TextView tvOrderNum;

    LinearLayout llMoney;
    LinearLayout llIntegral;
    LinearLayout llOrderNum;


    public UserInfoLayout(Context context) {
        this(context, null);
    }

    public UserInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_userinfo_layout, this);
        initView();
    }

    private void initView() {
        mTVName = (TextView) view.findViewById(R.id.tv_name);
        mTVStatus = (TextView) view.findViewById(R.id.tv_status);
        mTVScore = (TextView) view.findViewById(R.id.tv_score);
        mTVSignName = (TextView) view.findViewById(R.id.tv_sign_name);
        mIVUserPic = (ImageView) view.findViewById(R.id.iv_user_pic);
        mIVSignIcon = (ImageView) view.findViewById(R.id.iv_sign_icon);
        mLLSign = (LinearLayout) view.findViewById(R.id.ll_sign);

        tvMoney = (TextView) view.findViewById(R.id.tv_money);
        tvIntegral = (TextView) view.findViewById(R.id.tv_integral);
        tvOrderNum = (TextView) view.findViewById(R.id.tv_order_num);

        llMoney = (LinearLayout) view.findViewById(R.id.ll_money);
        llIntegral = (LinearLayout) view.findViewById(R.id.ll_integral);
        llOrderNum = (LinearLayout) view.findViewById(R.id.ll_order_num);
//        mUserInfoPointLayoutIntegral = (UserInfoPointLayout) view.findViewById(R.id.pointlayout_integral);
//        mUserInfoPointLayoutMoney = (UserInfoPointLayout) view.findViewById(R.id.pointlayout_money);
//        mUserInfoPointLayoutOrder = (UserInfoPointLayout) view.findViewById(R.id.pointlayout_order);
//
//        mUserInfoPointLayoutIntegral.initLayout(R.drawable.zhy_userinfo_integral, "积分", "0");
//        mUserInfoPointLayoutMoney.initLayout(R.drawable.zhy_userinfo_money, "钱包", "0");
//        mUserInfoPointLayoutOrder.initLayout(R.drawable.zhy_userinfo_order, "服务单", "0");
//
//        mUserInfoPointLayoutIntegral.setOnClickListener(onClickListener);
//        mUserInfoPointLayoutMoney.setOnClickListener(onClickListener);
        mLLSign.setOnClickListener(onClickListener);
        llMoney.setOnClickListener(onClickListener);
        llIntegral.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLLSign) {
                sign();
            } else if (v == llMoney) {
                moneyActivity();
            } else if (v == llIntegral) {
                integralActivity();
            }
//            else if (v == mUserInfoPointLayoutIntegral) {
//                integralActivity();
//            } else if (v == mUserInfoPointLayoutMoney) {
//                moneyActivity();
//            }
        }
    };

    /**
     * 进入积分界面
     */
    private void integralActivity() {
        Intent intent = new Intent(getContext(), UserInfoIntegralActivity.class);
        getContext().startActivity(intent);
    }

    /**
     * 进入钱包界面
     */
    private void moneyActivity() {
        Intent intent = new Intent(getContext(), UserInfoMoneyActivity.class);
        getContext().startActivity(intent);
    }

    /**
     * 签名
     */
    private void sign() {
        mIVSignIcon.setImageResource(R.drawable.zhy_main_sign_check);
        mTVSignName.setTextColor(getResources().getColor(R.color.zhy_text_color_5));
    }

    /**
     * 更改名字
     *
     * @param name
     */
    public void changeName(String name) {
        mTVName.setText(name);
    }

    /**
     * 改变分数
     *
     * @param point
     */
    public void changePoint(String point) {
        mTVStatus.setText("评分：" + point);
    }


    /**
     * 改变订单数量
     *
     * @param orderNum
     */
    public void changeOrderNum(String orderNum) {
        tvOrderNum.setText(orderNum);
    }
}
