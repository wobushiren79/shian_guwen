package com.shian.shianlife.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.view.ptr.CustomPtrFramelayout;

import in.srain.cube.views.ptr.PtrDefaultHandler2;

/**
 * Created by zm.
 */

public class BasePtrRecyclerView extends BaseLayout {
    private RecyclerView recyclerview;
    private CustomPtrFramelayout ptrLayout;
    private LinearLayout llTitle;

    public static final int Show_Mode_NoData = 0;
    public static final int Show_Mode_HasData = 1;

    public BasePtrRecyclerView(Context context) {
        this(context, null);
    }

    public BasePtrRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_base_ptr_recyclerview, attrs);
    }

    @Override
    protected void initView() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        ptrLayout = (CustomPtrFramelayout) findViewById(R.id.ptr_layout);
        llTitle = (LinearLayout) findViewById(R.id.ll_title);
    }

    @Override
    protected void initData() {

    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerview.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerview.setAdapter(adapter);
    }

    public void setRefresh() {
        ptrLayout.autoRefresh();
    }

    public void setRefreshComplete() {
        ptrLayout.refreshComplete();

    }

    public void setPtrHandler2(PtrDefaultHandler2 ptrHandler) {
        ptrLayout.setPtrHandler(ptrHandler);
    }

    public void setShowMode(int showMode) {
        switch (showMode) {
            case Show_Mode_NoData:
                llTitle.setVisibility(VISIBLE);
                break;
            case Show_Mode_HasData:
                llTitle.setVisibility(GONE);
                break;
        }
    }
}
