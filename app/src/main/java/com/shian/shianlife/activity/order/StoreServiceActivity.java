package com.shian.shianlife.activity.order;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsOrderServicePagerAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.listener.GoodsOrderServicePagerChangeListener;
import com.shian.shianlife.thisenum.GoodsPayEnum;
import com.shian.shianlife.view.goods.GoodsOrderServiceLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StoreServiceActivity extends BaseActivity {

    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    private GoodsOrderServicePagerAdapter pagerAdapter;
    private GoodsOrderServicePagerChangeListener pagerChangeListener;
    private List<View> listView;

    public static boolean isNeedRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_service);
        ButterKnife.inject(this);
        initView();
        initData();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        refreshView();
    }

    private void refreshView() {
        if (isNeedRefresh && listView != null) {
            appbar.setExpanded(true);
            for (View view : listView) {
                GoodsOrderServiceLayout item = (GoodsOrderServiceLayout) view;
                item.refreshData();
            }
        }
    }

    private void initView() {
        setTitle("订单列表");
    }


    private void initData() {
        listView = new ArrayList<>();


        GoodsOrderServiceLayout waitSubmitLayout = new GoodsOrderServiceLayout(this, new Integer[]{0}, null);
        waitSubmitLayout.setTitle("待提交");
        listView.add(waitSubmitLayout);

        GoodsOrderServiceLayout waitPayLayout = new GoodsOrderServiceLayout(this, null, GoodsPayEnum.no_pay.getCode());
        waitPayLayout.setTitle("待付款");
        listView.add(waitPayLayout);

        GoodsOrderServiceLayout serviceingLayout = new GoodsOrderServiceLayout(this, new Integer[]{1, 2}, null);
        serviceingLayout.setTitle("服务中");
        listView.add(serviceingLayout);

        GoodsOrderServiceLayout serviceSuccessLayout = new GoodsOrderServiceLayout(this, new Integer[]{3, 4}, null);
        serviceSuccessLayout.setTitle("已服务");
        listView.add(serviceSuccessLayout);

        GoodsOrderServiceLayout serviceCancelLayout = new GoodsOrderServiceLayout(this, new Integer[]{10}, null);
        serviceCancelLayout.setTitle("已关闭");
        listView.add(serviceCancelLayout);

        pagerAdapter = new GoodsOrderServicePagerAdapter(this, listView);
        pagerChangeListener = new GoodsOrderServicePagerChangeListener();
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(pagerChangeListener);
        tablayout.setupWithViewPager(viewpager);

        for (int i = 0; i < listView.size(); i++) {
            GoodsOrderServiceLayout item = (GoodsOrderServiceLayout) listView.get(i);
            tablayout.getTabAt(i).setText(item.getTitle());
        }
    }
}
