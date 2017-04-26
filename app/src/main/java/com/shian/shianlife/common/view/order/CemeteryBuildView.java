package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetOrderListResult;

/**
 * Created by zm.
 */

public class CemeteryBuildView extends BaseOrderView {
    View view;
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<OrderListModel> adapter;
    private SwipeRefreshHelper mSwipeRefreshHelper;
    private int pageSize = 20;
    private int page = 1;

    public CemeteryBuildView(Context context) {
        super(context, null);
        view = View.inflate(context, R.layout.view_order_cemetery_build, this);
        initView();
        initDate();
    }

    private void initView() {
        mSryt = (SwipeRefreshLayout) view.findViewById(R.id.sryt_swipe_listview);
        mListView = (ListView) view.findViewById(R.id.lv_swipe_listview);
        mSryt.setColorSchemeColors(Color.BLUE);
        adapter = new TArrayListAdapter<OrderListModel>(getContext());
    }

    private void initDate() {
        initAdapter();
        mListView.setAdapter(adapter);
        mSwipeRefreshHelper = new SwipeRefreshHelper(mSryt);
        mSwipeRefreshHelper
                .setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
                    @Override
                    public void onfresh() {
                        loadData();
                    }
                });

        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                loadMoreData();
            }
        });
    }

    private void initAdapter() {
        adapter.setLayout(R.layout.view_item_overserver);
        adapter.setDrawViewEx(overDrawViewEx);
    }

    TArrayListAdapter.IOnDrawViewEx<OrderListModel> overDrawViewEx = new TArrayListAdapter.IOnDrawViewEx<OrderListModel>() {

        @Override
        public void OnDrawViewEx(Context aContext, OrderListModel templateItem, ViewGropMap view, int aIndex) {

        }
    };

    protected void loadMoreData() {
        page++;
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
//        OrderManagerImpl.getInstance().getOrderList(getContext(), params,
//                orderType, new HttpResponseHandler<HrGetOrderListResult>() {
//
//                    @Override
//                    public void onSuccess(HrGetOrderListResult result) {
//                        if (result != null && result.getItems() != null
//                                && result.getItems().size() > 0) {
//                            adapter.addListData(result.getItems());
//                            adapter.notifyDataSetChanged();
//                            if (result.getItems().size() >= pageSize) {
//                                mSwipeRefreshHelper.setLoadMoreEnable(true);
//                            } else {
//                                mSwipeRefreshHelper.setLoadMoreEnable(false);
//                            }
//                        } else {
//                            mSwipeRefreshHelper.loadMoreComplete(false);
//                        }
//
//                    }
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        mSwipeRefreshHelper.loadMoreComplete(true);
//                        mSwipeRefreshHelper.setLoadMoreEnable(true);
//                    }
//                });

    }

    protected void loadData() {
        // rl_order_qt0.setVisibility(View.GONE);
        // ll_order_qt0.setVisibility(View.VISIBLE);
        page = 1;
        adapter.clear();
        adapter.notifyDataSetChanged();
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
//        OrderManagerImpl.getInstance().getOrderList(getContext(), params,
//                orderType, new HttpResponseHandler<HrGetOrderListResult>() {
//
//                    @Override
//                    public void onSuccess(HrGetOrderListResult result) {
//                        if (result != null && result.getItems() != null
//                                && result.getItems().size() > 0) {
//                            adapter.addListData(result.getItems());
//                            adapter.notifyDataSetChanged();
//                            if (result.getItems().size() >= pageSize) {
//                                mSwipeRefreshHelper.setLoadMoreEnable(true);
//                            } else {
//                                mSwipeRefreshHelper.setLoadMoreEnable(false);
//                            }
//                        } else {
//                            // showNodataLayout();
//                            ToastUtils.show(getContext(), "暂无订单");
//                        }
//                        mSwipeRefreshHelper.refreshComplete();
//                    }
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onError(String message) {
//                        mSwipeRefreshHelper.refreshComplete();
//                    }
//                });

    }

    @Override
    public void refresh() {
        mSryt.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });
    }
}
