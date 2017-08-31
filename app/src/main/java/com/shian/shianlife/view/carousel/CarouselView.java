package com.shian.shianlife.view.carousel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.CarouselAdapter;
import com.shian.shianlife.common.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class CarouselView extends LinearLayout {
    private View mView;
    private ViewPager mViewContent;
    private RadioGroup mRadioGroup;

    private CarouselAdapter carouselAdapter;
    private List<View> listIV;

    public CarouselView(Context context) {
        this(context, null);
    }

    public CarouselView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = View.inflate(context, R.layout.view_carousel_main_layout, this);
    }

    private void initView() {
        mViewContent = (ViewPager) mView.findViewById(R.id.viewpager);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_content);
    }

    private void initData() {
        listIV = new ArrayList<>();
    }

    public void setData(List<String> listUrl) {
        if (listUrl == null)
            return;
        for (String url : listUrl) {
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Utils.loadPic(getContext(), iv, url, R.drawable.zhy_load_icon);
            listIV.add(iv);
        }

        carouselAdapter = new CarouselAdapter(getContext(), listIV);
        mViewContent.setAdapter(carouselAdapter);
    }

}
