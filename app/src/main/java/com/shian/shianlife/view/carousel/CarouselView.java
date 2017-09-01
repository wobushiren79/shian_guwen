package com.shian.shianlife.view.carousel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.CarouselAdapter;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.view.picrg.PicRadioGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public class CarouselView extends LinearLayout implements PicRadioGroup.CallBack, ViewPager.OnPageChangeListener {
    private View mView;
    private ViewPager mViewContent;
    private PicRadioGroup mRG;

    private CarouselAdapter carouselAdapter;
    private List<View> listIV;

    public CarouselView(Context context) {
        this(context, null);
    }

    public CarouselView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mView = View.inflate(context, R.layout.view_carousel_main_layout, this);
        initView();
        initData();
    }

    private void initView() {
        mViewContent = (ViewPager) mView.findViewById(R.id.viewpager);
        mRG = (PicRadioGroup) mView.findViewById(R.id.pic_rg);
    }

    private void initData() {
        listIV = new ArrayList<>();
    }

    public void setData(List<String> listUrl) {
        if (listUrl == null)
            return;
        listUrl.add("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=d886c8e211d5ad6ebef46ca9e9a253ae/dcc451da81cb39dbbc609470da160924ab183077.jpg");
        listUrl.add("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=f98d7c40b919ebc4d4757edaea4fa589/b64543a98226cffc9eabfc97b3014a90f603ea16.jpg");
        for (String url : listUrl) {
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Utils.loadPic(getContext(), iv, url, R.drawable.zhy_pic_loading);
            listIV.add(iv);
        }

        carouselAdapter = new CarouselAdapter(getContext(), listIV);
        mViewContent.setAdapter(carouselAdapter);

        //個數爲1 則隱藏RG
        if (listUrl.size() <= 1) {
            mRG.setVisibility(GONE);
        } else {
            mRG.setData(listUrl.size());
            mRG.setSelectItem(0);
        }

        mRG.setCallBack(this);
        mViewContent.addOnPageChangeListener(this);
    }

    @Override
    public void checkChange(View rgView, int number) {
        mViewContent.setCurrentItem(number);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mRG.setSelectItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setRGAlpha(float alpha) {
        mRG.setAlpha(alpha);
    }
}
