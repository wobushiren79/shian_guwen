package com.shian.shianlife.thisenum;

/**
 * Created by zm
 */
public enum CemeteryBuildListTypeEnum {
    all(-1, "全部", "marketing/bespeak/build/list"),
    confirm_build(0, "待确认", "marketing/bespeak/build/list/confirm"),
    wait_look(1, "待看墓", "marketing/bespeak/build/list/wait"),
    look_ing(2, "看墓中", "marketing/bespeak/build/list/look"),
    has_order(3, "已定墓", "marketing/bespeak/build/list/has"),
    no_order(4, "未定墓", "marketing/bespeak/build/list/no");

    private int code;
    private String name;
    private String url;

    CemeteryBuildListTypeEnum(int code, String name, String url) {
        this.code = code;
        this.name = name;
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String getUrlFromCode(int code) {
        String url = "";
        CemeteryBuildListTypeEnum[] values = CemeteryBuildListTypeEnum.values();
        for (CemeteryBuildListTypeEnum item : values) {
            if (item.getCode() == code) {
                url = item.getUrl();
                break;
            }
        }
        return url;
    }
}
