package com.shian.shianlife.thisenum;

/**
 * app角色权限，值：顾问员-新建advisor.auth.new、洽谈员-接待，talker.auth.receive、安葬工-立碑，burier.auth.build、安葬工-安葬，burier.auth.burying
 * Created by zm
 */
public enum AppRolePermition {
    ADVISOR("advisor.auth.new"),
    TALKER("talker.auth.receive"),
    BURIERBUILD("burier.auth.build"),
    BURIERBURYING("burier.auth.burying");
    private String code;

    AppRolePermition(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}