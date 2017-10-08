package com.shian.shianlife.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.goods.GoodsOrderPayActivity;
import com.shian.shianlife.activity.goods.GoodsOrderPayCallBackActivity;
import com.shian.shianlife.activity.order.StoreServiceActivity;
import com.shian.shianlife.base.SaBaseApplication;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends GoodsOrderPayCallBackActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, AppContansts.WeChat_Pay_AppId);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
        Toast.makeText(this, "openid = " + req.openId, Toast.LENGTH_SHORT).show();
        switch (req.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:

                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:

                break;
            case ConstantsAPI.COMMAND_LAUNCH_BY_WX:

                break;
            default:
                break;
        }
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    @Override
    public void onResp(BaseResp resp) {
        GoodsOrderPayActivity.isPaying = false;
        StoreServiceActivity.isNeedRefresh = true;
        ((SaBaseApplication) getApplicationContext()).destroyActivity(GoodsOrderPayActivity.class);
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            Toast.makeText(this, "code = " + ((SendAuth.Resp) resp).code, Toast.LENGTH_SHORT).show();
        }

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //显示充值成功的页面和需要的操作
                isSuccessPay = true;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                isSuccessPay = false;
                //用户取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                isSuccessPay = false;
                break;
            default:
                isSuccessPay = false;
                break;
        }
        getDataSuccess();
    }
}