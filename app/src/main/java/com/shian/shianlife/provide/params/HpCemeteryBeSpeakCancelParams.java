package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class HpCemeteryBeSpeakCancelParams extends BaseHttpParams {
    /**
     * 订单ID
     */
    private Long bespeakId;

    /**
     * 申请取消时候的状态
     */
    private int bespeakStatus;

    /**
     * 申请取消原因
     */
    private String cancelReason;

    /**
     * 申请取消原因备注
     */
    private String reasonComment;

    public Long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(Long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public int getBespeakStatus() {
        return bespeakStatus;
    }

    public void setBespeakStatus(int bespeakStatus) {
        this.bespeakStatus = bespeakStatus;
    }


    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getReasonComment() {
        return reasonComment;
    }

    public void setReasonComment(String reasonComment) {
        this.reasonComment = reasonComment;
    }
}