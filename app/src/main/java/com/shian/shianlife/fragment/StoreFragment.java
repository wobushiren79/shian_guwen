package com.shian.shianlife.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.thisenum.BuildOrderEnum;
import com.shian.shianlife.view.searchview.SearchView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class StoreFragment extends BaseFragment {
    View mLayoutView;
    @InjectView(R.id.search_view)
    SearchView searchView;
    @InjectView(R.id.bt_cemetery_build)
    Button btCemeteryBuild;
    @InjectView(R.id.bt_funeral_build)
    Button btFuneralBuild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayoutView = inflater.inflate(R.layout.fragment_store, null, false);
        ButterKnife.inject(this, mLayoutView);
        return mLayoutView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.bt_cemetery_build, R.id.bt_funeral_build})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cemetery_build:
                Intent intent = new Intent(getContext(), BuildOrderEnum.GM.getActivity());
                startActivity(intent);
                break;
            case R.id.bt_funeral_build:
                break;
        }
    }
}
