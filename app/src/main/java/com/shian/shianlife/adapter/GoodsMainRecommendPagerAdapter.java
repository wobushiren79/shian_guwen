package com.shian.shianlife.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseViewPagerAdapter;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsMainRecommendPagerAdapter extends BaseViewPagerAdapter implements ViewPager.OnPageChangeListener {

    private Context context;
    private List<View> listTitleView;


    public GoodsMainRecommendPagerAdapter(Context context, List<View> mLayouts) {
        super(context, mLayouts);
        this.context = context;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
