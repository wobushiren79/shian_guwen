package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsMainRecommendPagerAdapter;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsMainRecommendTitleResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsMainRecommendTitlePresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsMainRecommendTitlePresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsMainRecommendTitleView;
import com.shian.shianlife.view.tablayout.BaseTabLayoutItem;
import com.shian.shianlife.view.tablayout.CustomTabLayout;
import com.shian.shianlife.view.tablayout.NormalTabLayoutItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsMainRecommendLayout extends BaseLayout implements IGoodsMainRecommendTitleView {

    @InjectView(R.id.tablayout)
    CustomTabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;


    private List<View> listView = new ArrayList<>();
    private List<View> titleListView = new ArrayList<>();

    private GoodsMainRecommendPagerAdapter pagerAdapter;
    private IGoodsMainRecommendTitlePresenter goodsMainRecommendTitlePresenter;

    public GoodsMainRecommendLayout(Context context) {
        this(context, null);
    }

    public GoodsMainRecommendLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_main_recommend, attrs);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        goodsMainRecommendTitlePresenter = new GoodsMainRecommendTitlePresenterImpl(this);
        goodsMainRecommendTitlePresenter.getRecommendTitleData();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsMainRecommendTitleSuccess(List<GoodsMainRecommendTitleResultBean> listData) {
        for (GoodsMainRecommendTitleResultBean itemData : listData) {
            NormalTabLayoutItem titleView = new NormalTabLayoutItem(getContext());
            titleView.setTitle(itemData.getLabel_name());
            titleListView.add(titleView);

            ViewPager.LayoutParams layout = new ViewPager.LayoutParams();
            layout.height = 200;
            TextView t = new TextView(getContext());
            t.setText(itemData.getLabel_name());
            t.setLayoutParams(layout);
            listView.add(t);
        }
        pagerAdapter = new GoodsMainRecommendPagerAdapter(getContext(), listView);
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(pagerAdapter);

        tablayout.setupWithViewPager(viewpager);
        tablayout.setTablViewList(titleListView);

        viewpager.setCurrentItem(0);
        tablayout.setSelect(0);
    }

    @Override
    public void getGoodsMainRecommendTitleFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }
}
