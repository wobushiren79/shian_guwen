package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.newcemetery.InfoDetailsActivity;
import com.shian.shianlife.activity.newcemetery.TalkFailActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.CemeteryOrderManagerImpl;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.model.CemeteryOrderModel;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;
import com.shian.shianlife.provide.result.HrGetOrderListResult;
import com.shian.shianlife.thisenum.BuildOrderEnum;
import com.shian.shianlife.thisenum.CemeteryBeSpeakStateEnum;
import com.shian.shianlife.view.popupbutton.PopupButton;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class CemeteryBuildView extends BaseOrderView {
    View view;

    private PopupButton mPopupButton;
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<CemeteryOrderModel> adapter;
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
        mPopupButton = (PopupButton) view.findViewById(R.id.popupbutton);
        mSryt.setColorSchemeColors(Color.BLUE);
        adapter = new TArrayListAdapter<CemeteryOrderModel>(getContext());

        initPopupButton();
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
        adapter.setLayout(R.layout.item_cemetery_build_list);
        adapter.setDrawViewEx(overDrawViewEx);
    }

    /**
     * 初始化popup
     */
    private void initPopupButton() {
        final List<BuildOrderEnum> buildButtons = new ArrayList<>();
        buildButtons.add(BuildOrderEnum.GM);

        for (int i = 0; i < buildButtons.size(); i++) {
            mPopupButton.addHorizontalButton(buildButtons.get(i).getName(), buildButtons.get(i).getIconId(), i);
        }

        mPopupButton.setCallBack(new PopupButton.PopupButtonCallBack() {
            @Override
            public void onClick(int positionButton) {
                for (int i = 0; i < buildButtons.size(); i++) {
                    if (positionButton == i) {
                        mPopupButton.mainButton();
                        Intent intent = new Intent(getContext(), buildButtons.get(i).getActivity());
                        getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    TArrayListAdapter.IOnDrawViewEx<CemeteryOrderModel> overDrawViewEx = new TArrayListAdapter.IOnDrawViewEx<CemeteryOrderModel>() {

        @Override
        public void OnDrawViewEx(Context aContext, final CemeteryOrderModel templateItem, ViewGropMap view, int position) {
            TextView tvState = (TextView) view.getView(R.id.tv_state);
            TextView tv1_title = (TextView) view.getView(R.id.tv_1_title);
            TextView tv1_content = (TextView) view.getView(R.id.tv_1_content);
            TextView tv2_title = (TextView) view.getView(R.id.tv_2_title);
            TextView tv2_content = (TextView) view.getView(R.id.tv_2_content);
            TextView tv3_title = (TextView) view.getView(R.id.tv_3_title);
            TextView tv3_content = (TextView) view.getView(R.id.tv_3_content);
            TextView tv4_title = (TextView) view.getView(R.id.tv_4_title);
            TextView tv4_content = (TextView) view.getView(R.id.tv_4_content);
            TextView tv5_title = (TextView) view.getView(R.id.tv_5_title);
            TextView tv5_content = (TextView) view.getView(R.id.tv_5_content);
            TextView tv6_title = (TextView) view.getView(R.id.tv_6_title);
            TextView tv6_content = (TextView) view.getView(R.id.tv_6_content);
            TextView tv7_title = (TextView) view.getView(R.id.tv_7_title);
            TextView tv7_content = (TextView) view.getView(R.id.tv_7_content);

            LinearLayout ll_info_more = (LinearLayout) view.getView(R.id.ll_info_more);
            TextView tv_info_more = (TextView) view.getView(R.id.tv_info_more);

            TextView tvTalkDetails = (TextView) view.getView(R.id.tv_talkdetails);
            TextView tvOrderDetails = (TextView) view.getView(R.id.tv_orderdetails);

            int orderState = position;

            switch (orderState) {
                case 0:
                    tv1_title.setText("客户姓名");
                    tv2_title.setText("联系电话");
                    tv3_title.setText("预约时间");
                    tv4_title.setText("客户地址");
                    tv5_title.setText("参观公墓");
                    tv6_title.setText("交通方式");
                    tv7_title.setText("人数");

                    ll_info_more.setVisibility(VISIBLE);
                    tv_info_more.setVisibility(VISIBLE);
                    break;
                case 1:
                    tv1_title.setText("客户姓名");
                    tv2_title.setText("联系电话");
                    tv3_title.setText("预约时间");
                    tv4_title.setText("客户地址");
                    tv5_title.setText("参观公墓");
                    tv6_title.setText("公墓接待");

                    ll_info_more.setVisibility(GONE);
                    tv_info_more.setVisibility(GONE);
                    break;
                case 2:
                    tv1_title.setText("客户姓名");
                    tv2_title.setText("联系电话");
                    tv3_title.setText("客户地址");
                    tv4_title.setText("定墓日期");
                    tv5_title.setText("完款日期");
                    tv6_title.setText("公墓名称");

                    ll_info_more.setVisibility(GONE);
                    tv_info_more.setVisibility(GONE);
                    break;
                default:
                    break;
            }


//            setState(tvState, templateItem);
//            makePhone(ivPhone,templateItem);

            tvTalkDetails.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    talkFail(templateItem);
                }
            });
            tvOrderDetails.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderInfo(templateItem);
                }
            });
        }
    };

    /**
     * 加载更多
     */
    protected void loadMoreData() {
        page++;
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);

        List<CemeteryOrderModel> list = new ArrayList<>();
        list.add(new CemeteryOrderModel());
        adapter.addListData(list);
        adapter.addListData(list);
        adapter.addListData(list);
        adapter.notifyDataSetChanged();
        mSwipeRefreshHelper.setLoadMoreEnable(true);
//        CemeteryOrderManagerImpl.getInstance().getOrderList(getContext(), params, 1, new HttpResponseHandler<HrGetCemeteryListData>() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onSuccess(HrGetCemeteryListData result) {
////                        if (result != null && result.getItems() != null
////                                && result.getItems().size() > 0) {
////                            adapter.addListData(result.getItems());
////                            adapter.notifyDataSetChanged();
////                            if (result.getItems().size() >= pageSize) {
////                                mSwipeRefreshHelper.setLoadMoreEnable(true);
////                            } else {
////                                mSwipeRefreshHelper.setLoadMoreEnable(false);
////                            }
////                        } else {
////                            mSwipeRefreshHelper.loadMoreComplete(false);
////                        }
//            }
//
//            @Override
//            public void onError(String message) {
//
//            }
//        });
    }

    /**
     * 加载数据
     */
    protected void loadData() {
        // rl_order_qt0.setVisibility(View.GONE);
        // ll_order_qt0.setVisibility(View.VISIBLE);
        page = 1;
        adapter.clear();
        adapter.notifyDataSetChanged();
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);

        List<CemeteryOrderModel> list = new ArrayList<>();
        list.add(new CemeteryOrderModel());
        adapter.addListData(list);
        adapter.addListData(list);
        adapter.addListData(list);
        adapter.notifyDataSetChanged();
        mSwipeRefreshHelper.setLoadMoreEnable(true);

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

    /**
     * 打电话
     */
    private void makePhone(View v, CemeteryOrderModel model) {
        Utils.call(v, model.getCustomerMobile());
    }

    /**
     * 设置状态
     *
     * @param tvState
     */
    private void setState(TextView tvState, CemeteryOrderModel data) {
        CemeteryBeSpeakStateEnum[] beSpeakState = {
                CemeteryBeSpeakStateEnum.undistributed,
                CemeteryBeSpeakStateEnum.unassigned,
                CemeteryBeSpeakStateEnum.unProcess,
                CemeteryBeSpeakStateEnum.accepted,
                CemeteryBeSpeakStateEnum.talkFail,
                CemeteryBeSpeakStateEnum.talkSuccess,
                CemeteryBeSpeakStateEnum.serviceOver
        };

        for (CemeteryBeSpeakStateEnum state : beSpeakState) {
            if (data.getBespeakStatus() == state.getCode()) {
                tvState.setText(state.getText());
                return;
            }
        }
    }

    /**
     * 查看详情界面
     *
     * @param model
     */
    private void orderInfo(CemeteryOrderModel model) {
        Intent intent = new Intent(getContext(), InfoDetailsActivity.class);
        intent.putExtra(IntentName.INTENT_BESPEAKID, model.getBespeakId());
        intent.putExtra(IntentName.INTENT_ORDERID, model.getOrderId());
        getContext().startActivity(intent);
    }

    /**
     * 查看洽谈失败
     */
    private void talkFail(CemeteryOrderModel model) {
        Intent intent = new Intent(getContext(), TalkFailActivity.class);
        intent.putExtra(IntentName.INTENT_BESPEAKID, model.getBespeakId());
        getContext().startActivity(intent);
    }

    /**
     * 刷新
     */
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
