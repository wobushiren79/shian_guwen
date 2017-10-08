package com.shian.shianlife.common.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.shian.shianlife.common.contanst.AppContansts;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

/**
 * Created by zm.
 */

public class PayUtils {
    /**
     * @param context
     * @param appid        微信开放平台审核通过的应用APPID
     * @param partnerid    微信支付分配的商户号
     * @param prepayid     微信返回的支付交易会话ID
     * @param packageValue 暂填写固定值Sign=WXPay
     * @param noncestr     随机字符串，不长于32位。推荐随机数生成算法
     * @param timestamp    时间戳，请见接口规则-参数规定
     * @param key          密鑰
     */
    public static void sendPayReq(Context context,
                                  String appid,
                                  String partnerid,
                                  String prepayid,
                                  String packageValue,
                                  String noncestr,
                                  Long timestamp,
                                  String sign,
                                  String key) {
        //微信注册APPID
        IWXAPI payApi = WXAPIFactory.createWXAPI(context, AppContansts.WeChat_Pay_AppId, false);
        payApi.registerApp(AppContansts.WeChat_Pay_AppId);
        PayReq request = new PayReq();
        request.appId = appid;//微信开放平台审核通过的应用APPID
        request.partnerId = partnerid;//微信支付分配的商户号
        request.prepayId = prepayid;//微信返回的支付交易会话ID
        request.packageValue = packageValue;//暂填写固定值Sign=WXPay
        request.nonceStr = noncestr;//随机字符串，不长于32位。推荐随机数生成算法
        request.timeStamp = timestamp + "";//时间戳，请见接口规则-参数规定
        request.sign = sign;//签名，详见签名生成算法
        request.checkArgs();
        payApi.sendReq(request);
    }

    public static String getUUIDString() {
        UUID uuid = UUID.randomUUID();
        String randNumber = uuid.toString().replace("-", "");
        return randNumber;
    }


    /**
     * 微信支付签名算法sign
     *
     * @return
     */
    public static String createSign(String appId, String packageValue, String partnerId, String prepayId, String timeStamp, String nonceStr, String key) {
        StringBuffer signTemp = new StringBuffer();
        signTemp.append("appid=" + appId);
        signTemp.append("&nonce_str=" + nonceStr);
        signTemp.append("&package=" + packageValue);
        signTemp.append("&partnerid=" + partnerId);
        signTemp.append("&prepayid=" + prepayId);
        signTemp.append("&timestamp=" + timeStamp);
        signTemp.append("&key=" + key);
        return MD5Utils.MD5(signTemp.toString()).toUpperCase();
    }
}
