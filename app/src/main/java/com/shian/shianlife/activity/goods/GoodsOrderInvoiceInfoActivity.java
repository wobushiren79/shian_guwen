package com.shian.shianlife.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.ViewUtils;
import com.shian.shianlife.mvp.shared.bean.SharedGoodsInvoiceInfoBean;
import com.shian.shianlife.mvp.shared.presenter.ISharedGoodsInvoiceInfoPresenter;
import com.shian.shianlife.mvp.shared.presenter.impl.SharedGoodsInvoiceInfoPresenterImpl;
import com.shian.shianlife.mvp.shared.view.ISharedGoodsInvoiceInfoView;
import com.shian.shianlife.thisenum.GoodsFinanceDeliveryEnum;
import com.shian.shianlife.thisenum.GoodsInvoiceTitleTypeEnum;
import com.shian.shianlife.view.goods.StoreEditNormalView;
import com.summerxia.dateselector.widget.LocationSelectorDialogBuilder;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GoodsOrderInvoiceInfoActivity extends BaseActivity implements ISharedGoodsInvoiceInfoView, View.OnClickListener, CompoundButton.OnCheckedChangeListener, StoreEditNormalView.CallBack {


    @InjectView(R.id.tv_submit)
    TextView tvSubmit;

    @InjectView(R.id.check_no_invoice)
    RadioButton checkNoInvoice;
    @InjectView(R.id.ll_rb_no_invoice)
    LinearLayout llRbNoInvoice;
    @InjectView(R.id.check_has_invoice)
    RadioButton checkHasInvoice;
    @InjectView(R.id.ll_rb_has_invoice)
    LinearLayout llRbHasInvoice;

    @InjectView(R.id.ll_invoice)
    LinearLayout llInvoice;

    @InjectView(R.id.check_invoice_self)
    RadioButton checkInvoiceSelf;
    @InjectView(R.id.ll_invoice_self)
    LinearLayout llInvoiceSelf;
    @InjectView(R.id.check_invoice_company)
    RadioButton checkInvoiceCompany;
    @InjectView(R.id.et_company_name)
    EditText etCompanyName;
    @InjectView(R.id.ll_invoice_company)
    LinearLayout llInvoiceCompany;
    @InjectView(R.id.layout_invoice_company_tax_number)
    StoreEditNormalView layoutInvoiceCompanyTaxNumber;
    @InjectView(R.id.layout_invoice_company_remark)
    StoreEditNormalView layoutInvoiceCompanyRemark;
    @InjectView(R.id.ll_invoice_company_info)
    LinearLayout llInvoiceCompanyInfo;
    @InjectView(R.id.layout_send_name)
    StoreEditNormalView layoutSendName;
    @InjectView(R.id.layout_send_phone)
    StoreEditNormalView layoutSendPhone;
    @InjectView(R.id.layout_send_location)
    StoreEditNormalView layoutSendLocation;
    @InjectView(R.id.layout_send_details_location)
    StoreEditNormalView layoutSendDetailsLocation;
    @InjectView(R.id.ll_invoice_info)
    LinearLayout llInvoiceInfo;

    @InjectView(R.id.layout_title_invoice_type)
    StoreEditNormalView layoutTitleInvoiceType;
    @InjectView(R.id.layout_title_invoice_send)
    StoreEditNormalView layoutTitleInvoiceSend;

    private Integer isNeedInvoice;//是否需要发票

    private Integer invoiceType;//个人还是单位

    private ISharedGoodsInvoiceInfoPresenter sharedGoodsInvoiceInfoPresenter;

    public static final int InvoiceResultCode = 6666;

    private SharedGoodsInvoiceInfoBean invoiceInfoData;//发票数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order_invoice_info);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initView() {
        setTitle("发票信息");

        tvSubmit.setOnClickListener(this);
        llRbNoInvoice.setOnClickListener(this);
        llRbHasInvoice.setOnClickListener(this);
        checkNoInvoice.setOnCheckedChangeListener(this);
        checkHasInvoice.setOnCheckedChangeListener(this);
        llInvoiceCompany.setOnClickListener(this);
        llInvoiceSelf.setOnClickListener(this);
        checkInvoiceSelf.setOnCheckedChangeListener(this);
        checkInvoiceCompany.setOnCheckedChangeListener(this);

        layoutSendLocation.setMode(StoreEditNormalView.Mode_Check);
        layoutSendLocation.setCallBack(this);

        checkNoInvoice.setChecked(true);


        layoutTitleInvoiceType.setIsEnabled(false);
        layoutTitleInvoiceSend.setIsEnabled(false);
    }

    private void initData() {
        sharedGoodsInvoiceInfoPresenter = new SharedGoodsInvoiceInfoPresenterImpl(this);
        sharedGoodsInvoiceInfoPresenter.getServiceInfoData();
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
    public void getSharedGoodsInvoiceInfoSuccess(SharedGoodsInvoiceInfoBean result) {
        this.invoiceInfoData = result;
    }

    @Override
    public void getSharedGoodsInvoiceInfoFail(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void setSharedGoodsInvoiceInfoSuccess(SharedGoodsInvoiceInfoBean data) {
        ToastUtils.show(this, "保存成功");
        this.invoiceInfoData = data;
        GoodsOrderSettlementActivity.isSaveInvoiceInfo = true;

        Intent intent = new Intent();
        intent.putExtra(IntentName.INTENT_DATA, data);
        setResult(InvoiceResultCode, intent);
        finish();
    }

    @Override
    public void setSharedGoodsInvoiceInfoFail(String msg) {
        ToastUtils.show(this, msg);

    }

    @Override
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
        if (invoiceType == GoodsInvoiceTitleTypeEnum.personal.getCode()) {
            checkInvoiceSelf.setChecked(true);
        } else if (invoiceType == GoodsInvoiceTitleTypeEnum.company.getCode()) {
            checkInvoiceCompany.setChecked(true);
        }
    }

    @Override
    public void setIsNeedInvoice(Integer isNeedInvoice) {
        this.isNeedInvoice = isNeedInvoice;
        if (isNeedInvoice == GoodsFinanceDeliveryEnum.notinvoicement.getCode()) {
            checkNoInvoice.setChecked(true);
        } else if (isNeedInvoice == GoodsFinanceDeliveryEnum.hasinvoicement.getCode()) {
            checkHasInvoice.setChecked(true);
        }
    }

    @Override
    public void setInvoiceCompanyName(String companyName) {
        etCompanyName.setText(companyName);
    }

    @Override
    public void setInvoiceCompanyTaxNumber(String companyTaxNumber) {
        layoutInvoiceCompanyTaxNumber.setData(companyTaxNumber);
    }

    @Override
    public void setInvoiceCompanyRemark(String companyRemark) {
        layoutInvoiceCompanyRemark.setData(companyRemark);
    }

    @Override
    public void setInvoiceReceiverName(String receiverName) {
        layoutSendName.setData(receiverName);
    }

    @Override
    public void setInvoiceReceiverPhone(String receiverPhone) {
        layoutSendPhone.setData(receiverPhone);
    }

    @Override
    public void setInvoiceReceiverDetailsLocation(String receiverDetailsLocation) {
        layoutSendDetailsLocation.setData(receiverDetailsLocation);
    }

    @Override
    public void setInvoiceReceiverLocation(String receiverLocation) {
        layoutSendLocation.setData(receiverLocation);
    }

    @Override
    public Integer getIsNeedInvoice() {
        return isNeedInvoice;
    }

    @Override
    public Integer getInvoiceType() {
        return invoiceType;
    }

    @Override
    public String getInvoiceCompanyName() {
        return etCompanyName.getText().toString();
    }

    @Override
    public String getInvoiceCompanyRemark() {
        return layoutInvoiceCompanyRemark.getData();
    }

    @Override
    public String getInvoiceCompanyTaxNumber() {
        return layoutInvoiceCompanyTaxNumber.getData();
    }

    @Override
    public String getInvoiceReceiverName() {
        return layoutSendName.getData();
    }

    @Override
    public String getInvoiceReceiverPhone() {
        return layoutSendPhone.getData();
    }

    @Override
    public String getInvoiceReceiverLocation() {
        return layoutSendLocation.getData();
    }

    @Override
    public String getInvoiceReceiverDetailsLocation() {
        return layoutSendDetailsLocation.getData();
    }

    @Override
    public void onClick(View v) {
        if (v == llRbNoInvoice) {
            checkNoInvoice.setChecked(true);
        } else if (v == llRbHasInvoice) {
            checkHasInvoice.setChecked(true);
        } else if (v == llInvoiceCompany) {
            checkInvoiceCompany.setChecked(true);
        } else if (v == llInvoiceSelf) {
            checkInvoiceSelf.setChecked(true);
        } else if (v == tvSubmit) {
            submitData();
        }
    }

    /**
     * 保存数据
     */
    private void submitData() {
        sharedGoodsInvoiceInfoPresenter.setServiceInfoData();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView == checkNoInvoice) {
                isNeedInvoice = GoodsFinanceDeliveryEnum.notinvoicement.getCode();
                checkHasInvoice.setChecked(false);
                llInvoiceInfo.setVisibility(View.GONE);
            } else if (buttonView == checkHasInvoice) {
                isNeedInvoice = GoodsFinanceDeliveryEnum.hasinvoicement.getCode();
                checkNoInvoice.setChecked(false);
                llInvoiceInfo.setVisibility(View.VISIBLE);
            } else if (buttonView == checkInvoiceSelf) {
                invoiceType = GoodsInvoiceTitleTypeEnum.personal.getCode();
                checkInvoiceCompany.setChecked(false);
                llInvoiceCompanyInfo.setVisibility(View.GONE);
            } else if (buttonView == checkInvoiceCompany) {
                invoiceType = GoodsInvoiceTitleTypeEnum.company.getCode();
                checkInvoiceSelf.setChecked(false);
                llInvoiceCompanyInfo.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onCheck(View view) {
        if (view == layoutSendLocation) {
            getLocationSelect();
        }
    }

    /**
     * 获取地区选择器
     */
    private void getLocationSelect() {
        ViewUtils.getLocationSelectDialog(this, new LocationSelectorDialogBuilder.OnSaveLocationLister() {
            @Override
            public void onSaveLocation(String location, String provinceId, String cityId) {
                layoutSendLocation.setData(location);
            }
        });
    }
}
