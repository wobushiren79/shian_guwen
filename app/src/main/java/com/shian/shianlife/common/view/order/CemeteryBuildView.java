package com.shian.shianlife.common.view.order;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.car.CarOrderActivity;
import com.shian.shianlife.activity.car.CarOrderDetailsActivity;
import com.shian.shianlife.activity.newcemetery.InfoDetailsActivity;
import com.shian.shianlife.activity.newcemetery.TalkFailActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.CemeteryManagerImpl;
import com.shian.shianlife.provide.model.CemeteryOrderModel;
import com.shian.shianlife.provide.params.HpCemeteryBeSpeakCancelParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.result.HrGetCemeteryListData;
import com.shian.shianlife.thisenum.BespeakCancelStatusEnum;
import com.shian.shianlife.thisenum.BuildOrderEnum;
import com.shian.shianlife.thisenum.CemeteryBeSpeakStateEnum;
import com.shian.shianlife.view.popupbutton.PopupButton;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

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
    private int pageSize = 10;
    private int page = 1;
    private int listType = 0;

    public CemeteryBuildView(Context context,int listType) {
        super(context, null);
        this.listType=listType;
        view = View.inflate(context, R.layout.view_order_cemetery_build, this);
        initView();
        initDate();
    }


    private void initView() {
        mSryt = (SwipeRefreshLayout) view.findViewById(R.id.sryt_swipe_listview);
        mListView = (ListView) view.findViewById(R.id.lv_swipe_listview);
        mPopupButton = (PopupButton) view.findViewById(R.id.popupbutton);
        mSryt.setColorSchemeColors(Color.BLUE);
        adapter = new TArrayListAdapter<>(getContext());
        initPopupButton();
    }

    private void initDate() {
        initAdapter();
        mListView.setAdapter(adapter);
        mSwipeRefreshHelper = new SwipeRefreshHelper(mSryt);
        mSwipeRefreshHelper.setLoadMoreEnable(true);
        mSwipeRefreshHelper.setOnSwipeRefreshListener(new SwipeRefreshHelper.OnSwipeRefreshListener() {
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
            ImageView ivPhone = (ImageView) view.getView(R.id.iv_phone);

            TextView tv_car = (TextView) view.getView(R.id.tv_car);
            TextView tv_cancel = (TextView) view.getView(R.id.tv_cancel);

            takeCar(tv_car, templateItem);
            cancelBeSpeak(tv_cancel, templateItem);

            int orderState = templateItem.getBespeakStatus();
            if (orderState == CemeteryBeSpeakStateEnum.undistributed.getCode() ||
                    orderState == CemeteryBeSpeakStateEnum.unassigned.getCode() ||
                    orderState == CemeteryBeSpeakStateEnum.unProcess.getCode()) {
                tv1_title.setText("客户姓名");
                tv1_content.setText(templateItem.getCustomerName());
                tv2_title.setText("联系电话");
                tv2_content.setText(templateItem.getCustomerMobile());
                tv3_title.setText("预约时间");
                tv3_content.setText(templateItem.getPromiseTime());
                tv4_title.setText("客户地址");
                tv4_content.setText(templateItem.getCustomerLocation());
                tv5_title.setText("参观公墓");
                tv5_content.setText(templateItem.getPlanCemeteryLocation());
                tv6_title.setText("交通方式");
                tv6_content.setText(templateItem.getTrafficWay());
                tv7_title.setText("人数");
                tv7_content.setText(templateItem.getPersonNum());

                ll_info_more.setVisibility(VISIBLE);
                tv_info_more.setVisibility(VISIBLE);
                tvTalkDetails.setVisibility(GONE);
                tvOrderDetails.setVisibility(GONE);

            } else if (orderState == CemeteryBeSpeakStateEnum.accepted.getCode()) {
                tv1_title.setText("客户姓名");
                tv1_content.setText(templateItem.getCustomerName());
                tv2_title.setText("联系电话");
                tv2_content.setText(templateItem.getCustomerMobile());
                tv3_title.setText("预约时间");
                tv3_content.setText(templateItem.getPromiseTime());
                tv4_title.setText("客户地址");
                tv4_content.setText(templateItem.getCustomerLocation());
                tv5_title.setText("参观公墓");
                tv5_content.setText(templateItem.getPlanCemeteryLocation());
                tv6_title.setText("公墓接待");
                tv6_content.setText(templateItem.getCemeteryReceive());

                ll_info_more.setVisibility(GONE);
                tv_info_more.setVisibility(GONE);
                tvTalkDetails.setVisibility(GONE);
                tvOrderDetails.setVisibility(GONE);
            } else if (orderState == CemeteryBeSpeakStateEnum.talkFail.getCode() ||
                    orderState == CemeteryBeSpeakStateEnum.talkAgain.getCode()) {
                tv1_title.setText("客户姓名");
                tv1_content.setText(templateItem.getCustomerName());
                tv2_title.setText("联系电话");
                tv2_content.setText(templateItem.getCustomerMobile());
                tv3_title.setText("预约时间");
                tv3_content.setText(templateItem.getPromiseTime());
                tv4_title.setText("客户地址");
                tv4_content.setText(templateItem.getCustomerLocation());
                tv5_title.setText("参观公墓");
                tv5_content.setText(templateItem.getPlanCemeteryLocation());
                tv6_title.setText("公墓接待");
                tv6_content.setText(templateItem.getCemeteryReceive());

                ll_info_more.setVisibility(GONE);
                tv_info_more.setVisibility(GONE);
                tvTalkDetails.setVisibility(VISIBLE);
                tvOrderDetails.setVisibility(GONE);
            } else if (orderState == CemeteryBeSpeakStateEnum.ready.getCode() ||
                    orderState == CemeteryBeSpeakStateEnum.talkSuccess.getCode() ||
                    orderState == CemeteryBeSpeakStateEnum.serviceOver.getCode()) {
                tv1_title.setText("客户姓名");
                tv1_content.setText(templateItem.getCustomerName());
                tv2_title.setText("联系电话");
                tv2_content.setText(templateItem.getCustomerMobile());
                tv3_title.setText("客户地址");
                tv3_content.setText(templateItem.getCustomerLocation());
                tv4_title.setText("定墓日期");
                if (templateItem.getOrderedTombDate() != null)
                    tv4_content.setText(templateItem.getOrderedTombDate());
                else
                    tv4_content.setText("未定墓");
                tv5_title.setText("完款日期");
                if (templateItem.getPayOffTime() != null)
                    tv5_content.setText(templateItem.getPayOffTime());
                else {
                    tv5_content.setText("未完款");
                }
                tv6_title.setText("公墓名称");
                tv6_content.setText(templateItem.getDetailsLocation());

                ll_info_more.setVisibility(GONE);
                tv_info_more.setVisibility(GONE);
                tvTalkDetails.setVisibility(GONE);
                tvOrderDetails.setVisibility(VISIBLE);
            } else {
                tv1_title.setText("客户姓名");
                tv1_content.setText(templateItem.getCustomerName());
                tv2_title.setText("联系电话");
                tv2_content.setText(templateItem.getCustomerMobile());
                tv3_title.setText("预约时间");
                tv3_content.setText(templateItem.getPromiseTime());
                tv4_title.setText("客户地址");
                tv4_content.setText(templateItem.getCustomerLocation());
                tv5_title.setText("参观公墓");
                tv5_content.setText(templateItem.getPlanCemeteryLocation());
                tv6_title.setText("交通方式");
                tv6_content.setText(templateItem.getTrafficWay());
                tv7_title.setText("人数");
                tv7_content.setText(templateItem.getPersonNum());

                ll_info_more.setVisibility(GONE);
                tv_info_more.setVisibility(GONE);
                tvTalkDetails.setVisibility(GONE);
                tvOrderDetails.setVisibility(GONE);
            }

            setState(tvState, templateItem);
            makePhone(ivPhone, templateItem);

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
     * 取消咨询单
     *
     * @param tv_cancel
     * @param orderModel
     */
    private void cancelBeSpeak(TextView tv_cancel, final CemeteryOrderModel orderModel) {
        //取消订单--------------------------------------------------------------------------
        if (orderModel.getBespeakStatus() == CemeteryBeSpeakStateEnum.undistributed.getCode() ||
                orderModel.getBespeakStatus() == CemeteryBeSpeakStateEnum.unassigned.getCode()) {
            if (orderModel.getCancelStatus() == BespeakCancelStatusEnum.UN_PROCESS.getCode()) {
                tv_cancel.setText("申请取消");
                tv_cancel.setTextColor(Color.WHITE);
                tv_cancel.setBackgroundResource(R.drawable.zhy_button_state_5);
                tv_cancel.setVisibility(VISIBLE);
                tv_cancel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText editText = new EditText(getContext());
                        editText.setHint("请填写取消原因");
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("申请取消咨询单")
                                .setView(editText)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HpCemeteryBeSpeakCancelParams params = new HpCemeteryBeSpeakCancelParams();
                                        params.setBespeakId(orderModel.getBespeakId());
                                        params.setBespeakStatus(orderModel.getBespeakStatus());
                                        params.setCancelReason(editText.getText().toString());
                                        MHttpManagerFactory.getCemeteryManager().cancelCemeteryBeSpeak(getContext(), params, new HttpResponseHandler<Object>() {


                                            @Override
                                            public void onStart(Request request, int id) {

                                            }

                                            @Override
                                            public void onSuccess(Object result) {
                                                refresh();
                                                ToastUtils.show(getContext(), "取消受理中");
                                            }

                                            @Override
                                            public void onError(String message) {
                                                ToastUtils.show(getContext(), "申请取消失败");
                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .create();
                        alertDialog.show();
                    }
                });
            } else {
                tv_cancel.setVisibility(VISIBLE);
                tv_cancel.setText("取消受理中");
                tv_cancel.setTextColor(getResources().getColor(R.color.zhy_text_color_1));
                tv_cancel.setBackgroundResource(R.drawable.zhy_button_state_2);
                tv_cancel.setOnClickListener(null);
            }

        } else {
            tv_cancel.setVisibility(GONE);
        }
        //取消订单--------------------------------------------------------------------------
    }


    private void takeCar(TextView tvCar, final CemeteryOrderModel data) {
        if (data.getIsSentCar() == 0) {
            if (data.getBespeakStatus() == CemeteryBeSpeakStateEnum.undistributed.getCode()
                    || data.getBespeakStatus() == CemeteryBeSpeakStateEnum.unassigned.getCode()) {
                tvCar.setText("申请用车");
                tvCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), CarOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", data);
                        intent.putExtras(bundle);
                        getContext().startActivity(intent);
                    }
                });
                tvCar.setVisibility(VISIBLE);
            } else {
                tvCar.setVisibility(GONE);
            }
        } else {
            tvCar.setText("用车记录");
            tvCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), CarOrderDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", data);
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }
            });
            tvCar.setVisibility(VISIBLE);
        }
    }


    /**
     * 加载更多
     */
    protected void loadMoreData() {
        page++;
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        MHttpManagerFactory.getCemeteryManager().getOrderList(getContext(), params, listType, new HttpResponseHandler<HrGetCemeteryListData>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryListData result) {
                if (result.getPages() < page) {
                    page = result.getPageNum();
                } else {
                    adapter.addListData(result.getList());
                    adapter.notifyDataSetChanged();
                }
                mSwipeRefreshHelper.loadMoreComplete(true);
            }

            @Override
            public void onError(String message) {
                mSwipeRefreshHelper.loadMoreComplete(true);
            }
        });
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
        MHttpManagerFactory.getCemeteryManager().getOrderList(getContext(), params, listType, new HttpResponseHandler<HrGetCemeteryListData>() {

            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(HrGetCemeteryListData result) {

                if (result != null && result.getList() != null && result.getList().size() > 0) {
                    adapter.addListData(result.getList());
                    adapter.notifyDataSetChanged();
                }
                mSwipeRefreshHelper.refreshComplete();

            }

            @Override
            public void onError(String message) {
                mSwipeRefreshHelper.refreshComplete();
            }
        });


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
                CemeteryBeSpeakStateEnum.serviceOver,
                CemeteryBeSpeakStateEnum.ready
        };



        for (CemeteryBeSpeakStateEnum state : beSpeakState) {
            if (data.getBespeakStatus() == state.getCode()) {
                tvState.setText(state.getOtherName());
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
