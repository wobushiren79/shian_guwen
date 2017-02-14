package com.shian.shianlife.common.view.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.shian.shianlife.activity.map.RoutePlanActivity;
import com.shian.shianlife.activity.cemetery.BuildNewOrderActivity;
import com.shian.shianlife.activity.cemetery.BuyCemeteryInfoActivity;
import com.shian.shianlife.activity.cemetery.CemeteryTalkFailActivity;
import com.shian.shianlife.activity.cemetery.InfoDetailsActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.fragment.CemeteryFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.CemeteryOrderManagerImpl;
import com.shian.shianlife.provide.model.CemeteryOrderModel;
import com.shian.shianlife.provide.params.HpCetemeryAcceptParams;
import com.shian.shianlife.provide.params.HpCetemeryRejectParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;

import java.util.ArrayList;
import java.util.List;



@SuppressLint("InflateParams")
public class CemeteryQTView extends BaseOrderView {

    public final static String TALK_INFO_STATE = "TalkInfoState";//跳转到洽谈信息状态（0，新建。1，查询详情）
    public final static String TALK_INFO_ID = "TalkInfoId";//跳转到洽谈信息所需预约单ID
    public final static String TALK_INFO_ORDER_ID = "TalkInfoOrderId";//跳转到洽谈信息所需订单ID
    public final static String TALK_CHANGE_INFO_STATE="TalkChangeInfoState";//修改购墓信息的人（1。新建下修改，2 售后下修改）
    public final static String BUY_INFO = "BuyInfo";//跳转到定墓资料填写（0，购墓信息。1，使用者信息，2，经办人信息）

    public final static String[] CEMETERY_LISTTYPE_1 = {"客户姓名", "联系电话", "预约地址", "预约时间", "预约参观公墓"};
    public final static String[] CEMETERY_LISTTYPE_2 = {"客户姓名", "联系电话", "预约地址", "预约时间", "预约参观公墓", "交通工具", "备注"};
    public final static String[] CEMETERY_LISTTYPE_3 = {"经办人", "经办人电话", "使用者", "公墓名称", "园区名称"};

    private int page = 0;
    private int pageSize = 20;

    LinearLayout mLinearLayoutBuildNew;
    ListView mListView;
    SwipeRefreshLayout mRefreshLayout;
    SwipeRefreshHelper mSwipeRefreshHelper;

    ListAdapter listAdapter = new ListAdapter();
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
//        getData();
        setRoles();
    }

    public void setRoles() {
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

    private void clearData() {
        page = 0;
        listData.clear();
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
                    listData.addAll(result.getItems());
                    if (result.getItems().size() == 0 && page != 0) {
                        page--;
                        mSwipeRefreshHelper.loadMoreComplete(false);
                    }
                    listAdapter.notifyDataSetChanged();
                }
                mSwipeRefreshHelper.refreshComplete();
                mSwipeRefreshHelper.loadMoreComplete(true);
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(getContext(), "获取列表失败");
                mSwipeRefreshHelper.refreshComplete();
                mSwipeRefreshHelper.loadMoreComplete(true);
            }
        });
    }

    private void initView() {
        mListView = (ListView) view.findViewById(R.id.recyclerView);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.sryt_swipe_listview);
        mLinearLayoutBuildNew = (LinearLayout) view.findViewById(R.id.ll_build_new);
        mListView.setAdapter(listAdapter);


        mLinearLayoutBuildNew.setOnClickListener(onClickListener);

        mRefreshLayout.setColorSchemeColors(Color.BLACK);

        mSwipeRefreshHelper = new SwipeRefreshHelper(mRefreshLayout);
        mSwipeRefreshHelper.setLoadMoreEnable(true);
        mSwipeRefreshHelper
                .setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
                    @Override
                    public void onfresh() {
                        clearData();
                        listAdapter.notifyDataSetChanged();
                        getData();
                    }
                });

        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                page++;
                getData();
            }
        });
    }

    /**
     * ListView适配器
     */
    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ListViewHolder holder;
            final CemeteryOrderModel data = listData.get(position);

            if (convertView == null) {
                holder = new ListViewHolder();

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_cemetery_qt_item, null);
                holder.llStateAccept = (LinearLayout) convertView.findViewById(R.id.ll_state_accept);
                holder.llStateTalk = (LinearLayout) convertView.findViewById(R.id.ll_state_talk);
                holder.llDetailes = (LinearLayout) convertView.findViewById(R.id.ll_details);

                holder.btUserInfo = (Button) convertView.findViewById(R.id.bt_detailes);
                holder.btTalkFail = (Button) convertView.findViewById(R.id.bt_talkfail);
                holder.btSubmit = (Button) convertView.findViewById(R.id.bt_submit);
                holder.btReject = (Button) convertView.findViewById(R.id.bt_reject);
                holder.btAccepted = (Button) convertView.findViewById(R.id.bt_accepted);

                holder.itemlayout_1 = convertView.findViewById(R.id.itemlayout_1);
                holder.itemlayout_2 = convertView.findViewById(R.id.itemlayout_2);
                holder.itemlayout_3 = convertView.findViewById(R.id.itemlayout_3);
                holder.itemlayout_4 = convertView.findViewById(R.id.itemlayout_4);
                holder.itemlayout_5 = convertView.findViewById(R.id.itemlayout_5);
                holder.itemlayout_6 = convertView.findViewById(R.id.itemlayout_6);
                holder.itemlayout_7 = convertView.findViewById(R.id.itemlayout_7);

                holder.tv_line_6 = (TextView) convertView.findViewById(R.id.line_6);
                holder.tv_line_5 = (TextView) convertView.findViewById(R.id.line_5);
                holder.tv_tag= (TextView) convertView.findViewById(R.id.tv_tag);

                convertView.setTag(holder);
            } else {
                holder = (ListViewHolder) convertView.getTag();
            }

            List<View> itemLayoutList = new ArrayList<>();
            itemLayoutList.add(holder.itemlayout_1);
            itemLayoutList.add(holder.itemlayout_2);
            itemLayoutList.add(holder.itemlayout_3);
            itemLayoutList.add(holder.itemlayout_4);
            itemLayoutList.add(holder.itemlayout_5);
            itemLayoutList.add(holder.itemlayout_6);
            itemLayoutList.add(holder.itemlayout_7);

            OnClickListener onBTOnClickListener = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view == holder.btUserInfo) {
                        //查询洽谈信息
                        queryDetailsInfo(data);
                    } else if (view == holder.btTalkFail) {
                        //填写洽谈失败信息
                        queryTalkInfo(0,data);
                    } else if (view == holder.btSubmit) {
                        //填写定墓信息
                        fillCetemeryInfo(data);
                    } else if (view == holder.btReject) {
                        //拒单
                        rejectOrder(data);
                    } else if (view == holder.btAccepted) {
                        //接单
                        acceptedOrder(data);
                    }
                }
            };

            String[] nameStrings = new String[0];
            final List<String> contentList = new ArrayList<String>();

            switch (data.getBespeakStatus()){
                case 1:
                    holder.tv_tag.setText("接单中");
                    break;
                case 2:
                    holder.tv_tag.setText("洽谈中");
                    break;
                case 3:
                    holder.tv_tag.setText("二次洽谈");
                    break;
                case 4:
                    holder.tv_tag.setText("购墓完成");
                    break;
                case 5:
                    holder.tv_tag.setText("服务结束");
                    break;
            }
            if (data.getBespeakStatus() == 1) {
                if (data.getIsEdit()==1) {
                    holder.llStateAccept.setVisibility(VISIBLE);
                    holder.llStateTalk.setVisibility(GONE);
                    holder.llDetailes.setVisibility(GONE);
                }else{
                    holder.llStateAccept.setVisibility(GONE);
                    holder.llStateTalk.setVisibility(GONE);
                    holder.llDetailes.setVisibility(GONE);
                }
                nameStrings = CEMETERY_LISTTYPE_1;
                contentList.add(data.getCustomerName());
                contentList.add(data.getCustomerMobile());
                contentList.add(data.getCustomerLocation());
                contentList.add(data.getPromiseTime());
                contentList.add(data.getPlanCemeteryLocation());
                holder.itemlayout_6.setVisibility(GONE);
                holder.itemlayout_7.setVisibility(GONE);
                holder.tv_line_5.setVisibility(GONE);
                holder.tv_line_6.setVisibility(GONE);

            } else if (data.getBespeakStatus() == 2 || data.getBespeakStatus() == 3) {
                if (data.getIsEdit()==1) {
                    holder.llStateAccept.setVisibility(GONE);
                    holder.llStateTalk.setVisibility(VISIBLE);
                    holder.llDetailes.setVisibility(GONE);
                }else{
                    holder.llStateAccept.setVisibility(GONE);
                    holder.llStateTalk.setVisibility(GONE);
                    holder.llDetailes.setVisibility(GONE);
                }
                nameStrings = CEMETERY_LISTTYPE_2;
                contentList.add(data.getCustomerName());
                contentList.add(data.getCustomerMobile());
                contentList.add(data.getCustomerLocation());
                contentList.add(data.getPromiseTime());
                contentList.add(data.getPlanCemeteryLocation());
                contentList.add(data.getTrafficWay());
                contentList.add(data.getRemark());

                holder.itemlayout_6.setVisibility(VISIBLE);
                holder.itemlayout_7.setVisibility(VISIBLE);
                holder.tv_line_5.setVisibility(VISIBLE);
                holder.tv_line_6.setVisibility(VISIBLE);
            } else {
                if (data.getIsEdit()==1) {
                    holder.llStateAccept.setVisibility(GONE);
                    holder.llStateTalk.setVisibility(GONE);
                    holder.llDetailes.setVisibility(VISIBLE);
                }else{
                    holder.llStateAccept.setVisibility(GONE);
                    holder.llStateTalk.setVisibility(GONE);
                    holder.llDetailes.setVisibility(GONE);
                }
                nameStrings = CEMETERY_LISTTYPE_3;
                contentList.add(data.getAgentmanName());
                contentList.add(data.getAgentmanMoblie());
                contentList.add(data.getDeadmanName());
                contentList.add(data.getChoiceCemeteryName());
                contentList.add(data.getDetailsLocation());
                holder.itemlayout_6.setVisibility(GONE);
                holder.itemlayout_7.setVisibility(GONE);
                holder.tv_line_5.setVisibility(GONE);
                holder.tv_line_6.setVisibility(GONE);
            }

            for (int i = 0; i < contentList.size(); i++) {
                View layout = itemLayoutList.get(i);
                TextView nameTV = (TextView) layout.findViewById(R.id.tv_name);
                TextView contentTV = (TextView) layout.findViewById(R.id.tv_content);
                ImageView phoneTV = (ImageView) layout.findViewById(R.id.iv_phone);
                Button detailsBT = (Button) layout.findViewById(R.id.bt_userinfo);
                Button mapBT = (Button) layout.findViewById(R.id.bt_map);

                nameTV.setText(nameStrings[i]);
                contentTV.setText(contentList.get(i));

                if (nameStrings[i].contains("地址")) {
                    if (contentList.get(i) != null) {
                        mapBT.setVisibility(VISIBLE);
                        final int finalI = i;
                        mapBT.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getContext(), RoutePlanActivity.class);
                                intent.putExtra("RoutePlanLocation", contentList.get(finalI));
                                getContext().startActivity(intent);
                            }
                        });
                    } else {
                        mapBT.setVisibility(GONE);
                    }
                } else {
                    mapBT.setVisibility(GONE);
                }


                if (nameStrings[i].contains("电话")) {
                    if (contentList.get(i) != null) {
                        phoneTV.setVisibility(VISIBLE);
                        Utils.call(phoneTV, contentList.get(i));
                    } else {
                        phoneTV.setVisibility(GONE);
                    }
                } else {
                    phoneTV.setVisibility(GONE);
                }

                if (i == (nameStrings.length-1)&&data.getTalkFailResult()==3) {
                    detailsBT.setVisibility(VISIBLE);
                    detailsBT.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //订单详情
                            queryTalkInfo(1,data);
                        }
                    });
                } else {
                    detailsBT.setVisibility(GONE);
                }
            }
//            holder.listView.setAdapter(new BaseAdapter() {
//                String[] nameStrings;
//                List<String> contentList = new ArrayList<String>();
//
//                @Override
//                public int getCount() {
//
//                    contentList.clear();
//                    if (data.getBespeakStatus() == 1) {
//                        nameStrings = CEMETERY_LISTTYPE_1;
//                        contentList.add(data.getCustomerName());
//                        contentList.add(data.getCustomerMobile());
//                        contentList.add(data.getPromiseTime());
//                        contentList.add(data.getPlanCemeteryLocation());
//                        contentList.add(data.getCustomerLocation());
//                        return CEMETERY_LISTTYPE_1.length;
//                    } else if (data.getBespeakStatus() == 2 || data.getBespeakStatus() == 3) {
//                        nameStrings = CEMETERY_LISTTYPE_2;
//                        contentList.add(data.getCustomerName());
//                        contentList.add(data.getCustomerMobile());
//                        contentList.add(data.getPromiseTime());
//                        contentList.add(data.getCustomerLocation());
//                        contentList.add(data.getPlanCemeteryLocation());
//                        contentList.add(data.getTrafficWay());
//                        contentList.add(data.getRemark());
//                        return CEMETERY_LISTTYPE_2.length;
//                    } else {
//                        nameStrings = CEMETERY_LISTTYPE_3;
//                        contentList.add(data.getAgentmanName());
//                        contentList.add(data.getAgentmanMoblie());
//                        contentList.add(data.getDeadmanName());
//                        contentList.add(data.getChoiceCemeteryName());
//                        contentList.add(data.getDetailsLocation());
//                        return CEMETERY_LISTTYPE_3.length;
//                    }
//
//                }
//
//                @Override
//                public Object getItem(int i) {
//                    return null;
//                }
//
//                @Override
//                public long getItemId(int i) {
//                    return 0;
//                }
//
//
//                @Override
//                public View getView(final int i, View layout, ViewGroup viewGroup) {
//                    final ThisViewHolder thisHolder;
//                    if (layout == null) {
//                        thisHolder = new ThisViewHolder();
//                        layout = LayoutInflater.from(getContext()).inflate(R.layout.view_cemetery_qt_item_text, null);
//                        thisHolder.contentTV = (TextView) layout.findViewById(R.id.tv_content);
//                        thisHolder.nameTV = (TextView) layout.findViewById(R.id.tv_name);
//                        thisHolder.phoneTV = (ImageView) layout.findViewById(R.id.iv_phone);
//                        thisHolder.detailsBT = (Button) layout.findViewById(R.id.bt_userinfo);
//                        thisHolder.mapBT = (Button) layout.findViewById(R.id.bt_map);
//                        layout.setTag(thisHolder);
//                    } else {
//                        thisHolder = (ThisViewHolder) layout.getTag();
//                    }
//                    OnClickListener itemClick = new OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (view == thisHolder.detailsBT) {
//                                //订单详情
//                                Log.v("this", "postion:" + i + " detailsBTClick");
//                                Intent intent = new Intent(getContext(), InfoDetailsActivity.class);
//                                getContext().startActivity(intent);
//                            } else if (view == thisHolder.mapBT) {
//                                Log.v("this", "postion:" + i + " mapBTClick");
//                                Intent intent = new Intent(getContext(), RoutePlanActivity.class);
//                                Log.v("this", contentList.get(i) + "");
//                                intent.putExtra("RoutePlanLocation", contentList.get(i));
//                                getContext().startActivity(intent);
//                            }
//                        }
//                    };
//
//
//                    thisHolder.nameTV.setText(nameStrings[i]);
//                    thisHolder.contentTV.setText(contentList.get(i));
//
//                    if (nameStrings[i].contains("地址")) {
//                        if (contentList.get(i) != null) {
//                            thisHolder.mapBT.setVisibility(VISIBLE);
//                            thisHolder.mapBT.setOnClickListener(itemClick);
//                        } else {
//                            thisHolder.mapBT.setVisibility(GONE);
//                        }
//                    } else {
//                        thisHolder.mapBT.setVisibility(GONE);
//                    }
//                    if (nameStrings[i].contains("电话")) {
//                        if (contentList.get(i) != null) {
//                            thisHolder.phoneTV.setVisibility(VISIBLE);
//                            Utils.call(thisHolder.phoneTV, contentList.get(i));
//                        } else {
//                            thisHolder.phoneTV.setVisibility(GONE);
//                        }
//                    } else {
//                        thisHolder.phoneTV.setVisibility(GONE);
//                    }
//                    if (i == nameStrings.length) {
//                        thisHolder.detailsBT.setVisibility(VISIBLE);
//                        thisHolder.detailsBT.setOnClickListener(itemClick);
//                    } else {
//                        thisHolder.detailsBT.setVisibility(GONE);
//                    }
//                    return layout;
//                }
//
//                class ThisViewHolder {
//                    TextView nameTV;
//                    TextView contentTV;
//                    ImageView phoneTV;
//                    Button detailsBT;
//                    Button mapBT;
//                }
//            });
            holder.btUserInfo.setOnClickListener(onBTOnClickListener);
            holder.btTalkFail.setOnClickListener(onBTOnClickListener);
            holder.btSubmit.setOnClickListener(onBTOnClickListener);
            holder.btAccepted.setOnClickListener(onBTOnClickListener);
            holder.btReject.setOnClickListener(onBTOnClickListener);
            return convertView;
        }

        class ListViewHolder {
            LinearLayout llStateAccept;
            LinearLayout llStateTalk;
            LinearLayout llDetailes;
            Button btUserInfo;
            Button btTalkFail;
            Button btSubmit;
            Button btReject;
            Button btAccepted;
            TextView tv_line_6;
            TextView tv_line_5;
            TextView tv_tag;
            View itemlayout_1;
            View itemlayout_2;
            View itemlayout_3;
            View itemlayout_4;
            View itemlayout_5;
            View itemlayout_6;
            View itemlayout_7;
        }
    }

    /**
     * 查询详情
     */
    private void queryDetailsInfo(CemeteryOrderModel model) {
        Intent intent = new Intent(getContext(), InfoDetailsActivity.class);
        intent.putExtra(TALK_INFO_ID,model.getBespeakId());
        intent.putExtra(TALK_INFO_ORDER_ID,model.getOrderedId());
        getContext().startActivity(intent);
    }

    /**
     * 接单
     */
    private void acceptedOrder(CemeteryOrderModel model) {
        HpCetemeryAcceptParams params = new HpCetemeryAcceptParams();
        params.setBespeakAssignId(model.getBespeakAssignId());
        params.setBespeakId(model.getBespeakId());
        MHttpManagerFactory.getAccountManager().acceptCemetery(getContext(),
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        refresh();
                        ToastUtils.show(getContext(), "接单成功");
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//                        ToastUtils.show(getContext(), "接单失败");
                    }
                });

    }

    /**
     * 拒单
     */
    private void rejectOrder(CemeteryOrderModel model) {
        HpCetemeryRejectParams params = new HpCetemeryRejectParams();
        params.setBespeakId(model.getBespeakId());
        params.setBespeakAssignId(model.getBespeakAssignId());
        MHttpManagerFactory.getAccountManager().rejectCemetery(getContext(),
                params, new HttpResponseHandler<Object>() {

                    @Override
                    public void onSuccess(Object result) {
                        refresh();
                        ToastUtils.show(getContext(), "拒单成功");
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//                      ToastUtils.show(getContext(), "拒单失败");
                    }
                });
    }

    /**
     * 填写购墓信息
     */
    private void fillCetemeryInfo(CemeteryOrderModel model) {
        Intent intent = new Intent(getContext(), BuyCemeteryInfoActivity.class);
        intent.putExtra(TALK_CHANGE_INFO_STATE,1);
        intent.putExtra(TALK_INFO_ID,model.getBespeakId());
        intent.putExtra(TALK_INFO_ORDER_ID,model.getOrderedId());
        switch (model.getInfoStatus()) {
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

    /**
     * 查询或填写洽谈信息
     *
     * @param value (0为填写，1为查询)
     */
    private void queryTalkInfo(int value,CemeteryOrderModel model) {
        Intent intent = new Intent(getContext(), CemeteryTalkFailActivity.class);
        intent.putExtra(TALK_INFO_STATE, value);
        intent.putExtra(TALK_INFO_ID,model.getBespeakId());
        intent.putExtra(TALK_INFO_ORDER_ID,model.getOrderedId());

        getContext().startActivity(intent);
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
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });
    }
}
