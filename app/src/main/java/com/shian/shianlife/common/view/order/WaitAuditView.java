package com.shian.shianlife.common.view.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.OrderDetailActivity;
import com.shian.shianlife.activity.PgzxActivity;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.TArrayListAdapter.IOnDrawViewEx;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.FuneralOrderManagerImpl;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetOrderListResult;

import okhttp3.Request;

/**
 * 待审核订单列表
 *
 * @author w9433
 */
@SuppressLint("InflateParams")
public class WaitAuditView extends BaseOrderView {
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<OrderListModel> adapter;

    private SwipeRefreshHelper mSwipeRefreshHelper;
    private int pageSize = 20;
    private int page = 0;
    private View v;
    private int orderType = 3;

    public WaitAuditView(Context context, String orderName) {
        super(context, null);
        v = LayoutInflater.from(context).inflate(R.layout.view_order_common, null, false);
        addView(v);
        initView();
        initDate();
    }

    public void refresh() {
        mSryt.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });
    }

    private void initView() {
        mSryt = (SwipeRefreshLayout) v.findViewById(R.id.sryt_swipe_listview);
        mListView = (ListView) v.findViewById(R.id.lv_swipe_listview);
        mSryt.setColorSchemeColors(Color.BLUE);
        adapter = new TArrayListAdapter<OrderListModel>(getContext());
    }

    private void initDate() {
        initAdapter();
        mListView.setAdapter(adapter);
        mSwipeRefreshHelper = new SwipeRefreshHelper(mSryt);
        mSwipeRefreshHelper.setOnSwipeRefreshListener(new OnSwipeRefreshListener() {
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

    protected void loadMoreData() {
        page++;
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        MHttpManagerFactory.getFuneralOrderManager().getOrderList(getContext(), params, orderType, new HttpResponseHandler<HrGetOrderListResult>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetOrderListResult result) {
                if (result != null && result.getItems() != null && result.getItems().size() > 0) {
                    adapter.addListData(result.getItems());
                    adapter.notifyDataSetChanged();
                    if (result.getItems().size() >= pageSize) {
                        mSwipeRefreshHelper.setLoadMoreEnable(true);
                    } else {
                        mSwipeRefreshHelper.setLoadMoreEnable(false);
                    }
                } else {
                    mSwipeRefreshHelper.loadMoreComplete(false);
                }

            }


            @Override
            public void onError(String message) {
                mSwipeRefreshHelper.loadMoreComplete(true);
                mSwipeRefreshHelper.setLoadMoreEnable(true);
            }
        });

    }

    protected void loadData() {
        // rl_order_qt0.setVisibility(View.GONE);
        // ll_order_qt0.setVisibility(View.VISIBLE);
        page = 0;
        adapter.clear();
        adapter.notifyDataSetChanged();
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        MHttpManagerFactory.getFuneralOrderManager().getOrderList(getContext(), params, orderType, new HttpResponseHandler<HrGetOrderListResult>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetOrderListResult result) {
                if (result != null && result.getItems() != null && result.getItems().size() > 0) {
                    adapter.addListData(result.getItems());
                    adapter.notifyDataSetChanged();
                    if (result.getItems().size() >= pageSize) {
                        mSwipeRefreshHelper.setLoadMoreEnable(true);
                    } else {
                        mSwipeRefreshHelper.setLoadMoreEnable(false);
                    }
                } else {
                    // showNodataLayout();
                    ToastUtils.show(getContext(), "暂无订单");
                }
                mSwipeRefreshHelper.refreshComplete();
            }

            @Override
            public void onError(String message) {
                mSwipeRefreshHelper.refreshComplete();
            }
        });

    }

    private void initAdapter() {
        adapter.setLayout(R.layout.view_item_waitaudit);
        adapter.setDrawViewEx(waitAuditDrawViewEx);
    }

    /**
     * 待审核
     */
    IOnDrawViewEx<OrderListModel> waitAuditDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

        @Override
        public void OnDrawViewEx(Context aContext, OrderListModel model, ViewGropMap view, int aIndex) {
            // 订单编号
            TextView tv_qt01 = (TextView) view.getView(R.id.tv_qt01);
            tv_qt01.setText(model.getOrderNum());
            // 经办人
            TextView tv_qt11 = (TextView) view.getView(R.id.tv_qt11);
            tv_qt11.setText(model.getAgentmanName());
            // 白事顾问
            TextView tv_qt21 = (TextView) view.getView(R.id.tv_qt21);
            tv_qt21.setText(model.getTalkerName());
            // 治丧指导
            TextView tv_qt31 = (TextView) view.getView(R.id.tv_qt31);
            tv_qt31.setText(model.getPerformerName());
            ImageView ivPhone1 = (ImageView) view.getView(R.id.iv_qt12);
            ImageView ivPhone2 = (ImageView) view.getView(R.id.iv_qt22);
            ImageView ivPhone3 = (ImageView) view.getView(R.id.iv_qt32);
            Utils.call(ivPhone1, model.getAgentmanLinkInfo());
            Utils.call(ivPhone2, model.getTalkerMobile());
            Utils.call(ivPhone3, model.getPerformerMobile());
            OrderListBtnClick clickListener = new OrderListBtnClick(model, aIndex);
            View audit = view.getView(R.id.fl_audit);
            View detail = view.getView(R.id.fl_detail);
            audit.setOnClickListener(clickListener);
            detail.setOnClickListener(clickListener);
        }
    };

    class OrderListBtnClick implements OnClickListener {

        OrderListModel model;
        int position;

        public OrderListBtnClick(OrderListModel model, int position) {
            this.model = model;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fl_audit:
                    audit();
                    break;
                case R.id.fl_detail:
                    detail();
                    break;
            }
        }

        private void detail() {
            // TODO Auto-generated method stub
            Intent in = new Intent(getContext(), OrderDetailActivity.class);
            in.putExtra("orderId", model.getOrderId());
            in.putExtra("consultId", model.getConsultId());
            getContext().startActivity(in);
        }

        private void audit() {
            // TODO Auto-generated method stub
            Intent in = new Intent(getContext(), PgzxActivity.class);
            in.putExtra("orderId", model.getOrderId());
            in.putExtra("isShenhe", true);
            getContext().startActivity(in);


        }
    }

}
