package com.shian.shianlife.common.contanst;

import com.shian.shianlife.mvp.login.bean.SystemLoginResultBean;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoResultBean;
import com.shian.shianlife.provide.result.HrGetMsgNumberForUntreated;
import com.shian.shianlife.provide.result.HrLoginResult;

import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;

public class AppContansts {
    //登陆地址
    public static final String Login_BaseUrl = "https://platform.shianlife.cn";
//    public static final String Login_BaseUrl = "http://prd-platform.xicp.cn";
//        public static final String Login_BaseUrl = "http://192.168.0.57:8099/ki4so-web";

    //单项地址
    public static final String Store_BaseUrl = "https://goods.shianlife.cn";
//        public static final String Store_BaseUrl = "http://prd-goods.xicp.cn";
//    public static final String Store_BaseUrl = "http://192.168.0.57:8080/goods";
    //殡仪地址
    public static final String Funeral_BaseUrl = "http://115.28.163.211:7088/shianlife-adviser-1.0-SNAPSHOT";
    //公墓地址
//    public static final String Cemetery_BaseUrl = "http://192.168.0.200:8000/shianlife-advisor-cemetery-1.0-SNAPSHOT";
    public static final String Cemetery_BaseUrl = "http://115.28.163.211:7088/shianlife-advisor-cemetery-1.0-SNAPSHOT";

    //商品地址
    public static final String Goods_BaseUrl = "http://goodsmgr.e-funeral.cn";
    //商品图片地址
    public static final String Goods_PicUrl = Goods_BaseUrl + "/Public/Uploads";


    public static final String PHP_BaseUrl = "http://app.e-funeral.cn";
    public static final String PHP_Web_BaseUrl = "http://m.e-funeral.cn";
    //临时用殡仪地址
    public static final String Temp_Funeral_BaseUrl = PHP_Web_BaseUrl + "/funeral";
    //临时用公墓地址
    public static final String Temp_Cemetery_BaseUrl = PHP_Web_BaseUrl + "/cemetery";

    //阿里云图片地址
    public static final String OSSURL = "http://shianlife123.oss-cn-qingdao.aliyuncs.com/";

    //KF5在线聊天地址
    public static final String KF5_BaseUrl = "https://wenshikai.kf5.com/kchat/18687?from=%E5%9C%A8%E7%BA%BF%E6%94%AF%E6%8C%81";


    public static final String siftsPHPURL = PHP_BaseUrl + "/home/index/sifts";//精选
    public static final String helpsPHPURL = PHP_BaseUrl + "/home/index/helps";//帮助
    public static final String dynamicsPHPURL = PHP_BaseUrl + "/home/index/dynamics";//动态
    public static final String phonePHPURL = PHP_BaseUrl + "/home/index/phone";//通讯宝
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

    public static HrLoginResult userLoginInfo = null;
    public static HrLoginResult userCemetery = null;
//

    //商品渠道ID
    public static Integer goodsChannelId;
    //推送渠道ID
    public static String pushChannelId;
    //平台用户数据
    public static SystemLoginResultBean systemLoginInfo;
    //登陆系统KEY
    public static String System_Ki4so_Client_Ec;
    //子系统-单项  登陆地址
    public static final String Login_Store_Url = Store_BaseUrl + "/login_sys_api";
    //cookie保存
    public static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    //世安電話
    public static final String Shianlife_Phone = "966188";

    //微信支付ID
    public static final String WeChat_Pay_AppId = "123";
    public static final String WeChat_Pay_Sign = "9f937fc5b1ba104e33cc3e422bce346b";
    public static final String WeChat_Pay_Package = "Sign=WXPay";

    //招商图片
    public static final String Cooperation_Pic_1 = "http://ovjs2f1iz.bkt.clouddn.com/index1.png";
    public static final String Cooperation_Pic_2 = "http://ovjs2f1iz.bkt.clouddn.com/index2.png";
    public static final String Cooperation_Pic_3 = "http://ovjs2f1iz.bkt.clouddn.com/index3.png";

}
