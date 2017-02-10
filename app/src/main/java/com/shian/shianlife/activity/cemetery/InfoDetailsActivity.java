package com.shian.shianlife.activity.cemetery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.cemeteryinfoview.CemeteryInfoView;

import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_INFO_ID;
import static com.shian.shianlife.common.view.order.CemeteryQTView.TALK_INFO_ORDER_ID;

public class InfoDetailsActivity extends BaseActivity {

    Button mBTDetailes;
    CemeteryInfoView cemeteryInfoView;
    long orderId;
    long beSpeakId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        setTitle("订单详情");
        initData();
        initView();

    }

    private void initData() {
        beSpeakId=getIntent().getLongExtra(TALK_INFO_ID,-1);
        orderId=getIntent().getLongExtra(TALK_INFO_ORDER_ID,-1);
    }

    private void initView() {
        mBTDetailes = (Button) findViewById(R.id.bt_detailes);
        cemeteryInfoView= (CemeteryInfoView) findViewById(R.id.cemetery_info);

        mBTDetailes.setOnClickListener(onClickListener);

        cemeteryInfoView.setStateShow();

        cemeteryInfoView.setBeSpeakId(beSpeakId);
        cemeteryInfoView.setOrderId(orderId);
        cemeteryInfoView.getDataStart();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTDetailes) {
                Intent intent = new Intent(InfoDetailsActivity.this, MoreInfoDetailsActivity.class);
                intent.putExtra(TALK_INFO_ID,beSpeakId);
                intent.putExtra(TALK_INFO_ORDER_ID,orderId);
                startActivity(intent);
            }
        }
    };


}
