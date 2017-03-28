package com.shian.shianlife.common.view.refund;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.RefundOrderActivity;
import com.shian.shianlife.provide.model.OrderProductItemModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

@SuppressLint("InflateParams")
public class ProductDetailRefundView extends LinearLayout {
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
    @InjectView(R.id.iv_sub)
    ImageView iv_sub;
    @InjectView(R.id.iv_add)
    ImageView iv_add;
    @InjectView(R.id.et_num)
    EditText etNum;

    private int mNum;
    private float mTotalPrice;
    private long orderItemId;

    public ProductDetailRefundView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.item_productdetailrefund_other, this);
        ButterKnife.inject(this);

        boolean isFlag = ((RefundOrderActivity) getContext()).isDetailFlag();
        if (isFlag) {
            iv_sub.setVisibility(View.GONE);
            iv_add.setVisibility(View.GONE);
            et_reason.setEnabled(false);
        }
    }

    public void setData(OrderProductItemModel mProductItemModel) {
        tv_name.setText(mProductItemModel.getName());
        tv_unit.setText(mProductItemModel.getUnit());
        tv_huohao.setText(mProductItemModel.getSpecification());
        tv_danjia.setText(mProductItemModel.getPrice() + "");
        tv_money.setText(mProductItemModel.getTotalPrice() + "");
        tv_num.append(mProductItemModel.getNumber() + "");
        etNum.setText(mProductItemModel.getRefundNumber() + "");
        tv_tkje.setText("￥" + mProductItemModel.getRefundTotalPrice());
        if (!TextUtils.isEmpty(mProductItemModel.getRefundReason())) {
            et_reason.setText(mProductItemModel.getRefundReason());
        }
        mNum = mProductItemModel.getNumber();
        mTotalPrice = mProductItemModel.getTotalPrice();
        orderItemId = mProductItemModel.getId();
    }

    @OnClick(R.id.iv_sub)
    void SubClick(ImageView v) {
        int num = Integer.valueOf(etNum.getText().toString());
        if (num == 0) return;
        etNum.setText(--num + "");
        tv_tkje.setText("￥" + (mTotalPrice * num));
        ((RefundOrderActivity) getContext()).setRefundMoney(-mTotalPrice);
        ((RefundOrderActivity) getContext()).setRefundItems(orderItemId, num, et_reason.getText().toString());
    }

    @OnClick(R.id.iv_add)
    void addClick(ImageView v) {
        int num = Integer.valueOf(etNum.getText().toString());
        if (num == mNum) return;
        etNum.setText(++num + "");
        tv_tkje.setText("￥" + (mTotalPrice * num));
        ((RefundOrderActivity) getContext()).setRefundMoney(mTotalPrice);
        ((RefundOrderActivity) getContext()).setRefundItems(orderItemId, num, et_reason.getText().toString());
    }


}
