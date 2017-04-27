package com.shian.shianlife.thisenum;

/**
 * 预约-状态，值：1未分配、2未指派,3未接单、4已接单、5再次洽谈、、6洽谈失败（未购墓）、7洽谈成功（购墓）、8服务结束
 * Created by Administrator
 */
public enum CemeteryBeSpeakStateEnum {
    undistributed(1, "未分配"),
    unassigned(2, "未指派"),
    unProcess(3, "未接单"),
    accepted(4, "已接单"),
    talkAgain(5, "再次洽谈"),
    talkFail(6, "洽谈失败（未购墓）"),
    talkSuccess(7, "洽谈成功（购墓）"),
    serviceOver(8, "服务结束");


    private Integer code;
    private String text;

    CemeteryBeSpeakStateEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
