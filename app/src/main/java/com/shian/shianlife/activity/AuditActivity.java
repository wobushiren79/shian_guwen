package com.shian.shianlife.activity;

import java.util.Date;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.TransitionDate;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpAuditOrder;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.result.HrGetOrderNote;
import com.shian.shianlife.provide.result.HrGetOrderNote.PicItem;
import com.squareup.picasso.Picasso;

public class AuditActivity extends BaseActivity {
	@InjectViews({ R.id.tv_au_0, R.id.tv_au_1, R.id.tv_au_2, R.id.tv_au_3, R.id.tv_au_4, R.id.tv_au_5, R.id.tv_au_6,
			R.id.tv_au_7, R.id.tv_au_8, R.id.tv_au_9 })
	List<TextView> tvList;
	private long orderItemId;
	@InjectView(R.id.et_au_note)
	EditText etNote;
	@InjectView(R.id.ll_pic)
	LinearLayout llPic;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.activity_audit);
		setTitle("审核执行单详情");
		orderItemId = getIntent().getLongExtra("orderItemId", 0);
		initData();
	}
	private Handler mHandler=new Handler();
	private void initData() {
		HpOrderIdParams params = new HpOrderIdParams();
		params.setOrderItemId(orderItemId);
		MHttpManagerFactory.getAccountManager().getOrderNote(this, params, new HttpResponseHandler<HrGetOrderNote>() {

					@Override
					public void onSuccess(HrGetOrderNote result) {
						// TODO Auto-generated method stub
						for (final PicItem url : result.getPicItems()) {
							final ImageView iv = new ImageView(getBaseContext());
							iv.setLayoutParams(new LayoutParams(200,200));
							llPic.addView(iv);
							mHandler.postDelayed(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									Picasso.with(getBaseContext()).load(AppContansts.OSSURL + url.getPicUrl()).into(iv);
									iv.setOnClickListener(new OnClickListener() {
										
										@Override
										public void onClick(View v) {
											// TODO Auto-generated method stub
											Intent in=new Intent (AuditActivity.this,ImagePreviewActivity.class);
											in.putExtra("url", AppContansts.OSSURL + url.getPicUrl());
											startActivity(in);
										}
									});
									
								}
							}, 1000);
							
						}
						tvList.get(0).append(result.getOrderNum());
						tvList.get(1).append(result.getAgentmanName());
						tvList.get(2).append(result.getSkuName());
						tvList.get(3).append(result.getExecutorName());
						tvList.get(4).append(result.getFuneralAddress() == null ? "": result.getFuneralAddress());
						tvList.get(5).append(getTime(result.getAcceptTime()));
						tvList.get(6).append(getTime(result.getStartTime()));
						tvList.get(7).append(getTime(result.getEndTime()));
						tvList.get(8).append(result.getExecutorNote()==null?"":result.getExecutorNote());
//						tvList.get(9).append(result.getAdviserNote());
						tvList.get(9).append(result.getLabel());
						
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

	private String getTime(long t) {
		return TransitionDate.DateToStr(new Date(t),"yyyy-MM-dd HH:ss");
	}

	@OnClick({ R.id.tv_yse, R.id.tv_no })
	void saveData(final View v) {
		TipsDialog mDialog = new TipsDialog(this);
		mDialog.setTitle("请认真核实执行单的完成情况，再确认执行单是否通过。");
		mDialog.setTopButton("再核实", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		mDialog.setBottomButton("已确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HpAuditOrder params = new HpAuditOrder();
				params.setOrderItemId(orderItemId);
				params.setAuditNote(etNote.getText().toString());
				params.setPass(v.getId() == R.id.tv_yse ? true : false);
				MHttpManagerFactory.getAccountManager().auditOrder(AuditActivity.this, params,
						new HttpResponseHandler<Object>() {

							@Override
							public void onSuccess(Object result) {
								// TODO Auto-generated method stub
								ToastUtils.show(getBaseContext(), "操作成功");
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
								ToastUtils.show(getBaseContext(), "提交失败:"+message);
							}
						});
			}
		});
		mDialog.show();

	}
}
