package com.shian.shianlife.activity.updata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MapLocation;
import com.shian.shianlife.base.BaseActivity;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

public class WaitServiceDataActivity extends BaseActivity {

    TextView mTVNext;
    TextView mTVBack;
    TextView mTVTime;

    TextView mTVMapText;
    ImageView mIVMapCheck;
    ImageView mIVMapSelect;

    long orderId;
    long consultId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_service_data);

        setTitle("服务信息");
        orderId = getIntent().getLongExtra("orderId", 0);
        consultId = getIntent().getLongExtra("consultId", 0);
        initView();
    }

    private void setTimeDialog(final TextView v) {
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(WaitServiceDataActivity.this);
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {

            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                v.setText(selectedDate);
            }
        });
    }

    private void initView() {
        mTVNext = (TextView) findViewById(R.id.tv_next);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVTime = (TextView) findViewById(R.id.et_zs_0);

        mTVMapText = (TextView) findViewById(R.id.tv_map_text);
        mIVMapCheck = (ImageView) findViewById(R.id.iv_map);
        mIVMapSelect = (ImageView) findViewById(R.id.iv_data);

        mTVTime.setOnClickListener(onClickListener);
        mIVMapSelect.setOnClickListener(onClickListener);
        mIVMapCheck.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                setTimeDialog(mTVTime);
            } else if (view == mIVMapCheck) {
                setMapLocation();
            } else if (view == mIVMapSelect) {

            }
        }
    };

    private void setMapLocation() {
        //点击地图定位
        Intent intent = new Intent(WaitServiceDataActivity.this, MapLocation.class);
        startActivityForResult(intent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1111:
                String location = data.getStringExtra("location");
//                    params.setCurAddress(location);
                mTVMapText.setText(location);

                break;
            default:
                break;
        }
    }
}
