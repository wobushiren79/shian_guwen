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
        inType = getIntent().getIntExtra(CemeteryQTView.BUY_INFO, -1);

        initView();
        initData();
    }

    private void initView() {
        mLLContent = (LinearLayout) findViewById(R.id.ll_content);

        mBTBack = (Button) findViewById(R.id.bt_cemeteryback);
        mBTNext = (Button) findViewById(R.id.bt_cemeterynext);

        mBTNext.setOnClickListener(onClickListener);
        mBTBack.setOnClickListener(onClickListener);
    }

    private void initData() {
        switch (inType) {
            case 0:
                setTitle("创建购墓订单");
                setButton();
                mCemeteryInfoView = new CemeteryInfoView(BuyCemeteryInfoActivity.this);
                mLLContent.addView(mCemeteryInfoView);
                break;
            case 1:
                setTitle("使用者信息");
                setButton();
                mDeadManInfoView = new DeadManInfoView(BuyCemeteryInfoActivity.this);
                mLLContent.addView(mDeadManInfoView);
                break;
            case 2:
                setTitle("经办人信息");
                setButton();
                mAgentManInfoView = new AgentManInfoView(BuyCemeteryInfoActivity.this);
                mLLContent.addView(mAgentManInfoView);
                break;
        }
    }

    private void setButton() {
        switch (inType) {
            case 0:
                mBTBack.setVisibility(View.GONE);
                mBTNext.setVisibility(View.VISIBLE);
                mBTNext.setText("下一步");
                break;
            case 1:
                mBTBack.setVisibility(View.VISIBLE);
                mBTNext.setVisibility(View.VISIBLE);
                mBTBack.setText("上一步");
                mBTNext.setText("下一步");
                break;
            case 2:
                mBTBack.setVisibility(View.VISIBLE);
                mBTNext.setVisibility(View.VISIBLE);
                mBTBack.setText("上一步");
                mBTNext.setText("完成");
                break;
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTBack) {
                mLLContent.removeAllViews();
                switch (inType) {
                    case 0:
                        break;
                    case 1:
                        inType = 0;
                        initData();
                        break;
                    case 2:
                        inType = 1;
                        initData();
                        break;
                }
            } else if (view == mBTNext) {
                mLLContent.removeAllViews();
                switch (inType) {
                    case 0:
                        inType = 1;
                        initData();
                        break;
                    case 1:
                        inType = 2;
                        initData();
                        break;
                    case 2:
                        finish();
                        break;
                }
            }
        }
    };
}
