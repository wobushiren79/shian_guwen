package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.adapter.StoreOrderGoodsListAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.bean.GoodsShoppingCartListChildBean;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.DataUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsInvoice;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.GoodsOrder;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderCreateResultBean;
import com.shian.shianlife.mvp.goods.bean.GoodsOrderItem;
import com.shian.shianlife.mvp.goods.bean.GoodsServiceInfo;
import com.shian.shianlife.mvp.goods.presenter.IGoodsOrderCreatePresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.GoodsOrderCreatePresenterImpl;
import com.shian.shianlife.mvp.goods.view.IGoodsOrderCreateView;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsInvoiceInfoClearPresenter;
import com.shian.shianlife.mvp.shared.presenter.impl.SharedGoodsInvoiceInfoClearPresenterImpl;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsInvoiceInfoClearView;
import com.shian.shianlife.thisenum.GoodsFinanceDeliveryEnum;
import com.shian.shianlife.thisenum.GoodsOrderChannelEnum;
import com.shian.shianlife.view.ScrollExpandableListView;
import com.shian.shianlife.view.goods.GoodsServiceInfoLayout;
import com.shian.shianlife.view.goods.StoreEditNormalView;
import com.shian.shianlife.view.goods.StoreExpandableTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderSettlementActivity extends BaseActivity implements StoreExpandableTitleView.CallBack, View.OnClickListener, View.OnLongClickListener, IGoodsOrderCreateView, ISharedGoodsInvoiceInfoClearView {

    @InjectView(R.id.layout_service_info)
    GoodsServiceInfoLayout layoutServiceInfo;
    @InjectView(R.id.goods_expand_title)
    StoreExpandableTitleView goodsExpandTitle;
    @InjectView(R.id.goods_expand_list_view)
    ScrollExpandableListView goodsExpandListView;
    @InjectView(R.id.invoice_title)
    StoreExpandableTitleView invoiceTitle;
    @InjectView(R.id.layout_remark)
    StoreEditNormalView layoutRemark;
    @InjectView(R.id.tv_money_customer)
    TextView tvMoneyCustomer;

    @InjectView(R.id.tv_submit_price_name)
    TextView tvSubmitPriceName;
    @InjectView(R.id.tv_submit_price_number)
    TextView tvSubmitPriceNumber;
    @InjectView(R.id.ll_submit_total_price)
    LinearLayout llSubmitTotalPrice;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;


    public static boolean isSaveServiceInfo = false;
    public static boolean isSaveInvoiceInfo = false;


    private StoreOrderGoodsListAdapter goodsListAdapter;
    private List<GoodsItemPerform> selectGoods;//選中的商品

    private SharedGoodsInvoiceInfoBean invoiceInfoData;//发票数据

    private IGoodsOrderCreatePresenter goodsOrderCreatePresenter;
    private ISharedGoodsInvoiceInfoClearPresenter sharedGoodsInvoiceInfoClearPresenter;

    public static final int InvoiceRequestCode = 6666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_settlement);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("结算");
        layoutServiceInfo.setMode(GoodsServiceInfoLayout.Mode_Edit);
        invoiceTitle.isOpenSelfClick(false);
        invoiceTitle.setOnClickListener(this);
        llSubmitTotalPrice.setOnLongClickListener(this);
        tvSubmit.setOnClickListener(this);
        setInvoiceLayout();
    }

    private void initData() {
        selectGoods = (ArrayList<GoodsItemPerform>) getIntent().getSerializableExtra(IntentName.INTENT_LIST_DATA);
        Map<String, List<GoodsItemPerform>> mapGoodsData = DataUtils.getMapForGoodsItemPerform(selectGoods);

        tvMoneyCustomer.setText("￥" + (getCustomerTotalPrice() / 100f));
        tvSubmitPriceName.setText("推荐价：");
        tvSubmitPriceNumber.setText("￥" + (getCustomerTotalPrice() / 100f));

        goodsExpandTitle.setCallBack(this);
        goodsListAdapter = new StoreOrderGoodsListAdapter(this);
        goodsListAdapter.setData(mapGoodsData);
        goodsExpandListView.setAdapter(goodsListAdapter);
        //默认展开
        goodsExpandTitle.setExpandable(true);
        ViewUtils.expandGroup(goodsExpandListView, goodsListAdapter);

        goodsOrderCreatePresenter = new GoodsOrderCreatePresenterImpl(this);
        sharedGoodsInvoiceInfoClearPresenter = new SharedGoodsInvoiceInfoClearPresenterImpl(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isSaveServiceInfo) {
            layoutServiceInfo.getDataForShared();
        }
        if (isSaveInvoiceInfo) {

        }
    }

    @Override
    public void onClickExpandable(View view, boolean isExpandable) {
        if (view == goodsExpandTitle) {
            if (isExpandable)
                goodsExpandListView.setVisibility(View.VISIBLE);
            else
                goodsExpandListView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == invoiceTitle) {
            editInvoiceInfo();
        } else if (v == tvSubmit) {
            createOrder();
        }
    }


    @Override
    public boolean onLongClick(View v) {
        if (v == llSubmitTotalPrice) {
            if (tvSubmitPriceName.getText().toString().contains("推荐价")) {
                tvSubmitPriceName.setText("顾问价：");
                tvSubmitPriceNumber.setText("￥" + (getAdviserTotalPrice() / 100f));
            } else {
                tvSubmitPriceName.setText("推荐价：");
                tvSubmitPriceNumber.setText("￥" + (getCustomerTotalPrice() / 100f));
            }
        }
        return true;
    }

    /**
     * 编辑发票信息
     */
    private void editInvoiceInfo() {
        Intent intent = new Intent(this, GoodsOrderInvoiceInfoActivity.class);
        startActivityForResult(intent, InvoiceRequestCode);
    }

    /**
     * 生成订单
     */
    private void createOrder() {
        goodsOrderCreatePresenter.createGoodsOrder();
    }


    /**
     * 设置发表信息
     */
    private void setInvoiceLayout() {
        if (invoiceInfoData == null) {
            invoiceTitle.setData(GoodsFinanceDeliveryEnum.notinvoicement.getName());
            return;
        }
        if (invoiceInfoData.getIsNeedInvoice() == GoodsFinanceDeliveryEnum.notinvoicement.getCode()) {
            invoiceTitle.setData(GoodsFinanceDeliveryEnum.notinvoicement.getName());
            invoiceInfoData = null;
        } else if (invoiceInfoData.getIsNeedInvoice() == GoodsFinanceDeliveryEnum.hasinvoicement.getCode()) {
            invoiceTitle.setData(GoodsFinanceDeliveryEnum.hasinvoicement.getName());
        }
    }

    /**
     * 获取总推荐价
     */
    private float getCustomerTotalPrice() {
        float totalPrice = 0f;
        for (GoodsItemPerform items : selectGoods) {
            totalPrice += items.getSpecOrderedPrice();
        }
        return totalPrice;
    }

    /**
     * 获取总顾问价
     */
    private float getAdviserTotalPrice() {
        float totalPrice = 0f;
        for (GoodsItemPerform items : selectGoods) {
            totalPrice += items.getAdviserPrice();
        }
        return totalPrice;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InvoiceRequestCode && resultCode == GoodsOrderInvoiceInfoActivity.InvoiceResultCode) {
            SharedGoodsInvoiceInfoBean invoiceInfoData = (SharedGoodsInvoiceInfoBean) data.getSerializableExtra(IntentName.INTENT_DATA);
            this.invoiceInfoData = invoiceInfoData;
            setInvoiceLayout();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void createGoodsOrderSuccess(GoodsOrderCreateResultBean resultBean) {
        ToastUtils.show(this, "生成订单成功");
        layoutServiceInfo.clearDataForShared();
        sharedGoodsInvoiceInfoClearPresenter.clearData();
        Intent intent = new Intent(this, GoodsOrderSubmitActivity.class);
        intent.putExtra(IntentName.INTENT_ORDERID, resultBean.getOrderId());
        startActivity(intent);
        finish();
    }

    @Override
    public void createGoodsOrderFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public GoodsOrder getGoodsOrder() {
        GoodsOrder goodsOrder = new GoodsOrder();
        goodsOrder.setOrderChannel(GoodsOrderChannelEnum.Adviser_App.getCode());
        goodsOrder.setOrderComment(layoutRemark.getData());
        goodsOrder.setTotalPrice((int) (getAdviserTotalPrice() * 100));
        goodsOrder.setShowTotalPrice((int) (getCustomerTotalPrice() * 100));
        if (layoutServiceInfo.getData() != null) {
            goodsOrder.setCustomerName(layoutServiceInfo.getData().getCustomerName());
            goodsOrder.setCustomerPhone(layoutServiceInfo.getData().getCustomerPhone());
        }
        if (invoiceInfoData == null) {
            goodsOrder.setNeedInvoice(GoodsFinanceDeliveryEnum.notinvoicement.getCode());
        } else {
            goodsOrder.setNeedInvoice(GoodsFinanceDeliveryEnum.hasinvoicement.getCode());
        }
        return goodsOrder;
    }

    @Override
    public GoodsInvoice getGoodsInvoice() {
        GoodsInvoice goodsInvoice = DataUtils.SharedGoodsInvoiceInfoBeanToGoodsInvoice(invoiceInfoData);
        return goodsInvoice;
    }

    @Override
    public GoodsServiceInfo getGoodsServiceInfo() {
        GoodsServiceInfo goodsServiceInfo = DataUtils.SharedGoodsServiceInfoBeanToGoodsInvoice(layoutServiceInfo.getData());
        return goodsServiceInfo;
    }

    @Override
    public List<GoodsOrderItem> getGoodsOrderItem() {
        List<GoodsOrderItem> listData = new ArrayList<>();
        for (GoodsItemPerform item : selectGoods) {
            GoodsOrderItem tempIte = item;
            listData.add(tempIte);
        }
        return listData;
    }
}
