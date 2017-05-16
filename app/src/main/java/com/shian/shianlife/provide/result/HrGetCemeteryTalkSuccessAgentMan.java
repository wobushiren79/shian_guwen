package com.shian.shianlife.provide.result;

/**
 * Created by Administrator on 2017/2/9.
 */

public class HrGetCemeteryTalkSuccessAgentMan {
    private String agentmanPhone;//经办人电话
    private boolean saveCan;//售后是否提交过（true:提交过。false:没有提交过）
    private String agentmanName;//经办人名字
    private String relation;//经办人与使用者关系
    private String agentmanLocation;//经办人地址
    private String agentmanCardId;//	经办人身份证号码
    private String agentmanEmail;//经办人邮箱
    private String remark;//备注

    public String getAgentmanPhone() {
        return agentmanPhone;
    }

    public void setAgentmanPhone(String agentmanPhone) {
        this.agentmanPhone = agentmanPhone;
    }

    public boolean isSaveCan() {
        return saveCan;
    }

    public void setSaveCan(boolean saveCan) {
        this.saveCan = saveCan;
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
