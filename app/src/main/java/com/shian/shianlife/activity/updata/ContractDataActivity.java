package com.shian.shianlife.activity.updata;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.view.customer.PicImageView;
import com.shian.shianlife.common.view.finger.DrawView;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.mapapi.CustomDialog;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.imp.impl.OrderManagerImpl;
import com.shian.shianlife.provide.imp.impl.ProductManagerImpl;
import com.shian.shianlife.provide.model.BordModel;
import com.shian.shianlife.provide.model.CemeteryModel;
import com.shian.shianlife.provide.model.OrderCtgItemModel;
import com.shian.shianlife.provide.model.OrderProductItemModel;
import com.shian.shianlife.provide.model.PayInfoModel;
import com.shian.shianlife.provide.model.ProductItemModel;
import com.shian.shianlife.provide.model.ProjectItemModel;
import com.shian.shianlife.provide.model.SetmealModel;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.params.HpGetGoodsListParams;
import com.shian.shianlife.provide.params.HpGetOrderDetailParams;
import com.shian.shianlife.provide.params.HpOrderIdParams;
import com.shian.shianlife.provide.params.HpSaveContractData;
import com.shian.shianlife.provide.params.HpSaveCustomerContract;
import com.shian.shianlife.provide.result.HrConsultAgentman;
import com.shian.shianlife.provide.result.HrGetCemeteryResult;
import com.shian.shianlife.provide.result.HrGetContractData;
import com.shian.shianlife.provide.result.HrGetFuneralSetmealResult;
import com.shian.shianlife.provide.result.HrGetGoodsListResult;
import com.shian.shianlife.provide.result.HrGetMainSetmealResult;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;
import com.shian.shianlife.provide.result.HrOrderFeedback;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.shian.shianlife.view.ScreenShot;
import com.shian.shianlife.view.ScrollListView;
import com.shian.shianlife.view.dialog.SignDialog;

import java.util.ArrayList;
import java.util.List;

public class ContractDataActivity extends BaseActivity {

    ScrollView theScrollView;
    ScrollListView mLSMain;
    ScrollListView mLSFuneral;
    ScrollListView mLSProject;

    TextView mTVNext;
    TextView mTVBack;
    TextView mTVComplete;

    TextView mTVAllMoney;
    TextView mTVFirstMoney;
    TextView mTVLastMoney;

    TextView mTVContractId;

    ImageView dvPay;
    long consultId;
    long orderId;

    TextView mTVFirstName;
    TextView mTVDeadName;
    TextView mTVDeadSex;
    TextView mTVDeadState;
    TextView mTVDeadCardId;
    TextView mTVDeadAge;
    TextView mTVDeadShoes;
    TextView mTVAgentManName;
    TextView mTVAgentManPhone;
    TextView mTVAgentManEmail;
    TextView mTVAgentManCardId;
    TextView mTVAgentManLocation;
    TextView mTVAgentManRelation;

    TextView mTVZSLocation;
    TextView mTVDLLocation;

    DisplayMetrics metrics = new DisplayMetrics();
    /**
     * 用户之前提交的订单详情内容
     */
    List<ProjectItemModel> projectItems = new ArrayList<>();

    List<OrderCtgItemModel> projectDD = new ArrayList<>();
    List<OrderCtgItemModel> funeralItems = new ArrayList<>();
    List<OrderCtgItemModel> ztcItems = new ArrayList<>();

    List<SetmealModel> mainSetmeals = new ArrayList<>();// 系统主套餐
    BordModel board;
    PayInfoModel payInfo;
    CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_data);
        consultId = getIntent().getLongExtra("consultId", 0);
        orderId = getIntent().getLongExtra("orderId", 0);
        setTitle("合同信息");

        initView();
        initData();
    }


    private void initView() {
        mTVNext = (TextView) findViewById(R.id.tv_next);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mTVComplete = (TextView) findViewById(R.id.tv_complete);
        mTVContractId = (TextView) findViewById(R.id.tv_contractId);

        mTVAllMoney = (TextView) findViewById(R.id.tv_allmoney);
        mTVFirstMoney = (TextView) findViewById(R.id.tv_firstmoney);
        mTVLastMoney = (TextView) findViewById(R.id.tv_lastmoney);

        dvPay = (ImageView) findViewById(R.id.dv_pay);
        theScrollView = (ScrollView) findViewById(R.id.activity_contract_data);

        mTVFirstName = (TextView) findViewById(R.id.tv_name);
        mTVDeadName = (TextView) findViewById(R.id.tv_deadmanname);
        mTVDeadSex = (TextView) findViewById(R.id.tv_deadmansex);
        mTVDeadState = (TextView) findViewById(R.id.tv_deadmanstate);
        mTVDeadCardId = (TextView) findViewById(R.id.tv_deadmancardid);
        mTVDeadAge = (TextView) findViewById(R.id.tv_deadmanage);
        mTVDeadShoes = (TextView) findViewById(R.id.tv_deadmanshoes);
        mTVAgentManName = (TextView) findViewById(R.id.tv_agentmanname);
        mTVAgentManPhone = (TextView) findViewById(R.id.tv_agentmanphone);
        mTVAgentManEmail = (TextView) findViewById(R.id.tv_agentmanemail);
        mTVAgentManCardId = (TextView) findViewById(R.id.tv_agentmancardid);
        mTVAgentManLocation = (TextView) findViewById(R.id.tv_agentmanlocation);
        mTVAgentManRelation = (TextView) findViewById(R.id.tv_agentmanrelation);
        mTVZSLocation = (TextView) findViewById(R.id.tv_zslocation);
        mTVDLLocation = (TextView) findViewById(R.id.tv_dlman);

        mLSMain = (ScrollListView) findViewById(R.id.ls_main);
        mLSFuneral = (ScrollListView) findViewById(R.id.ls_funeral);
        mLSProject = (ScrollListView) findViewById(R.id.ls_project);

        mTVNext.setOnClickListener(onClickListener);
        mTVBack.setOnClickListener(onClickListener);
        mTVComplete.setOnClickListener(onClickListener);
        dvPay.setOnClickListener(onClickListener);


    }

    private void initData() {
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getMainSetmeals();//获取订单信息
        getData();//获取基本数据
    }

    private void getData() {
        HpConsultIdParams params = new HpConsultIdParams();
        params.setConsultId((ContractDataActivity.this).getIntent().getLongExtra("consultId", 0));
        MHttpManagerFactory.getAccountManager().getContractData(ContractDataActivity.this, params,
                new HttpResponseHandler<HrGetContractData>() {

                    @Override
                    public void onSuccess(HrGetContractData result) {
                        // TODO Auto-generated method stub

                        String deadmanName = result.getDeadmanName();
                        String deadmanSex = result.getDeadmanSex();
                        String deadmanState = result.getDeadmanState();
                        String deadmanCardId = result.getDeadmanCardId();
                        String deadmanAge = result.getDeadmanAge();
                        String deadmanShoes = result.getDeadmanShoes();

                        String agentmanName = result.getAgentmanName();
                        String agentmanPhone = result.getAgentmanPhone();
                        String agentmanEmail = result.getAgentmanEmail();
                        String agentmanCardId = result.getAgentmanCardId();
                        String agentmanLocation = result.getAgentmanLocation();
                        String agentmanRelation = result.getAgentmanRelation();

                        String zsLocation = result.getZsLocation();
                        String adviserName = result.getAdviserName();


                        Utils.LogVPrint("deadmanName" + deadmanName);
                        Utils.LogVPrint("deadmanSex" + deadmanSex);
                        Utils.LogVPrint("deadmanState" + deadmanState);
                        Utils.LogVPrint("deadmanCardId" + deadmanCardId);
                        Utils.LogVPrint("deadmanAge" + deadmanAge);
                        Utils.LogVPrint("deadmanShoes" + deadmanShoes);
                        Utils.LogVPrint("agentmanPhone" + agentmanPhone);
                        Utils.LogVPrint("agentmanName" + agentmanName);
                        Utils.LogVPrint("agentmanEmail" + agentmanEmail);
                        Utils.LogVPrint("agentmanCardId" + agentmanCardId);
                        Utils.LogVPrint("agentmanLocation" + agentmanLocation);
                        Utils.LogVPrint("agentmanRelation" + agentmanRelation);
                        Utils.LogVPrint("zsLocation" + zsLocation);
                        Utils.LogVPrint("adviserName" + adviserName);

                        mTVFirstName.setText(agentmanName);
                        mTVDeadName.setText(deadmanName);
                        String sDeadmanSex = "";
                        switch (deadmanSex) {
                            case "1":
                                sDeadmanSex = "未知";
                                break;
                            case "2":
                                sDeadmanSex = "男";
                                break;
                            case "3":
                                sDeadmanSex = "女";
                                break;
                            case "4":
                                sDeadmanSex = "保密";
                                break;
                        }

                        mTVDeadSex.setText(sDeadmanSex);
                        mTVDeadState.setText(deadmanState);
                        mTVDeadCardId.setText(deadmanCardId);
                        mTVDeadAge.setText(deadmanAge);
                        mTVDeadShoes.setText(deadmanShoes);
                        mTVAgentManName.setText(agentmanName);
                        mTVAgentManPhone.setText(agentmanPhone);
                        mTVAgentManEmail.setText(agentmanEmail);
                        mTVAgentManCardId.setText(agentmanCardId);
                        mTVAgentManLocation.setText(agentmanLocation);
                        mTVAgentManRelation.setText(agentmanRelation);

                        mTVZSLocation.setText(zsLocation);
                        mTVDLLocation.setText(adviserName);
                    }

                    @Override
                    public void onStart() {
                        // TODO Auto-generated method stub

                    }


                    @Override
                    public void onError(String message) {
                        // TODO Auto-generated method stub
//                        finish();
                    }
                });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mTVNext) {
                Intent intent = new Intent(ContractDataActivity.this, TQZBDataActivity.class);
                intent.putExtra("consultId", consultId);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            } else if (view == mTVBack) {
                Intent intent = new Intent(ContractDataActivity.this, JBRDataActivity.class);
                intent.putExtra("consultId", consultId);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
                finish();
            } else if (view == mTVComplete) {
                complete();
            } else if (view == dvPay) {
                drawName();
            }
        }
    };

    private void complete() {
        if (dvPay.getDrawable() == null) {
            ToastUtils.showLongTime(ContractDataActivity.this, "还没有签名");
            return;
        }
        mTVNext.setVisibility(View.GONE);
        mTVBack.setVisibility(View.GONE);
        mTVComplete.setVisibility(View.GONE);
        final Bitmap bitmap = ScreenShot.getBitmapByView(theScrollView);
        mTVNext.setVisibility(View.VISIBLE);
        mTVBack.setVisibility(View.VISIBLE);
        mTVComplete.setVisibility(View.VISIBLE);
        final Bitmap bmp = Bitmap.createScaledBitmap(bitmap, (int) (metrics.widthPixels / 2.5), (int) ((bitmap.getHeight() * metrics.widthPixels / bitmap.getWidth()) / 2.5), true);
        new Thread() {
            @Override
            public void run() {
//                String fName = ScreenShot.savePic(ScreenShot.compressImage(bitmap));
                String fName = ScreenShot.savePic(bmp);
                uploadFile(consultId + "", fName);

            }
        }.start();

        customDialog = new CustomDialog(ContractDataActivity.this);
        customDialog.show();
    }

    private void uploadFile(final String file, String path) {
        MHttpManagerFactory.getFileManager().upLoadFile(ContractDataActivity.this, file,
                path, new FileHttpResponseHandler<HrUploadFile>() {

                    @Override
                    public void onSuccess(HrUploadFile t) {
                        if (customDialog != null) {
                            customDialog.cancel();
                        }
                        HpSaveCustomerContract.AddAddition add = new HpSaveCustomerContract.AddAddition();
                        add.setFileName(file);
                        add.setFileUrl(t.getNameMap().get(file).toString());
                        String FileUrl = AppContansts.OSSURL + add.getFileUrl();
                        Utils.LogVPrint("FileUrl:" + FileUrl);
                        HpSaveContractData params = new HpSaveContractData();
                        params.setPicUrl(FileUrl);
                        params.setContractAmount(payInfo.getTotalAmount());
                        params.setConsultId(consultId);
                        MHttpManagerFactory.getAccountManager().saveContractData(ContractDataActivity.this, params,
                                new HttpResponseHandler<Object>() {

                                    @Override
                                    public void onSuccess(Object result) {
                                        // TODO Auto-generated method stub
                                        ToastUtils.show(ContractDataActivity.this, "上传成功");
                                        OrderFragment.C_bOrder_isRefresh = true;
                                        finish();

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

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long total, long current,
                                           boolean isUploading) {

                    }

                    @Override
                    public void onError(String message) {
                        if (customDialog != null) {
                            customDialog.cancel();
                        }
                    }
                });
    }

    /**
     * 签名
     */
    private void drawName() {
//        AlertDialog alertDialog;
//        final DrawView drawView = new DrawView(ContractDataActivity.this);
//        drawView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.dimen_40dp)));
//        drawView.changeColour(7);
//        drawView.requestFocus();
//        drawView.changeColour(5);
//        alertDialog = new AlertDialog.Builder(ContractDataActivity.this)
//                .setTitle("请绘制签名")
//                .setView(drawView)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Bitmap bitmapName = drawView.getBitamp();
//                        dvPay.setImageBitmap(bitmapName);
//                    }
//                })
//                .create();
//        alertDialog.show();
//        alertDialog.getWindow().setLayout(getResources().getDimensionPixelOffset(R.dimen.dimen_600dp), getResources().getDimensionPixelOffset(R.dimen.dimen_595dp));
        SignDialog dialog = new SignDialog(ContractDataActivity.this, R.style.CustomDialog);
        dialog.setCallBack(new SignDialog.CallBack() {
            @Override
            public void signComplete(Bitmap bitmapName) {
                dvPay.setImageBitmap(bitmapName);
            }
        });
        dialog.show();
    }


    BaseAdapter mainAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (ztcItems != null) {
                return ztcItems.size();
            } else {
                return 0;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;

            if (view == null) {
                view = LayoutInflater.from(ContractDataActivity.this).inflate(R.layout.layout_contract_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.tv_name);
                holder.content = (ScrollListView) view.findViewById(R.id.tv_text);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            String name = ztcItems.get(i).getName();
            holder.name.setText(name + "：");
            holder.name.setTextColor(getResources().getColor(R.color.blackgroundmain));
            final List<OrderProductItemModel> listContent = ztcItems.get(i).getProductItems();
            BaseAdapter baseAdapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return listContent.size();
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
                public View getView(int p, View view, ViewGroup viewGroup) {
                    TextView textView = new TextView(ContractDataActivity.this);
                    textView.setTextColor(getResources().getColor(R.color.blackgroundmain));
                    textView.setText(listContent.get(p).getName());
                    return textView;
                }
            };
            holder.content.setAdapter(baseAdapter);
            ScrollListView.setListViewHeightBasedOnChildren(holder.content);

            return view;
        }

        class ViewHolder {
            TextView name;
            ScrollListView content;
        }
    };


    BaseAdapter funeralAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (funeralItems != null) {
                return funeralItems.size();
            } else {
                return 0;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;

            if (view == null) {
                view = LayoutInflater.from(ContractDataActivity.this).inflate(R.layout.layout_contract_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.tv_name);
                holder.content = (ScrollListView) view.findViewById(R.id.tv_text);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            String name = funeralItems.get(i).getName();
            holder.name.setText(name + "：");
            holder.name.setTextColor(getResources().getColor(R.color.blackgroundmain));
            final List<OrderProductItemModel> listContent = funeralItems.get(i).getProductItems();
            BaseAdapter baseAdapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return listContent.size();
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
                public View getView(int p, View view, ViewGroup viewGroup) {
                    TextView textView = new TextView(ContractDataActivity.this);
                    textView.setTextColor(getResources().getColor(R.color.blackgroundmain));
                    textView.setText(listContent.get(p).getName());
                    return textView;
                }
            };
            holder.content.setAdapter(baseAdapter);
            ScrollListView.setListViewHeightBasedOnChildren(holder.content);

            return view;
        }

        class ViewHolder {
            TextView name;
            ScrollListView content;
        }
    };


    BaseAdapter projectAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return projectDD.size() + 1;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(ContractDataActivity.this).inflate(R.layout.layout_contract_item_2, null);
                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.tv_name);
                holder.money = (TextView) view.findViewById(R.id.tv_money);
                holder.num = (TextView) view.findViewById(R.id.tv_num);
                holder.all = (TextView) view.findViewById(R.id.tv_all);
                holder.remark = (TextView) view.findViewById(R.id.tv_remark);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            if (i == 0) {
                holder.name.setTextColor(Color.BLACK);
                holder.money.setTextColor(Color.BLACK);
                holder.num.setTextColor(Color.BLACK);
                holder.remark.setTextColor(Color.BLACK);
                holder.all.setTextColor(Color.BLACK);

                holder.name.setText("名称");
                holder.money.setText("单价 ");
                holder.num.setText("数量");
                holder.all.setText( "总价");
                holder.remark.setText("备注");
            } else if (i > 0) {
                OrderProductItemModel data = projectDD.get(i - 1).getProductItems().get(0);
                String name = data.getName();
                float price = data.getTotalPrice();
                int num = data.getNumber();
                holder.name.setText(name);
                holder.money.setText(price + "");
                holder.num.setText(num + "");
                holder.all.setText((price * num) + "");
                holder.remark.setText("");

                holder.name.setTextColor(getResources().getColor(R.color.blackgroundmain));
                holder.money.setTextColor(getResources().getColor(R.color.blackgroundmain));
                holder.num.setTextColor(getResources().getColor(R.color.blackgroundmain));
                holder.remark.setTextColor(getResources().getColor(R.color.blackgroundmain));
                holder.all.setTextColor(getResources().getColor(R.color.blackgroundmain));
            }else{

            }


            return view;

        }

        class ViewHolder {
            TextView name;
            TextView money;
            TextView num;
            TextView all;
            TextView remark;
        }
    };

    /**
     * 获取套餐信息
     */
    private void getMainSetmeals() {
        ProductManagerImpl.getInstance().getMainSetmeal(this,
                new HttpResponseHandler<HrGetMainSetmealResult>() {

                    @Override
                    public void onSuccess(HrGetMainSetmealResult result) {
                        mainSetmeals = result.getMains();
                        getOrderDetail();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        System.out.println();
//                        finish();
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
                        board = result.getBoard();
                        payInfo = result.getPayInfo();

                        mTVFirstMoney.setText(payInfo.getPrepayAmount() + "元");
                        mTVAllMoney.setText(payInfo.getTotalAmount() + "元");
//                        mTVLastMoney.setText(payInfo.getRestPayAmount() + "元");
                        mTVLastMoney.setText((payInfo.getTotalAmount() - payInfo.getPrepayAmount()) + "元");

                        for (int i = 0; i < mainSetmeals.size(); i++) {
                            SetmealModel setmealModel = mainSetmeals.get(i);
                            if (setmealModel.getId() == result.getBoard().getSetmealMainId()) {
                                TextView textView = new TextView(ContractDataActivity.this);
                                textView.setText("方案名称：" + mainSetmeals.get(i).getName());
                                textView.setTextColor(getResources().getColor(R.color.blackgroundmain));
                                mLSMain.addHeaderView(textView);
                                break;
                            }
                        }


                        for (int i = 0; i < projectItems.size(); i++) {
                            if ("主套餐".equals(projectItems.get(i).getName())) {
                                ztcItems = projectItems.get(i).getCtgItems();
                            } else if ("增值项目".equals(projectItems.get(i).getName())) {
                                projectDD = projectItems.get(i).getCtgItems();
                            } else if ("殡仪馆".equals(projectItems.get(i).getName())) {
                                funeralItems = projectItems.get(i).getCtgItems();
                            }
                        }

                        if (projectDD.size() > 0) {
                            TextView headTx = new TextView(ContractDataActivity.this);
                            headTx.setText("增值服务");
                            mLSProject.addHeaderView(headTx);
                        }

                        if (funeralItems.size() > 0) {
                            TextView headTx = new TextView(ContractDataActivity.this);
                            headTx.setText("治丧配套商品");
                            mLSFuneral.addHeaderView(headTx);
                        }

                        mLSMain.setAdapter(mainAdapter);

                        if (projectDD.size() > 0) {
                            mLSProject.setAdapter(projectAdapter);
                        }
                        if (funeralItems.size() > 0) {
                            mLSFuneral.setAdapter(funeralAdapter);
                        }

                        ScrollListView.setListViewHeightBasedOnChildren(mLSMain);
                        ScrollListView.setListViewHeightBasedOnChildren(mLSFuneral);
                        ScrollListView.setListViewHeightBasedOnChildren(mLSProject);
                        mainAdapter.notifyDataSetChanged();
//                        projectAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
//                        finish();
                    }
                });
    }

}
