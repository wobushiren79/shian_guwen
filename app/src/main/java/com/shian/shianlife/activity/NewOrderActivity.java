package com.shian.shianlife.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.InjectView;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.FuneralServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.LBSLocalView;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpAddConsultParams;
import com.shian.shianlife.provide.result.HrAddConsultResult;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;

public class NewOrderActivity extends BaseActivity {

    @InjectView(R.id.write_name)
    EditTextViewNormal writeName;
    @InjectView(R.id.write_phone)
    EditTextViewNormal writePhone;
    @InjectView(R.id.write_location)
    EditTextViewNormal writeLocation;
    @InjectView(R.id.write_remark)
    EditTextViewNormal writeRemark;
    @InjectView(R.id.write_spinner)
    SpinnerViewNormal writeSpinner;

    private HpAddConsultParams params = new HpAddConsultParams();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_neworder);
        setTitle("新建工单");
        initView();
    }

    private void initView() {
        writeSpinner.initSpinner(R.array.ywlx);
        writeSpinner.setSpinnerCallBack(new SpinnerViewNormal.SpinnerCallBack() {
            @Override
            public void itemSelected(int position, String name, SpinnerViewNormal viewNormal) {
                params.setBusinessType(position+1);
            }

            @Override
            public void check(SpinnerViewNormal view) {

            }
        });
    }

    @OnClick(R.id.tv_editorder)
    void editOrderClick(View v) {
        editOrder(false);
    }

    @OnClick(R.id.tv_qiatan)
    void qiatan() {
        editOrder(true);
    }

    private void editOrder(final boolean isF) {

        final String name = writeName.getData();
        final String phone = writePhone.getData();
        final String bz = writeRemark.getData();
//		final String address = lbsView.getDetailAddress();
        final String address = writeLocation.getData();
//		final HpAddConsultParams.TalkAddress ad = lbsView.getTalkAddress();

        if (name == null || name.equals("")) {
            ToastUtils.show(this, "客户姓名不能为空");
            return;
        }
        if (phone == null || phone.equals("")) {
            ToastUtils.show(this, "客户电话不能为空");
            return;
        }
        if (address == null || address.equals("")) {
            ToastUtils.show(this, "详细地址不能为空");
            return;
        }
        if (params.getBusinessType() == 0) {
            ToastUtils.show(this, "请选择业务类型");
            return;
        }
        if (!Utils.isPhoneNumber(phone)) {
            ToastUtils.show(this, "请输入正确的号码");
            return;
        }
        TipsDialog mDialog = new TipsDialog(this);
        mDialog.setTitle("点击确认后，信息将无法修改\n请仔细核实信息");
        mDialog.setTopButton("取消", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        mDialog.setBottomButton("确定", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//				HpAddConsultParams.TalkAddress ad = lbsView.getTalkAddress();
                params.setCustomerName(name);
                params.setCustomerMobile(phone);
//				params.setCustomerAddress(ad);
                params.setCustomerAddressNew(address);
                params.setDescription(bz);
                MHttpManagerFactory.getAccountManager().addConsult(NewOrderActivity.this, params,
                        new HttpResponseHandler<HrAddConsultResult>() {

                            @Override
                            public void onSuccess(HrAddConsultResult result) {
                                if (result != null) {
                                    ToastUtils.show(getBaseContext(), "创建成功");
                                    if (!isF) {
                                        Intent in = new Intent(NewOrderActivity.this, EditOrderActivity.class);
                                        in.putExtra("consultId", result.getConsultId());
                                        startActivity(in);
                                    }
                                    FuneralServiceActivity.C_bOrder_isRefresh = true;
                                    finish();
                                }
                            }

                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onError(String message) {

                            }
                        });

            }
        });
        mDialog.show();

    }
}
