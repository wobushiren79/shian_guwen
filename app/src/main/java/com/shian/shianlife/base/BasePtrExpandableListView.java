package com.shian.shianlife.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.base.BaseExpandableAdapter;
import com.shian.shianlife.view.ptr.CustomPtrFramelayout;

import in.srain.cube.views.ptr.PtrDefaultHandler2;

/**
 * Created by zm.
 */

public class BasePtrExpandableListView extends BaseLayout {
    protected ExpandableListView expandableListView;
    protected CustomPtrFramelayout ptrLayout;
    protected LinearLayout llTitle;

    public static final int Show_Mode_NoData = 0;
    public static final int Show_Mode_HasData = 1;

    public BasePtrExpandableListView(Context context) {
        this(context, null);
    }

    public BasePtrExpandableListView(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_base_ptr_expandable, attrs);
    }

    @Override
    protected void initView() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_listview);
        expandableListView.setGroupIndicator(null);
        ptrLayout = (CustomPtrFramelayout) findViewById(R.id.ptr_layout);
        llTitle = (LinearLayout) findViewById(R.id.ll_title);
    }

    @Override
    protected void initData() {

    }


    public void setAdapter(BaseExpandableAdapter adapter) {
        expandableListView.setAdapter(adapter);
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
