package com.shian.shianlife.common.view.order;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.TArrayListAdapter.IOnDrawViewEx;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetOrderListResult;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class CommonView extends BaseOrderView {
	private SwipeRefreshLayout mSryt;
	private ListView mListView;
	private TArrayListAdapter<OrderListModel> adapter;

	private SwipeRefreshHelper mSwipeRefreshHelper;
	private int pageSize = 20;
	private int page = 0;
	private View v;
	private int orderType = -1;

	public CommonView(Context context, String orderName) {
		super(context, null);
		v = LayoutInflater.from(context).inflate(R.layout.view_order_common, null, false);
		selectOrderTypeByName(orderName);
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

	private void selectOrderTypeByName(String orderName) {
		if ("待服务".equals(orderName)) {
			orderType = 1;
		} else if ("待审核".equals(orderName)) {
			orderType = 3;
		} else if ("待收款".equals(orderName)) {
			orderType = 4;
		} else if ("成功服务".equals(orderName)) {
			orderType = 5;
		}

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
				if (orderType != -1) {
					loadData();
				} else {
					mSwipeRefreshHelper.refreshComplete();
					ToastUtils.show(getContext(), "无该订单类型！");
				}
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
		OrderManagerImpl.getInstance().getOrderList(getContext(), params, orderType,
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
		OrderManagerImpl.getInstance().getOrderList(getContext(), params, orderType,
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
							ToastUtils.show(getContext(), "暂无订单");
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
		switch (orderType) {
		case 1:
			adapter.setLayout(R.layout.view_item_waitservice);
			adapter.setDrawViewEx(waitServiceDrawViewEx);
			break;
		case 3:
			adapter.setLayout(R.layout.view_item_waitaudit);
			adapter.setDrawViewEx(waitAuditDrawViewEx);
			break;
		case 4:
			adapter.setLayout(R.layout.view_item_waitmoney);
			adapter.setDrawViewEx(waitMoneyDrawViewEx);
			break;
		case 5:
			adapter.setLayout(R.layout.view_item_overserver);
			adapter.setDrawViewEx(overDrawViewEx);
			break;
		}

	}

	/**
	 * 服务完成
	 */
	IOnDrawViewEx<OrderListModel> overDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

		@Override
		public void OnDrawViewEx(Context aContext, OrderListModel model, ViewGropMap view, int aIndex) {
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

		}
	};

	/**
	 * 待付款
	 */
	IOnDrawViewEx<OrderListModel> waitMoneyDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

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
				case R.id.fl_executelist:
					executelist();
					break;
				case R.id.fl_detail:
					detail();
					break;
				case R.id.fl_pay:
					pay();
					break;
				}
			}

			private void pay() {
				// TODO Auto-generated method stub
			}

			private void detail() {
				// TODO Auto-generated method stub

			}

			private void executelist() {
				// TODO Auto-generated method stub

			}
		}

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

			OrderListBtnClick clickListener = new OrderListBtnClick(model, aIndex);
			View executelist = view.getView(R.id.fl_executelist);
			View detail = view.getView(R.id.fl_detail);
			View pay = view.getView(R.id.fl_pay);
			executelist.setOnClickListener(clickListener);
			detail.setOnClickListener(clickListener);
			pay.setOnClickListener(clickListener);
		}
	};

	/**
	 * 待审核
	 */
	IOnDrawViewEx<OrderListModel> waitAuditDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

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

			}

			private void audit() {
				// TODO Auto-generated method stub

			}
		}

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

			OrderListBtnClick clickListener = new OrderListBtnClick(model, aIndex);
			View audit = view.getView(R.id.fl_audit);
			View detail = view.getView(R.id.fl_detail);
			audit.setOnClickListener(clickListener);
			detail.setOnClickListener(clickListener);
		}
	};

	/**
	 * 待服务
	 */
	IOnDrawViewEx<OrderListModel> waitServiceDrawViewEx = new IOnDrawViewEx<OrderListModel>() {

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
					accept();
					break;
				case R.id.fl_refuse:
					refuse();
					break;
				case R.id.fl_create:
					execute();
					break;
				case R.id.fl_close:
					detail();
					break;
				}
			}

			/**
			 * 订单详情
			 */
			private void detail() {
				// TODO Auto-generated method stub

			}

			/**
			 * 执行服务
			 */
			private void execute() {
				// TODO Auto-generated method stub

			}

			/**
			 * 拒单
			 */
			private void refuse() {
				// TODO Auto-generated method stub

			}

			/**
			 * 接单
			 */
			private void accept() {
				// TODO Auto-generated method stub

			}

		}

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
			// 逝者所在地
			TextView tv_qt31 = (TextView) view.getView(R.id.tv_qt31);
			tv_qt31.setText(model.getUsageCurAddress());

			OrderListBtnClick clickListener = new OrderListBtnClick(model, aIndex);
			View accept = view.getView(R.id.fl_accept);
			View refues = view.getView(R.id.fl_refuse);
			View execute = view.getView(R.id.fl_execute);
			View detail = view.getView(R.id.fl_detail);
			if (model.isShowAcceptOrReject()) {
				accept.setVisibility(View.VISIBLE);
				refues.setVisibility(View.VISIBLE);
				accept.setOnClickListener(clickListener);
				refues.setOnClickListener(clickListener);
			} else {
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

}
