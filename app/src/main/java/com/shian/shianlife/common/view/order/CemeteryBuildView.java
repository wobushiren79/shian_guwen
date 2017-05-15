package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
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

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class CemeteryBuildView extends BaseOrderView {
    View view;

    private   PopupButton mPopupButton;
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
        mPopupButton= (PopupButton) view.findViewById(R.id.popupbutton);
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
        public void OnDrawViewEx(Context aContext, CemeteryOrderModel templateItem, ViewGropMap view, int aIndex) {
            TextView tvAgentManName = (TextView) view.getView(R.id.tv_agentmanname);
            TextView tvAgentManPhone = (TextView) view.getView(R.id.tv_agentmanphone);
            TextView tvDeadManName = (TextView) view.getView(R.id.tv_deadmanname);
            TextView tvCemeteryName = (TextView) view.getView(R.id.tv_cemeteryname);
            TextView tvLocationName = (TextView) view.getView(R.id.tv_locationname);
            TextView tvState = (TextView) view.getView(R.id.tv_state);
            ImageView ivPhone = (ImageView)view.getView(R.id.iv_phone);

            setState(tvState, templateItem);
            tvAgentManName.setText(templateItem.getAgentmanName());
            tvAgentManPhone.setText(templateItem.getAgentmanMoblie());
            tvDeadManName.setText(templateItem.getDeadmanName());
            tvCemeteryName.setText(templateItem.getChoiceCemeteryName());
            tvLocationName.setText(templateItem.getDetailsLocation());

            makePhone(ivPhone,templateItem);
        }
    };

    protected void loadMoreData() {
        page++;
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        CemeteryOrderManagerImpl.getInstance().getOrderList(getContext(), params, 1, new HttpResponseHandler<HrGetCemeteryListData>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryListData result) {
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
            }

            @Override
            public void onError(String message) {

            }
        });
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
