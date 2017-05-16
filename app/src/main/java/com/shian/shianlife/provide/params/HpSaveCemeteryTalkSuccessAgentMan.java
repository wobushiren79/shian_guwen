package com.shian.shianlife.provide.params;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HpSaveCemeteryTalkSuccessAgentMan extends BaseHttpParams {
    private long bespeakId;//咨询ID
    private long orderId;//订单ID
    private int saveType;//提交人的类型（1，洽谈人。2，售后。如果是售后，那么这个提交只能进行一次）
    private String agentmanPhone;//经办人电话
    private String agentmanName;//经办人名字
    private String relation;//经办人与使用者关系
    private String agentmanLocation;//经办人地址
    private String agentmanCardId;//经办人身份证号码
    private String agentmanEmail;//经办人邮箱
    private String remark;//备注

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getBespeakId() {
        return bespeakId;
    }

    public void setBespeakId(long bespeakId) {
        this.bespeakId = bespeakId;
    }

    public int getSaveType() {
        return saveType;
    }

    public void setSaveType(int saveType) {
        this.saveType = saveType;
    }

    public String getAgentmanPhone() {
        return agentmanPhone;
    }

    public void setAgentmanPhone(String agentmanPhone) {
        this.agentmanPhone = agentmanPhone;
    }

    public String getAgentmanName() {
        return agentmanName;
    }

    public void setAgentmanName(String agentmanName) {
        this.agentmanName = agentmanName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAgentmanLocation() {
        return agentmanLocation;
    }

    public void setAgentmanLocation(String agentmanLocation) {
        this.agentmanLocation = agentmanLocation;
    }

    public String getAgentmanCardId() {
        return agentmanCardId;
    }

    public void setAgentmanCardId(String agentmanCardId) {
        this.agentmanCardId = agentmanCardId;
    }

    public String getAgentmanEmail() {
        return agentmanEmail;
    }

    public void setAgentmanEmail(String agentmanEmail) {
        this.agentmanEmail = agentmanEmail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
