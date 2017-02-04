package com.shian.shianlife.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseFragment;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.ViewPageAdapter;
import com.shian.shianlife.common.view.order.BaseOrderView;
import com.shian.shianlife.common.view.order.CemeteryOverServiceView;
import com.shian.shianlife.common.view.order.CemeteryQTView;
import com.shian.shianlife.common.view.order.CemeterySHView;
import com.shian.shianlife.provide.result.HrLoginResult;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Administrator on 2017/1/6.
 */
@SuppressLint("InflateParams")
public class CemeteryFragment extends BaseFragment {


    @InjectView(R.id.indicator)
    TabPageIndicator indicator;
    @InjectView(R.id.pager)
    ViewPager viewPager;

    private View mainLayout;
    private List<String> arrTitles = new ArrayList<String>();

    private BaseOrderView mOrderView;
    private int mIndex;
    private List<View> views;

    public static boolean C_bOrder_isRefresh;
    private HrLoginResult mLoginResult;

    public static ArrayList<Integer> LOGIN_ROLES_LIST = new ArrayList<>();//权限信息 角色列表(0,超级管理员，1客服，2新建，3洽谈，4售后)


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainLayout = inflater.inflate(R.layout.fragment_cemetery, null, false);
        ButterKnife.inject(this, mainLayout);
        return mainLayout;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initStates();
        initView();
        if (views.size() > 0) {
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (mOrderView != null)
            mOrderView.refresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        C_bOrder_isRefresh = false;
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (views.size() > 0 && C_bOrder_isRefresh) {
            ((BaseOrderView) views.get(mIndex)).refresh();
        }
    }


    private void initView() {
        views = new ArrayList<View>();
        ViewPageAdapter adapter = new ViewPageAdapter(views) {
            @Override
            public CharSequence getPageTitle(int position) {
                // TODO Auto-generated method stub
                return arrTitles.get(position);
            }
        };
        initPagerAdapter(views);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                mIndex = arg0;
                BaseOrderView orderView = (BaseOrderView) views.get(arg0);
                mOrderView = orderView;
                orderView.refresh();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }


    private void initStates() {
        boolean qtb = false;
        boolean shb = false;
        boolean fwb = false;

        arrTitles.clear();
        mLoginResult = JSONUtil.parseJSONString(getActivity().getIntent()
                .getStringExtra("loginData"), HrLoginResult.class);

        if (mLoginResult.getRoleIds() != null) {
            LOGIN_ROLES_LIST = mLoginResult.getRoleIds();
        }

        for (int role : mLoginResult.getRoleIds()) {
            switch (role) {
                case 0:
                    if (!qtb) {
                        arrTitles.add("洽谈");
                    }
                    if (!shb) {
                        arrTitles.add("售后");
                    }
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    if (!qtb) {
                        arrTitles.add("洽谈");
                    }
                    break;
                case 4:
                    if (!shb) {
                        arrTitles.add("售后");
                    }
                    break;
            }
            if (!fwb) {
                arrTitles.add("服务结束");
            }
        }

    }

    private void initPagerAdapter(List<View> views) {
        for (String n : arrTitles) {
            if (n.equals("洽谈")) {
                CemeteryQTView qtView = new CemeteryQTView(getActivity());
                views.add(qtView);
            } else if (n.equals("售后")) {
                CemeterySHView waitServiceView = new CemeterySHView(getActivity());
                views.add(waitServiceView);
            } else if (n.equals("服务结束")) {
                CemeteryOverServiceView overServiceView = new CemeteryOverServiceView(getActivity());
                views.add(overServiceView);
            }
        }
    }
}
