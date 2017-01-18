package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.activity.cemetery.BuyCemeteryInfoActivity;
import com.shian.shianlife.activity.cemetery.CemeteryTalkFailActivity;
import com.shian.shianlife.activity.cemetery.InfoDetailsActivity;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.CemeteryOrderManagerImpl;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/9.
 */

public class CemeteryQTView extends BaseOrderView {
    public final static String BUILD_NEW_ORDER = "BuildNewOrder";//跳转到新建预约单状态（0，新建。1，查询详情）
    public final static String BUY_INFO = "BuyInfo";//跳转到定墓资料填写（0，购墓信息。1，使用者信息，2，经办人信息）
    public final static String ROLES_INFO = "RolesInfo";//权限信息 角色列表(0,超级管理员，1客服，2新建，3洽谈，4售后)


    private int page = 0;
    private int pageSize = 20;

    LinearLayout mLLBuildNew;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    SwipeRefreshHelper mSwipeRefreshHelper;


    LinearLayout mLinearLayoutBuildNew;
    private View view;

    private ArrayList<Integer> rolesList = new ArrayList<>();

    public CemeteryQTView(Context context) {
        this(context, null);
    }

    public CemeteryQTView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.view_cemetery_qt, null, false);
        addView(view);
        initView();
    }

    public void setRoles(ArrayList<Integer> rolesList) {
        this.rolesList = rolesList;
        initData();

        //赋予新建权限
        for (int i = 0; i < rolesList.size(); i++) {
            if (rolesList.get(i) == 2) {
                mLLBuildNew.setVisibility(VISIBLE);
                break;
            }
        }
    }

    private void initData() {
        HpGetOrderListParams params=new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        CemeteryOrderManagerImpl.getInstance().getOrderList(getContext(), params, 0, new HttpResponseHandler<HrGetCemeteryListData>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryListData result) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sryt_swipe_listview);
        mLinearLayoutBuildNew = (LinearLayout) view.findViewById(R.id.ll_build_new);
        mLLBuildNew = (LinearLayout) view.findViewById(R.id.ll_build_new);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new RecyclerAdapter());

        mLinearLayoutBuildNew.setOnClickListener(onClickListener);

        mRefreshLayout.setColorSchemeColors(Color.BLACK);

        mSwipeRefreshHelper = new SwipeRefreshHelper(mRefreshLayout);
        mSwipeRefreshHelper
                .setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
                    @Override
                    public void onfresh() {
                        Log.v("this","onfresh");
                    }
                });

        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                Log.v("this","loadMore");
            }
        });
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {


        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(
                    LayoutInflater.from(getContext()).inflate(R.layout.view_cemetery_qt_item, parent, false)
            );
            return recyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            OnClickListener onBTOnClickListener = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == holder.btUserInfo) {
                        //查询洽谈信息
                        Intent intent = new Intent(getContext(), CemeteryTalkFailActivity.class);
                        intent.putExtra(BUILD_NEW_ORDER, 1);
                        getContext().startActivity(intent);
                    } else if (view == holder.btTalkFail) {
                        //填写洽谈失败信息
                        Intent intent = new Intent(getContext(), CemeteryTalkFailActivity.class);
                        intent.putExtra(BUILD_NEW_ORDER, 0);
                        getContext().startActivity(intent);
                    } else if (view == holder.btSubmit) {
                        //填写定墓信息
                        Intent intent = new Intent(getContext(), BuyCemeteryInfoActivity.class);
                        switch (0) {
                            case 0:
                                intent.putExtra(BUY_INFO, 0);
                                break;
                            case 1:
                                intent.putExtra(BUY_INFO, 1);
                                break;
                            case 2:
                                intent.putExtra(BUY_INFO, 2);
                                break;
                        }
                        getContext().startActivity(intent);
                    } else if (view == holder.btDetailes) {
                        //订单详情
                        Intent intent = new Intent(getContext(), InfoDetailsActivity.class);
                        getContext().startActivity(intent);

                    }
                }
            };
            if (position % 2 == 0) {
                holder.llStateAccept.setVisibility(VISIBLE);
                holder.llStateTalk.setVisibility(GONE);
                holder.llDetailes.setVisibility(VISIBLE);
            } else {
                holder.llStateAccept.setVisibility(GONE);
                holder.llStateTalk.setVisibility(VISIBLE);
                holder.llDetailes.setVisibility(VISIBLE);
            }

            holder.tvName.setText("tvName" + position);
            holder.tvTime.setText("tvTime" + position);
            holder.tvPhone.setText("tvPhone" + position);
            holder.tvLocation.setText("tvLocation" + position);
            holder.tvCemeteryName.setText("tvCemeteryName" + position);
            holder.tvTraffic.setText("tvTraffic" + position);
            holder.tvRemark.setText("tvRemark" + position);

            Utils.call(holder.ivPhone, "15198088403");


            holder.btUserInfo.setOnClickListener(onBTOnClickListener);
            holder.btTalkFail.setOnClickListener(onBTOnClickListener);
            holder.btSubmit.setOnClickListener(onBTOnClickListener);
            holder.btDetailes.setOnClickListener(onBTOnClickListener);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            LinearLayout llStateAccept;
            LinearLayout llStateTalk;
            LinearLayout llDetailes;

            TextView tvName;
            TextView tvTime;
            TextView tvPhone;
            TextView tvLocation;
            TextView tvCemeteryName;
            TextView tvTraffic;
            TextView tvRemark;

            ImageView ivPhone;

            Button btUserInfo;
            Button btTalkFail;
            Button btSubmit;
            Button btDetailes;

            public RecyclerViewHolder(View itemView) {
                super(itemView);
                llStateAccept = (LinearLayout) itemView.findViewById(R.id.ll_state_accept);
                llStateTalk = (LinearLayout) itemView.findViewById(R.id.ll_state_talk);
                llDetailes = (LinearLayout) itemView.findViewById(R.id.ll_details);

                tvName = (TextView) itemView.findViewById(R.id.tv_name);
                tvTime = (TextView) itemView.findViewById(R.id.tv_time);
                tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
                tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
                tvCemeteryName = (TextView) itemView.findViewById(R.id.tv_cemeteryName);
                tvTraffic = (TextView) itemView.findViewById(R.id.tv_traffic);
                tvRemark = (TextView) itemView.findViewById(R.id.tv_remark);

                ivPhone = (ImageView) itemView.findViewById(R.id.iv_phone);

                btUserInfo = (Button) itemView.findViewById(R.id.bt_userinfo);
                btTalkFail = (Button) itemView.findViewById(R.id.bt_talkfail);
                btSubmit = (Button) itemView.findViewById(R.id.bt_submit);
                btDetailes = (Button) itemView.findViewById(R.id.bt_detailes);
            }
        }
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mLinearLayoutBuildNew) {
                Intent intent = new Intent(getContext(), BuildNewOrderActivity.class);
                intent.putIntegerArrayListExtra(ROLES_INFO,rolesList);
                getContext().startActivity(intent);
            }
        }
    };

    @Override
    public void refresh() {
//        mRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshHelper.autoRefresh();
//            }
//        });
    }
}
