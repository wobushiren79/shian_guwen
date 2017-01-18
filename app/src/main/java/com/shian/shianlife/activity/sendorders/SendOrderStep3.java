package com.shian.shianlife.activity.sendorders;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpSaveSendOrderDataFour;
import com.shian.shianlife.provide.result.HrGetSendOrderDataFour;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2016/12/21.
 */

public class SendOrderStep3 extends BaseSendOrder {

    Spinner mSPCremator;
    Spinner mSpBodiespark;

    EditText mETBodiespark;
    EditText mETServiceWindows;
    EditText mETRemark;

    TextView mTVFuneralTime;
    TextView mTVFireTime;
    TextView mTVBodiesByeTime;

    HpSaveSendOrderDataFour params = new HpSaveSendOrderDataFour();

    public SendOrderStep3(Context context, long consultId) {
        super(context, R.layout.layout_sendorder_3, consultId);
        initView();

    }

    private void initView() {
        mSPCremator = (Spinner) findViewById(R.id.sp_jbr_0);
        mSpBodiespark = (Spinner) findViewById(R.id.bodiespark);
        mETBodiespark = (EditText) findViewById(R.id.et_bodiespark);
        mETServiceWindows = (EditText) findViewById(R.id.tv_servicewindows);

        mTVFuneralTime = (TextView) findViewById(R.id.tv_funeraltime);
        mTVFireTime = (TextView) findViewById(R.id.tv_firetime);
        mTVBodiesByeTime = (TextView) findViewById(R.id.tv_bodiesbyetime);
        mETRemark = (EditText) findViewById(R.id.et_remark);
        mTVFuneralTime.setOnClickListener(onClickListener);
        mTVFireTime.setOnClickListener(onClickListener);
        mTVBodiesByeTime.setOnClickListener(onClickListener);
        initSp("其他", 0);
        initSp("单间停放", 1);
        getData();
    }

    @Override
    public void saveData() {
        params.setConsultId(consultId);
        if (mSpBodiespark.getSelectedItemPosition() == 0) {
            if (mETBodiespark.getText().toString().equals("")) {
                params.setBodiesPark("单间停放");
            } else {
                params.setBodiesPark(mETBodiespark.getText().toString());
            }
        }
        if (mETServiceWindows.getText().toString().equals("")) {
            ToastUtils.show(getContext(), "服务窗口没有设置");
            return;
        }
        params.setServiceWindows(mETServiceWindows.getText().toString());

        if (!mTVFireTime.getText().toString().equals("")) {
            params.setFireTime(TransitionDate.StrToDate(mTVFireTime.getText().toString(), "yyyy-MM-dd").getTime());
        } else {
            ToastUtils.show(getContext(), "火化时间没有设置");
            return;
        }

        if (!mTVBodiesByeTime.getText().toString().equals("")) {
            params.setBodiesByeTime(TransitionDate.StrToDate(mTVBodiesByeTime.getText().toString(), "yyyy-MM-dd").getTime());
        } else {
            ToastUtils.show(getContext(), "遗体告别仪式时间没有设置");
            return;
        }

        if (!mTVFuneralTime.getText().toString().equals("")) {
            params.setFuneralTime(TransitionDate.StrToDate(mTVFuneralTime.getText().toString(), "yyyy-MM-dd").getTime());
        } else {
            ToastUtils.show(getContext(), "出殡时间没有设置");
            return;
        }

        params.setFuneralRemark(mETRemark.getText().toString());


        MHttpManagerFactory.getAccountManager().saveSendOrderDataFour(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(), "处理出殡前服务成功");
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                intent.putExtra("finsh", 0);
                getContext().sendBroadcast(intent);
            }

            @Override
            public void onError(String message) {

            }
        });
    }


    @Override
    public void getData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId(consultId);
        MHttpManagerFactory.getAccountManager().getSendOrderDataFour(getContext(), params, new HttpResponseHandler<HrGetSendOrderDataFour>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetSendOrderDataFour result) {
                Log.v("this", "getZsLocation:" + result.getZsLocation());
                Log.v("this", "getCrematorName:" + result.getCrematorName());
                Log.v("this", "getServiceWindows:" + result.getServiceWindows());
                Log.v("this", "getBodiesPark:" + result.getBodiesPark());
                Log.v("this", "getBodiesParkName:" + result.getBodiesParkName());
                Log.v("this", "getFireTime:" + result.getFireTime());
                Log.v("this", "getBodiesByeTime:" + result.getBodiesByeTime());
                Log.v("this", "getFuneralTime:" + result.getFuneralTime());
                Log.v("this", "getFuneralRemark:" + result.getFuneralRemark());

                if (result.getCrematorName() != null) {
                    if (result.getCrematorName().equals("其他")) {
                        mSPCremator.setSelection(0);
                    } else if (result.getCrematorName().equals("豪华炉")) {
                        mSPCremator.setSelection(1);
                    } else if (result.getCrematorName().equals("普通炉")) {
                        mSPCremator.setSelection(2);
                    }
                }
                if (result.getServiceWindows() != null) {
                    mETServiceWindows.setText(result.getServiceWindows());
                }
                if (result.getBodiesPark() != null) {
                    if (result.getBodiesPark().equals("单间停放")) {
                        mSpBodiespark.setSelection(0);
                    } else if (result.getBodiesPark().equals("集体停放")) {
                        mSpBodiespark.setSelection(1);
                    } else if (result.getBodiesPark().equals("其他")) {
                        mSpBodiespark.setSelection(2);
                    } else {
                        mETBodiespark.setText(result.getBodiesPark());
                    }
                }

                if (result.getFireTime() != 0) {
                    mTVFireTime.setText(TransitionDate.DateToStr(new Date(result.getFireTime()), "yyyy-MM-dd HH:ss"));
                }
                if (result.getFuneralTime() != 0) {
                    mTVFuneralTime.setText(TransitionDate.DateToStr(new Date(result.getFuneralTime()), "yyyy-MM-dd HH:ss"));
                }
                if (result.getBodiesByeTime() != 0) {
                    mTVBodiesByeTime.setText(TransitionDate.DateToStr(new Date(result.getBodiesByeTime()), "yyyy-MM-dd HH:ss"));
                }
                if (result.getFuneralRemark() != null) {
                    mETRemark.setText(result.getFuneralRemark());
                }
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                ArrayList<String> data = new ArrayList<String>();
                data.add(result.getZsLocation());
                intent.putStringArrayListExtra("data", data);
                getContext().sendBroadcast(intent);
            }

            @Override
            public void onError(String message) {
                Intent intent = new Intent(SendOrderActivity.UPDATA_ACTION);
                intent.putExtra("finsh", 1);
                getContext().sendBroadcast(intent);
            }
        });
    }

    private void initSp(String i, final int type) {
        Spinner spinner = mSPCremator;
        int array = R.array.fire_cremator;
        if (type == 0) {
            array = R.array.fire_cremator;
            spinner = mSPCremator;
        } else if (type == 1) {
            array = R.array.bodies_park;
            spinner = mSpBodiespark;
        }
        ArrayAdapter<CharSequence> province_adapter = ArrayAdapter.createFromResource(getContext(), array,
                android.R.layout.simple_spinner_item);
        final String[] arrs = getResources().getStringArray(array);
        province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(province_adapter);
        spinner.setSelection(Utils.getArrayINdex(arrs, i));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (type == 0) {
                    params.setCrematorName(arrs[position]);
                } else if (type == 1) {
                    params.setBodiesPark(arrs[position]);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVFireTime) {
                setBirthdayTime(0);
            } else if (view == mTVBodiesByeTime) {
                setBirthdayTime(1);
            } else if (view == mTVFuneralTime) {
                setBirthdayTime(2);
            }
        }
    };

    private void setBirthdayTime(final int type) {
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(getContext());
        dialog.setShowHour(true);
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {

            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                if (type == 0) {
                    mTVFireTime.setText(selectedDate);
                } else if (type == 1) {
                    mTVBodiesByeTime.setText(selectedDate);
                } else if (type == 2) {
                    mTVFuneralTime.setText(selectedDate);
                }

            }
        });
    }
}
