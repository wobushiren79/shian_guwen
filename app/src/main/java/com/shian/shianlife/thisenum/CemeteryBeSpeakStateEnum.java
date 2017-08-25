package com.shian.shianlife.thisenum;

/**
 * 预约洽谈状态，值：1未分配、2未指派、3未接单、4已接单、5再次洽谈、6洽谈失败（未购墓）、7已下单、8洽谈成功（购墓）、9服务结束
 * Created by Administrator
 */
public enum CemeteryBeSpeakStateEnum {
    undistributed(1, "未分配", ""),
    unassigned(2, "未指派", "未分配接待"),
    unProcess(3, "未接单", ""),
    accepted(4, "已接单", ""),
    talkAgain(5, "再次洽谈", ""),
    talkFail(6, "洽谈失败（未购墓）", "参观未定墓"),
    ready(7, "已下单", ""),
    talkSuccess(8, "洽谈成功（购墓）", ""),
    serviceOver(9, "服务结束", "已安葬"),
    alreadyCancel(10, "已取消","取消看墓");


    private Integer code;
    private String text;
    private String otherName;

    CemeteryBeSpeakStateEnum(Integer code, String text, String otherName) {
        this.code = code;
        this.text = text;
        this.otherName = otherName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
}
