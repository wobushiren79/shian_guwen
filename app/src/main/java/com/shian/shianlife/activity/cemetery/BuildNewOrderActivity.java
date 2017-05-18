package com.shian.shianlife.activity.cemetery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.CemeteryNameModel;
import com.shian.shianlife.provide.model.CemeteryStructureModel;
import com.shian.shianlife.provide.params.HpCemeteryStructureParams;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCemeteryBuildData;
import com.shian.shianlife.provide.result.HrGetCemeteryBuildData;
import com.shian.shianlife.provide.result.HrGetCemeteryStructure;
import com.shian.shianlife.thisenum.CemeteryLocationEnum;
import com.shian.shianlife.view.CetemeryTextSelectLayoutView;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.SelectData;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;

import java.util.ArrayList;
import java.util.List;

import static com.shian.shianlife.common.contanst.SelectDictCode.CONSULT_TRAFFICWAY;


public class BuildNewOrderActivity extends BaseActivity implements CetemeryTextSelectLayoutView.onSelectedListener {
    Button mBTSubmit;

    EditTextViewNormal mUserName;
    EditTextViewNormal mUserPhone;
    EditTextViewNormal mPersonNum;
    TimeSelectViewNormal mMeetTime;
    SpinnerViewNormal mTraffic;
    SpinnerViewNormal mCemeteryName;
    MapSelectViewNormal mUserLocation;

    List<CemeteryStructureModel> ctemeryNameList = new ArrayList<>();


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
        mBTSubmit = (Button) findViewById(R.id.bt_submit);

        mUserName = (EditTextViewNormal) findViewById(R.id.write_username);
        mUserPhone = (EditTextViewNormal) findViewById(R.id.write_userphone);
        mPersonNum = (EditTextViewNormal) findViewById(R.id.write_personnum);
        mMeetTime = (TimeSelectViewNormal) findViewById(R.id.write_meettime);
        mTraffic = (SpinnerViewNormal) findViewById(R.id.write_traffic);
        mCemeteryName = (SpinnerViewNormal) findViewById(R.id.write_cemeteryname);
        mUserLocation = (MapSelectViewNormal) findViewById(R.id.write_userlocation);

        mBTSubmit.setOnClickListener(onClickListener);
        mPersonNum.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void initData() {
        setTitle("新建预约单");
        mUserLocation.setNumView(0);
        mTraffic.initSpinner(CONSULT_TRAFFICWAY);

        setRoles();
        getData();
    }


    public void getData() {
        HpCemeteryStructureParams params = new HpCemeteryStructureParams();
        params.setItemId(-1);
        params.setItemType(0);
        params.setToken(AppContansts.userLoginInfo.getToken());
        MHttpManagerFactory.getAccountManager().getCemeteryStructure(BuildNewOrderActivity.this, params, new HttpResponseHandler<HrGetCemeteryStructure>() {


            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryStructure result) {
                if (result == null || result.getItems().size() == 0) {
                    ToastUtils.show(BuildNewOrderActivity.this, "没有找到数据");
                    return;
                }
                if (result != null) {
                    ctemeryNameList = result.getItems();
                    String[] list = new String[ctemeryNameList.size()];
                    for (int i = 0; i < list.length; i++) {
                        list[i] = ctemeryNameList.get(i).getName();
                    }
                    mCemeteryName.initSpinner(list);
                }
            }

            @Override
            public void onError(String message) {
            }
        });
    }

    private void setRoles() {
        //赋予权限
//        for (int i = 0; i < CemeteryFragment.LOGIN_ROLES_LIST.size(); i++) {
//            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 0) {
//                rolesBuild = true;
//                rolesTalk = true;
//            }
//            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 2) {
//                rolesBuild = true;
//            }
//            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 3) {
//                rolesTalk = true;
//            }
//        }
//
//        if (rolesTalk && rolesBuild) {
//            mBTSubmit.setText("进入洽谈");
//        } else if (!rolesTalk && rolesBuild) {
//            mBTSubmit.setText("提交");
//        }
        mBTSubmit.setText("提交");
        mBTSubmit.setVisibility(View.VISIBLE);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBTSubmit) {
                dataSubmit();
            }
        }
    };

    private void dataSubmit() {
        String dataName = mUserName.getData();
        String dataPhone = mUserPhone.getData();
        String dataTime = mMeetTime.getData();
        String dataLocation = mCemeteryName.getData();
        String dataTraffic = mTraffic.getData();
        String dataPersonNum = mPersonNum.getData();
        String dataUserLocation = mUserLocation.getData();
        long cemeteryId = ctemeryNameList.get(mCemeteryName.getSelectPosition()).getId();
        if (dataName.isEmpty()) {
            ToastUtils.show(BuildNewOrderActivity.this, "客户姓名不能为空");
            return;
        }
        if (dataPhone.equals("")) {
            ToastUtils.show(BuildNewOrderActivity.this, "电话不能为空");
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
        params.setPlanCemeteryId(cemeteryId);
        params.setToken(AppContansts.userLoginInfo.getToken());
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
