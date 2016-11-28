package com.shian.shianlife.common.view.customerdetail;

import com.shian.shianlife.R;
import com.shian.shianlife.provide.result.HrConsultFuneral;
import com.shian.shianlife.provide.result.HrConsultFuneral.HrConsultFunerals;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class TalkDetailInfoView extends FrameLayout {

	@InjectView(R.id.tv_frBelief)
	TextView tv_frBelief;

	@InjectView(R.id.tv_talkAddress)
	TextView tv_talkAddress;

	@InjectView(R.id.tv_frAddress)
	TextView tv_frAddress;

	@InjectView(R.id.tv_other)
	TextView tv_other;

	public TalkDetailInfoView(Context context) {
		super(context);
		init();
	}

	public TalkDetailInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TalkDetailInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_talkdetailinfo, null);
		addView(view);
		ButterKnife.inject(this);
	}

	public void setData(HrConsultFuneral result) {
		HrConsultFunerals consultFuneral = result.getConsultFuneral();
		tv_frAddress.setText(consultFuneral.getFrAddress());
//		switch (consultFuneral.getFrBelief()) {
//		case 0:
			tv_frBelief.setText(consultFuneral.getFrBelief());
//			break;
//		case 1:
//			tv_frBelief.setText("基督教");
//			break;
//		case 2:
//			tv_frBelief.setText("基督教");
//			break;
//		case 3:
//			tv_frBelief.setText("无信仰");
//			break;
//		case 4:
//			tv_frBelief.setText("其它");
//			break;
//		}
		tv_other.setText(consultFuneral.getOther());
		tv_talkAddress.setText(consultFuneral.getTalkAddress());

	}

}
