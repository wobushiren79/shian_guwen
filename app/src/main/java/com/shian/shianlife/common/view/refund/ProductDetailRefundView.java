package com.shian.shianlife.common.view.refund;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.RefundOrderActivity;
import com.shian.shianlife.provide.model.OrderProductItemModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

@SuppressLint("InflateParams")
public class ProductDetailRefundView extends FrameLayout {

    private LinearLayout parentLayout;
    @InjectView(R.id.tv_name)
    TextView tv_name;
    @InjectView(R.id.tv_huohao)
    TextView tv_huohao;
    @InjectView(R.id.tv_unit)
    TextView tv_unit;
    @InjectView(R.id.tv_danjia)
    TextView tv_danjia;
    @InjectView(R.id.tv_money)
    TextView tv_money;
    @InjectView(R.id.tv_num)
    TextView tv_num;
    @InjectView(R.id.tv_tkje)
    TextView tv_tkje;
    @InjectView(R.id.et_reason)
    TextView et_reason;
    @InjectView(R.id.tv_sub)
    TextView tv_sub;
    @InjectView(R.id.tv_add)
    TextView tv_add;

    public ProductDetailRefundView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mParams.topMargin = getContext().getResources().getDimensionPixelSize(R.dimen.dimen_45dp);
        setLayoutParams(mParams);
        parentLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_productdetailrefund, null);
        addView(parentLayout);
        ButterKnife.inject(this);

        boolean isFlag = ((RefundOrderActivity) getContext()).isDetailFlag();
        if (isFlag) {
            tv_sub.setVisibility(View.GONE);
            tv_add.setVisibility(View.GONE);
            et_reason.setEnabled(false);
        }
    }

    private int mNum;
    private float mTotalPrice;
    private long orderItemId;

    public void setData(OrderProductItemModel mProductItemModel) {
        tv_name.setText(mProductItemModel.getName());
        tv_unit.setText(mProductItemModel.getUnit());
        tv_huohao.setText(mProductItemModel.getSpecification());
        tv_danjia.setText(mProductItemModel.getPrice() + "");
        tv_money.setText(mProductItemModel.getTotalPrice() + "");
        tv_num.append(mProductItemModel.getNumber() + "");
        etNum.setText(mProductItemModel.getRefundNumber()+"");
        tv_tkje.setText("退款金额:" + mProductItemModel.getRefundTotalPrice()+ "");
        if (!TextUtils.isEmpty(mProductItemModel.getRefundReason())) {
            et_reason.setText(mProductItemModel.getRefundReason());
        }
        mNum = mProductItemModel.getNumber();
        mTotalPrice = mProductItemModel.getTotalPrice();
        orderItemId = mProductItemModel.getId();
    }

    @InjectView(R.id.et_num)
    EditText etNum;

    @OnClick(R.id.tv_sub)
    void SubClick(TextView v) {
        int num = Integer.valueOf(etNum.getText().toString());
        if (num == 0) return;
        etNum.setText(--num + "");
        tv_tkje.setText("退款金额:" + (mTotalPrice * num) + "");
        ((RefundOrderActivity) getContext()).setRefundMoney(-mTotalPrice);
        ((RefundOrderActivity) getContext()).setRefundItems(orderItemId, num, et_reason.getText().toString());
    }

    @OnClick(R.id.tv_add)
    void addClick(TextView v) {
        int num = Integer.valueOf(etNum.getText().toString());
        if (num == mNum) return;
        etNum.setText(++num + "");
        tv_tkje.setText("退款金额:" + (mTotalPrice * num) + "");
        ((RefundOrderActivity) getContext()).setRefundMoney(mTotalPrice);
        ((RefundOrderActivity) getContext()).setRefundItems(orderItemId, num, et_reason.getText().toString());
    }


}
