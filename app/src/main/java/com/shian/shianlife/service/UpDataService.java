package com.shian.shianlife.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/2/13.
 */

public class UpDataService extends Service {
    //乐视体育
    // Uri.parse("http://122.228.237.132/apk.r1.market.hiapk.com/data/upload/apkres/2016/6_12/16/com.lesports.glivesports_040405.apk");
    //百度音乐
    String downloadURL = "http://gdown.baidu.com/data/wisegame/fd84b7f6746f0b18/baiduyinyue_4802.apk";
    String downloadName="guwen.apk";

    boolean isDown = false;

    public UpDataService() {
    }

    /**
     * 安卓系统下载类
     **/
    DownloadManager manager;
    /**
     * 接收下载完的广播
     **/
    DownloadCompleteReceiver receiver;

    /**
     * 初始化下载器
     **/
    private void initDownManager() {
        delFile("/download/"+downloadName);
        isDown = true;
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        receiver = new DownloadCompleteReceiver();
        //设置下载地址
        DownloadManager.Request down = new DownloadManager.Request(Uri.parse(downloadURL));
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        // 下载时，通知栏显示途中
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        // 显示下载界面
        down.setVisibleInDownloadsUi(true);
        // 设置下载后文件存放的位置
//        down.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "yuanmanrensheng.apk");
        down.setDestinationInExternalPublicDir("/download/", downloadName);
        // 将下载请求放入队列
        manager.enqueue(down);
        //注册下载广播
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 调用下载
        if (!isDown) {
            initDownManager();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // 注销下载广播
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    // 接受下载完成后的intent
    class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //判断是否下载完成的广播
            isDown = false;
            DownloadManager.Query query = new DownloadManager.Query();
            long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            query.setFilterById(downId);
            Cursor c = manager.query(query);

            if (c.moveToFirst()) {
                int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                switch (status) {
                    case DownloadManager.STATUS_PAUSED:
                        Log.v("down", "STATUS_PAUSED");
                    case DownloadManager.STATUS_PENDING:
                        Log.v("down", "STATUS_PENDING");
                    case DownloadManager.STATUS_RUNNING:
                        //正在下载，不做任何事情
                        Log.v("down", "STATUS_RUNNING");
                        break;
                    case DownloadManager.STATUS_SUCCESSFUL:
                        //完成
                        Log.v("down", "下载完成");
                        //自动安装apk
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            Uri uriForDownloadedFile = manager.getUriForDownloadedFile(downId);
                            installApkNew(uriForDownloadedFile);
                        }
                        break;
                    case DownloadManager.STATUS_FAILED:
                        //清除已下载的内容，重新下载
                        Log.v("down", "STATUS_FAILED");
                        manager.remove(downId);
                        break;
                }
            }

//            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
//                //获取下载的文件id
//                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                //自动安装apk
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                    Uri uriForDownloadedFile = manager.getUriForDownloadedFile(downId);
//                    Log.d("kodulf", "uri=" + uriForDownloadedFile);
//                    installApkNew(uriForDownloadedFile);
//                }
//                //停止服务并关闭广播
                UpDataService.this.stopSelf();
//            }
        }


        //安装apk
        protected void installApkNew(Uri uri) {
            Intent intent = new Intent();
            //执行动作
            intent.setAction(Intent.ACTION_VIEW);
            //执行的数据类型
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            //不加下面这句话是可以的，查考的里面说如果不加上这句的话在apk安装完成之后点击单开会崩溃
            // android.os.Process.killProcess(android.os.Process.myPid());
            startActivity(intent);
        }
    }
    //删除文件
    public static void delFile(String fileName){
        Log.v("down","Environment.getExternalStorageDirectory():"+Environment.getExternalStorageDirectory());
        File file = new File(Environment.getExternalStorageDirectory() +fileName);
        if(file.isFile()){
            file.delete();
        }
        file.exists();
    }
}
