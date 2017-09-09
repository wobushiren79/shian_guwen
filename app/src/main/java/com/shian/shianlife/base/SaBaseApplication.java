package com.shian.shianlife.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;


import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.kf5sdk.init.KF5SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.local.LocationService;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.base.SSLSocketFactoryCompat;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class SaBaseApplication extends MultiDexApplication {
    public static final Boolean LOGFLAG = true;
    public static final String DIR = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/Victor/log/";
    public static final String NAME = getCurrentDateString() + ".txt";
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private ArrayList<Activity> list = new ArrayList<Activity>();
    public LocationService locationService;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                KF5SDKInitializer.initialize(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initOkHttp();
    }

    /**
     * 初始化Okhttp
     */
    private void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                .addInterceptor(new LoggerInterceptor("TAG"));
        try {
            // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
            final X509TrustManager trustAllCert =
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    };
            final SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.sslSocketFactory(sslSocketFactory, trustAllCert);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = builder.connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .followRedirects(true)
                .followSslRedirects(true)
                .cookieJar(new LocalCookieJar())
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    //CookieJar是用于保存Cookie的
    class LocalCookieJar implements CookieJar {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            String tempUrl = getBaseUrl(url.toString());
            AppContansts.cookieStore.put(tempUrl, cookies);
            //新增添加子系统KEY
            if (tempUrl.contains(AppContansts.Login_BaseUrl) && cookies.size() >= 2) {
                String setCookies = cookies.get(1).toString();
                String[] cookiesList = setCookies.split(";");
                for (String cookie : cookiesList) {
                    if (cookie.contains("KI4SO_SERVER_EC")) {
                        AppContansts.System_Ki4so_Client_Ec = cookie;
                    }
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            String tempUrl = getBaseUrl(url.toString());
            List<Cookie> cookies = AppContansts.cookieStore.get(tempUrl);
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }

        private String getBaseUrl(String url) {
            String temp = "";
            if (!url.contains("https")) {
                int hostLocation = url.indexOf("/", 8);
                int urlLocation = url.indexOf("/", hostLocation + 1);
                if (urlLocation != -1)
                    temp = url.substring(0, urlLocation);
            } else {
                int hostLocation = url.indexOf("/", 8);
                temp = url.substring(0, hostLocation);
            }
            return temp;
        }
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
