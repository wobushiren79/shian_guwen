package com.shian.shianlife.common.view.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.OrderDetailActivity;
import com.shian.shianlife.activity.map.NewRoutePlanOtherActivity;
import com.shian.shianlife.activity.updata.WaitServiceDataActivity;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.TArrayListAdapter.IOnDrawViewEx;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.FuneralOrderManagerImpl;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpAcceptParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.params.HpRejectParams;
import com.shian.shianlife.provide.result.HrGetOrderListResult;

import okhttp3.Request;

/**
 * 待服务订单列表
 *
 * @author w9433
 */
@SuppressLint("InflateParams")
public class WaitServiceView extends BaseOrderView {
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<OrderListModel> adapter;

    private SwipeRefreshHelper mSwipeRefreshHelper;
    private int pageSize = 20;
    private int page = 0;
    private View v;
    private int orderType = 1;

    public WaitServiceView(Context context, String orderName) {
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
        adapter.setLayout(R.layout.view_item_waitservice);
        adapter.setDrawViewEx(waitServiceDrawViewEx);
    }

    /**
     * 待服务
     */
    IOnDrawViewEx<OrderListModel> waitServiceDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

        @Override
        public void OnDrawViewEx(Context aContext, final OrderListModel model,
                                 ViewGropMap view, int aIndex) {
            // 订单编号
            TextView tv_qt01 = (TextView) view.getView(R.id.tv_qt01);
            tv_qt01.setText(model.getOrderNum());
            // 经办人
            TextView tv_qt11 = (TextView) view.getView(R.id.tv_qt11);
            TextView tv_qt10 = (TextView) view.getView(R.id.tv_qt10);

            // 白事顾问
            TextView tv_qt21 = (TextView) view.getView(R.id.tv_qt21);
            tv_qt21.setText(model.getTalkerName());
            // 逝者所在地
            TextView tv_qt31 = (TextView) view.getView(R.id.tv_qt31);
            ImageView ivMap = (ImageView) view.getView(R.id.iv_map);
            if (model.getUsageCurAddress() == null) {
                tv_qt31.setText("");
                ivMap.setVisibility(GONE);
            } else {
                tv_qt31.setText(model.getUsageCurAddress());
                ivMap.setVisibility(VISIBLE);
                ivMap.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), NewRoutePlanOtherActivity.class);
                        intent.putExtra("LocationType", 3);
                        intent.putExtra("ConsultId", model.getConsultId());
                        intent.putExtra("RoutePlanLocation", model.getUsageCurAddress());
                        getContext().startActivity(intent);
                    }
                });
            }

            TextView tv_qtbz = (TextView) view.getView(R.id.tv_qt_bz);
            ImageView ivPhone1 = (ImageView) view.getView(R.id.iv_qt12);
            ImageView ivPhone2 = (ImageView) view.getView(R.id.iv_qt22);
            ImageView ivPhone3 = (ImageView) view.getView(R.id.iv_qt32);

            Utils.call(ivPhone1, model.getAgentmanLinkInfo());
            Utils.call(ivPhone2, model.getTalkerMobile());
//			Utils.call(ivPhone3, model.getCustomerMobile());
            tv_qtbz.setVisibility(View.GONE);
            OrderListBtnClick clickListener = new OrderListBtnClick(model,
                    aIndex);
            View accept = view.getView(R.id.fl_accept);
            View refues = view.getView(R.id.fl_refuse);
            View execute = view.getView(R.id.fl_execute);
            View detail = view.getView(R.id.fl_detail);
            if (model.isShowAcceptAndReject()) {
                tv_qt10.setText("经办人：");
                tv_qt11.setText(model.getAgentmanName());
                Utils.call(ivPhone1, model.getAgentmanLinkInfo());
                ((ViewGroup) tv_qt01.getParent()).setVisibility(View.GONE);
                ((ViewGroup) tv_qt11.getParent()).setVisibility(View.GONE);
                ((ViewGroup) tv_qt21.getParent()).setVisibility(View.GONE);
                tv_qtbz.setVisibility(View.GONE);
                accept.setVisibility(View.VISIBLE);
                refues.setVisibility(View.VISIBLE);
                accept.setOnClickListener(clickListener);
                refues.setOnClickListener(clickListener);
            } else {
                tv_qtbz.setVisibility(View.VISIBLE);
                tv_qt10.setText("客户姓名：");
                tv_qtbz.setText("备注：" + model.getUsageNote());
                tv_qt11.setText(model.getCustomerName());
                Utils.call(ivPhone1, model.getCustomerMobile());
                ((ViewGroup) tv_qt01.getParent()).setVisibility(View.VISIBLE);
                ((ViewGroup) tv_qt11.getParent()).setVisibility(View.VISIBLE);
                ((ViewGroup) tv_qt21.getParent()).setVisibility(View.VISIBLE);
                accept.setVisibility(View.GONE);
                refues.setVisibility(View.GONE);
            }
            if (model.isShowStartService()) {
                execute.setVisibility(View.VISIBLE);
                detail.setVisibility(View.VISIBLE);
                execute.setOnClickListener(clickListener);
                detail.setOnClickListener(clickListener);
            } else {
                execute.setVisibility(View.GONE);
                detail.setVisibility(View.GONE);
            }

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
                case R.id.fl_accept:
                    accept(model.getAssignId(), model.getOrderId());
                    break;
                case R.id.fl_refuse:
                    refuse();
                    break;
                case R.id.fl_execute:
                    execute();
                    break;
                case R.id.fl_detail:
                    detail();
                    break;
            }
        }

        /**
         * 订单详情
         */
        private void detail() {
            // TODO Auto-generated method stub
            Intent in = new Intent(getContext(), OrderDetailActivity.class);
            in.putExtra("orderId", model.getOrderId());
            in.putExtra("consultId", model.getConsultId());
            getContext().startActivity(in);
        }

        /**
         * 执行服务
         */
        private void execute() {
            // TODO Auto-generated method stub
            Intent intent = new Intent(getContext(), WaitServiceDataActivity.class);
            intent.putExtra("orderId", model.getOrderId());
            intent.putExtra("consultId", model.getConsultId());
            getContext().startActivity(intent);
//            TipsDialog dialog = new TipsDialog(getContext());
//            dialog.setTitle("请确认使用者状态为已去世，开始执行任务后，客户服务将不可取消。");
//            dialog.setTopButton("未去世", null);
//            dialog.setBottomButton("确认去世", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    if (model.isHasPrepay()) {
//                        HpAcceptParams params = new HpAcceptParams();
//                        params.setOrderId(model.getOrderId());
//                        MHttpManagerFactory.getAccountManager().startService(getContext(),
//                                params, new HttpResponseHandler<Object>() {
//
//                                    @Override
//                                    public void onSuccess(Object result) {
//                                        // TODO Auto-generated method stub
//                                        ToastUtils.show(getContext(), "开始执行成功");
//                                        refresh();
//                                    }
//
//                                    @Override
//                                    public void onStart() {
//                                        // TODO Auto-generated method stub
//
//                                    }
//
//                                    @Override
//                                    public void onError(String message) {
//                                        // TODO Auto-generated method stub
//
//                                    }
//                                });
//                    } else {
//                        dialog.cancel();
//                        Toast.makeText(getContext(), "定金未支付，不能开始执行", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//            });
//            dialog.show();

        }

        /**
         * 拒单
         */
        private void refuse() {
            // TODO Auto-generated method stub
            HpRejectParams params = new HpRejectParams();
            params.setId(model.getAssignId());
            params.setOrderId(model.getOrderId());
            MHttpManagerFactory.getFuneralManager().rejectZ(getContext(), params, new HttpResponseHandler<Object>() {

                @Override
                public void onStart(Request request, int id) {

                }

                @Override
                public void onSuccess(Object result) {
                    // TODO Auto-generated method stub
                    ToastUtils.show(getContext(), "拒单成功");
                    refresh();
                }


                @Override
                public void onError(String message) {
                    // TODO Auto-generated method stub

                }
            });
        }

        /**
         * 接单
         */
        private void accept(long id, long orderID) {
            // TODO Auto-generated method stub
            HpAcceptParams params = new HpAcceptParams();
            params.setId(id);
            params.setOrderId(orderID);
            params.setTalkGpsAddress("北京-北京-东城区");
            MHttpManagerFactory.getFuneralManager().acceptZ(getContext(), params, new HttpResponseHandler<Object>() {

                @Override
                public void onStart(Request request, int id) {

                }

                @Override
                public void onSuccess(Object result) {
                    // TODO Auto-generated method stub
                    ToastUtils.show(getContext(), "接单成功");
                    refresh();
                }


                @Override
                public void onError(String message) {
                    // TODO Auto-generated method stub

                }
            });
        }

    }

}
