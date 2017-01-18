package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.cemeteryinfoview.AgentManInfoView;
import com.shian.shianlife.view.cemeteryinfoview.DeadManInfoView;

public class MoreInfoDetailsActivity extends BaseActivity {
    DeadManInfoView deadManInfoView;
    AgentManInfoView agentManInfoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info_details);

        setTitle("客户详情");
        initView();

    }

    private void initView() {
        deadManInfoView= (DeadManInfoView) findViewById(R.id.deadman_info);
        agentManInfoView= (AgentManInfoView) findViewById(R.id.agentman_info);

        deadManInfoView.setStateShow();
        agentManInfoView.setStateShow();
    }
}
