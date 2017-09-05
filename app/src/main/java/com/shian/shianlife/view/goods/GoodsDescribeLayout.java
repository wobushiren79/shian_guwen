package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsDescribeAdapter;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.thisenum.GoodsDescribeEnum;
import com.shian.shianlife.view.ScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static com.shian.shianlife.thisenum.GoodsDescribeEnum.goods_apply;
import static com.shian.shianlife.thisenum.GoodsDescribeEnum.goods_details;
import static com.shian.shianlife.thisenum.GoodsDescribeEnum.goods_security;

/**
 * Created by zm.
 */

public class GoodsDescribeLayout extends BaseLayout implements ViewPager.OnPageChangeListener {
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.viewpager)
    ScrollViewPager viewpager;

    private GoodsDescribeEnum[] describeEna;
    private GoodsDescribeAdapter describeAdapter;
    private List<View> views;

    private GoodsDescribeDetailsLayout detailsLayout;
    private GoodsDescribeApplyLayout applyLayout;
    private GoodsDescribeSecurityLayout securityLayout;


    public GoodsDescribeLayout(Context context) {
        this(context, null);
    }

    public GoodsDescribeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_describle, attrs);
    }

    @Override
    protected void initView() {
//        tablayout.setTabMode(TabLayout.MODE_FIXED);
//        tablayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    @Override
    protected void initData() {
        views = new ArrayList<>();
        describeEna = new GoodsDescribeEnum[]{
                goods_details,
                goods_apply,
                goods_security
        };

        for (GoodsDescribeEnum enu : describeEna) {
            if (enu == goods_details) {
                detailsLayout = new GoodsDescribeDetailsLayout(getContext());
                views.add(detailsLayout);
            } else if (enu == goods_apply) {
                applyLayout = new GoodsDescribeApplyLayout(getContext());
                views.add(applyLayout);
            } else if (enu == goods_security) {
                securityLayout = new GoodsDescribeSecurityLayout(getContext());
                views.add(securityLayout);
            }
        }
        describeAdapter = new GoodsDescribeAdapter(getContext(), views);
        viewpager.setAdapter(describeAdapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(3);
        tablayout.setupWithViewPager(viewpager);

        for (int i = 0; i < describeEna.length; i++) {
            tablayout.getTabAt(i).setText(describeEna[i].getName());
        }
    }

    public void setDetailsData(String html) {
        detailsLayout.setWebData(html);
        try {
            ViewUtils.setIndicator(tablayout,20,20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setApplyBury(String applyBury) {
        applyLayout.setApplyBury(applyBury);
    }

    public void setApplyPerson(String applyPerson) {
        applyLayout.setApplyPerson(applyPerson);
    }

    public void setApplyPhase(String applyPhase) {
        applyLayout.setApplyPhase(applyPhase);
    }

    public void setApplyAge(String applyAge) {
        applyLayout.setApplyAge(applyAge);
    }

    public void setApplyLocation(String location) {
        applyLayout.setApplyLocation(location);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewpager.requestLayout();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
