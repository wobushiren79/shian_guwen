package com.shian.shianlife.activity.goods;

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
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;
import com.shian.shianlife.thisenum.GoodsFinanceDeliveryEnum;
import com.shian.shianlife.view.ScrollExpandableListView;
import com.shian.shianlife.view.goods.GoodsServiceInfoLayout;
import com.shian.shianlife.view.goods.StoreEditNormalView;
import com.shian.shianlife.view.goods.StoreExpandableTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderSettlementActivity extends BaseActivity implements StoreExpandableTitleView.CallBack, View.OnClickListener {

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
    private List<GoodsShoppingCartListChildBean> selectGoods;//選中的商品

    private SharedGoodsInvoiceInfoBean invoiceInfoData;//发票数据


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
        llSubmitTotalPrice.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        setInvoiceLayout();
    }

    private void initData() {
        selectGoods = (ArrayList<GoodsShoppingCartListChildBean>) getIntent().getSerializableExtra(IntentName.INTENT_LIST_DATA);
        List<GoodsItemPerform> listGoods = DataUtils.ShoppingCartToGoodsData(selectGoods);
        Map<String, List<GoodsItemPerform>> mapGoodsData = DataUtils.getMapForGoodsItemPerform(listGoods);

        tvMoneyCustomer.setText(getCustomerTotalPrice());

        goodsExpandTitle.setCallBack(this);
        goodsListAdapter = new StoreOrderGoodsListAdapter(this);
        goodsListAdapter.setData(mapGoodsData);
        goodsExpandListView.setAdapter(goodsListAdapter);
        //默认展开
        goodsExpandTitle.setExpandable(true);
        ViewUtils.expandGroup(goodsExpandListView, goodsListAdapter);
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
        }
    }

    /**
     * 编辑发票信息
     */
    private void editInvoiceInfo() {
        Intent intent = new Intent(this, GoodsOrderInvoiceInfoActivity.class);
        startActivityForResult(intent, InvoiceRequestCode);
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
    private String getCustomerTotalPrice() {
        float totalPrice = 0f;
        for (GoodsShoppingCartListChildBean items : selectGoods) {
            totalPrice += items.getResultBean().getSpec_price();
        }
        return "￥" + totalPrice;
    }

    /**
     * 获取总顾问价
     */
    private String getAdviserTotalPrice() {
        float totalPrice = 0f;
        for (GoodsShoppingCartListChildBean items : selectGoods) {
            totalPrice += items.getResultBean().getAdviser_price();
        }
        return "￥" + totalPrice;
    }
}
