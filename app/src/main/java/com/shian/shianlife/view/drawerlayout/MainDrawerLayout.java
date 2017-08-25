package com.shian.shianlife.view.drawerlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.CustomerHelpActivity;
import com.shian.shianlife.activity.IdeaFeedbackActivity;
import com.shian.shianlife.activity.MyCollectionActivity;
import com.shian.shianlife.activity.SettingsActivity;
import com.shian.shianlife.activity.UserInfoActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.thisenum.ShowModeEnum;


/**
 * Created by zm.
 */

public class MainDrawerLayout extends LinearLayout implements View.OnClickListener {
    View mLayoutView;
    LinearLayout llSign;
    ImageView ivIcon;
    TextView tvName;
    TextView tvPoint;
    TextView tvPhone;
    TextView tvVersion;
    LinearLayout llHelp;
    LinearLayout llCollection;
    LinearLayout llIdea;
    LinearLayout llPlatform;
    LinearLayout llVersion;
    LinearLayout llSetting;
    LinearLayout llEdit;
    LinearLayout llUserInfo;


    public MainDrawerLayout(Context context) {
        this(context, null);
    }

    public MainDrawerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLayoutView = View.inflate(getContext(), R.layout.layout_main_drawer, this);
        initView();
    }

    private void initView() {
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvPoint = (TextView) findViewById(R.id.tv_point);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        llSign = (LinearLayout) findViewById(R.id.ll_sign);
        llHelp = (LinearLayout) findViewById(R.id.ll_help);
        llCollection = (LinearLayout) findViewById(R.id.ll_collection);
        llIdea = (LinearLayout) findViewById(R.id.ll_idea);
        llPlatform = (LinearLayout) findViewById(R.id.ll_platform);
        llVersion = (LinearLayout) findViewById(R.id.ll_version);
        llSetting = (LinearLayout) findViewById(R.id.ll_setting);
        llEdit = (LinearLayout) findViewById(R.id.ll_edit);
        llUserInfo = (LinearLayout) findViewById(R.id.ll_userinfo);

        llSign.setOnClickListener(this);
        llHelp.setOnClickListener(this);
        llCollection.setOnClickListener(this);
        llIdea.setOnClickListener(this);
        llVersion.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        llEdit.setOnClickListener(this);
        llUserInfo.setOnClickListener(this);

        tvVersion.setText(getContext().getString(R.string.zhy_user_center_version) + Utils.getVersion(getContext()));
    }


    @Override
    public void onClick(View view) {
        if (view == llSign) {

        } else if (view == llHelp) {
            help();
        } else if (view == llCollection) {
            collection();
        } else if (view == llIdea) {
            idea();
        } else if (view == llVersion) {
            //检测更新
            Utils.checkUpData(getContext(), true);
        } else if (view == llSetting) {
            setting();
        } else if (view == llEdit) {
            edit();
        } else if (view == llUserInfo) {
            userInfo();
        }
    }

    /**
     * 我的资料
     */
    private void userInfo() {
        if (AppContansts.userInfoData != null) {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            intent.putExtra(IntentName.INTENT_TYPE, ShowModeEnum.show.getCode());
            getContext().startActivity(intent);
        }
    }

    /**
     * 意见反馈
     */
    private void idea() {
        Intent intent = new Intent(getContext(), IdeaFeedbackActivity.class);
        getContext().startActivity(intent);
    }

    /**
     * 帮助
     */
    private void help() {
        Intent intent = new Intent(getContext(), CustomerHelpActivity.class);
        getContext().startActivity(intent);
    }

    /**
     * 收藏
     */
    private void collection() {
        Intent intent = new Intent(getContext(), MyCollectionActivity.class);
        getContext().startActivity(intent);
    }

    /**
     * 设置
     */
    private void setting() {
        if (AppContansts.userInfoData != null) {
            Intent in = new Intent(getContext(), SettingsActivity.class);
            Activity activity = (Activity) getContext();
            activity.startActivityForResult(in, 101);
        }
    }

    /**
     * 編輯個人資料
     */
    private void edit() {
        if (AppContansts.userInfoData != null) {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            intent.putExtra(IntentName.INTENT_TYPE, ShowModeEnum.edit.getCode());
            getContext().startActivity(intent);
        }
    }


    /**
     * 改变头像
     *
     * @param imgUrl
     */
    public void changeHeadImg(String imgUrl) {
        PicassoUD.loadImage(getContext(), imgUrl, ivIcon);
    }

    /**
     * 改变名字
     *
     * @param name
     */
    public void changeName(String name) {
        tvName.setText(name);
    }

    /**
     * 改变电话
     *
     * @param phone
     */
    public void changePhone(String phone) {
        tvPhone.setText(phone);
    }

    /**
     * 改变分数
     *
     * @param point
     */
    public void changePoint(String point) {
        tvPoint.setText(point);
    }
}
