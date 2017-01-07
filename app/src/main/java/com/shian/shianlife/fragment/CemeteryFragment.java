package com.shian.shianlife.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseFragment;


/**
 * Created by Administrator on 2017/1/6.
 */

public class CemeteryFragment extends BaseFragment {
    private View mainLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainLayout = inflater.inflate(R.layout.fragment_cemetery, null, false);
        return mainLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
