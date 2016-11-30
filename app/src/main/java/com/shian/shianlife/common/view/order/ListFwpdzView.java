package com.shian.shianlife.common.view.order;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.OrderDetailActivity;
import com.shian.shianlife.activity.PgzxActivity;
import com.shian.shianlife.activity.RoutePlanActivity;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.TArrayListAdapter.IOnDrawViewEx;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpAcceptParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetOrderListResult;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ListFwpdzView extends BaseOrderView {
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<OrderListModel> adapter;

    private SwipeRefreshHelper mSwipeRefreshHelper;

    private int page = 0;
    private int pageSize = 20;
    private View v;

    public ListFwpdzView(Context context) {
        this(context, null);
    }

    public ListFwpdzView(Context context, AttributeSet attrs) {
        super(context, attrs);
        v = LayoutInflater.from(context).inflate(R.layout.view_order_common, null, false);
        addView(v);
        initView();
        initDate();
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
        OrderManagerImpl.getInstance().getOrderList(getContext(), params, 2,
                new HttpResponseHandler<HrGetOrderListResult>() {

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
                    public void onStart() {

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
        OrderManagerImpl.getInstance().getOrderList(getContext(), params, 2,
                new HttpResponseHandler<HrGetOrderListResult>() {

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
                            ToastUtils.show(getContext(), "暂无派单中订单");
                        }
                        mSwipeRefreshHelper.refreshComplete();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        mSwipeRefreshHelper.refreshComplete();
                    }
                });

    }

    private void initAdapter() {
        adapter.setLayout(R.layout.view_item_fwpdz);
        adapter.setDrawViewEx(new IOnDrawViewEx<OrderListModel>() {

            @Override
            public void OnDrawViewEx(Context aContext, final OrderListModel model, ViewGropMap view, int aIndex) {
                // 订单编号
                TextView tv_qt01 = (TextView) view.getView(R.id.tv_qt01);
                tv_qt01.setText(model.getOrderNum());
                // 经办人
                TextView tv_qt11 = (TextView) view.getView(R.id.tv_qt11);
                tv_qt11.setText(model.getCustomerName());
                // 白事顾问
                TextView tv_qt21 = (TextView) view.getView(R.id.tv_qt21);
                tv_qt21.setText(model.getTalkerName());
                // 收款员
                TextView tv_qt31 = (TextView) view.getView(R.id.tv_qt31);
                tv_qt31.setText(model.getPayeeName());
                // 治丧地址
                TextView tv_qt41 = (TextView) view.getView(R.id.tv_qt41);
                tv_qt41.setText(model.getFuneralAddress());
                View khxq = view.getView(R.id.fl_kexq);
                ImageView ivPhone1 = (ImageView) view.getView(R.id.iv_qt12);
                ImageView ivPhone2 = (ImageView) view.getView(R.id.iv_qt22);
                ImageView ivPhone3 = (ImageView) view.getView(R.id.iv_qt42);
                Utils.call(ivPhone1, model.getCustomerMobile());
                Utils.call(ivPhone2, model.getTalkerMobile());
                // Utils.call(ivPhone3, model.getCustomerMobile());
                OrderListBtnClick clickListener = new OrderListBtnClick(model, aIndex);
                View dispatch_execute = view.getView(R.id.fl_dispatch_execute);
                View over = view.getView(R.id.fl_over);
                dispatch_execute.setOnClickListener(clickListener);
                over.setOnClickListener(clickListener);
                khxq.setOnClickListener(clickListener);
                // khxq.setVisibility(View.GONE);
                //地图
                View button_map = view.getView(R.id.button_map);
                if(model.getFuneralAddress()==null){
                    button_map.setVisibility(GONE);
                }else{
                    button_map.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getContext(), RoutePlanActivity.class);
                            Log.v("this", model.getFuneralAddress());
                            intent.putExtra("RoutePlanLocation", model.getFuneralAddress());
                            getContext().startActivity(intent);
                        }
                    });
                }

            }
        });
    }

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
                case R.id.fl_dispatch_execute:
                    dispatchExecute();
                    break;
                case R.id.fl_kexq:
                    khxq();
                    break;
                case R.id.fl_over:
                    over();
                    break;
            }
        }

        /**
         * 派工执行
         */
        private void dispatchExecute() {
            // TODO Auto-generated method stub
            Intent in = new Intent(getContext(), PgzxActivity.class);
            in.putExtra("khxqtype", 1);
            in.putExtra("orderId", model.getOrderId());
            in.putExtra("consultId", model.getConsultId());
            getContext().startActivity(in);
        }

        /**
         * 客户详情
         */
        private void khxq() {
            Intent in = new Intent(getContext(), OrderDetailActivity.class);
            in.putExtra("orderId", model.getOrderId());
            in.putExtra("consultId", model.getConsultId());
            getContext().startActivity(in);
        }

        /**
         * 结束派单
         */
        private void over() {
            TipsDialog dialog = new TipsDialog(getContext());
            dialog.setTitle("请确认客户无需其他服务，结束服务后将不能再编辑订单。");
            dialog.setTopButton("继续服务", null);
            dialog.setBottomButton("结束服务", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HpAcceptParams params = new HpAcceptParams();
                    params.setOrderId(model.getOrderId());
                    MHttpManagerFactory.getAccountManager().endService(getContext(), params,
                            new HttpResponseHandler<Object>() {

                                @Override
                                public void onSuccess(Object result) {
                                    // TODO Auto-generated method stub
                                    ToastUtils.show(getContext(), "结束派单成功");
                                    refresh();
                                }

                                @Override
                                public void onStart() {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void onError(String message) {
                                    // TODO Auto-generated method stub

                                }
                            });

                }
            });
            dialog.show();
        }

    }

    public void refresh() {
        mSryt.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });
    }

}
