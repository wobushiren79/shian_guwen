package com.shian.shianlife.common.view.order;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.OrderDetailActivity;
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import okhttp3.Request;

/**
 * 服务结束列表
 *
 * @author w9433
 */
@SuppressLint("InflateParams")
public class OverServiceView extends BaseOrderView {
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<OrderListModel> adapter;

    private SwipeRefreshHelper mSwipeRefreshHelper;
    private int pageSize = 20;
    private int page = 0;
    private View v;
    private int orderType = 5;

    public OverServiceView(Context context, String orderName) {
        super(context, null);
        v = LayoutInflater.from(context).inflate(R.layout.view_order_common,
                null, false);
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
        mSwipeRefreshHelper
                .setOnSwipeRefreshListener(new OnSwipeRefreshListener() {
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
                if (result != null && result.getItems() != null
                        && result.getItems().size() > 0) {
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
                if (result != null && result.getItems() != null
                        && result.getItems().size() > 0) {
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
        adapter.setLayout(R.layout.view_item_overserver);
        adapter.setDrawViewEx(overDrawViewEx);
    }

    /**
     * 服务完成
     */
    IOnDrawViewEx<OrderListModel> overDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

        @Override
        public void OnDrawViewEx(Context aContext, final OrderListModel model,
                                 ViewGropMap view, int aIndex) {
            View detail = view.getView(R.id.fl_detail);
            LinearLayout llDetail = (LinearLayout) view
                    .getView(R.id.ll_over_detail);
            View rlKuName = view
                    .getView(R.id.rl_qt00);
            // 客户姓名
            TextView tv_qtKUname = (TextView) view.getView(R.id.tv_qt010);
            tv_qtKUname.setText(model.getOrderNum());
            ImageView ivPhone0 = (ImageView) view.getView(R.id.iv_qt020);
            ImageView ivPhone1 = (ImageView) view.getView(R.id.iv_qt12);
            ImageView ivPhone2 = (ImageView) view.getView(R.id.iv_qt22);
            ImageView ivPhone3 = (ImageView) view.getView(R.id.iv_qt32);
            ivPhone0.setVisibility(View.GONE);
            ivPhone1.setVisibility(View.GONE);
            ivPhone2.setVisibility(View.GONE);
            ivPhone3.setVisibility(View.GONE);

            TextView tv_qt10 = (TextView) view.getView(R.id.tv_qt10);
            TextView tv_qt20 = (TextView) view.getView(R.id.tv_qt20);
            TextView tv_qt30 = (TextView) view.getView(R.id.tv_qt30);
            // 订单编号
            TextView tv_qt01 = (TextView) view.getView(R.id.tv_qt01);
            tv_qt01.setText(model.getOrderNum());
            // 治丧地址
            TextView tv_qt11 = (TextView) view.getView(R.id.tv_qt11);
            tv_qt11.setText(model.getFuneralAddress());
            // 服务时间
            TextView tv_qt21 = (TextView) view.getView(R.id.tv_qt21);
            tv_qt21.setText(model.getStartTime() + "至" + model.getEndTime());
            // 服务满意度
            TextView tv_qt31 = (TextView) view.getView(R.id.tv_qt31);
            tv_qt31.setText(model.getSatTotal() + "");

            TextView tv_qt02 = (TextView) view.getView(R.id.tv_qt02);
            String state = "";
            int orderState = model.getOrderStatus();
            int moneyState = model.getRestMoneyStatus();
            int financeStatus = model.getFinanceStatus();
            llDetail.setVisibility(View.GONE);
            rlKuName.setVisibility(View.GONE);
            if (orderState == 7) {
                tv_qt10.setText("治丧地址：");
                tv_qt11.setText(model.getFuneralAddress());
                tv_qt20.setText("服务时间：");
                tv_qt21.setText(model.getStartTime() + "至"
                        + model.getEndTime());
                tv_qt30.setText("服务满意度");
                tv_qt31.setText(model.getSatTotal() + "");
                state = "服务完成";
            } else if (orderState == 5 && financeStatus == 2 && model.isAuditPass()) {
                tv_qt10.setText("治丧地址：");
                tv_qt11.setText(model.getFuneralAddress());
                tv_qt20.setText("服务开始时间：");
                tv_qt21.setText(model.getStartTime());
                tv_qt30.setText("服务结束时间：");
                tv_qt31.setText(model.getEndTime());
                state = "待回访";
            } else {
                rlKuName.setVisibility(View.VISIBLE);
                ivPhone0.setVisibility(View.VISIBLE);
                ivPhone1.setVisibility(View.VISIBLE);
                ivPhone2.setVisibility(View.VISIBLE);
                ivPhone3.setVisibility(View.VISIBLE);
                llDetail.setVisibility(View.VISIBLE);
                tv_qtKUname.setText(model.getCustomerName());
                tv_qt10.setText("经办人：");
                tv_qt11.setText(model.getAgentmanName());
                tv_qt20.setText("白事顾问：");
                tv_qt21.setText(model.getTalkerName());
                tv_qt30.setText("治丧指导");
                tv_qt31.setText(model.getPerformerName());
                Utils.call(ivPhone0, model.getCustomerMobile());
                Utils.call(ivPhone1, model.getAgentmanLinkInfo());
                Utils.call(ivPhone2, model.getTalkerMobile());
                Utils.call(ivPhone3, model.getPerformerMobile());
                detail.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        Intent in = new Intent(getContext(),
                                OrderDetailActivity.class);
                        in.putExtra("orderId", model.getOrderId());
                        in.putExtra("consultId", model.getConsultId());
                        getContext().startActivity(in);
                    }
                });
                switch (model.getOrderStatus()) {
                    case 5:
                        state = "结束派单";
                        break;
                    case 6:
                        state = "已确认";
                        break;
                    case 7:
                        state = "服务完成";
                        break;

                    default:
                        break;
                }

                switch (model.getRestMoneyStatus()) {
                    case 1:
                        state += "\n未收款";
                        break;
                    case 2:
                        state += "\n款项未确认";
                        break;
                    case 3:
                        state += "\n款项已确认";
                        break;

                    default:
                        break;
                }
            }

            tv_qt02.setText(state);
        }
    };


}
