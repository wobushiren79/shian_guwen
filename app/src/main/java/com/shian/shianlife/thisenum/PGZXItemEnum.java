package com.shian.shianlife.thisenum;

/**
 * Created by Administrator on 2017/3/26.
 */

public enum PGZXItemEnum {


    DayOne1("处理临终现场"), DayOne2("治丧第一天\n现场服务"), DayOne3("结束治丧\n初期服务"),
    DayTwo1("开始服务"), DayTwo2("出殡前服务"), DayTwo3("结束出殡\n前服务"),
    DayThree1("开始服务"), DayThree2("出殡当天\n现场服务"), DayThree3("殡仪馆服务");


    private String name;

    PGZXItemEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
