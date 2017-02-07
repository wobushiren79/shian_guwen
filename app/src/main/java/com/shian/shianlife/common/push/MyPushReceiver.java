package com.shian.shianlife.common.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Administrator on 2017/1/16.
 */

public class MyPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            com.shian.shianlife.common.utils.Utils.LogVPrint("ACTION_USER_PRESENT");
            startUploadService(context);
        }
        if (Intent.ACTION_PACKAGE_RESTARTED.equals(intent.getAction())) {
            com.shian.shianlife.common.utils.Utils.LogVPrint("ACTION_PACKAGE_RESTARTED");
            startUploadService(context);
        }
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            com.shian.shianlife.common.utils.Utils.LogVPrint("ACTION_BOOT_COMPLETED");
            startUploadService(context);
        }
    }

    private void startUploadService(Context context) {
        if (!PushManager.isPushEnabled(context)) {
            PushManager.startWork(context,
                    PushConstants.LOGIN_TYPE_API_KEY,
                    Utils.getMetaValue(context, "api_key"));
        }
    }
}
