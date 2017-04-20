package com.shian.shianlife.common.contanst;

import com.shian.shianlife.provide.result.HrGetMsgNumberForUntreated;
import com.shian.shianlife.provide.result.HrLoginResult;

public class AppContansts {
    public static final String BaseURL = "http://192.168.0.146:8080";
//    public static final String BaseURL = "http://115.28.163.211:7080/shianlife-adviser-1.0-SNAPSHOT";
    public static final String OSSURL = "http://shianlife123.oss-cn-qingdao.aliyuncs.com/";
    public static final String PhpURL ="http://app.e-funeral.cn";
//    public static final String PhpURL = "http://192.168.0.170/shian_app_mgr";

    public static final String siftsPHPURL = PhpURL + "/home/index/sifts";//精选
    public static final String helpsPHPURL = PhpURL + "/home/index/helps";//帮助
    public static final String dynamicsPHPURL = PhpURL + "/home/index/dynamics";//动态
    public static final String phonePHPURL = PhpURL + "/home/index/phone";//通讯宝
    public static final String DiDichannel = "55455";//滴滴渠道号

    public static int MessageCount = 0;
    public static String LocalString;

    public static String LOCAL_PROVINCE = "";
    public static String LOCAL_CITY = "";
    public static String LOCAL_COUNTY = "";
    public static String LOCAL_STREET = "";
    public static String LOCAL_STREETNUM = "";
    public static String LOCAL_ADDRESS = "";
    public static double LOCAL_latitude = 30.6634450000;//纬度;
    public static double LOCAL_longitude = 104.0722210000;//经度;

    public static HrGetMsgNumberForUntreated MsgNumber;
    public static int MsgNumberTotal;

    public static HrLoginResult userLoginInfo = new HrLoginResult();
}
