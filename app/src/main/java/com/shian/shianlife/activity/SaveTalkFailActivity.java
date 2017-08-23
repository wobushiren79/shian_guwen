package com.shian.shianlife.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.order.FuneralServiceActivity;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpTalkFailParams;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.shian.shianlife.view.writeview.EditTextViewNormal;
import com.shian.shianlife.view.writeview.MapSelectViewNormal;
import com.shian.shianlife.view.writeview.SpinnerViewEdit;
import com.shian.shianlife.view.writeview.SpinnerViewNormal;
import com.shian.shianlife.view.writeview.TimeSelectViewNormal;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;

import okhttp3.Request;

public class SaveTalkFailActivity extends BaseActivity {
    Long ConsultId;
    TextView mSubmit;

    EditTextViewNormal mWriteName;
    EditTextViewNormal mWriteTalkPoint;
    SpinnerViewNormal mWriteState;
    SpinnerViewNormal mWritePlanProject;
    SpinnerViewNormal mWriteTalkResult;
    MapSelectViewNormal mWriteNowLocation;
    MapSelectViewNormal mWritePlanLocation;
    SpinnerViewEdit mWriteRelation;
    TimeSelectViewNormal mWriteTime;

    HpTalkFailParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_talk_fail);

        init();
        initView();
    }

    private void init() {
        setTitle("洽谈信息");
        ConsultId = getIntent().getLongExtra("SaveTalkFailActivity", -1);

        params = new HpTalkFailParams();
    }

    private void initView() {
        mSubmit = (TextView) findViewById(R.id.submit);

        mWriteName = (EditTextViewNormal) findViewById(R.id.write_name);
        mWriteTalkPoint = (EditTextViewNormal) findViewById(R.id.write_talkpoint);
        mWriteState = (SpinnerViewNormal) findViewById(R.id.write_state);
        mWritePlanProject = (SpinnerViewNormal) findViewById(R.id.write_planproject);
        mWriteTalkResult = (SpinnerViewNormal) findViewById(R.id.write_talkresult);
        mWriteNowLocation = (MapSelectViewNormal) findViewById(R.id.write_mapselect_now);
        mWritePlanLocation = (MapSelectViewNormal) findViewById(R.id.write_mapselect_plan);
        mWriteRelation = (SpinnerViewEdit) findViewById(R.id.write_relation);
        mWriteTime = (TimeSelectViewNormal) findViewById(R.id.write_timeselect);

        mSubmit.setOnClickListener(onClickListener);
        mWriteState.initSpinner(R.array.szxz);
        mWriteRelation.initSpinner(R.array.gx);
        mWritePlanProject.initSpinner(R.array.zsfx);
        mWriteTalkResult.initSpinner(R.array.qtjg);
        mWriteNowLocation.setNumView(0);
        mWritePlanLocation.setNumView(1);
        mWriteTalkResult.setSpinnerCallBack(new SpinnerViewNormal.SpinnerCallBack() {
            @Override
            public void itemSelected(int position, String name, SpinnerViewNormal viewNormal) {
                if (position == 0) {
                    params.setResult(true);
                    mWriteTime.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    params.setResult(false);
                    mWriteTime.setVisibility(View.GONE);
                }
            }

            @Override
            public void check(SpinnerViewNormal view) {

            }
        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mSubmit) {
                if (mWriteTalkPoint.getData().equals("")) {
                    Toast.makeText(SaveTalkFailActivity.this, "洽谈要点不能为空", Toast.LENGTH_LONG).show();
                } else {
                    if (mWriteTime.getData().equals("") && mWriteTalkResult.getSelectPosition() == 0) {
                        Toast.makeText(SaveTalkFailActivity.this, "请设置洽谈时间", Toast.LENGTH_LONG).show();
                    } else {
                        failTalk();
                    }
                }
            }

        }
    };


    /**
     * 提交网络请求
     */
    private void failTalk() {
        params.setHealth(mWriteState.getData());
        params.setRelation(mWriteRelation.getData());
        params.setConsultId(ConsultId + "");
        params.setDeadName(mWriteName.getData());
        params.setRemark(mWriteTalkPoint.getData());
        params.setResultTime(mWriteTime.getData());
        params.setLocation(mWriteNowLocation.getData());
        params.setProject(mWritePlanProject.getData());
        params.setPlanLocation(mWritePlanLocation.getData());
        Utils.LogVPrint("ConsultId:" + params.getConsultId());
        Utils.LogVPrint("Relation:" + params.getRelation());
        Utils.LogVPrint("Name:" + params.getDeadName());
        Utils.LogVPrint("Health:" + params.getHealth());
        Utils.LogVPrint("Project:" + params.getProject());
        Utils.LogVPrint("Remark:" + params.getRemark());
        Utils.LogVPrint("Result:" + params.isResult());
        Utils.LogVPrint("Time:" + params.getResultTime());
        Utils.LogVPrint("Location:" + params.getLocation());
        Utils.LogVPrint("PlanLocation:" + params.getPlanLocation());

        TipsDialog mDialog = new TipsDialog(SaveTalkFailActivity.this);
        mDialog.setTitle("请确认结束洽谈的客户信息已填写完善。");
        mDialog.setTopButton("继续填写", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mDialog.setBottomButton("结束洽谈",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MHttpManagerFactory.getFuneralManager().saveTalkFailData(SaveTalkFailActivity.this, params,
                                new HttpResponseHandler<Object>() {
                                    @Override
                                    public void onStart(Request request, int id) {

                                    }

                                    @Override
                                    public void onSuccess(Object result) {
                                        // TODO Auto-generated method stub
                                        if (!params.isResult()) {
                                            HpConsultIdParams params = new HpConsultIdParams();
                                            params.setConsultId(ConsultId);
                                            MHttpManagerFactory.getFuneralManager().talkFinish(
                                                    SaveTalkFailActivity.this, params,
                                                    new HttpResponseHandler<Object>() {



                                                        @Override
                                                        public void onStart(Request request, int id) {

                                                        }

                                                        @Override
                                                        public void onSuccess(Object result) {
                                                            Toast.makeText(SaveTalkFailActivity.this, "提交成功，结束洽谈", Toast.LENGTH_LONG).show();
                                                            FuneralServiceActivity.C_bOrder_isRefresh = true;
                                                            finish();
                                                        }

                                                        @Override
                                                        public void onError(String message) {

                                                        }
                                                    });
                                        } else {
                                            Toast.makeText(SaveTalkFailActivity.this, "提交成功，结束洽谈", Toast.LENGTH_LONG).show();
                                            FuneralServiceActivity.C_bOrder_isRefresh = true;
                                            finish();
                                        }
                                    }


                                    @Override
                                    public void onError(String message) {
                                        // TODO Auto-generated method stub
                                        Toast.makeText(SaveTalkFailActivity.this, "提交失败", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });
        mDialog.show();


    }


}
