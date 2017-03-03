package com.shian.shianlife.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.shian.shianlife.activity.MainActivity;
import com.shian.shianlife.activity.PgzxActivity;
import com.shian.shianlife.base.SaBaseApplication;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.fragment.OrderFragment;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpSaveTime;
import com.shian.shianlife.provide.params.HpSkuIdParams;
import com.shian.shianlife.provide.result.HrGetMsgNumberForUntreated;
import com.shian.shianlife.provide.result.HrGetSKUDetails;
import com.summerxia.dateselector.widget.DateTimeSelectorDialogBuilder;
import com.viewpagerindicator.TabPageIndicator;

import me.leolin.shortcutbadger.ShortcutBadger;

public class Utils {

    public static void LogVPrint(String content){
        if(SaBaseApplication.LOGFLAG){
            Log.v("this",content+"");
        }
    }

    public static String getDateUtils(long paramLong) {
        return TransitionDate.DateToStr(new Date(paramLong), "yyyy-MM-dd HH:mm");
    }

    public static void call(final View v, final String phone) {
        if (!TextUtils.isEmpty(phone)) {
            v.setVisibility(View.VISIBLE);
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View vv) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            v.setVisibility(View.GONE);
        }
    }

    public static boolean isPhoneNumber(CharSequence input) {
        if (input == null) {
            return false;
        } else {
            String regex = "(\\+\\d+)?1[3458]\\d{9}$";
            return Pattern.matches(regex, input);
        }
    }

    public static int getArrayINdex(String[] arr, String text) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(text)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void timeSelect(Context context, final TextView textView) {
        DateTimeSelectorDialogBuilder dialog = DateTimeSelectorDialogBuilder
                .getInstance(context);
        dialog.setShowHour(true);
        dialog.show();
        dialog.setOnSaveListener(new DateTimeSelectorDialogBuilder.OnSaveListener() {

            @Override
            public void onSaveSelectedDate(String selectedDate) {
                // TODO Auto-generated method stub
                textView.setText(selectedDate);
            }
        });
    }

    public static List<String> stringsToList(String[] data) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        return list;
    }


   public static void  getSKUDetails(final Context context, long id){
       //获取商品详情
       HpSkuIdParams params = new HpSkuIdParams();
       params.setSkuId(id);
       MHttpManagerFactory.getAccountManager().getSKUDetails(context, params, new HttpResponseHandler<HrGetSKUDetails>() {
           @Override
           public void onStart() {

           }

           @Override
           public void onSuccess(HrGetSKUDetails result) {

               if (result.getDetails() != null && !result.getDetails().equals("")) {
                   AlertDialog dialog = new AlertDialog.Builder(context)
                           .setTitle("商品详情")
                           .setMessage(result.getDetails())
                           .setPositiveButton("确认", null)
                           .create();
                   dialog.show();
               } else {
                   ToastUtils.show(context, "没有商品详情数据");
               }
           }

           @Override
           public void onError(String message) {

           }
       });
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

}
