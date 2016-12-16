package com.shian.shianlife.activity.updata;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;

public class ContractDataActivity extends BaseActivity {

    TextView mTVNext;
    TextView mTVBack;
    TextView mTVComplete;

    TextView mTVContractId;

    long consultId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_data);
        consultId = getIntent().getLongExtra("consultId", 0);
        setTitle("合同信息");

        initView();
    }

    private void initView() {
        mTVNext = (TextView) findViewById(R.id.tv_next);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVComplete = (TextView) findViewById(R.id.tv_complete);
        mTVContractId = (TextView) findViewById(R.id.tv_contractId);

        mTVNext.setOnClickListener(onClickListener);
        mTVBack.setOnClickListener(onClickListener);
        mTVComplete.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVNext) {
                Intent intent = new Intent(ContractDataActivity.this, TQZBDataActivity.class);
                intent.putExtra("consultId", consultId);
                startActivity(intent);
            } else if (view == mTVBack) {
                Intent intent = new Intent(ContractDataActivity.this, JBRDataActivity.class);
                intent.putExtra("consultId", consultId);
                startActivity(intent);
                finish();
            } else if (view == mTVComplete) {

            }
        }
    };
}
