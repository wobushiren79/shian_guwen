package com.shian.shianlife.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/5.
 */

public class NewHomeFragment extends BaseFragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_newhome, null, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
