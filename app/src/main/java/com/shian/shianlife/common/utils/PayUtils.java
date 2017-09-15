package com.shian.shianlife.common.utils;

import android.content.Context;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by zm.
 */

public class PayUtils {
    private static void sendPayReq(Context context,
                                   String appid,
                                   String partnerid,
                                   String prepayid,
                                   String packageValue,
                                   String noncestr,
                                   int timestamp,
                                   String sign) {
        //微信注册APPID
        IWXAPI api = WXAPIFactory.createWXAPI(context, null);
        api.registerApp(appid);
        PayReq request = new PayReq();
        request.appId = appid;//微信开放平台审核通过的应用APPID
        request.partnerId = partnerid;//微信支付分配的商户号
        request.prepayId = prepayid;//微信返回的支付交易会话ID
        request.packageValue = packageValue;//暂填写固定值Sign=WXPay
        request.nonceStr = noncestr;//随机字符串，不长于32位。推荐随机数生成算法
        request.timeStamp = timestamp + "";//时间戳，请见接口规则-参数规定
        request.sign = sign;//签名，详见签名生成算法
        api.sendReq(request);
    }
}
