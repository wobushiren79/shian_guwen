package com.shian.shianlife.thisenum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zm.
 */

public enum RoleEnum {
    Car_Driver("car.driver", "派车司机"),
    Goods_Audit("goods.auditor", "单项审核员"),
    Goods_Executor("goods.executor", "单项执行者"),
    Goods_Advisor("goods.advisor", "单项顾问"),
    Funeral_Executor("funeral.executor", "殡仪执行者"),
    Funeral_Advisor("funeral.advisor", "殡仪顾问"),
    Cemetery_Advisor("cemetery.advisor", "公墓-顾问"),
    Cemetery_Talker("cemetery.talker", "公墓-洽谈"),
    Cemetery_Burier_Stone("cemetery.burier.stone", "公墓-安葬工-立碑"),
    Cemetery_Burier_Bury("cemetery.burier.bury", "公墓-安葬工-安葬");

    private String code;
    private String name;

    RoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<String> getRoleNameList(List<String> listCode) {
        List<String> roleNameList = new ArrayList<>();
        if (listCode == null)
            return roleNameList;
        RoleEnum[] roleEna = RoleEnum.values();
        for (RoleEnum item : roleEna) {
            for (String code : listCode) {
                if (code.equals(item.getCode())) {
                    roleNameList.add(item.getName());
                }
            }
        }
        return roleNameList;
    }

    public static boolean checkHasRole(List<String> listCode, RoleEnum role) {
        boolean hasRole = false;
        if (listCode == null)
            return hasRole;
        for (String code : listCode) {
            if (code.equals(role.getCode())) {
                hasRole = true;
                break;
            }
        }
        return hasRole;
    }
}
