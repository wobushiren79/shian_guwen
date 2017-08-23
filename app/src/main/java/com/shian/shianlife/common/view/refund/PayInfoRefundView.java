package com.shian.shianlife.common.view.refund;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.RefundOrderActivity;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.BordModel;
import com.shian.shianlife.provide.params.HpRefundParams;
import com.shian.shianlife.provide.result.HrGetOrderDetailResult;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Request;

public class PayInfoRefundView extends LinearLayout {


    @InjectView(R.id.tv_refund_yske)
    TextView tv_totalAmount;

    @InjectView(R.id.tv_prepayAmount)
    TextView tv_prepayAmount;

    @InjectView(R.id.tv_prepayEndTime)
    TextView tv_prepayEndTime;

    @InjectView(R.id.tv_yfje)
    TextView tv_restPayAmount;

    @InjectView(R.id.tv_sure)
    TextView tv_sure;


    public PayInfoRefundView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View.inflate(getContext(),R.layout.view_moneydetailrefund,this);
        ButterKnife.inject(this);
        boolean isFlag = ((RefundOrderActivity) getContext()).isDetailFlag();
        if (isFlag) {
            tv_sure.setVisibility(View.GONE);
        }
    }

    public void setData(HrGetOrderDetailResult result) {
        BordModel payInfo = result.getBoard();
        tv_totalAmount.append(payInfo.getTotalAmount() + "");
        tv_prepayAmount.append(payInfo.getPrepayAmount() + "");
        tv_prepayEndTime.append(payInfo.getRefundAmount() + "");
        tv_restPayAmount.append(payInfo.getRestAmount() + "");
    }

    public TextView getreFundMoney() {
        return tv_prepayEndTime;
    }

    @OnClick(R.id.tv_sure)
    void submit(View v) {
        HpRefundParams params = new HpRefundParams();
        params.setRefundItems((ArrayList<HpRefundParams.RefundItem>) ((RefundOrderActivity) getContext()).getRefundItems());
        MHttpManagerFactory.getFuneralManager().refundOrder(getContext(), params, new HttpResponseHandler<Object>() {
            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(getContext(),"提交成功");
                ((RefundOrderActivity) getContext()).finish();
            }



            @Override
            public void onError(String message) {

            }
        });
    }

}
