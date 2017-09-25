package com.shian.shianlife.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.view.carousel.CarouselView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zm.
 */

public class NoPermissionsFragment extends BaseFragment {
    View view;
    @InjectView(R.id.layout_carouse)
    CarouselView layoutCarouse;

    private ArrayList<String> listData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nopermissions, null, false);
        ButterKnife.inject(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {

    }

    private void initData() {
        listData.clear();
        listData.add(AppContansts.Cooperation_Pic_1);
        listData.add(AppContansts.Cooperation_Pic_2);
        listData.add(AppContansts.Cooperation_Pic_3);
        layoutCarouse.setData(listData);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
