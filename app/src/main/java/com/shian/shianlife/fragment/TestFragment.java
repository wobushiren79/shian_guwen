package com.shian.shianlife.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shian.shianlife.base.BaseFragment;

public class TestFragment extends BaseFragment {
	private TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (tv == null) {
			tv = new TextView(getActivity());
			tv.setText(mTx);
		}
		return tv;
	}

	private String mTx;
	public void setTextViewContent(String tx) {
		mTx=tx;
	}
}
