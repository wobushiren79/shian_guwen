package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.cemeteryinfoview.AgentManInfoView;
import com.shian.shianlife.view.cemeteryinfoview.DeadManInfoView;

import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_INFO_ID;
import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_INFO_ORDER_ID;

public class MoreInfoDetailsActivity extends BaseActivity {
    DeadManInfoView deadManInfoView;
    AgentManInfoView agentManInfoView;

    long beSpeakId;
    long orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_details);

        setTitle("客户详情");
        initData();
        initView();

    }

    private void initData() {
        beSpeakId=getIntent().getLongExtra(TALK_INFO_ID,-1);
        orderId=getIntent().getLongExtra(TALK_INFO_ORDER_ID,-1);
    }

    private void initView() {
        deadManInfoView= (DeadManInfoView) findViewById(R.id.deadman_info);
        agentManInfoView= (AgentManInfoView) findViewById(R.id.agentman_info);

        deadManInfoView.setStateShow();
        agentManInfoView.setStateShow();

        deadManInfoView.setBeSpeakId(beSpeakId);
        deadManInfoView.setOrderId(orderId);
        deadManInfoView.getDataStart();
        agentManInfoView.setBeSpeakId(beSpeakId);
        agentManInfoView.setOrderId(orderId);
        agentManInfoView.getDataStart();
    }
}
