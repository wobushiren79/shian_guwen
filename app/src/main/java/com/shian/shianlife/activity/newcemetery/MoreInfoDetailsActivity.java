package com.shian.shianlife.activity.newcemetery;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.view.cemeteryinfoview.infolayout.CemeteryAgentManInfo;
import com.shian.shianlife.view.cemeteryinfoview.infolayout.CemeteryDeadManInfo;


public class MoreInfoDetailsActivity extends BaseActivity {
    CemeteryDeadManInfo deadManInfoView;
    CemeteryAgentManInfo agentManInfoView;

    RelativeLayout mRLDeadMan;
    RelativeLayout mRLAgentMan;

    long beSpeakId;
    long orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_details_2);

        setTitle("客户详情");
        initData();
        initView();

    }

    private void initData() {
        beSpeakId = getIntent().getLongExtra(IntentName.INTENT_BESPEAKID, -1);
        orderId = getIntent().getLongExtra(IntentName.INTENT_ORDERID, -1);
    }

    private void initView() {
        mRLDeadMan = (RelativeLayout) findViewById(R.id.rl_deadman);
        mRLAgentMan = (RelativeLayout) findViewById(R.id.rl_agentman);

        deadManInfoView = new CemeteryDeadManInfo(MoreInfoDetailsActivity.this, orderId, beSpeakId);
        agentManInfoView = new CemeteryAgentManInfo(MoreInfoDetailsActivity.this, orderId, beSpeakId);
        final TextView mask1 = new TextView(this);
        final TextView mask2 = new TextView(this);
        deadManInfoView.setThisCallBack(new CemeteryDeadManInfo.CallBack() {
            @Override
            public void initDataSuccess() {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(deadManInfoView.getWidth(), deadManInfoView.getHeight());
                mask1.setLayoutParams(layoutParams);

            }
        });
        agentManInfoView.setThisCallBack(new CemeteryAgentManInfo.CallBack() {
            @Override
            public void initDataSuccess() {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(agentManInfoView.getWidth(), agentManInfoView.getHeight());
                mask2.setLayoutParams(layoutParams);
            }
        });
        mask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRLAgentMan.addView(agentManInfoView);
        mRLDeadMan.addView(deadManInfoView);
        mRLAgentMan.addView(mask2);
        mRLDeadMan.addView(mask1);
        deadManInfoView.setShowMode();
        agentManInfoView.setShowMode();
    }


}
