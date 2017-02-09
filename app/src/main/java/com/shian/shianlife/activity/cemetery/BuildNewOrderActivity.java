package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.CemeteryNameModel;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryBuildData;
import com.shian.shianlife.provide.result.HrGetCemeteryBuildData;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;

import java.util.ArrayList;
import java.util.List;


public class BuildNewOrderActivity extends BaseActivity implements CetemeryTextSelectLayoutView.onSelectedListener {

    CetemeryTextSelectLayoutView mCetemeryNameSelectLayout;
    CetemeryTextSelectLayoutView mTrafficeSelectLayout;

    MapSelectLayoutView selectLayoutView;

    TextView mTVTime;
    EditText mETName;
    EditText mETPhone;
    EditText mETPersonNum;
    Button mBTSubmit;

    List<CemeteryNameModel> ctemeryNameList = new ArrayList<>();
    List<String> trafficeWayList = new ArrayList<>();


    boolean rolesBuild = false;
    boolean rolesTalk = false;

    HpSaveCemeteryBuildData params = new HpSaveCemeteryBuildData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_order);

        initView();
        initData();
    }

    private void initView() {
        mCetemeryNameSelectLayout = (CetemeryTextSelectLayoutView) findViewById(R.id.select_cemeteryname);
        mTrafficeSelectLayout = (CetemeryTextSelectLayoutView) findViewById(R.id.select_traffic);
        selectLayoutView = (MapSelectLayoutView) findViewById(R.id.mapselect);

        mTVTime = (TextView) findViewById(R.id.tv_time);
        mETName = (EditText) findViewById(R.id.et_name);
        mETPhone = (EditText) findViewById(R.id.et_phone);
        mETPersonNum = (EditText) findViewById(R.id.et_num);
        mBTSubmit = (Button) findViewById(R.id.bt_submit);

        mCetemeryNameSelectLayout.setName("预约参观公墓：");
        mTrafficeSelectLayout.setName("交通方式：");

        mTVTime.setOnClickListener(onClickListener);
        mBTSubmit.setOnClickListener(onClickListener);
    }

    private void initData() {
        setTitle("新建预约单");
        trafficeWayList = Utils.stringsToList(SelectData.CEMETERY_TRAFFICEWAY);

        selectLayoutView.setData(0, new ArrayList<String>());
        mTrafficeSelectLayout.setData(trafficeWayList, 1, this);

        setRoles();
        getData();
    }

    private void getData() {
        MHttpManagerFactory.getAccountManager().getCemeteryBuildData(BuildNewOrderActivity.this, new HttpResponseHandler<HrGetCemeteryBuildData>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryBuildData result) {
                if (result != null) {
                    ctemeryNameList = result.getCemeteryLocationList();
                    List<String> list = new ArrayList<String>();
                    for (CemeteryNameModel model : ctemeryNameList) {
                        list.add(model.getCemeteryName());
                    }
                    mCetemeryNameSelectLayout.setData(list, 0, BuildNewOrderActivity.this);
                }

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void setRoles() {
        //赋予权限
        for (int i = 0; i < CemeteryFragment.LOGIN_ROLES_LIST.size(); i++) {
            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 0) {
                rolesBuild = true;
                rolesTalk = true;
            }
            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 2) {
                rolesBuild = true;
            }
            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 3) {
                rolesTalk = true;
            }
        }

        if (rolesTalk && rolesBuild) {
            mBTSubmit.setText("进入洽谈");
        } else if (!rolesTalk && rolesBuild) {
            mBTSubmit.setText("提交");
        }
        mBTSubmit.setVisibility(View.VISIBLE);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVTime) {
                timeSelect();
            } else if (view == mBTSubmit) {
                dataSubmit();
            }
        }
    };

    private void dataSubmit() {
        String dataName = mETName.getText().toString();
        String dataPhone = mETPhone.getText().toString();
        String dataTime = mTVTime.getText().toString();
        String dataLocation = mCetemeryNameSelectLayout.getSelectedData();
        String dataTraffic = mTrafficeSelectLayout.getSelectedData();
        String dataPersonNum = mETPersonNum.getText().toString();
        String dataUserLocation = selectLayoutView.getLocation();

        if (dataName.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "客户姓名不能为空");
            return;
        }
        if (!Utils.isPhoneNumber(dataPhone)) {
            ToastUtils.show(BuildNewOrderActivity.this, "联系电话格式错误");
            return;
        }
        if (dataTime.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "预约时间不能为空");
            return;
        }
        if (dataPersonNum.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "参观人数不能为空");
            return;
        }
        if (dataLocation.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "预约参观公墓不能为空");
            return;
        }
        if (dataTraffic.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "交通方式不能为空");
            return;
        }
        if (dataUserLocation.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "客户地址不能为空");
            return;
        }


        if (rolesBuild && rolesTalk) {
            params.setSubmitType(1);
        } else if (!rolesTalk && rolesBuild) {
            params.setSubmitType(0);
        }
        params.setCustomerName(dataName);
        params.setCustomerMobile(dataPhone);
        params.setPromiseTime(dataTime);
        params.setPlanCemeteryLocation(dataLocation);
        params.setTrafficWay(dataTraffic);
        params.setPersonNum(dataPersonNum);
        params.setCustomerLocation(dataUserLocation);


        Utils.LogVPrint("CustomerName" + dataName);
        Utils.LogVPrint("CustomerMobile" + dataPhone);
        Utils.LogVPrint("PromiseTime" + dataTime);
        Utils.LogVPrint("PlanCemeteryLocation" + dataLocation);
        Utils.LogVPrint("TrafficWay" + dataTraffic);
        Utils.LogVPrint("PersonNum" + dataPersonNum);
        Utils.LogVPrint("Customer" + dataUserLocation);
        Utils.LogVPrint("submitType" + params.getSubmitType());


        MHttpManagerFactory.getAccountManager().saveCemeteryBuildData(BuildNewOrderActivity.this, params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(BuildNewOrderActivity.this, "创建成功");
                CemeteryFragment.C_bOrder_isRefresh = true;
                finish();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void timeSelect() {
        Utils.timeSelect(BuildNewOrderActivity.this, mTVTime);
    }

    @Override
    public void onItemSelected(View view, int i, long l, int num) {
        switch (num) {
            case 0:
                break;
            case 1:
                break;
        }
    }


}
