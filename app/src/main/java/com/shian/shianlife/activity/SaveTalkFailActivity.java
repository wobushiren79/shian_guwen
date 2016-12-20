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
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpTalkFailParams;
import com.shian.shianlife.view.MapSelectLayoutView;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;

public class SaveTalkFailActivity extends BaseActivity {
    Long ConsultId;

    TextView mTalkTime;
    TextView mETTime;
    Button mSubmit;

    Spinner mSPRelation;
    Spinner mSPState;
    Spinner mSPPlanProject;
    Spinner mSPQTJG;

    EditText mETRelation;
    EditText mETName;
    EditText mRemark;


    LinearLayout mTimeLL;


    HpTalkFailParams params;


    MapSelectLayoutView mSelectLayoutView1;
    MapSelectLayoutView mSelectLayoutView2;

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
        Log.v("this", "ConsultId:" + ConsultId);

        params = new HpTalkFailParams();
    }

    private void initView() {
        mTalkTime = (TextView) findViewById(R.id.et_talk_time);
        mSubmit = (Button) findViewById(R.id.submit);
        mSPRelation = (Spinner) findViewById(R.id.sp_relation);
        mETRelation = (EditText) findViewById(R.id.et_relation);
        mETName = (EditText) findViewById(R.id.et_name);
        mSPState = (Spinner) findViewById(R.id.sp_state);
        mSPPlanProject = (Spinner) findViewById(R.id.sp_planproject);
        mRemark = (EditText) findViewById(R.id.et_remark);
        mSPQTJG = (Spinner) findViewById(R.id.sp_qtjg);
        mTimeLL = (LinearLayout) findViewById(R.id.timell);
        mETTime = (TextView) findViewById(R.id.et_talk_time);


        mSelectLayoutView1 = (MapSelectLayoutView) findViewById(R.id.mapselect1);
        mSelectLayoutView2 = (MapSelectLayoutView) findViewById(R.id.mapselect2);

        mSelectLayoutView1.setData(1,new ArrayList<String>());
        mSelectLayoutView2.setData(2,new ArrayList<String>());


        mTalkTime.setOnClickListener(onClickListener);
        mSubmit.setOnClickListener(onClickListener);


        setSPText(0, "其他", mSPRelation);
        setSPText(1, "其他", mSPState);
        setSPText(2, "其他", mSPPlanProject);
        setSPText(3, "预约二次洽谈", mSPQTJG);
    }


    private void setSPText(final int type, String name, Spinner spinner) {
        int listData = 0;
        if (type == 0) {
            //与逝者关系
            listData = R.array.gx;
        } else if (type == 1) {
            listData = R.array.szxz;
        } else if (type == 2) {
            listData = R.array.zsfx;
        } else if (type == 3) {
            listData = R.array.qtjg;
        }

        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(SaveTalkFailActivity.this, listData,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(listData);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(province_adapter);
        spinner.setSelection(Utils.getArrayINdex(arrs, name));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (type == 0) {
                    params.setRelation(arrs[position]);
                } else if (type == 1) {
                    params.setHealth(arrs[position]);
                } else if (type == 2) {
                    params.setProject(arrs[position]);
                } else if (type == 3) {
                    if (position == 0) {
                        params.setResult(true);
                        mTimeLL.setVisibility(View.VISIBLE);
                    } else {
                        params.setResult(false);
                        mTimeLL.setVisibility(View.GONE);
                        mTalkTime.setText("");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTalkTime) {
                setTime();
            } else if (view == mSubmit) {
                if (mRemark.getText().toString().equals("")) {
                    Toast.makeText(SaveTalkFailActivity.this, "洽谈要点不能为空", Toast.LENGTH_LONG).show();
                } else {
                    if (mETTime.getText().toString().equals("") && mSPQTJG.getSelectedItemPosition() == 0) {
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
        params.setConsultId(ConsultId + "");
        if (mSPRelation.getSelectedItemPosition() == 0) {
            if (mETRelation.getText().toString().equals("")) {
                params.setRelation("其他");
            } else {
                params.setRelation(mETRelation.getText().toString());
            }
        }
        params.setDeadName(mETName.getText().toString());
        params.setRemark(mRemark.getText().toString());

        params.setResultTime(mTalkTime.getText().toString());

        Log.v("this", "ConsultId:" + params.getConsultId());
        Log.v("this", "Relation:" + params.getRelation());
        Log.v("this", "Name:" + params.getDeadName());
        Log.v("this", "Health:" + params.getHealth());
        Log.v("this", "Project:" + params.getProject());
        Log.v("this", "Remark:" + params.getRemark());
        Log.v("this", "Result:" + params.isResult());
        Log.v("this", "Time:" + params.getResultTime());


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
                        MHttpManagerFactory.getAccountManager().saveTalkFailData(SaveTalkFailActivity.this, params,
                                new HttpResponseHandler<Object>() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        // TODO Auto-generated method stub
                                        if (!params.isResult()) {
                                            HpConsultIdParams params = new HpConsultIdParams();
                                            params.setConsultId(ConsultId);
                                            MHttpManagerFactory.getAccountManager().talkFinish(
                                                    SaveTalkFailActivity.this, params,
                                                    new HttpResponseHandler<Object>() {

                                                        @Override
                                                        public void onStart() {

                                                        }

                                                        @Override
                                                        public void onSuccess(Object result) {
                                                            Toast.makeText(SaveTalkFailActivity.this, "提交成功，结束洽谈", Toast.LENGTH_LONG).show();
                                                            OrderFragment.C_bOrder_isRefresh = true;
                                                            finish();
                                                        }

                                                        @Override
                                                        public void onError(String message) {

                                                        }
                                                    });
                                        } else {
                                            Toast.makeText(SaveTalkFailActivity.this, "提交成功，结束洽谈", Toast.LENGTH_LONG).show();
                                            OrderFragment.C_bOrder_isRefresh = true;
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onStart() {
                                        // TODO Auto-generated method stub

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

    /**
     * 设置洽谈时间
     */
    private void setTime() {
        Log.v("this", "DateTimeSelectorDialogBuilder");
        //设置二次洽谈时间
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(SaveTalkFailActivity.this);
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {
            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                mTalkTime.setText(selectedDate);
            }
        });
    }


}
