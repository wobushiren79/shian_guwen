package com.shian.shianlife.common.view.customerdetail;

import java.util.List;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.ImagePreviewActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.view.DownLoadView;
import com.shian.shianlife.provide.model.ConsultPrereadyModel;
import com.shian.shianlife.provide.result.ConsultPrereadies;
import com.shian.shianlife.provide.result.ConsultPrereadies.ConsultPrereadie;
import com.shian.shianlife.provide.result.HrGetCustomerPreready;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class PrereadyDetailInfoView extends FrameLayout {

	@InjectView(R.id.ll_fujian)
	LinearLayout ll_fujian;

	@InjectView(R.id.ll_img)
	LinearLayout ll_img;

	@InjectView(R.id.tv_status)
	TextView tv_status;

	public PrereadyDetailInfoView(Context context) {
		super(context);
		init();
	}

	public PrereadyDetailInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PrereadyDetailInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_prereadydetailinfo, null);
		addView(view);
		ButterKnife.inject(this);
	}

	public void setData(HrGetCustomerPreready result) {
		List<ConsultPrereadyModel> consultPrereadies = result.getConsultPrereadies();
		tv_status.setText(result.getCompleteResult());
		LinearLayout.LayoutParams mLinearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mLinearParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		LinearLayout.LayoutParams mBtnParams = new LinearLayout.LayoutParams(0,
				getResources().getDimensionPixelOffset(R.dimen.dimen_80dp));
		mBtnParams.weight = 1;
		mBtnParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		mBtnParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		mBtnParams.gravity = Gravity.CENTER_VERTICAL;

		LinearLayout.LayoutParams mImgParams = new LinearLayout.LayoutParams(0,
				getResources().getDimensionPixelOffset(R.dimen.dimen_200dp));
		mImgParams.weight = 1;
		mImgParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		mImgParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.dimen_10dp);
		mImgParams.gravity = Gravity.CENTER_VERTICAL;

		int position = 0;
		int tmp = consultPrereadies.size() % 3;
		int line = tmp == 0 ? tmp : tmp + 1;
		for (int i = 0; i < line; i++) {
			LinearLayout mLinearLayout = new LinearLayout(getContext());
			mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
			mLinearLayout.setWeightSum(3);
			mLinearLayout.setLayoutParams(mLinearParams);
			ll_fujian.addView(mLinearLayout);
			for (int j = 0; j < 3; j++) {
				if (position < consultPrereadies.size()) {
					ConsultPrereadyModel prereadie = consultPrereadies.get(position);
					DownLoadView dv = new DownLoadView(getContext());
					dv.setLayoutParams(mBtnParams);
					dv.setFileUrl(AppContansts.OSSURL + prereadie.getFileUrl());
					mLinearLayout.addView(dv);
					position++;
				}
			}
		}

		position = 0;
		tmp = consultPrereadies.size() % 3;
		line = tmp == 0 ? tmp : tmp + 1;
		for (int i = 0; i < line; i++) {
			LinearLayout mLinearLayout = new LinearLayout(getContext());
			mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
			mLinearLayout.setWeightSum(3);
			mLinearLayout.setLayoutParams(mLinearParams);
			ll_img.addView(mLinearLayout);
			for (int j = 0; j < 3; j++) {
				if (position < consultPrereadies.size()) {
					final ConsultPrereadyModel prereadie = consultPrereadies.get(position);
					ImageView iv = new ImageView(getContext());
					iv.setLayoutParams(mBtnParams);
					PicassoUD.loadByImageLoader(getContext(), AppContansts.OSSURL + prereadie.getFileUrl(), iv, 0);
					iv.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							Intent mIntent = new Intent();
							mIntent.setClass(getContext(), ImagePreviewActivity.class);
							mIntent.putExtra("url", AppContansts.OSSURL + prereadie.getFileUrl());
							((Activity) getContext()).startActivity(mIntent);
						}
					});
					mLinearLayout.addView(iv);
					position++;
				}
			}
		}
	}

}
