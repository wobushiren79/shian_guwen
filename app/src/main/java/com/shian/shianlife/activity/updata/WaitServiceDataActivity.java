package com.shian.shianlife.activity.updata;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveWaitServicePostData;
import com.shian.shianlife.provide.result.HrGetWaitServicePostData;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaitServiceDataActivity extends BaseActivity {
    TextView mTVNext;
    TextView mTVBack;

    long orderId;
    long consultId;

    TimeSelectViewNormal mWriteTimeSelect;
    MapSelectViewNormal mWriteMapSelect;
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

    private void initView() {
        mWriteTimeSelect = (TimeSelectViewNormal) findViewById(R.id.write_timeselect);
        mWriteMapSelect = (MapSelectViewNormal) findViewById(R.id.write_location);

        mWriteMapSelect.setNumView(0);

        mTVNext = (TextView) findViewById(R.id.tv_next);
        mTVBack = (TextView) findViewById(R.id.tv_back);

        mTVNext.setOnClickListener(onClickListener);
        mTVBack.setOnClickListener(onClickListener);
    }

    private void initData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getWaitServicePostData(WaitServiceDataActivity.this,
                params, new HttpResponseHandler<HrGetWaitServicePostData>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(HrGetWaitServicePostData result) {

                        Utils.LogVPrint("DeadLocation:" + result.getDeadLocation());
                        Utils.LogVPrint("DeadTime:" + result.getDeadTime());
                        Utils.LogVPrint("AgentmanLocation:" + result.getAgentmanLocation());
                        Utils.LogVPrint("DeadmanLocation:" + result.getDeadmanLocation());
                        Utils.LogVPrint("ZsLocation:" + result.getZsLocation());

                        if (result.getDeadLocation() != null) {
                            listLocation.add(result.getDeadLocation());
                        }
                        if (result.getAgentmanLocation() != null) {
                            listLocation.add(result.getAgentmanLocation());
                        }
                        if (result.getDeadmanLocation() != null) {
                            listLocation.add(result.getDeadmanLocation());
                        }
                        if (result.getZsLocation() != null) {
                            listLocation.add(result.getZsLocation());
                        }
                        mWriteMapSelect.initAutoTextView(listLocation);
                        mWriteTimeSelect.setData(TransitionDate.DateToStr(new Date(
                                        result.getDeadTime()),
                                "yyyy-MM-dd"));

                    }

                    @Override
                    public void onError(String message) {
                    }
                });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVBack) {
                finish();
            } else if (view == mTVNext) {
                setUpData();
            }
        }
    };

    private void setUpData() {

        String time = mWriteTimeSelect.getData();
        String location = mWriteMapSelect.getData();
        if (time.equals("")) {
            ToastUtils.show(this, "去世时间不能为空");
            return;
        }
        if (location.equals("")) {
            ToastUtils.show(this, "去世地点不能为空");
            return;
        }
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


}
