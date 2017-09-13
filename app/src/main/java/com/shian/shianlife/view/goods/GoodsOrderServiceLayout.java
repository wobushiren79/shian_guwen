package com.shian.shianlife.view.goods;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.GoodsOrderListAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderListResultBean;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderListPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderListPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderListView;
import com.shian.shianlife.view.ptr.CustomPtrFramelayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zm.
 */

public class GoodsOrderServiceLayout extends LinearLayout implements IGoodsOrderListView {
    View view;

    private RecyclerView rcContent;
    private CustomPtrFramelayout ptrLayout;

    private String title;
    private Integer payStatus;
    private Integer[] orderStatus;

    private int pageSize = 10;
    private int pageNumber = 1;


    private IGoodsOrderListPresenter goodsOrderListPresenter;
    private GoodsOrderListAdapter listAdapter;

    public GoodsOrderServiceLayout(Context context, Integer[] orderStatus, Integer payStatus) {
        super(context);
        view = View.inflate(context, R.layout.layout_goods_order_service, this);
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
        initView();
        initData();

    }


    private void initView() {
        rcContent = (RecyclerView) view.findViewById(R.id.rc_content);
        ptrLayout = (CustomPtrFramelayout) view.findViewById(R.id.ptr_layout);

        listAdapter = new GoodsOrderListAdapter(getContext());
        rcContent.setAdapter(listAdapter);
        rcContent.setLayoutManager(new LinearLayoutManager(getContext()));
        ptrLayout.setPtrHandler(ptrDefaultHandler2);
    }

    private void initData() {
        goodsOrderListPresenter = new GoodsOrderListPresenterImpl(this);
                                 /* 延时100秒,自动刷新 */
        ptrLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrLayout.autoRefresh();
            }
        }, 100);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getGoodsOrderListDataSuccess(GoodsOrderListResultBean resultBean) {
        ptrLayout.refreshComplete();
        if (resultBean.getPageNumber() < pageNumber && pageNumber > 1) {
            pageNumber--;
        } else {
            if (pageNumber == 1) {
                listAdapter.setData(resultBean.getContent());
            } else {
                listAdapter.addData(resultBean.getContent());
            }
        }
    }

    @Override
    public void getGoodsOrderListDataFail(String resultBean) {
        ptrLayout.refreshComplete();
        pageNumber = pageNumber > 0 ? pageNumber : pageNumber--;
    }

    @Override
    public List<Integer> getOrderStatus() {
        if (orderStatus == null) {
            return null;
        } else {
            return Arrays.asList(orderStatus);
        }
    }

    @Override
    public Integer getPayStatus() {
        return payStatus;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    PtrDefaultHandler2 ptrDefaultHandler2 = new PtrDefaultHandler2() {
        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            pageNumber++;
            goodsOrderListPresenter.getGoodsOrderListData();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            pageNumber = 1;
            goodsOrderListPresenter.getGoodsOrderListData();
        }
    };
}
