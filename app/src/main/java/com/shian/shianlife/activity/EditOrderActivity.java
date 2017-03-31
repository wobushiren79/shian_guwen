package com.shian.shianlife.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.view.TipsDialog;
import com.shian.shianlife.common.view.editor.AddedSetmealOtherView;
import com.shian.shianlife.common.view.editor.CemeterySetmealView;
import com.shian.shianlife.common.view.editor.CemeterySetmealView.OnCemeteryChangeListener;
import com.shian.shianlife.common.view.editor.FuneralSetmealOtherView;
import com.shian.shianlife.common.view.editor.FuneralSetmealView;
import com.shian.shianlife.common.view.editor.FuneralSetmealView.OnFuneralChangeListener;
import com.shian.shianlife.common.view.editor.MainSetmealOtherView;
import com.shian.shianlife.common.view.editor.MainSetmealView;
import com.shian.shianlife.common.view.editor.MainSetmealView.OnMainChangeListener;
import com.shian.shianlife.common.view.order.AddedSetmealView;
import com.shian.shianlife.common.view.order.AddedSetmealView.OnAddedChangeListener;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.imp.impl.ProductManagerImpl;
import com.shian.shianlife.provide.model.CemeteryModel;
import com.shian.shianlife.provide.model.CreateOrderProductItemModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpCreateOrderParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.result.HrGetCemeteryResult;
import com.shian.shianlife.provide.result.HrGetFuneralSetmealResult;
import com.shian.shianlife.provide.result.HrGetMainSetmealResult;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.provide.result.HrOderId;

public class EditOrderActivity extends BaseActivity {
    @InjectView(R.id.csv)
    CemeterySetmealView cemeterySetmealView;
    @InjectView(R.id.asv)
    AddedSetmealView addedSetmealView;
    @InjectView(R.id.asvother)
    AddedSetmealOtherView addedSetmealOtherView;
    @InjectView(R.id.tv_total)
    TextView tv_total;
    @InjectView(R.id.msv)
    MainSetmealView mainSetmealView;
    @InjectView(R.id.msvother)
    MainSetmealOtherView mainSetmealOtherView;
    @InjectView(R.id.fsv)
    FuneralSetmealView funeralSetmealView;
    @InjectView(R.id.fsvother)
    FuneralSetmealOtherView funeralSetmealOtherView;
    @InjectView(R.id.rl_top)
    View rltop;
    /**
     * 咨询ID
     */
    long consultId;
    /**
     * 订单ID
     */
    long orderId;
    private List<SetmealModel> mainSetmeals;// 系统主套餐
    private List<SetmealModel> funeralSetmeals;// 系统殡仪馆信息
    private List<CemeteryModel> cemeteries;// 系统公墓信息
    /**
     * 用户之前提交的订单详情内容
     */
    List<ProjectItemModel> projectItems;
    /**
     * 上次编辑或者创建订单提交的产品项
     */
    List<CreateOrderProductItemModel> mLastProducts;

    float totalPrice;
    int khxqType;
    int pgzx;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_editorder);
        setTitle("编辑订单");

        consultId = getIntent().getLongExtra("consultId", -1);
        orderId = getIntent().getLongExtra("orderId", -1);
        khxqType = getIntent().getIntExtra("khxqtype", -1);
        pgzx = getIntent().getIntExtra("pgzx", -1);
        mLastProducts = new ArrayList<CreateOrderProductItemModel>();
//		if (orderId != -1) {
        rltop.setVisibility(View.VISIBLE);
//		}
        if (consultId <= 0) {
            ToastUtils.show(this, "无法新建或者编辑订单！");
        }
        getMainSetmeals();
    }

    /**
     * 获取套餐信息
     */
    private void getMainSetmeals() {
        ProductManagerImpl.getInstance().getMainSetmeal(this,
                new HttpResponseHandler<HrGetMainSetmealResult>() {

                    @Override
                    public void onSuccess(HrGetMainSetmealResult result) {
                        mainSetmeals = result.getMains();
                        getFuneralSetmeals();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
                    }
                });

    }

    /**
     * 获取殡仪馆信息
     */
    protected void getFuneralSetmeals() {
        ProductManagerImpl.getInstance().getFuneralsSetmeal(this,
                new HttpResponseHandler<HrGetFuneralSetmealResult>() {

                    @Override
                    public void onSuccess(HrGetFuneralSetmealResult result) {
                        funeralSetmeals = result.getFunerals();
                        getCemeterys();
                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
                    }
                });
    }

    /**
     * 获取公墓信息
     */
    protected void getCemeterys() {
        ProductManagerImpl.getInstance().getCemeteryResult(this,
                new HttpResponseHandler<HrGetCemeteryResult>() {

                    @Override
                    public void onSuccess(HrGetCemeteryResult result) {
                        cemeteries = result.getRetCemeteries();
                        if (orderId != -1) {
                            getOrderDetail();
                        } else {
                            initOrderView();
                        }
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
                    }
                });

    }

    /**
     * 获取订单详情
     */
    protected void getOrderDetail() {
        HpGetOrderDetailParams params = new HpGetOrderDetailParams();
        params.setOrderId(orderId);
        OrderManagerImpl.getInstance().getOrderDetail(this, params,
                new HttpResponseHandler<HrGetOrderDetailResult>() {

                    @Override
                    public void onSuccess(HrGetOrderDetailResult result) {
                        projectItems = result.getProjectItems();
                        initOrderView(result);
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }

    private void initOrderView() {
        mainSetmealOtherView.setChangeListener(new MainSetmealOtherView.SetmealOtherViewChangeListener() {

            @Override
            public void changeTotalPrice() {
                change();
            }
        });
        mainSetmealOtherView.setInitData("治丧主套餐", mainSetmeals);


        mainSetmealView.setCtgItems("治丧主套餐", mainSetmeals);
        mainSetmealView.setOnMainChangeListener(new OnMainChangeListener() {

            @Override
            public void onMainChange() {
                change();
            }
        });
//        funeralSetmealView.setCtgItems("殡仪馆项目", funeralSetmeals);
//        funeralSetmealView
//                .setOnFuneralChangeListener(new OnFuneralChangeListener() {
//
//                    @Override
//                    public void onFuneralChange() {
//                        change();
//                    }
//                });
        funeralSetmealOtherView.setOnFuneralChangeListener(new OnFuneralChangeListener() {
            @Override
            public void onFuneralChange() {
                change();
            }
        });
        funeralSetmealOtherView.setCtgItems("治丧配套商品", funeralSetmeals);

        cemeterySetmealView.setCtgItems("公墓项目", cemeteries);
        cemeterySetmealView.setOnCemeteryChangeListener(new OnCemeteryChangeListener() {

                    @Override
                    public void onCemeteryChange() {
                        change();
                    }
                });
//        addedSetmealView.setOnAddedChangeListener(new OnAddedChangeListener() {
//
//            @Override
//            public void onChange() {
//                change();
//            }
//        });
        addedSetmealOtherView.setOnAddedChangeListener(new AddedSetmealOtherView.OnAddedChangeListener() {
            @Override
            public void onChange() {
                change();
            }
        });


    }

    protected void initOrderView(HrGetOrderDetailResult result) {
        mainSetmealOtherView.setChangeListener(new MainSetmealOtherView.SetmealOtherViewChangeListener() {

            @Override
            public void changeTotalPrice() {
                change();
            }
        });
        mainSetmealOtherView.setInitData("治丧主套餐", mainSetmeals, result);

        mainSetmealView.setCtgItems("治丧主套餐", mainSetmeals, result);
        mainSetmealView.setOrderId(orderId);
        mainSetmealView.setOnMainChangeListener(new OnMainChangeListener() {

            @Override
            public void onMainChange() {
                change();
            }
        });

//        funeralSetmealView.setCtgItems("殡仪馆项目", funeralSetmeals, result);
//        funeralSetmealView.setOrderId(orderId);
//        funeralSetmealView
//                .setOnFuneralChangeListener(new OnFuneralChangeListener() {
//                    @Override
//                    public void onFuneralChange() {
//                        change();
//                    }
//                });
        funeralSetmealOtherView.setOnFuneralChangeListener(new OnFuneralChangeListener() {
            @Override
            public void onFuneralChange() {
                change();
            }
        });
        funeralSetmealOtherView.setCtgItems("治丧配套商品", funeralSetmeals, result);

        cemeterySetmealView.setCtgItems("公墓项目", cemeteries, result);
        cemeterySetmealView.setOrderId(orderId);
        cemeterySetmealView
                .setOnCemeteryChangeListener(new OnCemeteryChangeListener() {

                    @Override
                    public void onCemeteryChange() {
                        change();
                    }
                });
//        addedSetmealView.setCtgItems(result);
//        addedSetmealView.setOnAddedChangeListener(new OnAddedChangeListener() {
//
//            @Override
//            public void onChange() {
//                change();
//            }
//        });
        addedSetmealOtherView.setCtgItems(result);
        addedSetmealOtherView.setOnAddedChangeListener(new AddedSetmealOtherView.OnAddedChangeListener() {
            @Override
            public void onChange() {
                change();
            }
        });
        for (ProjectItemModel mProjectItemModel : result.getProjectItems()) {
            for (OrderCtgItemModel mCtgItemModel : mProjectItemModel
                    .getCtgItems()) {
                for (OrderProductItemModel mProductItemModel : mCtgItemModel
                        .getProductItems()) {
                    CreateOrderProductItemModel model = new CreateOrderProductItemModel();
                    model.setCategoryId(mCtgItemModel.getId());
                    model.setNumber(mProductItemModel.getNumber());
                    model.setPrice(mProductItemModel.getPrice());
                    model.setSkuId(mProductItemModel.getSkuId());
                    model.setProjectId(mProjectItemModel.getId());
                    model.setTotalPrice(mProductItemModel.getTotalPrice());
                    model.setId(orderId);
                    model.setStatusFlag(2);
                    mLastProducts.add(model);
                }
            }
        }
    }

    @OnClick(R.id.tv_edit_commit)
    void commitOrder(View v) {
        final HpCreateOrderParams params = new HpCreateOrderParams();
        params.setSetmealMain(1);
        List<CreateOrderProductItemModel> mList = new ArrayList<CreateOrderProductItemModel>();
//        mList.addAll(mainSetmealView.getProductItemModels());
        mList.addAll(mainSetmealOtherView.getProductItemModels());
        mList.addAll(funeralSetmealOtherView.getProductItemModels());
//        mList.addAll(funeralSetmealView.getProductItemModels());
        mList.addAll(cemeterySetmealView.getProductItemModels());
        mList.addAll(addedSetmealOtherView.getProductItemModels());
//        mList.addAll(addedSetmealView.getProductItemModels());
//		mList.addAll(mLastProducts);
        params.setConsultId(consultId);
        params.setItems(mList);
        params.setSetmealCemetery(cemeterySetmealView.getCemeterID());
        params.setSetmealFuneral(funeralSetmealOtherView.getFuneralID());
//        params.setSetmealMain(mainSetmealView.getMainID());
        params.setSetmealMain(mainSetmealOtherView.getMainID());
        if (orderId != -1) {
            OrderManagerImpl.getInstance().editOrder(this, params,
                    new HttpResponseHandler<HrOderId>() {

                        @Override
                        public void onSuccess(HrOderId result) {
                            OrderFragment.C_bOrder_isRefresh = true;
                            if (pgzx == 1) {
                                sendBroadcast(new Intent(PgzxActivity.PGZX_ACTION));
                            }
                            finish();
                        }

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onError(String message) {

                        }
                    });
        } else {
            TipsDialog mDialog = new TipsDialog(this);
            mDialog.setTitle("请确认客户选择由圆满人生进行全程服务，创建订单后不可取消。");
            mDialog.setTopButton("暂不创建", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            mDialog.setBottomButton("创建",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            OrderFragment.C_bOrder_isRefresh = true;
                            OrderManagerImpl.getInstance().createOrder(EditOrderActivity.this, params,
                                    new HttpResponseHandler<HrOderId>() {

                                        @Override
                                        public void onSuccess(HrOderId result) {
                                            finish();
                                        }

                                        @Override
                                        public void onStart() {

                                        }

                                        @Override
                                        public void onError(String message) {

                                        }
                                    });
                        }
                    });
            mDialog.show();

        }
    }


    protected void change() {
        totalPrice = 0;
        List<CreateOrderProductItemModel> mainProductItemOtherModels = mainSetmealOtherView
                .getProductItemModels();
        if (mainProductItemOtherModels != null) {
            Log.v("this", mainProductItemOtherModels.size() + "szie");
            for (CreateOrderProductItemModel model : mainProductItemOtherModels) {
                if (model.getStatusFlag() != 2) {
                    totalPrice += model.getTotalPrice();
                }
            }
        }

//        List<CreateOrderProductItemModel> mainProductItemModels = mainSetmealView
//                .getProductItemModelsT();
//        if (mainProductItemModels != null) {
//            for (CreateOrderProductItemModel model : mainProductItemModels) {
//                totalPrice += model.getTotalPrice();
//            }
//        }


        List<CreateOrderProductItemModel> funeralProductItemModels = funeralSetmealOtherView
                .getProductItemModelsT();
        if (funeralProductItemModels != null) {
            for (CreateOrderProductItemModel model : funeralProductItemModels) {
                totalPrice += model.getTotalPrice();
            }
        }
//        List<CreateOrderProductItemModel> funeralProductItemModels = funeralSetmealView
//                .getProductItemModelsT();
//        if (funeralProductItemModels != null) {
//            for (CreateOrderProductItemModel model : funeralProductItemModels) {
//                totalPrice += model.getTotalPrice();
//            }
//        }

        List<CreateOrderProductItemModel> cemeteryProductItemModels = cemeterySetmealView
                .getProductItemModelsT();
        if (cemeteryProductItemModels != null) {
            for (CreateOrderProductItemModel model : cemeteryProductItemModels) {
                totalPrice += model.getTotalPrice();
            }
        }
        List<CreateOrderProductItemModel> addedProductItemModels = addedSetmealOtherView
                .getProductItemModelsT();
        if (addedProductItemModels != null) {
            for (CreateOrderProductItemModel model : addedProductItemModels) {
                totalPrice += model.getTotalPrice();
            }
        }
//        List<CreateOrderProductItemModel> addedProductItemModels = addedSetmealView
//
//                .getProductItemModelsT();
//        if (addedProductItemModels != null) {
//            for (CreateOrderProductItemModel model : addedProductItemModels) {
//                totalPrice += model.getTotalPrice();
//            }
//        }
        tv_total.setText(totalPrice + "");


    }

    @OnClick(R.id.tv_customerdetail)
    void customerDeatail(View v) {
        Intent in = new Intent(this, CustomerActivity.class);
        in.putExtra("khxqtype", khxqType);
        in.putExtra("consultId", consultId);
        in.putExtra("orderId", orderId);
        startActivity(in);
    }
}
