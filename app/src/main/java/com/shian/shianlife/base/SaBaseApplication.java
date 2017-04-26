package com.shian.shianlife.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;


import android.util.DisplayMetrics;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.kf5sdk.init.KF5SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.shian.shianlife.common.local.LocationService;
import com.shian.shianlife.common.utils.Utils;
import com.tencent.bugly.crashreport.CrashReport;

public class SaBaseApplication extends Application {
    public static final Boolean LOGFLAG = true;
    public static final String DIR = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Victor/log/";
    public static final String NAME = getCurrentDateString() + ".txt";
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private ArrayList<Activity> list = new ArrayList<Activity>();
    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                this).memoryCacheExtraOptions(480, 800).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheSize(100 * 1024 * 1024).diskCacheFileCount(100)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(configuration);
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        // calculatedDispdpi();
        CrashReport.initCrashReport(getApplicationContext(), "58aeede7f2", false);
        KF5SDKInitializer.initialize(this);
    }

    /**
     * acitivity关闭时候，删除activity列表中的activity对象
     */
    public void removeActivity(Activity a) {
        list.remove(a);
    }

    /**
     * 向activiy列表中添加对象
     */
    public void addActivity(Activity a) {
        list.add(a);
    }

    /**
     * 捕获错误信息的handler
     */
    private UncaughtExceptionHandler uncaughtExceptionHandler = new UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            writeErrorLogToTxt(ex);
            mDefaultHandler.uncaughtException(thread, ex);
        }
    };

    private void writeErrorLogToTxt(Throwable ex) {
        String info = null;
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            info = new String(data);
            data = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writeErrorLog(info);
    }

    /**
     * 向文件中写入错误信息
     *
     * @param info
     */
    protected void writeErrorLog(String info) {
        File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, NAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(info.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取当前日期
     *
     * @return
     */
    private static String getCurrentDateString() {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        Date nowDate = new Date();
        result = sdf.format(nowDate);
        return result;
    }

    public void finishActivity() {
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    private void calculatedDispdpi() {
        String str = "";
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getApplicationContext().getResources().getDisplayMetrics();

        int screenHeight = dm.heightPixels;
        float density = dm.density;

        str += "屏幕分辨率为 :" + dm.widthPixels + " * " + dm.heightPixels + "\n";
        str += "绝对宽度: " + String.valueOf(dm.widthPixels) + "pixels\n";
        str += "绝对高度:" + String.valueOf(dm.heightPixels) + "pixels\n";
        str += "逻辑密度:" + String.valueOf(density) + "\n";

        int picHeightDp = (int) (screenHeight * 0.6);
        str += "屏幕长 :" + String.valueOf(dm.widthPixels / density) + "dp\n";
        str += "屏幕宽:" + String.valueOf(dm.heightPixels / density) + "dp\n";
        str += "图片总高度:" + String.valueOf(picHeightDp);

        Log.i("GlobalApplication", str);

    }



}
