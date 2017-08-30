package com.shian.shianlife.view.listview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsClassAttrAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsClassAttrResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsClassAttrPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsClassAttrPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsClassAttrView;

import java.util.List;

/**
 * Created by zm.
 */

public class GoodsClassAttrListView extends RecyclerView implements IGoodsClassAttrView {
    private Long classId;//分类ID
    private GoodsClassAttrAdapter goodsClassAttrAdapter;
    private IGoodsClassAttrPresenter goodsClassAttrPresenter;

    public GoodsClassAttrListView(Context context) {
        this(context, null);
    }

    public GoodsClassAttrListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        goodsClassAttrAdapter = new GoodsClassAttrAdapter(context);
        goodsClassAttrPresenter = new GoodsClassAttrPresenterImpl(this);

        this.setAdapter(goodsClassAttrAdapter);
        this.setLayoutManager(new GridLayoutManager(context, 3));
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public void getData() {
        goodsClassAttrPresenter.getGoodsClassAttrData();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsClassAttrDataSuccess(List<GoodsClassAttrResultBean> listData) {
        goodsClassAttrAdapter.setData(listData);
    }

    @Override
    public void getGoodsClassAttrDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public Long getGoodsClassId() {
        return classId;
    }

}
