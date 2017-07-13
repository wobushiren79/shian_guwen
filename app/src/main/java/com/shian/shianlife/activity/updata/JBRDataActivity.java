package com.shian.shianlife.activity.updata;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.FuneralServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveCustomerAgentmanParams;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewEdit;

import java.util.ArrayList;
import java.util.List;


public class JBRDataActivity extends BaseActivity {
    TextView mTVBack;
    TextView mTVNext;

    EditTextViewNormal mWriteAgentName;
    EditTextViewNormal mWriteAgentMoblie;
    EditTextViewNormal mWriteAgentCardId;
    EditTextViewNormal mWriteAgentEmail;
    EditTextViewNormal mWriteRemark;

    SpinnerViewEdit mWriteAgentRelation;

    MapSelectViewNormal mWriteMapSelectAgentLocation;
    MapSelectViewNormal mWriteMapSelectZSLocation;

    long consultId;
    long orderId;

    private HpSaveCustomerAgentmanParams params = new HpSaveCustomerAgentmanParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jbrdata);

        initView();
        initData();
    }

    private void initView() {
        mWriteAgentName = (EditTextViewNormal) findViewById(R.id.write_agentname);
        mWriteAgentMoblie = (EditTextViewNormal) findViewById(R.id.write_agentmoblie);
        mWriteAgentRelation = (SpinnerViewEdit) findViewById(R.id.write_agentrelation);
        mWriteAgentCardId = (EditTextViewNormal) findViewById(R.id.write_agentcardid);
        mWriteMapSelectAgentLocation = (MapSelectViewNormal) findViewById(R.id.write_mapselect_agentlocation);
        mWriteMapSelectZSLocation = (MapSelectViewNormal) findViewById(R.id.write_mapselect_zslocation);
        mWriteAgentEmail= (EditTextViewNormal) findViewById(R.id.write_agentemail);
        mWriteRemark= (EditTextViewNormal) findViewById(R.id.write_remark);

        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVNext = (TextView) findViewById(R.id.tv_next);

        mTVBack.setOnClickListener(onClickListener);
        mTVNext.setOnClickListener(onClickListener);

        mWriteAgentRelation.initSpinner(R.array.gx);
        mWriteMapSelectAgentLocation.setNumView(0);
        mWriteMapSelectZSLocation.setNumView(1);
    }

    int mapCheckNum = 0;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVBack) {
                //返回往生者信息
                toBack();
            } else if (view == mTVNext) {
                upData();
            }

        }
    };


    private void toBack() {
        Intent intent = new Intent(JBRDataActivity.this, WSZDataActivity.class);
        intent.putExtra("consultId", consultId);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
        finish();
    }

    private void upData() {
        //生成合同信息
        String name = mWriteAgentName.getData();
        String phone = mWriteAgentMoblie.getData();
        String location = mWriteMapSelectAgentLocation.getData();
        String zsLocation = mWriteMapSelectZSLocation.getData();
        String cardid = mWriteAgentCardId.getData();
        String email = mWriteAgentEmail.getData();
        String remark = mWriteRemark.getData();

        if (TextUtils.isEmpty(name)) {
            ToastUtils.show(JBRDataActivity.this, "经办人不能为空");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.show(JBRDataActivity.this, "经办人电话不能为空");
            return;
        }
        if (!Utils.isPhoneNumber(phone)) {
            ToastUtils.show(JBRDataActivity.this, "请输入正确的号码");
            return;
        }
        if (TextUtils.isEmpty(location)) {
            ToastUtils.show(JBRDataActivity.this, "经办人地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(zsLocation)) {
            ToastUtils.show(JBRDataActivity.this, "治丧地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(cardid)) {
            ToastUtils.show(JBRDataActivity.this, "经办人身份证号不能为空");
            return;
        }
        if (cardid.getBytes().length != 18) {
            ToastUtils.show(JBRDataActivity.this, "往生者身份证号码不足18位");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ToastUtils.show(JBRDataActivity.this, "经办人邮箱不能为空");
            return;
        }
        if (!Utils.isEmail(email)) {
            ToastUtils.show(JBRDataActivity.this, "经办人邮箱格式错误");
            return;
        }
        params.setRelation(mWriteAgentRelation.getData());
        params.setConsultId((JBRDataActivity.this).getIntent().getLongExtra("consultId", 0));
        params.setName(name);
        params.setLinkInfo(phone);
        params.setLocation(location);
        params.setCardId(cardid);
        params.setEmail(email);
        params.setRemark(remark);
        params.setZsLocation(zsLocation);
        params.setBirthday(TransitionDate.StrToDate("2016-12-13", "yyyy-MM-dd").getTime());

        Utils.LogVPrint("ConsultId:" + params.getConsultId());
        Utils.LogVPrint("Name:" + params.getName());
        Utils.LogVPrint("LinkInfo:" + params.getLinkInfo());
        Utils.LogVPrint("Location:" + params.getLocation());
        Utils.LogVPrint("zsLocation:" + params.getZsLocation());
        Utils.LogVPrint("CardId:" + params.getCardId());
        Utils.LogVPrint("Email:" + params.getEmail());
        Utils.LogVPrint("Remark:" + params.getRemark());
        Utils.LogVPrint("Relation:" + params.getRelation());

        MHttpManagerFactory.getAccountManager().saveCustomerAgentman(JBRDataActivity.this, params,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(JBRDataActivity.this, "保存成功");
                        Intent intent = new Intent(JBRDataActivity.this, ContractDataActivity.class);
                        intent.putExtra("consultId", consultId);
                        intent.putExtra("orderId", orderId);
                        startActivity(intent);
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

    public void initData() {
        setTitle("经办人信息");
        consultId = getIntent().getLongExtra("consultId", 0);
        orderId = getIntent().getLongExtra("orderId", 0);
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId((JBRDataActivity.this).getIntent().getLongExtra("consultId", 0));
        MHttpManagerFactory.getAccountManager().getCustomerAgentman(JBRDataActivity.this, params,
                new HttpResponseHandler<HrConsultAgentman>() {

                    @Override
                    public void onSuccess(HrConsultAgentman result) {
                        // TODO Auto-generated method stub
                        Utils.LogVPrint("Name:" + result.getConsultAgentman().getName());
                        Utils.LogVPrint("LinkInfo:" + result.getConsultAgentman().getLinkInfo());
                        Utils.LogVPrint("Location:" + result.getConsultAgentman().getLocation());
                        Utils.LogVPrint("CardId:" + result.getConsultAgentman().getCardId());
                        Utils.LogVPrint("Email:" + result.getConsultAgentman().getEmail());
                        Utils.LogVPrint("Remark:" + result.getConsultAgentman().getRemark());
                        Utils.LogVPrint("Relation:" + result.getConsultAgentman().getRelation());
                        Utils.LogVPrint("zsLocation" + result.getConsultAgentman().getZsLocation());


                        mWriteAgentName.setData(result.getConsultAgentman().getName());
                        mWriteAgentMoblie.setData(result.getConsultAgentman().getLinkInfo());
                        mWriteAgentRelation.setData(result.getConsultAgentman().getRelation());
                        mWriteAgentCardId.setData(result.getConsultAgentman().getCardId());
                        mWriteAgentEmail.setData(result.getConsultAgentman().getEmail());
                        mWriteRemark.setData(result.getConsultAgentman().getRemark());
                        mWriteMapSelectAgentLocation.setData(result.getConsultAgentman().getLocation());
                        mWriteMapSelectZSLocation.setData(result.getConsultAgentman().getZsLocation());

                        List<String> listLocationData = new ArrayList<String>();
                        if (result.getConsultAgentman().getLocation() != null) {
                            listLocationData.add(result.getConsultAgentman().getLocation());
                        }
                        if (result.getConsultAgentman().getZsLocation() != null) {
                            listLocationData.add(result.getConsultAgentman().getZsLocation());
                        }
                        if (result.getConsultAgentman().getDeadLocation() != null) {
                            listLocationData.add(result.getConsultAgentman().getDeadLocation());
                        }
                        if (result.getConsultAgentman().getDeadmanLocation() != null) {
                            listLocationData.add(result.getConsultAgentman().getDeadmanLocation());
                        }
                        mWriteMapSelectAgentLocation.initAutoTextView(listLocationData);
                        mWriteMapSelectZSLocation.initAutoTextView(listLocationData);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FuneralServiceActivity.C_bOrder_isRefresh = true;
    }

}
