package com.shian.shianlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.shian.shianlife.adapter.base.BaseViewPagerAdapter;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsDescribeAdapter extends BaseViewPagerAdapter {

    public GoodsDescribeAdapter(Context context, List<View> mLayouts) {
        super(context, mLayouts);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mLayouts.get(position), 0);
        mLayouts.get(position).setId(position);
        return mLayouts.get(position);
    }
}
