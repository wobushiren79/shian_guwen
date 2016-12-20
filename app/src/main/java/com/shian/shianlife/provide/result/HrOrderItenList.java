package com.shian.shianlife.provide.result;

import java.util.List;

public class HrOrderItenList {
    /**
     * "canEdit":false, "items":[ { "id":178, "skuCtgName":"string",
     * "skuName":"string", "number":5, "note":"string", "executorId":178,
     * "executorName":"string", "itemStatus":5 } ] }
     */
    boolean canEdit;
    boolean canRefund;
    int orderHandleStatus;
    List<OrderItem> items;

    public int getOrderHandleStatus() {
        return orderHandleStatus;
    }

    public void setOrderHandleStatus(int orderHandleStatus) {
        this.orderHandleStatus = orderHandleStatus;
    }

    public boolean isCanRefund() {
        return canRefund;
    }

    public void setCanRefund(boolean canRefund) {
        this.canRefund = canRefund;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }


}
