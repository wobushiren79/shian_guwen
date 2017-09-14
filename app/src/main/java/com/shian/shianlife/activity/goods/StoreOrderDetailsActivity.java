package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;


import com.shian.shianlife.R;
import com.shian.shianlife.adapter.StoreOrderGoodsListAdapter;
import com.shian.shianlife.adapter.StoreOrderInvoiceDetailsAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.mvp.goods.bean.GoodsInvoiceDetailsItem;
import com.shian.shianlife.mvp.goods.bean.GoodsItemPerform;
import com.shian.shianlife.mvp.goods.bean.StoreOrderDetailsResultBean;
import com.shian.shianlife.mvp.goods.presenter.IStoreOrderDetailsPresenter;
import com.shian.shianlife.mvp.goods.presenter.impl.StoreOrderDetailsPresenterImpl;
import com.shian.shianlife.mvp.goods.view.IStoreOrderDetailsView;
import com.shian.shianlife.view.ScrollExpandableListView;
import com.shian.shianlife.view.ScrollRecyclerView;
import com.shian.shianlife.view.goods.StoreEditNormalView;
import com.shian.shianlife.view.goods.StoreExpandableTitleView;

import java.util.List;
import java.util.Map;

import butterknife.InjectView;
import butterknife.InjectView;

public class StoreOrderDetailsActivity extends BaseActivity implements IStoreOrderDetailsView, StoreExpandableTitleView.CallBack {

    @InjectView(R.id.tv_customer_name)
    TextView tvCustomerName;
    @InjectView(R.id.tv_customer_phone)
    TextView tvCustomerPhone;
    @InjectView(R.id.tv_service_location)
    TextView tvServiceLocation;
    @InjectView(R.id.tv_service_time)
    TextView tvServiceTime;
    @InjectView(R.id.goods_expand_list_view)
    ScrollExpandableListView expandListView;
    @InjectView(R.id.goods_expand_title)
    StoreExpandableTitleView goodsExpandTitle;
    @InjectView(R.id.invoice_title)
    StoreExpandableTitleView invoiceTitle;
    @InjectView(R.id.invoice_list_view)
    ScrollRecyclerView invoiceListView;
    @InjectView(R.id.tv_remark)
    StoreEditNormalView tvRemark;
    @InjectView(R.id.tv_money_customer)
    TextView tvMoneyCustomer;
    @InjectView(R.id.tv_money_counselor)
    TextView tvMoneyCounselor;
    @InjectView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @InjectView(R.id.tv_order_time)
    TextView tvOrderTime;
    @InjectView(R.id.tv_order_pay_time)
    TextView tvOrderPayTime;
    @InjectView(R.id.tv_order_pay_number)
    TextView tvOrderPayNumber;

    private Intent intent;
    private IStoreOrderDetailsPresenter storeOrderDetailsPresenter;
    private StoreOrderGoodsListAdapter goodsListAdapter;
    private StoreOrderInvoiceDetailsAdapter invoiceDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order_details);
        initView();
        initData();
    }

    private void initView() {
        setTitle("订单详情");
        goodsExpandTitle.setCallBack(this);
        invoiceTitle.setCallBack(this);

        goodsListAdapter = new StoreOrderGoodsListAdapter(this, true);
        invoiceDetailsAdapter = new StoreOrderInvoiceDetailsAdapter(this);

        expandListView.setAdapter(goodsListAdapter);
        invoiceListView.setAdapter(invoiceDetailsAdapter);
        invoiceListView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void initData() {
        intent = getIntent();
        storeOrderDetailsPresenter = new StoreOrderDetailsPresenterImpl(this);
        storeOrderDetailsPresenter.getStoreOrderDetails();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getStoreOrderDetailsSuccess(StoreOrderDetailsResultBean resultBean) {

    }

    @Override
    public void getStoreOrderDetailsFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public Long getOrderId() {
        return intent.getLongExtra(IntentName.INTENT_ORDERID, -1);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void setCustomerName(String name) {
        tvCustomerName.setText("客户：" + name);
    }

    @Override
    public void setCustomerPhone(String phone) {
        tvCustomerPhone.setText(phone);
    }

    @Override
    public void setServiceLocation(String location) {
        tvServiceLocation.setText("地址：" + location);
    }

    @Override
    public void setServiceTime(String time) {
        tvServiceTime.setText("服务时间：" + time);
    }

    @Override
    public void setGoodsItemNumber(String number) {
        goodsExpandTitle.setData("总计：" + number);
    }

    @Override
    public void onClickExpandable(View view, boolean isExpandable) {
        if (view == goodsExpandTitle) {
            if (isExpandable)
                expandListView.setVisibility(View.VISIBLE);
            else
                expandListView.setVisibility(View.GONE);
        } else if (view == invoiceTitle) {
            if (isExpandable)
                invoiceListView.setVisibility(View.VISIBLE);
            else
                invoiceListView.setVisibility(View.GONE);
        }

    }

    @Override
    public void setGoodsListData(Map<String, List<GoodsItemPerform>> data) {
        goodsListAdapter.setData(data);
        ViewUtils.expandGroup(expandListView, goodsListAdapter);
    }

    @Override
    public void setIsNeedInvoice(String content) {
        invoiceTitle.setData(content);
    }

    @Override
    public void setInvoiceData(List<GoodsInvoiceDetailsItem> listData) {
        invoiceDetailsAdapter.setData(listData);
    }

    @Override
    public void setRemark(String remark) {
        tvRemark.setData(remark);
    }

    @Override
    public void setCustomerMoney(String money) {
        tvMoneyCustomer.setText(money);
    }

    @Override
    public void setCounselorMoney(String money) {
        tvMoneyCounselor.setText(money);
    }

    @Override
    public void setOrderNumber(String number) {
        tvOrderNumber.setText(number);
    }

    @Override
    public void setOrderTime(String time) {
        tvOrderTime.setText(time);
    }

    @Override
    public void setOrderPayTime(String time) {
        tvOrderPayTime.setText(time);
    }

    @Override
    public void setOrderPayNumber(String payNumber) {
        tvOrderPayNumber.setText(payNumber);
    }


}
