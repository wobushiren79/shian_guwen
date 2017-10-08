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
    private Long classId;
    private String goodsName;


    private IGoodsQueryListPresenter goodsQueryListPresenter;
    private GoodsQueryListAdapter adapter;


    private Integer pageNumber;
    private Integer pageSize;
    private String rankOrder;

    public GoodsQueryListView(Context context) {
        this(context, null);
    }

    public GoodsQueryListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pageNumber = 1;
        pageSize = 10;

        goodsQueryListPresenter = new GoodsQueryListPresenterImpl(this);
        adapter = new GoodsQueryListAdapter(getContext());
        this.setAdapter(adapter);
        this.setLayoutManager(new LinearLayoutManager(getContext()));
        this.setPtrHandler2(ptrHandler2);
    }

    public void setClassId(Long classId) {
        this.classId = classId;
        adapter.setGoodsClassId(classId);
    }

    public void setClassAttId(Long classAttId) {
        this.classAttId = classAttId;
        adapter.setGoodsClassAttrId(classAttId);
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
    public Integer getPageNumber() {
        return pageNumber;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    @Override
    public String getRankOrder() {
        return rankOrder;
    }

    @Override
    public String getGoodsName() {
        return goodsName;
    }


    @Override
    public void getGoodsQueryListDataSuccess(List<GoodsQueryListResultBean> listData) {
        setRefreshComplete();
        if (listData == null || listData.size() == 0) {
            if (pageNumber == 1)
                adapter.setData(listData);
            else {
                pageNumber--;
                pageNumber = pageNumber < 1 ? 1 : pageNumber;
            }
        } else {
            if (pageNumber == 1)
                adapter.setData(listData);
            else
                adapter.addData(listData);
        }
        hasData(adapter);
    }

    @Override
    public void getGoodsQueryListDataFail(String msg) {
        ToastUtils.show(getContext(), msg);
        setRefreshComplete();
        pageNumber = pageNumber > 1 ? pageNumber : pageNumber--;
        ToastUtils.show(getContext(), msg);
        hasData(adapter);
    }


    public void startQuery() {
        setRefresh();
    }

    public void startOrderBy(String rankOrder) {
        this.rankOrder = rankOrder;
        startQuery();
//        adapter.orderBy(orderBy, orderForm);
    }

    PtrDefaultHandler2 ptrHandler2 = new PtrDefaultHandler2() {
        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            pageNumber++;
            goodsQueryListPresenter.getGoodsQueryListData();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            pageNumber = 1;
            goodsQueryListPresenter.getGoodsQueryListData();
        }
    };

}
