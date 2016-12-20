package com.shian.shianlife.activity.updata;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.MapLocation;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveWaitServicePostData;
import com.shian.shianlife.provide.result.HrGetWaitServicePostData;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaitServiceDataActivity extends BaseActivity {
    TextView mTVNext;
    TextView mTVBack;
    TextView mTVTime;

    TextView mTVMapText;
    ImageView mIVMapCheck;
    ImageView mIVMapSelect;

    long orderId;
    long consultId;

    List<String> listLocation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_service_data);

        setTitle("服务信息");
        orderId = getIntent().getLongExtra("orderId", 0);
        consultId = getIntent().getLongExtra("consultId", 0);
        initView();
        initData();
    }

    private void initData() {
        Log.v("this","initData");
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getWaitServicePostData(WaitServiceDataActivity.this,
                params, new HttpResponseHandler<HrGetWaitServicePostData>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(HrGetWaitServicePostData result) {
                        Log.v("this", "onSuccess");

                        Log.v("this", "DeadLocation:" + result.getDeadLocation());
                        Log.v("this", "DeadTime:" + result.getDeadTime());
                        Log.v("this", "AgentmanLocation:" + result.getAgentmanLocation());
                        Log.v("this", "DeadmanLocation:" + result.getDeadmanLocation());
                        Log.v("this", "ZsLocation:" + result.getZsLocation());

                        if (result.getDeadLocation()!=null) {
                            listLocation.add(result.getDeadLocation());
                        }
                        if (result.getAgentmanLocation()!=null) {
                            listLocation.add(result.getAgentmanLocation());
                        }
                        if (result.getDeadmanLocation()!=null) {
                            listLocation.add(result.getDeadmanLocation());
                        }
                        if (result.getZsLocation()!=null) {
                            listLocation.add(result.getZsLocation());
                        }


                        mTVTime.setText(TransitionDate.DateToStr(new Date(
                                        result.getDeadTime()),
                                "yyyy-MM-dd"));
                        mIVMapSelect.setOnClickListener(onClickListener);
                    }

                    @Override
                    public void onError(String message) {
                        Log.v("this", "onError");
                    }
                });
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
        mIVMapCheck.setOnClickListener(onClickListener);
        mTVNext.setOnClickListener(onClickListener);
        mTVBack.setOnClickListener(onClickListener);
        mIVMapSelect.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                setTimeDialog(mTVTime);
            } else if (view == mIVMapCheck) {
                setMapLocation();
            } else if (view == mIVMapSelect) {
                setSelect();
            } else if (view == mTVBack) {
                finish();
            } else if (view == mTVNext) {
                setUpData();
            }
        }
    };

    private void setUpData() {
        String time = mTVTime.getText().toString();
        String location = mTVMapText.getText().toString();

        HpSaveWaitServicePostData params = new HpSaveWaitServicePostData();
        params.setConsultId(consultId);
        params.setDeadLocation(location);
        params.setDeadTime(TransitionDate.StrToDate(time, "yyyy-MM-dd")
                .getTime());
        MHttpManagerFactory.getAccountManager().saveWaitServicePostData(WaitServiceDataActivity.this, params,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        Toast.makeText(WaitServiceDataActivity.this, "开始执行成功", Toast.LENGTH_LONG).show();
                        OrderFragment.C_bOrder_isRefresh = true;
                        finish();
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    private void setSelect() {
        ListView textListView = new ListView(WaitServiceDataActivity.this);
        textListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return listLocation.size();
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                TextView textView = new TextView(WaitServiceDataActivity.this);
                textView.setText(listLocation.get(i));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTVMapText.setText(listLocation.get(i));
                    }
                });
                return textView;
            }
        });
        AlertDialog dialog = new AlertDialog.Builder(WaitServiceDataActivity.this)
                .setTitle("请选择地址")
                .setView(textListView)
                .create();
        dialog.show();
    }

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
