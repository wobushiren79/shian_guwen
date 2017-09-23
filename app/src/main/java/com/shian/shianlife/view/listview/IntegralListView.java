package com.shian.shianlife.view.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.shian.shianlife.adapter.IntegralListAdapter;
import com.shian.shianlife.base.BasePtrRecyclerView;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListResultBean;
import com.shian.shianlife.mvp.userinfo.presenter.IUserInfoIntegralListPresenter;
import com.shian.shianlife.mvp.userinfo.presenter.impl.UserInfoIntegralListPresenterImpl;
import com.shian.shianlife.mvp.userinfo.view.IUserInfoIntegralListView;

import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by zm.
 */

public class IntegralListView extends BasePtrRecyclerView implements IUserInfoIntegralListView {

    private IntegralListAdapter listAdapter;
    private IUserInfoIntegralListPresenter userInfoIntegralListPresenter;

    private Integer pageSize;
    private Integer pageNumber;

    public IntegralListView(Context context) {
        super(context);
    }

    public IntegralListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        pageSize = 10;
        pageNumber = 1;

        listAdapter = new IntegralListAdapter(context);
        userInfoIntegralListPresenter = new UserInfoIntegralListPresenterImpl(this);

        setLayoutManager(new LinearLayoutManager(context));
        setPtrHandler2(handler2);
        setAdapter(listAdapter);
    }

    PtrDefaultHandler2 handler2 = new PtrDefaultHandler2() {
        @Override
        public void onLoadMoreBegin(PtrFrameLayout frame) {
            pageNumber++;
            startFindData();
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {
            pageNumber = 1;
            startFindData();
        }
    };

    public void startFindData() {
        userInfoIntegralListPresenter.getIntegralList();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public void getIntegralListSuccess(UserInfoIntegralListResultBean resultBean) {
        ptrLayout.refreshComplete();
        if (resultBean.getContent() != null)
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
    public void getIntegralListFail(String msg) {
        ToastUtils.show(getContext(), msg);
        ptrLayout.refreshComplete();
        pageNumber = pageNumber > 0 ? pageNumber : pageNumber--;
    }

    @Override
    public Integer getPageNumber() {
        return pageNumber;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }
}
