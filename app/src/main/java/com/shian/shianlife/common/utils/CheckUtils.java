package com.shian.shianlife.common.utils;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;


import com.shian.shianlife.base.BaseActivity;

import java.util.List;

/**
 * Created by zm.
 */

public class CheckUtils {
    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 檢測是否有獲取照片權限
     *
     * @param context
     * @param resultCode
     */
    public static boolean checkPhotoPermission(Context context, int resultCode, String whyText) {
        boolean hasPermission = CheckUtils.getPermissionToReadUserContacts(
                context,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                whyText,
                resultCode);
        return hasPermission;
    }

    /**
     * 权限检测
     */
    public static boolean getPermissionToReadUserContacts(Context context, String[] permissions, String toastContent, int requestCode) {
        /**
         * 1)使用ContextCompat.chefkSelfPermission(),因为Context.permission
         * 只在棒棒糖系统中使用
         * 2）总是检查权限（即使权限被授予）因为用户可能会在设置中移除你的权限*/
        boolean isPermission = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                //权限为获取，检查用户是否被询问过并且拒绝了，如果是这样的话，给予更多
                //解释
//                if (ActivityCompat.shouldShowRequestPermissionRationale(scanForActivity(context), permission)) {
//                    //在界面上展示为什么需要該權限
//                    Toast.makeText(context, toastContent, Toast.LENGTH_SHORT).show();
//                }
                //发起请求获得用户许可,可以在此请求多个权限
                isPermission = false;
            }
        }
        if (isPermission) {
            return isPermission;
        } else {
            ActivityCompat.requestPermissions(scanForActivity(context), permissions, requestCode);
            return isPermission;
        }
    }


    /**
     * 判断能否转为Activity
     *
     * @param cont
     * @return
     */
    public static BaseActivity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof BaseActivity)
            return (BaseActivity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String pkName = packageInfos.get(i).packageName;
                if (pkName.equals(packageName)) return true;
            }
        }
        return false;
    }


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(Integer str) {
        return str == null || str == -1;
    }

    public static boolean isEmpty(Long str) {
        return str == null || str == -1;
    }

    public static boolean isEmpty(Float str) {
        return str == null ;
    }
}
