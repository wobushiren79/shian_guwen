package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsDescribeAdapter;
import com.shian.shianlife.base.BaseLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsDescribeLayout extends BaseLayout {
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;

    String[] tabNames;
    private GoodsDescribeAdapter describeAdapter;
    private List<View> views;

    public GoodsDescribeLayout(Context context) {
        this(context, null);
    }

    public GoodsDescribeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_describle, attrs);
    }

    @Override
    protected void initView() {
//        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tablayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    @Override
    protected void initData() {
        views = new ArrayList<>();
        tabNames = new String[]{
                "产品详情", "适用信息", "售后保障"
        };
        for (String name : tabNames) {
            TextView tv = new TextView(getContext());
            tv.setText(name);
            views.add(tv);
        }
        describeAdapter = new GoodsDescribeAdapter(getContext(), views);
        viewpager.setAdapter(describeAdapter);
        tablayout.setupWithViewPager(viewpager);


        for (int i = 0; i < tabNames.length; i++) {
            tablayout.getTabAt(i).setText(tabNames[i]);
        }
    }
}
