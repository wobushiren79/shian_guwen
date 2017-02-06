package com.shian.shianlife.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.MainActivity;
import com.shian.shianlife.common.push.Utils;

/**
 * Created by Administrator
 */

public class PushService extends Service {
    Thread thread;
    boolean isRun=true;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final Context context=this.getApplicationContext();
       thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (isRun) {
                    try {
                        sleep(10000);
                        PushManager.startWork(getApplicationContext(),
                                PushConstants.LOGIN_TYPE_API_KEY,
                                Utils.getMetaValue(context, "api_key"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
            Intent nfIntent = new Intent(this, MainActivity.class);
            builder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                    .setContentTitle("消息通知") // 设置下拉列表里的标题
                    .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                    .setContentText("已开启消息通知") // 设置上下文内容
                    .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间
            Notification notification = null; // 获取构建好的Notification

            notification = builder.build();
            notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音}

            // 参数一：唯一的通知标识；参数二：通知消息。
            startForeground(999, notification);// 开始前台服务


//            RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.notification_layout);// 获取remoteViews（参数一：包名；参数二：布局资源）
//            builder = new Notification.Builder(this.getApplicationContext()).setContent(remoteViews);// 设置自定义的Notification内容
//            builder.setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher);
//            Notification notification = builder.getNotification();// 获取构建好的通知--.build()最低要求在 API16及以上版本上使用，低版本上可以使用.getNotification()。
//            Notificationnotification.defaults = Notification.DEFAULT_SOUND;//设置为默认的声音
//            startForeground(110, notification);// 开始前台服务
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        isRun=false;
        super.onDestroy();
    }
}
