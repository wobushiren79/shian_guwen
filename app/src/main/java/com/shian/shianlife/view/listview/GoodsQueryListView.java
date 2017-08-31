package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.shian.shianlife.adapter.GoodsQueryListAdapter;
import com.shian.shianlife.base.BasePtrRecyclerView;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsQueryListResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsQueryListPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsQueryListPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsQueryListView;
import com.shian.shianlife.thisenum.OrderByEnum;

import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zm.
 */

public class GoodsQueryListView extends BasePtrRecyclerView implements IGoodsQueryListView {
    private Long classAttId;
    private String goodsName;
    private int orderForm;
    private OrderByEnum orderBy;

    private IGoodsQueryListPresenter goodsQueryListPresenter;
    private GoodsQueryListAdapter adapter;

    public GoodsQueryListView(Context context) {
        this(context, null);
    }

    public GoodsQueryListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        orderForm = GoodsQueryListAdapter.Order_Form_Sale;
        orderBy = OrderByEnum.DESC;

        goodsQueryListPresenter = new GoodsQueryListPresenterImpl(this);
        adapter = new GoodsQueryListAdapter(getContext());
        this.setAdapter(adapter);
        this.setLayoutManager(new LinearLayoutManager(getContext()));
        this.setPtrHandler2(ptrHandler2);
    }

    public void setClassAttId(Long classAttId) {
        this.classAttId = classAttId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
        setRefreshComplete();
    }

    @Override
    public Long getClassAttrId() {
        return classAttId;
    }

    @Override
    public Integer getChannelId() {
        return AppContansts.goodsChannelId;
    }

    @Override
    public String getGoodsName() {
        return goodsName;
    }

    @Override
    public void getGoodsQueryListDataSuccess(List<GoodsQueryListResultBean> listData) {
        adapter.setData(listData);
        startOrderBy(orderForm, orderBy);
        setRefreshComplete();
        hasData();
    }

    @Override
    public void getGoodsQueryListDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
        setRefreshComplete();
        hasData();
    }

    /**
     * 设置是否显示无数据
     */
    private void hasData() {
        if (adapter.getData().size() == 0) {
            this.setShowMode(Show_Mode_NoData);
        } else {
            this.setShowMode(Show_Mode_HasData);
        }
    }

    public void startQuery() {
        setRefresh();
    }

    public void startOrderBy(int orderForm, OrderByEnum orderBy) {
        this.orderForm = orderForm;
        this.orderBy = orderBy;
        adapter.orderBy(orderBy, orderForm);
    }

    PtrDefaultHandler2 ptrHandler2 = new PtrDefaultHandler2() {
        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            goodsQueryListPresenter.getGoodsQueryListData();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            goodsQueryListPresenter.getGoodsQueryListData();
        }
    };

}
