package com.shian.shianlife.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpSaveComment;
import com.shian.shianlife.provide.result.HrGetComment;
import com.shian.shianlife.provide.result.HrGetComment.PicItem;
import com.squareup.picasso.Picasso;

public class EvaluateActivity extends BaseActivity {
    @InjectViews({R.id.tv_ev_0, R.id.tv_ev_1, R.id.tv_ev_2})
    List<TextView> tvList;
    @InjectView(R.id.rb_ev)
    RatingBar rb;
    @InjectView(R.id.et_ev_note)
    EditText etNote;
    private long orderId;
    private long orderItemId;
    @InjectView(R.id.ll_pic)
    LinearLayout llPic;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_evaluate);
        setTitle("评价");
        orderId = getIntent().getLongExtra("orderId", 0);
        orderItemId = getIntent().getLongExtra("orderItemId", 0);
        initData();
    }

    private Handler mHandler = new Handler();

    private void initData() {
        HpOrderIdParams params = new HpOrderIdParams();
        params.setOrderId(orderId);
        params.setOrderItemId(orderItemId);
        MHttpManagerFactory.getAccountManager().getOrderComment(this, params,
                new HttpResponseHandler<HrGetComment>() {

                    @Override
                    public void onSuccess(HrGetComment result) {
                        // TODO Auto-generated method stub
                        for (final PicItem url : result.getPicItems()) {
                            final ImageView iv = new ImageView(getBaseContext());
                            iv.setLayoutParams(new LayoutParams(200, 200));
                            llPic.addView(iv);
                            mHandler.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    Picasso.with(getBaseContext()).load(AppContansts.OSSURL + url.getPicUrl()).into(iv);
                                    ;
                                    iv.setOnClickListener(new OnClickListener() {

                                        @Override
                                        public void onClick(View v) {
                                            // TODO Auto-generated method stub
                                            Intent in = new Intent(EvaluateActivity.this, ImagePreviewActivity.class);
                                            in.putExtra("url", AppContansts.OSSURL + url.getPicUrl());
                                            startActivity(in);
                                        }
                                    });

                                }
                            }, 1000);

                        }
                        tvList.get(0).setText(result.getExecutorName());
                        tvList.get(1).setText(result.getExecutorMobile());
                        tvList.get(2).setText(result.getExecutorNote());

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

    private HpSaveComment params = new HpSaveComment();
    List<String> listLabel = new ArrayList<>();

    @OnClick({R.id.btn_ev_0, R.id.btn_ev_1, R.id.btn_ev_2, R.id.btn_ev_3,
            R.id.btn_ev_4, R.id.btn_ev_5})
    void btnClick(Button v) {

        if (v.getTag() == null) {
            v.setTag(false);
            v.setBackgroundResource(R.drawable.shape_et_login);
            listLabel.add(v.getText().toString());
        } else if ((Boolean) v.getTag()) {
            v.setTag(false);
            v.setBackgroundResource(R.drawable.shape_et_login);
            listLabel.add(v.getText().toString());
        } else {
            v.setTag(true);
            v.setBackgroundResource(R.drawable.shape_et_order);
            listLabel.remove(v.getText().toString());
        }

    }


    @OnClick(R.id.tv_editorder)
    void saveData(View v) {
        for (int i = 0; i < listLabel.size(); i++) {
            params.setLabel(params.getLabel() + "," + listLabel.get(i));
        }

        params.setContent(etNote.getText().toString());
        params.setStarLevel((int) rb.getRating());
        params.setOrderId(orderId);
        params.setOrderItemId(orderItemId);
        MHttpManagerFactory.getAccountManager().saveOrderComment(this, params,
                new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(getBaseContext(), "提交成功");
                        sendBroadcast(new Intent(PgzxActivity.PGZX_ACTION));
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
