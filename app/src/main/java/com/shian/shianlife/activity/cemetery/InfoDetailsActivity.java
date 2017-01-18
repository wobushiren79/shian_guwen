package com.shian.shianlife.activity.cemetery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.view.cemeteryinfoview.CemeteryInfoView;

public class InfoDetailsActivity extends BaseActivity {

    Button mBTDetailes;
    CemeteryInfoView cemeteryInfoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        setTitle("订单详情");
        initView();
    }

    private void initView() {
        mBTDetailes = (Button) findViewById(R.id.bt_detailes);
        cemeteryInfoView= (CemeteryInfoView) findViewById(R.id.cemetery_info);

        mBTDetailes.setOnClickListener(onClickListener);

        cemeteryInfoView.setStateShow();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTDetailes) {
                Intent intent = new Intent(InfoDetailsActivity.this, MoreInfoDetailsActivity.class);
                startActivity(intent);
            }
        }
    };


}
