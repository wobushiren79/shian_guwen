package com.shian.shianlife.common.view.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.BaiduMapActivity;
import com.shian.shianlife.activity.CustomerActivity;
import com.shian.shianlife.activity.CustomerDetailActivity;
import com.shian.shianlife.activity.EditOrderActivity;
import com.shian.shianlife.activity.MainActivity;
import com.shian.shianlife.activity.NewOrderActivity;
import com.shian.shianlife.activity.OrderDetailActivity;
import com.shian.shianlife.activity.PayActivity;
import com.shian.shianlife.activity.RefundActivity;
import com.shian.shianlife.activity.RoutePlanActivity;
import com.shian.shianlife.activity.SaveTalkFailActivity;
import com.shian.shianlife.activity.updata.ContractDataActivity;
import com.shian.shianlife.activity.updata.JBRDataActivity;
import com.shian.shianlife.activity.updata.WSZDataActivity;
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
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpGetOrderListParams;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpRejectParams;
import com.shian.shianlife.provide.result.HrGetOrderListResult;
import com.shian.shianlife.provide.result.HrGetTalkFail;
import com.shian.shianlife.view.TalkDataDialog;

@SuppressLint("InflateParams")
public class QTView extends BaseOrderView {
    private final String LOG_TGA = "QT_VIEW";
    private View v;


    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<OrderListModel> adapter;

    private SwipeRefreshHelper mSwipeRefreshHelper;

    RelativeLayout rl_order_qt0;
    LinearLayout ll_order_qt0;

    private int page = 0;
    private int pageSize = 20;

    public QTView(Context context) {
        this(context, null);
    }

    public QTView(Context context, AttributeSet attrs) {
        super(context, attrs);
        v = LayoutInflater.from(context).inflate(R.layout.view_order_qt, null,
                false);
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
        rl_order_qt0 = (RelativeLayout) v.findViewById(R.id.rl_order_qt0);
        ll_order_qt0 = (LinearLayout) v.findViewById(R.id.ll_order_qt0);
        mSryt.setColorSchemeColors(Color.BLUE);
        adapter = new TArrayListAdapter<OrderListModel>(getContext());
        v.findViewById(R.id.tv_neworder).setOnClickListener(
                newOrderClickListener);
        v.findViewById(R.id.tv_neworder1).setOnClickListener(
                newOrderClickListener);

    }

    private OnClickListener newOrderClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(getContext(), NewOrderActivity.class);
            getContext().startActivity(in);
        }
    };

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
        OrderManagerImpl.getInstance().getOrderList(getContext(), params, 0,
                new HttpResponseHandler<HrGetOrderListResult>() {

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
        rl_order_qt0.setVisibility(View.GONE);
        ll_order_qt0.setVisibility(View.VISIBLE);
        page = 0;
        adapter.clear();
        adapter.notifyDataSetChanged();
        HpGetOrderListParams params = new HpGetOrderListParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        OrderManagerImpl.getInstance().getOrderList(getContext(), params, 0,
                new HttpResponseHandler<HrGetOrderListResult>() {

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
                            showNodataLayout();
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

    protected void showNodataLayout() {
        rl_order_qt0.setVisibility(View.VISIBLE);
        ll_order_qt0.setVisibility(View.GONE);
    }

    private void initAdapter() {
        final SimpleDateFormat mDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm", Locale.CHINA);
        adapter.setLayout(R.layout.view_item_qt);
        adapter.setDrawViewEx(new IOnDrawViewEx<OrderListModel>() {

            @Override
            public void OnDrawViewEx(Context aContext, final OrderListModel model,
                                     ViewGropMap view, int aIndex) {
                // 客户姓名
                View rl_qt0 = view.getView(R.id.rl_qt0);
                rl_qt0.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                TextView tv_qt01 = (TextView) view.getView(R.id.tv_qt01);

                // 洽谈时间
                View rl_qt1 = view.getView(R.id.rl_qt1);
                rl_qt1.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                TextView tv_qt11 = (TextView) view.getView(R.id.tv_qt11);
                // ImageView ivPhone = (ImageView	) view.getView(R.id.iv_qt02);
                // Utils.call(ivPhone, model.getCustomerMobile());
                String promiseTime = model.getPromiseTime();

                // 使用者当前地址
                View rl_qt2 = view.getView(R.id.rl_qt2);
                rl_qt2.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                TextView tv_qt21 = (TextView) view.getView(R.id.tv_qt21);

                // 备注
                View rl_qt3 = view.getView(R.id.rl_qt3);
                rl_qt3.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }
                });
                TextView tv_qt31 = (TextView) view.getView(R.id.tv_qt31);
                //地图
                View button_map = view.getView(R.id.button_map);
                button_map.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), RoutePlanActivity.class);
                        intent.putExtra("RoutePlanLocation", model.getCustomerAddress());
                        getContext().startActivity(intent);
                    }
                });
                View ddxq = view.getView(R.id.fl_ddxq);
                TextView tvq0 = (TextView) view.getView(R.id.tv_qt00);
                TextView tvq1 = (TextView) view.getView(R.id.tv_qt10);
                TextView tvq2 = (TextView) view.getView(R.id.tv_qt20);
                TextView tvq3 = (TextView) view.getView(R.id.tv_qt30);
                TextView tvqfp = (TextView) view.getView(R.id.iv_qt32);
                TextView tvclose = (TextView) view.getView(R.id.tv_close);
                TextView tvedit = (TextView) view.getView(R.id.tv_edit);
                TextView tvwaitservice = (TextView) view.getView(R.id.tv_waitservice);
                TextView tvpay = (TextView) view.getView(R.id.tv_pay);
                ImageView tishi = (ImageView) view.getView(R.id.iv_data);


                ImageView ivq0 = (ImageView) view.getView(R.id.iv_qt02);
                ImageView ivq1 = (ImageView) view.getView(R.id.iv_qt12);
                ImageView ivq2 = (ImageView) view.getView(R.id.iv_qt22);
                ImageView ivq3 = (ImageView) view.getView(R.id.iv_qt32p);
                TextView tv_qt02 = (TextView) view.getView(R.id.tv_qt02);
                TextView tv_talkagain = (TextView) view.getView(R.id.tv_talkagain);


                tv_qt02.setText("");


                switch (model.getOrderStatus()) {
                    case 1:
                        tv_qt02.setText("未处理");
                        break;
                    case 2:
                        tv_qt02.setText("待服务");
                        break;
                    case 3:
                        tv_qt02.setText("已接受");
                        break;
                    case 4:
                        tv_qt02.setText("服务派单中");
                        break;
                    case 5:
                        tv_qt02.setText("结束派单");
                        break;
                    case 6:
                        tv_qt02.setText("已确认");
                        break;
                    case 7:
                        tv_qt02.setText("服务完成");
                        break;

                    default:
                        break;
                }
                ddxq.setVisibility(View.GONE);
                if (model.getConsultStatus() == 1
                        || model.getConsultStatus() == 2
                        || model.getConsultStatus() == 3) {
                    // tvqfp.setVisibility(View.VISIBLE);
                    // tvqfp.setEnabled(true);
                    // tvqfp.setBackgroundColor(getResources().getColor(
                    // R.color.sienna));
                    // tvqfp.setOnClickListener(new OrderListBtnClick(
                    // model, aIndex));
                    ddxq.setVisibility(View.GONE);
                } else {
                    ddxq.setVisibility(View.VISIBLE);

                    // tvqfp.setVisibility(View.VISIBLE);
                    // tvqfp.setEnabled(false);
                    // tvqfp.setBackgroundColor(getResources().getColor(
                    // R.color.gray));
                }
                if ((model.getOrderStatus() == 2 || model.getOrderStatus() == 3 || model
                        .getOrderStatus() == 4)) {
                    ivq0.setVisibility(View.GONE);

                    button_map.setVisibility(GONE);
                    tvq0.setText("订单编号：");
                    tvq1.setText("经办人：");
                    tvq2.setText("客户姓名：");
                    tvq3.setText("治丧指导：");

                    tvq0.setTextColor(getContext().getResources().getColor(R.color.blackgroundmain));
                    tv_qt01.setTextColor(getContext().getResources().getColor(R.color.blackgroundmain));

                    Utils.call(ivq1, model.getAgentmanMobile());
                    Utils.call(ivq2, model.getCustomerMobile());

                    tv_qt11.setText(model.getAgentmanName());
                    tv_qt21.setText(model.getCustomerName());
                    tv_qt31.setText(model.getPerformerName());
                    tv_qt01.setText(model.getOrderNum());

                    if (model.getOrderStatus() == 2) {
                        ivq3.setVisibility(View.GONE);
                        tvqfp.setVisibility(View.VISIBLE);
                        tvqfp.setEnabled(true);
                        tvqfp.setBackgroundColor(getResources().getColor(
                                R.color.chlickcolor));
                        tvqfp.setOnClickListener(new OrderListBtnClick(model,
                                aIndex));

                    } else {
                        ivq3.setVisibility(View.VISIBLE);
                        tvqfp.setVisibility(View.GONE);
                        Utils.call(ivq3, model.getPerformerMobile());
                    }
                } else {
                    button_map.setVisibility(VISIBLE);
                    Utils.call(ivq0, model.getCustomerMobile());
                    tvq0.setText("客户姓名：");
                    tvq1.setText("洽谈时间：");
                    tvq2.setText("客户当前地址：");
                    tvq3.setText("备注：");
                    ivq1.setVisibility(View.GONE);
                    ivq2.setVisibility(View.GONE);
                    tvqfp.setVisibility(View.GONE);
                    ivq3.setVisibility(View.GONE);

                    tvq0.setTextColor(getContext().getResources().getColor(R.color.black));
                    tv_qt01.setTextColor(getContext().getResources().getColor(R.color.black));

                    if (!TextUtils.isEmpty(promiseTime)) {
                        tv_qt11.setText(mDateFormat.format(new Date(Long
                                .parseLong(model.getPromiseTime()))));
                    } else {
                        tv_qt11.setText("");
                    }
                    tv_qt21.setText(model.getCustomerAddress());
                    if (model.isShowAcceptOrReject()) {
                        tv_qt01.setText("xxx");
                        tv_qt31.setText("xxx");
                    } else {
                        tv_qt31.setText(model.getDescription());
                        tv_qt01.setText(model.getCustomerName());
                    }
                }

                OrderListBtnClick clickListener = new OrderListBtnClick(model,
                        aIndex);
                View accept = view.getView(R.id.fl_accept);
                View refues = view.getView(R.id.fl_refuse);
                View edit = view.getView(R.id.fl_edit);
                View close = view.getView(R.id.fl_close);
                View shift = view.getView(R.id.fl_shift);
                View create = view.getView(R.id.fl_create);
                // View khxq = view.getView(R.id.fl_kexq);
                View payFre = view.getView(R.id.fl_payfre);
                View tk = view.getView(R.id.fl_tk);
                TextView tvPay = ((TextView) ((ViewGroup) payFre).getChildAt(0));

                if (model.isShowSwitch2waitService()) {
                    shift.setVisibility(View.VISIBLE);
                    shift.setOnClickListener(clickListener);
                } else {
                    shift.setVisibility(View.GONE);
                }

                if (model.isHasPrepay()) {
                    payFre.setEnabled(false);

                    tvPay.setBackgroundColor(getResources().getColor(
                            R.color.gray_common));
                    tvPay.setTextColor(getContext().getResources().getColor(R.color.white));
                    tvPay.setText("定金已支付");
                } else {
                    shift.setVisibility(View.GONE);
                    tvPay.setBackgroundResource(R.drawable.bg_button_yellow);
                    tvPay.setTextColor(getContext().getResources().getColor(R.color.text_color));
                    tvPay.setText("支付定金");
                    payFre.setEnabled(true);
                }

                if (model.getOrderId() != 0) {
                    payFre.setVisibility(View.VISIBLE);
                    create.setVisibility(View.GONE);
                    payFre.setOnClickListener(clickListener);
                    tvclose.setText("结束洽谈");
                } else {
                    payFre.setVisibility(View.GONE);
                    create.setVisibility(View.VISIBLE);
                    tvclose.setText("洽谈失败，结束洽谈");
                }

                if (model.getConsultStatus() == 4
                        || model.getConsultStatus() == 5
                        ) {
                    payFre.setVisibility(View.GONE);
                }
                if (model.isShowAcceptOrReject()) {
                    // tv_qt01.setText("xxx");
                    // tv_qt31.setText("xxx");
                    create.setVisibility(View.GONE);
                    // khxq.setVisibility(View.GONE);
                    accept.setVisibility(View.VISIBLE);
                    refues.setVisibility(View.VISIBLE);
                    accept.setOnClickListener(clickListener);
                    refues.setOnClickListener(clickListener);
                } else {
                    // tv_qt31.setText(model.getDescription());
                    // tv_qt01.setText(model.getCustomerName());
                    // khxq.setVisibility(View.VISIBLE);
                    accept.setVisibility(View.GONE);
                    refues.setVisibility(View.GONE);
                }
                if (model.isShowEditOrder()) {
                    edit.setVisibility(View.VISIBLE);
                    edit.setOnClickListener(clickListener);
                } else {
                    edit.setVisibility(View.GONE);
                }
                if (model.isShowFinishTalk()) {
                    if (model.isHasPrepay()) {
                        close.setVisibility(View.VISIBLE);
                        close.setOnClickListener(clickListener);
                    } else {
                        close.setVisibility(View.GONE);
                    }
                    if (model.getOrderId() == 0) {
                        close.setVisibility(View.VISIBLE);
                        close.setOnClickListener(clickListener);
                    }
                } else {
                    close.setVisibility(View.GONE);
                }


                create.setOnClickListener(clickListener);
                ddxq.setOnClickListener(clickListener);
                tk.setOnClickListener(clickListener);
                // khxq.setOnClickListener(clickListener);
                Log.v("this", "getOrderId():" + model.getOrderId());
                if (model.getOrderId() == 0 && !model.isShowFirstTalk()) {
                    tv_talkagain.setVisibility(VISIBLE);
                    tv_talkagain.setOnClickListener(clickListener);
                } else {
                    tv_talkagain.setVisibility(GONE);
                }
                tishi.setOnClickListener(clickListener);
            }
        });
    }

    public class OrderListBtnClick implements OnClickListener {

        OrderListModel model;
        int position;


        public OrderListBtnClick(OrderListModel model, int position) {
            this.model = model;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_data:
                    tishi();
                    break;
                case R.id.fl_accept:
                    accept();
                    break;
                case R.id.fl_refuse:
                    refuse();
                    break;
                case R.id.fl_create:
                    create();
                    break;
                case R.id.fl_close:
                    close();
                    break;
                case R.id.fl_shift:
                    shift();
                    break;
                case R.id.fl_edit:
                    edit();
                    break;
                case R.id.fl_kexq:
                    khxq();
                    break;
                case R.id.fl_payfre:
                    payFre();
                    break;
                case R.id.iv_qt32:
                    assignNotify();
                    break;
                case R.id.fl_ddxq:
                    Intent in = new Intent(getContext(), OrderDetailActivity.class);
                    in.putExtra("orderId", model.getOrderId());
                    in.putExtra("consultId", model.getConsultId());
                    getContext().startActivity(in);
                    break;
                case R.id.fl_tk:
                    Intent in1 = new Intent(getContext(), RefundActivity.class);
                    in1.putExtra("orderId", model.getOrderId());
                    in1.putExtra("consultId", model.getConsultId());
                    getContext().startActivity(in1);
                    break;
                case R.id.tv_talkagain:
                    //二次洽谈资料
                    Log.v("this", "二次洽谈资料");
                    HpConsultIdParams params = new HpConsultIdParams();
                    params.setConsultId(model.getConsultId());
                    MHttpManagerFactory.getAccountManager().getTalkFailData(getContext(),
                            params, new HttpResponseHandler<HrGetTalkFail>() {

                                @Override
                                public void onSuccess(HrGetTalkFail result) {
                                    // TODO Auto-generated method stub
                                    TalkDataDialog dialog = new TalkDataDialog(getContext(), R.style.CustomDialog, result);
                                    dialog.show();
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

                    break;
            }

        }

        private void tishi() {
            AlertDialog dialog = new AlertDialog.Builder(getContext())
                    .setTitle("提示")
                    .setMessage("【需要及时服务】\n指已确认往生者逝世，需要立即派单开始服务。")
                    .setPositiveButton("确认", null)
                    .create();
            dialog.show();
        }

        private void assignNotify() {
            HpOrderIdParams params = new HpOrderIdParams();
            params.setOrderId(model.getOrderId());
            MHttpManagerFactory.getAccountManager().assignNotify(getContext(),
                    params, new HttpResponseHandler<Object>() {

                        @Override
                        public void onSuccess(Object result) {
                            // TODO Auto-generated method stub
                            ToastUtils.show(getContext(), "申请分配成功");
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

        /**
         * 编辑订单
         */
        private void edit() {
            Intent mIntent = new Intent(getContext(), EditOrderActivity.class);
            mIntent.putExtra("khxqtype", 0);
            mIntent.putExtra("consultId", model.getConsultId());
            mIntent.putExtra("orderId", model.getOrderId());
            getContext().startActivity(mIntent);
        }

        /**
         * 支付定金
         */
        private void payFre() {
            long orderId = model.getOrderId();
            if (orderId != 0) {
                Intent in = new Intent(getContext(), PayActivity.class);
                in.putExtra("orderId", orderId);
                in.putExtra("orderCode", model.getOrderNum());
                getContext().startActivity(in);
            } else {
                ToastUtils.show(getContext(), "订单尚未创建，无法支付定金！");
            }
        }

        /**
         * 转待服务
         */
        private void shift() {

            TipsDialog mDialog = new TipsDialog(getContext());
            mDialog.setTitle("转待服务后治丧指导可进行下一步的客户服务，请确认是否转待服务。");
            mDialog.setBottomButton("确认", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    HpConsultIdParams params = new HpConsultIdParams();
                    params.setConsultId(model.getConsultId());
                    MHttpManagerFactory.getAccountManager().switch2waitService(
                            getContext(), params,
                            new HttpResponseHandler<Object>() {
                                @Override
                                public void onStart() {
                                }

                                @Override
                                public void onSuccess(Object result) {
                                    refresh();
                                }

                                @Override
                                public void onError(String message) {

                                }
                            });

                }
            });
            mDialog.setTopButton("未确认", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            mDialog.show();
        }

        /**
         * 结束洽谈
         */
        private void close() {

            if (model.getOrderId() == 0) {
                //未创建订单时
                Log.v("this", "未创建订单时");
                Log.v("this", "是否是第一次洽谈:" + model.isShowFirstTalk());
                Intent intent = new Intent(getContext(), SaveTalkFailActivity.class);
                intent.putExtra("SaveTalkFailActivity", model.getConsultId());
                getContext().startActivity(intent);
            } else {
                //创建订单后
                Log.v("this", "创建订单后");
                Log.v("this", "step:" + model.getStep());
                Intent intent = null;
                switch (model.getStep()) {
                    case 0:
                        //未进行任何步骤
                        intent = new Intent(getContext(), WSZDataActivity.class);
                        intent.putExtra("consultId", model.getConsultId());
                        intent.putExtra("orderId", model.getOrderId());
                        break;
                    case 1:
                        //提交了往生者信息
                        intent = new Intent(getContext(), JBRDataActivity.class);
                        intent.putExtra("consultId", model.getConsultId());
                        intent.putExtra("orderId", model.getOrderId());
                        break;
                    case 2:
                        //提交了经办人信息
                        intent = new Intent(getContext(), ContractDataActivity.class);
                        intent.putExtra("consultId", model.getConsultId());
                        intent.putExtra("orderId", model.getOrderId());
                        break;
//                    case 3:
//                        //提交了预备信息资料
//                        intent = new Intent(getContext(), ContractDataActivity.class);
//                        intent.putExtra("consultId", model.getConsultId());
//                        intent.putExtra("orderId", model.getOrderId());
//                        break;
                    case 3:
                        //提交了合同
                        break;

                }
                if (intent != null) {
                    getContext().startActivity(intent);
                }
//                TipsDialog mDialog = new TipsDialog(getContext());
//                mDialog.setTitle("请确认客户信息已填写完善。");
//                mDialog.setTopButton("继续洽谈", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                mDialog.setBottomButton("结束洽谈",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                HpConsultIdParams params = new HpConsultIdParams();
//                                params.setConsultId(model.getConsultId());
//                                MHttpManagerFactory.getAccountManager().talkFinish(
//                                        getContext(), params,
//                                        new HttpResponseHandler<Object>() {
//
//                                            @Override
//                                            public void onStart() {
//
//                                            }
//
//                                            @Override
//                                            public void onSuccess(Object result) {
//                                                refresh();
//
//                                            }
//
//                                            @Override
//                                            public void onError(String message) {
//
//                                            }
//                                        });
//                            }
//                        });
//                mDialog.show();
            }


        }

        /**
         * 创建订单
         */
        private void create() {
            Intent mIntent = new Intent(getContext(), EditOrderActivity.class);
            mIntent.putExtra("consultId", model.getConsultId());
            getContext().startActivity(mIntent);
        }

        /**
         * 拒单
         */
        private void refuse() {
            HpRejectParams params = new HpRejectParams();
            params.setId(model.getConsultAssignId());
            params.setConsultId(model.getConsultId());
            params.setOrderId(model.getOrderId());
            MHttpManagerFactory.getAccountManager().reject(getContext(),
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

                        }
                    });

        }

        /**
         * 接单
         */
        private void accept() {
            HpAcceptParams params = new HpAcceptParams();
            params.setId(model.getConsultAssignId());
            params.setConsultId(model.getConsultId());
            params.setOrderId(model.getOrderId());
            params.setTalkGpsAddress("北京");
            MHttpManagerFactory.getAccountManager().accept(getContext(),
                    params, new HttpResponseHandler<Object>() {

                        @Override
                        public void onSuccess(Object result) {
                            // TODO Auto-generated method stub
                            refresh();
                            ToastUtils.show(getContext(), "接单成功");
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

        /**
         * 客户详情
         */
        private void khxq() {
            Intent in = new Intent(getContext(), CustomerActivity.class);
            in.putExtra("consultId", model.getConsultId());
            in.putExtra("orderId", model.getOrderId());
            getContext().startActivity(in);
        }


    }

    public void setWH() {

    }
}
