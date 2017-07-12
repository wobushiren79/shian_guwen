package com.shian.shianlife.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseFragment;

/**
 * Created by zm.
 */

public class StoreFragment extends BaseFragment {
    View mLayoutView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(R.layout.fragment_store, null, false);
        return mLayoutView;
    }
}
