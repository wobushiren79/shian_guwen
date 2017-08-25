package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

import com.shian.shianlife.adapter.GoodsClassAttrMainAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrMainResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassAttrMainPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsClassAttrMainPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsClassAttrMainView;
import com.shian.shianlife.view.ScrollRecyclerView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassAttrMainListView extends ScrollRecyclerView implements IGoodsClassAttrMainView {

    private IGoodsClassAttrMainPresenter goodsClassAttrMainPresenter;
    private GoodsClassAttrMainAdapter attrMainAdapter;

    public GoodsClassAttrMainListView(Context context) {
        this(context, null);
    }

    public GoodsClassAttrMainListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        attrMainAdapter = new GoodsClassAttrMainAdapter(context);

        goodsClassAttrMainPresenter = new GoodsClassAttrMainPresenterImpl(this);
        goodsClassAttrMainPresenter.getGoodsClassAttrMainData();


        this.setLayoutManager(new GridLayoutManager(context, 4));
        this.setAdapter(attrMainAdapter);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsClassAttrMainDataSuccess(List<GoodsClassAttrMainResultBean> data) {
        attrMainAdapter.setData(data);
    }

    @Override
    public void getGoodsClassAttrMainDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }
}
