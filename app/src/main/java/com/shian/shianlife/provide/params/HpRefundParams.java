package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/20.
 */
public class HpRefundParams extends BaseHttpParams {
    private ArrayList<RefundItem> refundItems;

    public ArrayList<RefundItem> getRefundItems() {
        return refundItems;
    }

    public void setRefundItems(ArrayList<RefundItem> refundItems) {
        this.refundItems = refundItems;
    }

    public static class RefundItem{
         long orderItemId;
        int refundNumber;
        String reason;

        public long getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(long orderItemId) {
            this.orderItemId = orderItemId;
        }

        public int getRefundNumber() {
            return refundNumber;
        }

        public void setRefundNumber(int refundNumber) {
            this.refundNumber = refundNumber;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
