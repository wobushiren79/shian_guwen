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

    UserInfoPointLayout mUserInfoPointLayoutIntegral;
    UserInfoPointLayout mUserInfoPointLayoutMoney;
    UserInfoPointLayout mUserInfoPointLayoutOrder;

    private HrUserInfo mHrUserInfo;

    public UserInfoLayout(Context context) {
        this(context, null);
    }

    public UserInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_userinfo_layout, this);
        initView();
        getUserInfo();
    }

    private void initView() {
        mTVName = (TextView) view.findViewById(R.id.tv_name);
        mTVStatus = (TextView) view.findViewById(R.id.tv_status);
        mTVScore = (TextView) view.findViewById(R.id.tv_score);
        mTVSignName = (TextView) view.findViewById(R.id.tv_sign_name);
        mIVUserPic = (ImageView) view.findViewById(R.id.iv_user_pic);
        mIVSignIcon = (ImageView) view.findViewById(R.id.iv_sign_icon);
        mLLSign = (LinearLayout) view.findViewById(R.id.ll_sign);

        mUserInfoPointLayoutIntegral = (UserInfoPointLayout) view.findViewById(R.id.pointlayout_integral);
        mUserInfoPointLayoutMoney = (UserInfoPointLayout) view.findViewById(R.id.pointlayout_money);
        mUserInfoPointLayoutOrder = (UserInfoPointLayout) view.findViewById(R.id.pointlayout_order);

        mUserInfoPointLayoutIntegral.initLayout(R.drawable.zhy_userinfo_integral, "积分", "0");
        mUserInfoPointLayoutMoney.initLayout(R.drawable.zhy_userinfo_money, "钱包", "0");
        mUserInfoPointLayoutOrder.initLayout(R.drawable.zhy_userinfo_order, "服务单", "0");

        mUserInfoPointLayoutIntegral.setOnClickListener(onClickListener);
        mUserInfoPointLayoutMoney.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mLLSign) {
                sign();
            } else if (v == mUserInfoPointLayoutIntegral) {
                integralActivity();
            } else if (v == mUserInfoPointLayoutMoney) {
                moneyActivity();
            }
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
     * 获取用户信息
     */
    private void getUserInfo() {
        MHttpManagerFactory.getAccountManager().getUserInfo(getContext(), new HttpResponseHandler<HrUserInfo>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(final HrUserInfo result) {
                mHrUserInfo = result;
                PicassoUD.loadImage(getContext(), AppContansts.OSSURL + result.getHeadImg(), mIVUserPic);
                mTVName.setText(result.getName());
                mTVStatus.setText("");
                for (int i = 0; i < result.getRoles().size(); i++) {
                    mTVStatus.append(result.getRoles().get(i).getName() + " \n");
                }
                mTVScore.setText(result.getAvgSatis() + "");
                mUserInfoPointLayoutOrder.setPoint(result.getServiceSuccessSum() + "");
                mLLSign.setOnClickListener(onClickListener);
            }

            @Override
            public void onError(String message) {

            }
        });
    }


}
