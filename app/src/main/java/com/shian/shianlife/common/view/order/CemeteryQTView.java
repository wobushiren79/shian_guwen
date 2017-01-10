package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.activity.cemetery.CemeteryTalkFailActivity;
import com.shian.shianlife.common.utils.Utils;

/**
 * Created by Administrator on 2017/1/9.
 */

public class CemeteryQTView extends BaseOrderView {
    public static String BUILD_NEW_ORDER = "BuildNewOrder";//跳转到新建预约单状态（0，新建。1，查询详情）

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    SwipeRefreshHelper mSwipeRefreshHelper;


    LinearLayout mLinearLayoutBuildNew;
    private View view;

    public CemeteryQTView(Context context) {
        this(context, null);

    }

    public CemeteryQTView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.view_cemetery_qt, null, false);
        addView(view);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sryt_swipe_listview);
        mLinearLayoutBuildNew = (LinearLayout) view.findViewById(R.id.ll_build_new);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new RecyclerAdapter());

        mLinearLayoutBuildNew.setOnClickListener(onClickListener);
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
        public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
            OnClickListener onBTOnClickListener = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == holder.btUserInfo) {
                        Intent intent = new Intent(getContext(), CemeteryTalkFailActivity.class);
                        intent.putExtra(BUILD_NEW_ORDER, 1);
                        getContext().startActivity(intent);
                    } else if (view == holder.btTalkFail) {
                        Intent intent = new Intent(getContext(), CemeteryTalkFailActivity.class);
                        intent.putExtra(BUILD_NEW_ORDER, 0);
                        getContext().startActivity(intent);
                    }
                }
            };
            if (position % 2 == 0) {
                holder.llStateAccept.setVisibility(VISIBLE);
                holder.llStateTalk.setVisibility(GONE);
            } else {
                holder.llStateAccept.setVisibility(GONE);
                holder.llStateTalk.setVisibility(VISIBLE);
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


        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            LinearLayout llStateAccept;
            LinearLayout llStateTalk;

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

            public RecyclerViewHolder(View itemView) {
                super(itemView);
                llStateAccept = (LinearLayout) itemView.findViewById(R.id.ll_state_accept);
                llStateTalk = (LinearLayout) itemView.findViewById(R.id.ll_state_talk);

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
            }
        }
    }

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mLinearLayoutBuildNew) {
                Intent intent = new Intent(getContext(), BuildNewOrderActivity.class);
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
