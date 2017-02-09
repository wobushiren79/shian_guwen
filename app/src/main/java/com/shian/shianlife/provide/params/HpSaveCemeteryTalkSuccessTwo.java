package com.shian.shianlife.provide.params;

import com.shian.shianlife.provide.base.BaseHttpParams;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HpSaveCemeteryTalkSuccessTwo extends BaseHttpParams {

    private long bespeakId;//咨询ID
    private  long orderedId;//订单编号
    private int saveType;//提交人的类型（1，洽谈人。2，售后。如果是售后，那么这个提交只能进行一次）
    private String deadmanOneName;//使用者1
    private String deadmanTwoName;//使用者2
    private String deadmanOneAge;//使用者1年龄
    private String deadmanTwoAge;//使用者2年龄
    private String deadmanOneSex;//使用者1性别
    private String deadmanTwoSex;//使用者2性别
    private String deadmanOneState;//	使用者1现状
    private String deadmanTwoState;//使用者2现状
    private String deadmanOneCardId;//使用者1身份证
    private String deadmanTwoCardId;//使用者2身份证
    private String deadmanOneDeadTime;//使用者1死亡时间
    private String deadmanTwoDeadTime;//使用者2死亡时间
    private String remark;//备注

    public long getOrderedId() {
        return orderedId;
    }

    public void setOrderedId(long orderedId) {
        this.orderedId = orderedId;
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

    public String getDeadmanOneName() {
        return deadmanOneName;
    }

    public void setDeadmanOneName(String deadmanOneName) {
        this.deadmanOneName = deadmanOneName;
    }

    public String getDeadmanTwoName() {
        return deadmanTwoName;
    }

    public void setDeadmanTwoName(String deadmanTwoName) {
        this.deadmanTwoName = deadmanTwoName;
    }

    public String getDeadmanOneAge() {
        return deadmanOneAge;
    }

    public void setDeadmanOneAge(String deadmanOneAge) {
        this.deadmanOneAge = deadmanOneAge;
    }

    public String getDeadmanTwoAge() {
        return deadmanTwoAge;
    }

    public void setDeadmanTwoAge(String deadmanTwoAge) {
        this.deadmanTwoAge = deadmanTwoAge;
    }

    public String getDeadmanOneSex() {
        return deadmanOneSex;
    }

    public void setDeadmanOneSex(String deadmanOneSex) {
        this.deadmanOneSex = deadmanOneSex;
    }

    public String getDeadmanTwoSex() {
        return deadmanTwoSex;
    }

    public void setDeadmanTwoSex(String deadmanTwoSex) {
        this.deadmanTwoSex = deadmanTwoSex;
    }

    public String getDeadmanOneState() {
        return deadmanOneState;
    }

    public void setDeadmanOneState(String deadmanOneState) {
        this.deadmanOneState = deadmanOneState;
    }

    public String getDeadmanTwoState() {
        return deadmanTwoState;
    }

    public void setDeadmanTwoState(String deadmanTwoState) {
        this.deadmanTwoState = deadmanTwoState;
    }

    public String getDeadmanOneCardId() {
        return deadmanOneCardId;
    }

    public void setDeadmanOneCardId(String deadmanOneCardId) {
        this.deadmanOneCardId = deadmanOneCardId;
    }

    public String getDeadmanTwoCardId() {
        return deadmanTwoCardId;
    }

    public void setDeadmanTwoCardId(String deadmanTwoCardId) {
        this.deadmanTwoCardId = deadmanTwoCardId;
    }

    public String getDeadmanOneDeadTime() {
        return deadmanOneDeadTime;
    }

    public void setDeadmanOneDeadTime(String deadmanOneDeadTime) {
        this.deadmanOneDeadTime = deadmanOneDeadTime;
    }

    public String getDeadmanTwoDeadTime() {
        return deadmanTwoDeadTime;
    }

    public void setDeadmanTwoDeadTime(String deadmanTwoDeadTime) {
        this.deadmanTwoDeadTime = deadmanTwoDeadTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
