package com.shian.shianlife.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.PicassoUD;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.TArrayListAdapter.IOnDrawViewEx;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpAcceptParams;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.params.HpSaveCustomerContract.AddAddition;
import com.shian.shianlife.provide.params.HpSkuIdParams;
import com.shian.shianlife.provide.result.HrGetSKUDetails;
import com.shian.shianlife.provide.result.HrOrderItenList;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.shian.shianlife.provide.result.OrderItem;
import com.shian.shianlife.view.PGZXLayoutView;
import com.shian.shianlife.view.PGZXOtherLayoutView;
import com.shian.shianlife.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class PgzxActivity extends BaseActivity {
    @InjectView(R.id.tv_neworder1)
    TextView tvNew;
    @InjectView(R.id.tv_refund)
    TextView tvRefund;
    private ScrollListView mListView;
    private TArrayListAdapter<OrderItem> adapter;
    private FrameLayout LLPGZXState;
    private LinearLayout LLPDZT;
    boolean isShenhe;
    boolean isShoukuan;

    TextView newOrder;
    TextView newOrder2;

    TextView mTVAllNum;
    TextView mTVWaitNum;
    TextView mTVPJNum;
    TextView mTVShenHeNum;

    TextView mTV1;
    TextView mTV2;

    int khxqType;

    public int orderStatus = 0;

    PGZXOtherLayoutView mPgzxLayoutView1;
    PGZXOtherLayoutView mPgzxLayoutView2;
    PGZXOtherLayoutView mPgzxLayoutView3;

    ImageView mTVOver;

    long orderId;
    long consultId;

    int waitPDNum = 0;
    int waitPJNum = 0;
    int waitSHNum = 0;
    int completeNum = 0;

    public static String PGZX_ACTION = "PgzxActivity";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_pgzx);

        isShenhe = getIntent().getBooleanExtra("isShenhe", false);
        isShoukuan = getIntent().getBooleanExtra("isShoukuan", false);
        khxqType = getIntent().getIntExtra("khxqtype", -1);

        initView();
        initDate();
        getOrderList();


        if (isShenhe || isShoukuan) {
            setTitle("派单列表详情");
            LLPGZXState.setVisibility(View.GONE);
//            newOrder.setVisibility(View.GONE);
            newOrder2.setVisibility(View.GONE);
            LLPDZT.setVisibility(View.GONE);
        } else {
            setTitle("派工执行");
        }


        IntentFilter filter = new IntentFilter();
        filter.addAction(PGZX_ACTION);
        registerReceiver(refresh, filter);
    }

    private void initView() {
        mListView = (ScrollListView) findViewById(R.id.lv_swipe_listview);
        adapter = new TArrayListAdapter<OrderItem>(this);
        newOrder = (TextView) findViewById(R.id.tv_neworder1);
        newOrder2 = (TextView) findViewById(R.id.tv_neworder2);
        LLPDZT = (LinearLayout) findViewById(R.id.ll_data);
        LLPGZXState = (FrameLayout) findViewById(R.id.ll_pgzx);
        mTVOver = (ImageView) findViewById(R.id.tv_serviceover);
        mPgzxLayoutView1 = (PGZXOtherLayoutView) findViewById(R.id.pgzx_1);
        mPgzxLayoutView2 = (PGZXOtherLayoutView) findViewById(R.id.pgzx_2);
        mPgzxLayoutView3 = (PGZXOtherLayoutView) findViewById(R.id.pgzx_3);


        mTVAllNum = (TextView) findViewById(R.id.tv_allnum);
        mTVWaitNum = (TextView) findViewById(R.id.tv_waitnum);
        mTVPJNum = (TextView) findViewById(R.id.tv_pjnum);
        mTVShenHeNum = (TextView) findViewById(R.id.tv_shenhenum);


        mTV1 = (TextView) findViewById(R.id.tv_1);
        mTV2 = (TextView) findViewById(R.id.tv_btn2);

        newOrder.setOnClickListener(newOrderClickListener);
        newOrder2.setOnClickListener(newOrder2ClickListener);
        tvRefund.setOnClickListener(reFundClick);
        mTV1.setOnClickListener(onClickListener);
        mTV2.setOnClickListener(onClickListener);
    }

    private OnClickListener newOrder2ClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            over();
        }
    };
    private OnClickListener newOrderClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(PgzxActivity.this, EditOrderActivity.class);
            in.putExtra("pgzx", 1);
            in.putExtra("khxqtype", khxqType);
            in.putExtra("orderId", getIntent().getLongExtra("orderId", 0));
            in.putExtra("consultId", getIntent().getLongExtra("consultId", 0));
            startActivity(in);
        }
    };

    private OnClickListener reFundClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent in = new Intent(PgzxActivity.this, RefundOrderActivity.class);
            in.putExtra("orderId", getIntent().getLongExtra("orderId", 0));
            in.putExtra("consultId", getIntent().getLongExtra("consultId", 0));
            startActivity(in);
        }
    };


    private void initDate() {
        initAdapter();
        orderId = getIntent().getLongExtra("orderId", 0);
        consultId = getIntent().getLongExtra("consultId", 0);

        mPgzxLayoutView1.setID(orderId, consultId, 1);
        mPgzxLayoutView2.setID(orderId, consultId, 2);
        mPgzxLayoutView3.setID(orderId, consultId, 3);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }


    private void initAdapter() {
        adapter.setLayout(R.layout.view_item_pgzx);
        adapter.setDrawViewEx(new IOnDrawViewEx<OrderItem>() {

            @Override
            public void OnDrawViewEx(Context aContext,
                                     final OrderItem templateItem, ViewGropMap view, int aIndex) {
                LinearLayout llPic = (LinearLayout) view
                        .getView(R.id.ll_ht_pic);
                LinearLayout remakell = (LinearLayout) view.getView(R.id.remakell);
                LinearLayout pdrlocationll = (LinearLayout) view.getView(R.id.ll_pdrlocation);
                final ImageView ivPic0 = (ImageView) view
                        .getView(R.id.btn_ht_pic_0);
                final ImageView ivPic1 = (ImageView) view
                        .getView(R.id.btn_ht_pic_1);
                ivPic0.setImageResource(R.drawable.ic_ht_pic);
                ivPic1.setImageResource(R.drawable.ic_ht_pic);
                Button btnSave = (Button) view.getView(R.id.btn_save);
                btnSave.setVisibility(View.VISIBLE);
                final EditText etBz = (EditText) view.getView(R.id.et_oi_bz);
                TextView tv0 = (TextView) view.getView(R.id.tv_oi_0);
                TextView tv1 = (TextView) view.getView(R.id.tv_oi_1);
                TextView tv2 = (TextView) view.getView(R.id.tv_oi_2);
                TextView tv3 = (TextView) view.getView(R.id.tv_oi_3);
                final TextView tv4 = (TextView) view.getView(R.id.tv_oi_4);
                TextView tv5 = (TextView) view.getView(R.id.tv_oi_5);
                TextView name = (TextView) view.getView(R.id.tv_pgzx_name);
                TextView ev = (TextView) view.getView(R.id.tv_pgzx_ev);
                ImageView ivPhone = (ImageView) view.getView(R.id.iv_phone);
                ImageView ivData = (ImageView) view.getView(R.id.iv_data);

                TextView tvPdrLocation = (TextView) view.getView(R.id.text_pdrlocation);
                Button btnPdrLocation = (Button) view.getView(R.id.button_pdrlocation);

                ivData.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.getSKUDetails(PgzxActivity.this, templateItem.getSkuId());
                    }
                });


                if (!isShenhe) {
                    llPic.setVisibility(View.VISIBLE);
                } else {
                    llPic.setVisibility(View.GONE);
                }
                if (isShenhe || isShoukuan) {
                    llPic.setVisibility(View.GONE);
                    remakell.setVisibility(View.GONE);

                } else {
                    setTitle("派工执行");
                    llPic.setVisibility(View.VISIBLE);
                }


                if (!TextUtils.isEmpty(templateItem.getUrl2())) {
                    ImageLoader.getInstance().displayImage(templateItem.getUrl2(), ivPic1);
                } else {
                    ivPic1.setBackgroundResource(R.drawable.ic_ht_pic);
                }
                if (!TextUtils.isEmpty(templateItem.getUrl1())) {
                    ImageLoader.getInstance().displayImage(templateItem.getUrl1(), ivPic0);
                } else {
                    ivPic0.setBackgroundResource(R.drawable.ic_ht_pic);
                }


                ivPic0.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (templateItem.getAdditions().size() > 0) {
                            showPic(AppContansts.OSSURL
                                    + templateItem.getAdditions().get(0)
                                    .getFileUrl());
                        } else
                            btnPicClick(ivPic0, templateItem, 1);
                    }
                });
                ivPic1.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (templateItem.getAdditions().size() > 1) {
                            showPic(AppContansts.OSSURL
                                    + templateItem.getAdditions().get(1)
                                    .getFileUrl());
                        } else
                            btnPicClick(ivPic1, templateItem, 2);
                    }
                });
                btnSave.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        saveNote(etBz.getText().toString(),
                                templateItem.getId());
                    }
                });
                tv3.setText(templateItem.getSpecification());
                tv0.setText(templateItem.getSkuCtgName());
                tv1.setText(templateItem.getSkuName());
                tv2.setText("数量:" + templateItem.getNumber() + "套");
                // tv0.setText(templateItem.get);
                if (!TextUtils.isEmpty(templateItem.getNote())) {
                    etBz.setText(templateItem.getNote());
                }
                if (TextUtils.isEmpty(templateItem.getExecutorName())) {
                    ((ViewGroup) name.getParent()).setVisibility(View.GONE);
                } else {
                    ((ViewGroup) name.getParent()).setVisibility(View.VISIBLE);
                }
                name.setText(templateItem.getExecutorName() + "/"
                        + templateItem.getExecutorId());
                Utils.call(ivPhone, templateItem.getExecutorMobile());
                // tv5.setText("备注说明:" + templateItem.getNote());
                if (isShenhe) {
                    if (templateItem.getAdditions().size() == 0) {
                        ivPic0.setEnabled(false);
                        ivPic1.setEnabled(false);
                    } else if (templateItem.getAdditions().size() == 1) {
                        ivPic1.setEnabled(false);
                        ivPic0.setEnabled(true);
                    } else {
                        ivPic1.setEnabled(true);
                        ivPic0.setEnabled(true);
                    }
                    btnSave.setVisibility(View.GONE);
                    if (templateItem.getItemStatus() != 5) {
//                        ev.setBackgroundColor(getResources().getColor(
//                                R.color.gray_common));
                        ev.setBackgroundResource(R.drawable.zhy_button_state_item_gray);
                        ev.setTextColor(Color.WHITE);
                        ev.setEnabled(false);
                    } else {
                        if (templateItem.isHasComment()) {
                            ev.setEnabled(true);
//                            ev.setBackgroundColor(getResources().getColor(
//                                    R.color.orangeyellow));
                            ev.setBackgroundResource(R.drawable.zhy_button_state_5);
                            ev.setTextColor(getResources().getColor(R.color.white));
                        } else {
//                            ev.setBackgroundColor(getResources().getColor(
//                                    R.color.gray_common));
                            ev.setBackgroundResource(R.drawable.zhy_button_state_item_gray);
                            ev.setEnabled(false);
                            ev.setTextColor(Color.WHITE);
                        }
                    }
                    ev.setText("审核");
                    ev.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            Intent in = new Intent(PgzxActivity.this,
                                    AuditActivity.class);
                            in.putExtra("orderItemId", templateItem.getId());
                            startActivity(in);
                        }
                    });
                    tv4.setEnabled(false);

                } else if (isShoukuan) {
                    if (templateItem.getAdditions().size() == 0) {
                        ivPic0.setEnabled(false);
                        ivPic1.setEnabled(false);
                    } else if (templateItem.getAdditions().size() == 1) {
                        ivPic1.setEnabled(false);
                        ivPic0.setEnabled(true);
                    } else {
                        ivPic1.setEnabled(true);
                        ivPic0.setEnabled(true);
                    }
                    btnSave.setVisibility(View.GONE);
                    ev.setVisibility(View.GONE);
                    tv4.setEnabled(false);
                } else {
                    tv4.setEnabled(true);
                    // if (templateItem.getItemStatus() == 1
                    // || templateItem.getItemStatus() == 2
                    // || templateItem.getItemStatus() == 3
                    // || templateItem.getItemStatus() == 4) {
                    ev.setVisibility(View.GONE);
                    // } else {
                    // ev.setVisibility(View.VISIBLE);
                    // }
                    ev.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(PgzxActivity.this,
                                    EvaluateActivity.class);
                            intent.putExtra("orderId", getIntent()
                                    .getLongExtra("orderId", 0));
                            intent.putExtra("orderItemId", templateItem.getId());
                            startActivity(intent);
                        }
                    });
                }

                if (templateItem.getAdditions().size() > 0) {
                    PicassoUD.loadImage(getBaseContext(),
                            AppContansts.OSSURL
                                    + templateItem.getAdditions().get(0)
                                    .getFileUrl(), ivPic0);
                    if (templateItem.getAdditions().size() > 1) {
                        PicassoUD.loadImage(getBaseContext(),
                                AppContansts.OSSURL
                                        + templateItem.getAdditions()
                                        .get(1).getFileUrl(),
                                ivPic1);
                    } else {
                        ivPic1.setBackgroundResource(R.drawable.ic_ht_pic);
                    }
                    llPic.setVisibility(View.VISIBLE);
                } else {
                    if (templateItem.getItemStatus() != 1)
                        llPic.setVisibility(View.GONE);
                }
                if (templateItem.getItemStatus() != 1) {
                    etBz.setEnabled(false);
                    // ivPic0.setEnabled(false);
                    // ivPic1.setEnabled(false);
                    btnSave.setVisibility(View.GONE);


                } else {
                    btnSave.setVisibility(View.VISIBLE);
                    etBz.setEnabled(true);
                    // ivPic0.setEnabled(true);
                    // ivPic1.setEnabled(true);
                }

                tv4.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (templateItem.getItemStatus() != 1) return;
                        TipsDialog mDialog = new TipsDialog(PgzxActivity.this);
                        mDialog.setTitle("派单后将不可取消，请确认是否现在派此项执行单。");
                        mDialog.setTopButton("稍后派单", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        mDialog.setBottomButton("现在派单",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HpAcceptParams params = new HpAcceptParams();
                                        params.setOrderItemId(templateItem.getId());
                                        MHttpManagerFactory.getAccountManager()
                                                .applyDispatch(PgzxActivity.this, params,
                                                        new HttpResponseHandler<Object>() {

                                                            @Override
                                                            public void onSuccess(
                                                                    Object result) {
                                                                ToastUtils.show(
                                                                        getBaseContext(),
                                                                        "派单成功");
                                                                tv4.setText("派单中");
                                                                tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                                                                waitPDNum--;
                                                                mTVWaitNum.setText(waitPDNum + "");
                                                                templateItem.setItemStatus(2);
                                                            }

                                                            @Override
                                                            public void onStart() {
                                                                // TODO Auto-generated
                                                                // method stub

                                                            }

                                                            @Override
                                                            public void onError(
                                                                    String message) {
                                                                // TODO Auto-generated
                                                                // method stub

                                                            }
                                                        });
                                    }
                                });
                        mDialog.show();

                    }
                });

                switch (templateItem.getItemStatus()) {
                    case 1:
                        tv4.setText("派单");
                        if (isShenhe || isShoukuan) {
                            tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        } else {
                            tv4.setBackgroundResource(R.drawable.zhy_button_state_2);
                        }
                        break;
                    case 2:
                        tv4.setText("派单中");
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        break;
                    case 3:
                        tv4.setText("已接受");
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        break;
                    case 4:
                        tv4.setText("正在执行");
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        pdrlocationll.setVisibility(View.VISIBLE);
                        if (templateItem.getPdrLocation() == null) {
                            btnPdrLocation.setVisibility(View.GONE);
                        } else {
                            tvPdrLocation.setText(templateItem.getPdrLocation());
                            btnPdrLocation.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(PgzxActivity.this, BaiduMapActivity.class);
                                    intent.putExtra("pdrLocation", templateItem.getPdrLocation());
                                    startActivity(intent);
                                }
                            });
                        }
                        break;
                    case 5:
                        if (templateItem.isHasComment()) {
                            tv4.setText("待审核");
                            if (!isShenhe)
                                ev.setVisibility(View.GONE);
                        } else {
                            ev.setVisibility(View.VISIBLE);
                            tv4.setText("待评价");
                            if (isShoukuan) {
                                ev.setVisibility(View.GONE);
                            }
                        }
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        break;
                    case 6:
                        tv4.setText("审核未通过");
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        break;
                    case 7:
                        tv4.setText("审核通过");
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_item_white);
                        break;
                    default:
                        tv4.setBackgroundResource(R.drawable.zhy_button_state_2);
                        break;
                }
            }
        });
    }

    public void setLayout() {
        mListView.setAdapter(adapter);
        ScrollListView.setListViewHeightBasedOnChildren(mListView);
    }

    private void getOrderList() {
        HpAcceptParams params = new HpAcceptParams();
        params.setOrderId(getIntent().getLongExtra("orderId", 0));
        MHttpManagerFactory.getAccountManager().getOrderItemList(this, params,
                new HttpResponseHandler<HrOrderItenList>() {

                    @Override
                    public void onSuccess(HrOrderItenList result) {
                        // TODO Auto-generated method stub
                        if (result.getOrderHandleStatus() == 7) {
                            tvNew.setVisibility(View.VISIBLE);
                            tvNew.setEnabled(false);
                            tvNew.setBackgroundResource(R.drawable.zhy_button_state_item_gray);
                            tvNew.setTextColor(Color.WHITE);
                        } else {
                            if (!result.isCanEdit()) {
                                tvNew.setEnabled(false);
                                tvNew.setVisibility(View.VISIBLE);
                                tvNew.setBackgroundResource(R.drawable.zhy_button_state_item_gray);
                                tvNew.setTextColor(Color.WHITE);
                            } else if (isShenhe || isShoukuan) {
                                tvNew.setVisibility(View.GONE);
                            } else {
                                tvNew.setVisibility(View.VISIBLE);
                            }
                        }
                        if (result.isCanRefund() && !isShenhe && !isShoukuan) {
                            tvRefund.setVisibility(View.VISIBLE);
                        } else {
                            tvRefund.setVisibility(View.GONE);
                        }

                        adapter.clear();
                        adapter.addListData(result.getItems());
                        setLayout();
                        adapter.notifyDataSetChanged();
                        orderStatus = result.getOrderHandleStatus();

                        setNum(result);
                        setState();
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

    private void setNum(HrOrderItenList result) {
        if (result.getItems() != null) {
            List<OrderItem> listdata = result.getItems();
            mTVAllNum.setText(listdata.size() + "");
            for (int i = 0; i < listdata.size(); i++) {

                int state = listdata.get(i).getItemStatus();
                if (state == 1) {
                    waitPDNum++;
                } else if (state == 5) {
                    if (!listdata.get(i).isHasComment()) {
                        waitPJNum++;
                    } else {
                        waitSHNum++;
                    }
                } else if (state == 7 && listdata.get(i).isHasComment()) {
                    completeNum++;
                }
            }
            if (orderStatus == 7 && completeNum == result.getItems().size()) {
                if (isShenhe || isShoukuan) {
                    newOrder2.setVisibility(View.GONE);
                } else {
                    newOrder2.setVisibility(View.VISIBLE);
                }
            }
            mTVPJNum.setText(waitPJNum + "");
            mTVShenHeNum.setText(waitSHNum + "");
            mTVWaitNum.setText(waitPDNum + "");
        }
    }

    private void setState() {
        if (isShenhe || isShoukuan) {
        } else {
            LLPGZXState.setVisibility(View.VISIBLE);
            LLPDZT.setVisibility(View.VISIBLE);
        }
        switch (orderStatus) {
            case 0:
                mPgzxLayoutView1.setState(2, 1, 1);
                mPgzxLayoutView2.setState(1, 1, 1);
                mPgzxLayoutView3.setState(1, 1, 1);
                break;
            case 1:
                mPgzxLayoutView1.setState(3, 2, 1);
                mPgzxLayoutView2.setState(1, 1, 1);
                mPgzxLayoutView3.setState(1, 1, 1);
                break;
            case 2:
                mPgzxLayoutView1.setState(3, 3, 4);
                mPgzxLayoutView2.setState(2, 1, 1);
                mPgzxLayoutView3.setState(1, 1, 1);
                break;
            case 3:
                mPgzxLayoutView1.setState(3, 3, 4);
                mPgzxLayoutView2.setState(3, 2, 1);
                mPgzxLayoutView3.setState(1, 1, 1);
                break;
            case 4:
                mPgzxLayoutView1.setState(3, 3, 4);
                mPgzxLayoutView2.setState(3, 3, 4);
                mPgzxLayoutView3.setState(2, 1, 1);
                break;
            case 5:
                mPgzxLayoutView1.setState(3, 3, 4);
                mPgzxLayoutView2.setState(3, 3, 4);
                mPgzxLayoutView3.setState(3, 2, 1);
                break;
            case 6:
                mPgzxLayoutView1.setState(3, 3, 4);
                mPgzxLayoutView2.setState(3, 3, 4);
                mPgzxLayoutView3.setState(3, 3, 2);
                break;
            case 7:
                mPgzxLayoutView1.setState(3, 3, 4);
                mPgzxLayoutView2.setState(3, 3, 4);
                mPgzxLayoutView3.setState(3, 3, 3);
                mTVOver.setVisibility(View.VISIBLE);
                break;
        }

    }

    void btnPicClick(final ImageView v, final OrderItem templateItem, final int dex) {
        showPhotoPicker();
        setOnPhotoPickerListener(new OnPhotoPickerListener() {

            @Override
            public void onPhoto(ArrayList<String> paths) {
                // TODO Auto-generated method stub
                uploadFile(v, v.getTag().toString(), paths.get(0));
                if (dex == 1) {
                    templateItem.setUrl1(
                            "file://" + paths.get(0));
                } else {
                    templateItem.setUrl2(
                            "file://" + paths.get(0));
                }
                ImageLoader.getInstance().displayImage(
                        "file://" + paths.get(0), v);
            }
        });

    }

    private void showPic(String url) {
        Intent in = new Intent(this, ImagePreviewActivity.class);
        in.putExtra("url", url);
        startActivity(in);
    }

    private void uploadFile(final ImageView ib, final String file, String path) {
        final ProgressBar pbVIew = (ProgressBar) ((ViewGroup) ib.getParent())
                .getChildAt(1);
        MHttpManagerFactory.getFileManager().upLoadFile(this, file, path,
                new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        ToastUtils.show(PgzxActivity.this, "上传成功");
                        AddAddition add = new HpSaveCustomerContract.AddAddition();
                        add.setFileName(file);
                        add.setFileUrl(t.getNameMap().get(file).toString());
                        addList.add(add);
                        pbVIew.setVisibility(View.GONE);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long total, long current,
                                           boolean isUploading) {
                        pbVIew.setVisibility(View.VISIBLE);
                        pbVIew.setProgress((int) (current / total * 100));
                    }

                    @Override
                    public void onError(String message) {

                    }

                });
    }

    HpSaveCustomerContract params = new HpSaveCustomerContract();
    List<AddAddition> addList = new ArrayList<AddAddition>();

    private void saveNote(String note, long id) {
        params.setAddAdditions(addList);
        params.setOrderItemId(id);
        params.setNote(note);
        MHttpManagerFactory.getAccountManager().saveItemNote(this, params,
                new HttpResponseHandler<Object>() {
                    @Override
                    public void onSuccess(Object result) {
                        // TODO Auto-generated method stub
                        ToastUtils.show(getBaseContext(), "保存备注和图片成功");
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
     * 结束派单
     */
    private void over() {
        TipsDialog dialog = new TipsDialog(PgzxActivity.this);
        dialog.setTitle("请确认客户无需其他服务，结束服务后将不能再编辑订单。");
        dialog.setTopButton("继续服务", null);
        dialog.setBottomButton("结束服务", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                HpAcceptParams params = new HpAcceptParams();
                params.setOrderId(orderId);
                MHttpManagerFactory.getAccountManager().endService(PgzxActivity.this, params,
                        new HttpResponseHandler<Object>() {

                            @Override
                            public void onSuccess(Object result) {
                                // TODO Auto-generated method stub
                                ToastUtils.show(PgzxActivity.this, "结束派单成功");
                                finish();
                                OrderFragment.C_bOrder_isRefresh = true;
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

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTV1 || view == mTV2) {
                String text = "";
                if (mTV1 == view) {
                    text = getString(R.string.tishi_1);
                } else {
                    text = getString(R.string.tishi_2);
                }
                AlertDialog dialog = new AlertDialog.Builder(PgzxActivity.this)
                        .setTitle("提示")
                        .setMessage(text)
                        .setPositiveButton("确定", null)
                        .create();
                dialog.show();
            }
        }
    };

    BroadcastReceiver refresh = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            waitPDNum = 0;
            waitPJNum = 0;
            waitSHNum = 0;
            completeNum = 0;
            getOrderList();
        }
    };
}
