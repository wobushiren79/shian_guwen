package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.RoutePlanActivity;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.activity.cemetery.BuyCemeteryInfoActivity;
import com.shian.shianlife.activity.cemetery.CemeteryTalkFailActivity;
import com.shian.shianlife.activity.cemetery.InfoDetailsActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.CemeteryOrderManagerImpl;
import com.shian.shianlife.provide.model.CemeteryOrderModel;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/9.
 */

public class CemeteryQTView extends BaseOrderView {

    public final static String BUILD_NEW_ORDER = "BuildNewOrder";//跳转到新建预约单状态（0，新建。1，查询详情）
    public final static String BUY_INFO = "BuyInfo";//跳转到定墓资料填写（0，购墓信息。1，使用者信息，2，经办人信息）

    public final static String[] CEMETERY_LISTTYPE_1 = {"客户姓名", "联系电话", "预约时间", "预约参观公墓", "客户地址"};
    public final static String[] CEMETERY_LISTTYPE_2 = {"客户姓名", "联系电话", "预约时间", "预约地址", "预约参观公墓", "交通工具", "备注"};
    public final static String[] CEMETERY_LISTTYPE_3 = {"经办人", "经办人电话", "使用者", "公墓名称", "园区名称"};

    private int page = 0;
    private int pageSize = 20;

    LinearLayout mLinearLayoutBuildNew;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    SwipeRefreshHelper mSwipeRefreshHelper;

    RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
    List<CemeteryOrderModel> listData = new ArrayList<>();

    private View view;


    public CemeteryQTView(Context context) {
        this(context, null);
    }

    public CemeteryQTView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.view_cemetery_qt, null, false);
        addView(view);

        initView();
        setRoles();
    }

    public void setRoles() {
        getData();
        //赋予新建权限
        for (int i = 0; i < CemeteryFragment.LOGIN_ROLES_LIST.size(); i++) {
            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 2) {
                mLinearLayoutBuildNew.setVisibility(VISIBLE);
                break;
            }
            if (CemeteryFragment.LOGIN_ROLES_LIST.get(i) == 0) {
                mLinearLayoutBuildNew.setVisibility(VISIBLE);
                break;
            }
        }
    }

    private void getData() {
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        CemeteryOrderManagerImpl.getInstance().getOrderList(getContext(), params, 0, new HttpResponseHandler<HrGetCemeteryListData>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(HrGetCemeteryListData result) {
                if (result.getItems() != null) {
                    listData = result.getItems();
                    recyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(getContext(),"获取列表失败");
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sryt_swipe_listview);
        mLinearLayoutBuildNew = (LinearLayout) view.findViewById(R.id.ll_build_new);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(recyclerAdapter);

        mLinearLayoutBuildNew.setOnClickListener(onClickListener);

        mRefreshLayout.setColorSchemeColors(Color.BLACK);

        mSwipeRefreshHelper = new SwipeRefreshHelper(mRefreshLayout);
        mSwipeRefreshHelper
                .setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
                    @Override
                    public void onfresh() {
                        Log.v("this", "onfresh");
                    }
                });

        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                Log.v("this", "loadMore");
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
            final CemeteryOrderModel data = listData.get(position);

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
                    }
                }
            };


            if (data.getBespeakStatus() == 1) {
                holder.llStateAccept.setVisibility(VISIBLE);
                holder.llStateTalk.setVisibility(GONE);
                holder.llDetailes.setVisibility(GONE);
            } else if (data.getBespeakStatus() == 2 || data.getBespeakStatus() == 3) {
                holder.llStateAccept.setVisibility(GONE);
                holder.llStateTalk.setVisibility(VISIBLE);
                holder.llDetailes.setVisibility(GONE);
            } else {
                holder.llStateAccept.setVisibility(GONE);
                holder.llStateTalk.setVisibility(GONE);
                holder.llDetailes.setVisibility(VISIBLE);
            }

            holder.listView.setAdapter(new BaseAdapter() {
                String[] nameStrings;
                List<String> contentList = new ArrayList<String>();

                @Override
                public int getCount() {
                    contentList.clear();
                    if (data.getBespeakStatus() == 1) {
                        nameStrings = CEMETERY_LISTTYPE_1;
                        contentList.add(data.getCustomerName());
                        contentList.add(data.getCustomerMobile());
                        contentList.add(data.getPromiseTime());
                        contentList.add(data.getPlanCemeteryLocation());
                        contentList.add(data.getCustomerLocation());
                        return CEMETERY_LISTTYPE_1.length;
                    } else if (data.getBespeakStatus() == 2 || data.getBespeakStatus() == 3) {
                        nameStrings = CEMETERY_LISTTYPE_2;
                        contentList.add(data.getCustomerName());
                        contentList.add(data.getCustomerMobile());
                        contentList.add(data.getPromiseTime());
                        contentList.add(data.getCustomerLocation());
                        contentList.add(data.getPlanCemeteryLocation());
                        contentList.add(data.getTrafficWay());
                        contentList.add(data.getRemark());
                        return CEMETERY_LISTTYPE_2.length;
                    } else {
                        nameStrings = CEMETERY_LISTTYPE_3;
                        contentList.add(data.getAgentmanName());
                        contentList.add(data.getAgentmanMoblie());
                        contentList.add(data.getDeadmanName());
                        contentList.add(data.getChoiceCemeteryName());
                        contentList.add(data.getDetailsLocation());
                        return CEMETERY_LISTTYPE_3.length;
                    }
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return 0;
                }


                @Override
                public View getView(final int i, View view, ViewGroup viewGroup) {

                    View layout = LayoutInflater.from(getContext()).inflate(R.layout.view_cemetery_qt_item_text, null);
                    TextView nameTV = (TextView) layout.findViewById(R.id.tv_name);
                    TextView contentTV = (TextView) layout.findViewById(R.id.tv_content);
                    final ImageView phoneTV = (ImageView) layout.findViewById(R.id.iv_phone);
                    final Button detailsBT = (Button) layout.findViewById(R.id.bt_userinfo);
                    final Button mapBT = (Button) layout.findViewById(R.id.bt_map);

                    OnClickListener itemClick = new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (view == detailsBT) {
                                //订单详情
                                Log.v("this", "postion:" + i + " detailsBTClick");
                                Intent intent = new Intent(getContext(), InfoDetailsActivity.class);
                                getContext().startActivity(intent);
                            } else if (view == mapBT) {
                                Log.v("this", "postion:" + i + " mapBTClick");
                                Intent intent = new Intent(getContext(), RoutePlanActivity.class);
                                Log.v("this", contentList.get(i) + "");
                                intent.putExtra("RoutePlanLocation", contentList.get(i));
                                getContext().startActivity(intent);
                            }
                        }
                    };


                    nameTV.setText(nameStrings[i]);
                    contentTV.setText(contentList.get(i));

                    if (nameStrings[i].contains("地址")) {
                        mapBT.setVisibility(VISIBLE);
                        mapBT.setOnClickListener(itemClick);
                    } else {
                        mapBT.setVisibility(GONE);
                    }
                    if (nameStrings[i].contains("电话")) {
                        phoneTV.setVisibility(VISIBLE);
                        Utils.call(phoneTV, contentList.get(i));
                    } else {
                        phoneTV.setVisibility(GONE);
                    }
                    if (i == nameStrings.length) {
                        detailsBT.setVisibility(VISIBLE);
                        detailsBT.setOnClickListener(itemClick);
                    } else {
                        detailsBT.setVisibility(GONE);
                    }
                    return layout;
                }
            });
            holder.btUserInfo.setOnClickListener(onBTOnClickListener);
            holder.btTalkFail.setOnClickListener(onBTOnClickListener);
            holder.btSubmit.setOnClickListener(onBTOnClickListener);
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder {

            LinearLayout llStateAccept;
            LinearLayout llStateTalk;
            LinearLayout llDetailes;

            Button btUserInfo;
            Button btTalkFail;
            Button btSubmit;

            ListView listView;

            public RecyclerViewHolder(View itemView) {
                super(itemView);
                llStateAccept = (LinearLayout) itemView.findViewById(R.id.ll_state_accept);
                llStateTalk = (LinearLayout) itemView.findViewById(R.id.ll_state_talk);
                llDetailes = (LinearLayout) itemView.findViewById(R.id.ll_details);

                btUserInfo = (Button) itemView.findViewById(R.id.bt_userinfo);
                btTalkFail = (Button) itemView.findViewById(R.id.bt_talkfail);
                btSubmit = (Button) itemView.findViewById(R.id.bt_submit);

                listView = (ListView) itemView.findViewById(R.id.listview_data);
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
