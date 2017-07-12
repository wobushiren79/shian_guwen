package com.shian.shianlife.adapter.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class BaseViewPagerAdapter extends PagerAdapter {

    protected Context mContext;
    protected List<View> mLayouts = new ArrayList<>();

    public BaseViewPagerAdapter(Context context, List<View> mLayouts) {
        this.mContext = context;
        this.mLayouts = mLayouts;
    }

    @Override
    public int getCount() {
        return mLayouts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view==object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mLayouts.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mLayouts.get(position),0);
        return mLayouts.get(position);
    }
}
