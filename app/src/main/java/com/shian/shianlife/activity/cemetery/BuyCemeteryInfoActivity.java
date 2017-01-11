package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.view.cemeteryinfoview.AgentManInfoView;
import com.shian.shianlife.view.cemeteryinfoview.CemeteryInfoView;
import com.shian.shianlife.view.cemeteryinfoview.DeadManInfoView;

public class BuyCemeteryInfoActivity extends BaseActivity {
    int inType = -1;

    LinearLayout mLLContent;
    CemeteryInfoView mCemeteryInfoView;
    DeadManInfoView mDeadManInfoView;
    AgentManInfoView mAgentManInfoView;

    Button mBTBack;
    Button mBTNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_cemetery_info);
        initView();
        initData();
    }

    private void initView() {
        mLLContent = (LinearLayout) findViewById(R.id.ll_content);

        mBTBack = (Button) findViewById(R.id.bt_cemeteryback);
        mBTNext = (Button) findViewById(R.id.bt_cemeterynext);
    }

    private void initData() {
        inType = getIntent().getIntExtra(CemeteryQTView.BUY_INFO, -1);

        switch (inType) {
            case 0:
                setTitle("创建购墓订单");
                mBTBack.setVisibility(View.GONE);
                mBTNext.setText("下一步");
                mCemeteryInfoView = new CemeteryInfoView(BuyCemeteryInfoActivity.this);
                mLLContent.addView(mCemeteryInfoView);

                break;
            case 1:
                setTitle("使用者信息");
                mDeadManInfoView = new DeadManInfoView(BuyCemeteryInfoActivity.this);
                mLLContent.addView(mDeadManInfoView);
                break;
            case 2:
                setTitle("经办人信息");
                mAgentManInfoView = new AgentManInfoView(BuyCemeteryInfoActivity.this);
                mLLContent.addView(mAgentManInfoView);
                break;
        }
    }
}
