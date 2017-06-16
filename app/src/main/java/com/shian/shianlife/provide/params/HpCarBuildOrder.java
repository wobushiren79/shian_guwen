package com.shian.shianlife.provide.params;


import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by zm.
 */

public class HpCarBuildOrder extends BaseHttpParams {

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务对应的id
     */
    private Long busiId;

    /**
     * 申请人id
     */
    private Long proposerId;

    /**
     *
     */
    private Long customerId;

    /**
     * 用车人
     */
    private String connecterName;

    /**
     * 用车人联系号码
     */
    private String connecterMobile;

    /**
     * 用车目的
     */
    private String purpose;

    /**
     * 乘车人数
     */
    private String seats;

    /**
     * 出发地
     */
    private String source;

    /**
     * 出发地_经度
     */
    private String sourceLongitude;

    /**
     * 出发地_维度
     */
    private String sourceLatitude;

    /**
     * 目的地
     */
    private String target;

    /**
     * 目的地
     */
    private String targetLongitude;

    /**
     * 目的地_维度
     */
    private String targetLatitude;

    /**
     * 预约用车时间
     */
    private String appointmentTime;

    /**
     * 司机
     */
    private Long driverId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 当前经度
     */
    private String curLongitude;

    /**
     * 当前纬度
     */
    private String curLatitude;


    /**
     * 申请人
     */
    private String proposerName;

    /**
     * 申请人电话
     */
    private String proposerMobile;



    public String getCurLongitude() {
        return curLongitude;
    }

    public void setCurLongitude(String curLongitude) {
        this.curLongitude = curLongitude;
    }

    public String getCurLatitude() {
        return curLatitude;
    }

    public void setCurLatitude(String curLatitude) {
        this.curLatitude = curLatitude;
    }



    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public String getProposerMobile() {
        return proposerMobile;
    }

    public void setProposerMobile(String proposerMobile) {
        this.proposerMobile = proposerMobile;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Long getBusiId() {
        return busiId;
    }

    public void setBusiId(Long busiId) {
        this.busiId = busiId;
    }

    public Long getProposerId() {
        return proposerId;
    }

    public void setProposerId(Long proposerId) {
        this.proposerId = proposerId;
    }



    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getConnecterName() {
        return connecterName;
    }

    public void setConnecterName(String connecterName) {
        this.connecterName = connecterName;
    }

    public String getConnecterMobile() {
        return connecterMobile;
    }

    public void setConnecterMobile(String connecterMobile) {
        this.connecterMobile = connecterMobile;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(String sourceLongitude) {
        this.sourceLongitude = sourceLongitude;
    }

    public String getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(String sourceLatitude) {
        this.sourceLatitude = sourceLatitude;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetLongitude() {
        return targetLongitude;
    }

    public void setTargetLongitude(String targetLongitude) {
        this.targetLongitude = targetLongitude;
    }

    public String getTargetLatitude() {
        return targetLatitude;
    }

    public void setTargetLatitude(String targetLatitude) {
        this.targetLatitude = targetLatitude;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
