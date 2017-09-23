package com.shian.shianlife.activity.userinfo;

import android.content.Context;
import android.os.Bundle;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.listview.IntegralListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserInfoIntegralDetailsActivity extends BaseActivity {
    @InjectView(R.id.listview)
    IntegralListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_integral_details);
        ButterKnife.inject(this);

        setTitle("积分明细");
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        listview.startFindData();
    }

}
