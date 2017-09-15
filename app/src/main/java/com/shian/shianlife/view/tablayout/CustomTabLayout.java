package com.shian.shianlife.view.tablayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zm.
 */

public class CustomTabLayout extends TabLayout implements TabLayout.OnTabSelectedListener {

    private List<View> tablViewList = new ArrayList<>();
    private ViewPager mCustomViewPager;

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 设置tablayout的控件
     * 在绑定viewpager之后设置
     *
     * @param tablViewList
     */
    public void setTablViewList(List<View> tablViewList) {
        if (tablViewList == null)
            return;
        this.tablViewList = tablViewList;
        for (int i = 0; i < this.getTabCount(); i++) {
            TabLayout.Tab tab = this.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(tablViewList.get(i));
            }
        }
        this.setOnTabSelectedListener(this);
    }


    public void setSelect(int position) {
//        this.set(position);
    }

    @Override
    public void onTabSelected(Tab tab) {
        if (mCustomViewPager != null)
            mCustomViewPager.setCurrentItem(tab.getPosition());
        for (int i = 0; i < tablViewList.size(); i++) {
            try {
                View itemView = tablViewList.get(i);
                BaseTabLayoutItem baseItemView = (BaseTabLayoutItem) itemView;
                if (i == tab.getPosition()) {
                    baseItemView.setSelect(true);
                } else {
                    baseItemView.setSelect(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTabUnselected(Tab tab) {

    }

    @Override
    public void onTabReselected(Tab tab) {

    }

    @Override
    public void setupWithViewPager(@NonNull ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        this.mCustomViewPager = viewPager;
    }
}
