package com.shian.shianlife.view.goods;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsMainRecommendPagerAdapter;
import com.shian.shianlife.base.BaseLayout;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsLabelResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsLabelPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsLabelPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsLabelView;
import com.shian.shianlife.view.tablayout.CustomTabLayout;
import com.shian.shianlife.view.tablayout.NormalTabLayoutItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsMainRecommendLayout extends BaseLayout implements IGoodsLabelView, GoodsLabelDetailsLayout.CallBack, CustomTabLayout.CallBack {
    @InjectView(R.id.tablayout)
    CustomTabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;


    private List<View> listView = new ArrayList<>();
    private List<View> titleListView = new ArrayList<>();

    private GoodsMainRecommendPagerAdapter pagerAdapter;
    private IGoodsLabelPresenter goodsMainRecommendTitlePresenter;

    public GoodsMainRecommendLayout(Context context) {
        this(context, null);
    }

    public GoodsMainRecommendLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_main_recommend, attrs);
    }

    @Override
    protected void initView() {
        this.setVisibility(GONE);
    }

    @Override
    protected void initData() {
        goodsMainRecommendTitlePresenter = new GoodsLabelPresenterImpl(this);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsMainRecommendTitleSuccess(List<GoodsLabelResultBean> listData) {
        if (listData == null || listData.size() <= 0)
            return;
        this.setVisibility(VISIBLE);
        for (GoodsLabelResultBean itemData : listData) {
            NormalTabLayoutItem titleView = new NormalTabLayoutItem(getContext());
            titleView.setTitle(itemData.getLabel_name());
            titleListView.add(titleView);

            if (itemData.getId() == null)
                return;
            GoodsLabelDetailsLayout contentView = new GoodsLabelDetailsLayout(getContext());
            contentView.setLabelId(itemData.getId());
            contentView.setCallBack(this);
            contentView.startFindData();
            listView.add(contentView);
        }
        pagerAdapter = new GoodsMainRecommendPagerAdapter(getContext(), listView);
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(pagerAdapter);

        tablayout.setCallBack(this);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTablViewList(titleListView);
        tablayout.setSelect(0);
    }

    @Override
    public void getGoodsMainRecommendTitleFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void findDataSuccess(View view) {
        tablayout.setSelect(0);
    }

    @Override
    public void onTabSelected(View view, TabLayout.Tab tab) {
        GoodsLabelDetailsLayout itemView = (GoodsLabelDetailsLayout) listView.get(tab.getPosition());
        Integer allItemHeight = itemView.getAllItemHeight();
        LayoutParams layout = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, allItemHeight);
        viewpager.setLayoutParams(layout);
    }

    /**
     * 开始查找数据
     */
    public void startFindData() {
        goodsMainRecommendTitlePresenter.getRecommendTitleData();
    }
}
