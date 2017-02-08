package com.shian.shianlife.provide.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.LoginActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.FilePathUtils;
import com.shian.shianlife.common.utils.ObjectMapperFactory;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mapapi.CustomDialog;
import com.tencent.bugly.crashreport.CrashReport;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonNode;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * 数据传输底层封装
 *
 * @author Paul
 */
@SuppressWarnings("deprecation")
public class HttpRequestExecutor {
    private static final String C_sBaseUrl = AppContansts.BaseURL;// "http://120.25.103.60:8080/hzrapi/";
    private AsyncHttpClient httpClient = new AsyncHttpClient();
    private Header[] headers;

    /**
     * 初始化请求头
     */
    public HttpRequestExecutor() {
        if (headers == null)
            headers = new Header[3];
        headers[0] = new BasicHeader("systemType", "2");
        headers[1] = new BasicHeader("Content-Type", "application/json");
        httpClient.setTimeout(15000);
    }

    CustomDialog pd = null;
//	ProgressDialog pd = null;

    /**
     * Post请求
     *
     * @param context
     * @param method
     * @param c
     * @param params
     * @param response
     */
    public <T> void requestPost(final Context context, final String method,
                                final Class<T> c, BaseHttpParams params,
                                final HttpResponseHandler<T> response) {
        if (!isNetworkConnected(context)) {
            onError(response, context.getString(R.string.net_work_off), context);
            return;
        }
        HttpEntity httpEntity = null;
        try {
            // 判断是否有参数
            if (params != null) {
                String httpParams = params.getHttpParams();
                httpEntity = new StringEntity(httpParams, HTTP.UTF_8);
            }
            if (method.contains("doLogout") || method.contains("doLogin")
                    || method.contains("address/load")) {
                pd = null;
                getSession(context);
            } else {
                pd = new CustomDialog(context);
//                pd.setCancelable(false);
                pd.setCanceledOnTouchOutside(false);
//				pd = new ProgressDialog(context);
//				pd.setCancelable(false);
//				pd.setMessage("正在载入...");
//				pd.setIndeterminateDrawable(context.getResources().getDrawable(
//						R.drawable.spinner));
                getSession(context);
            }
            Log.i("tag", "methed=" + C_sBaseUrl + "/" + method);
            httpClient.post(context, C_sBaseUrl + "/" + method, headers,
                    httpEntity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            super.onStart();
                            if (response != null) {
                                if (pd != null && (context instanceof Activity) && !((Activity) context).isFinishing())
                                    pd.show();
                                response.onStart();
                            }
                        }

                        @Override
                        public void onSuccess(int arg0, Header[] arg1,
                                              byte[] arg2) {
//							setSession(arg1, context);
                            if (pd != null) {
                                pd.cancel();
                            }
                            if ((context instanceof Activity)
                                    && !((Activity) context).isFinishing()
                                    || method.contains("doLogout")) {
                                if (pd != null && (context instanceof Activity) && !((Activity) context).isFinishing())
                                    pd.cancel();
                                response(context, method, c, response, arg2);
                            }


                        }

                        @Override
                        public void onFailure(int arg0, Header[] arg1,
                                              byte[] arg2, Throwable arg3) {
                            if (pd != null) {
                                pd.cancel();
                            }
                            String s = arg3.getMessage();
                            if (s != null) {
                                Log.e("tag", s);
                            }
                            // onError(response,
                            // context.getString(R.string.servererror),
                            // context);
                            onError(response, s, context);

                        }
                    });
        } catch (UnsupportedEncodingException e1) {
            // onError(response, context.getString(R.string.servererror),
            // context);
            if (pd != null) {
                pd.cancel();
            }
            onError(response, e1.getMessage(), context);
        } finally {
            if (pd != null) {
                pd.cancel();
            }
        }
    }

    /**
     * 请求答复
     *
     * @param ctx
     * @param methed
     * @param c
     * @param response
     * @param arg2
     */
    private <T> void response(final Context ctx, final String methed,
                              final Class<T> c, HttpResponseHandler<T> response, byte[] arg2) {
        Log.i("tag", new String(arg2));
        if (response != null) {
            try {
                JsonNode node = ObjectMapperFactory.getInstance().readTree(
                        new String(arg2));
                String error = node.findValue("code").toString();
                String errorMsg = node.findValue("message").toString();
                // String validErrors =
                // node.findValue("validErrors").toString();
                if ("1000".equals(error)) {
                    JsonNode jn = node.findValue("content");
                    if (jn == null)
                        response.onSuccess(null);
                    else {
                        T result = ObjectMapperFactory.getInstance().readValue(
                                jn, c);
                        response.onSuccess(result);
                    }
                } else if ("1009".equals(error)) {
                    CrashReport.putUserData(ctx, methed, errorMsg);
                    if (ctx instanceof Activity) {
                        ((Activity) ctx).finish();
                    }
                    Intent in = new Intent(ctx, LoginActivity.class);
                    ctx.startActivity(in);
                } else {
                    CrashReport.putUserData(ctx, methed, errorMsg);
                    onError(response, errorMsg, ctx);
                }
            } catch (Exception e) {
                onError(response, "", ctx);
            }

        }

    }

    /**
     * 文件下载
     *
     * @param context
     * @param url
     * @param model
     * @param ptah
     * @param response
     */
    public void requestDownloadFile(Context context, String url, String model,
                                    String ptah, final FileHttpResponseHandler<File> response) {
        File file = FilePathUtils.getDownloadFile(model, ptah);
        httpClient.get(context, url, new FileAsyncHttpResponseHandler(file) {

            @Override
            public void onStart() {
                super.onStart();
                if (response != null) {
                    response.onStart();
                }
                // Thread.currentThread().setName("download");
            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, File arg2) {
                if (response != null) {
                    response.onSuccess(arg2);
                }
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                super.onProgress(bytesWritten, totalSize);
                if (response != null) {
                    response.onProgress(bytesWritten, totalSize, false);
                } else {
                }
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, Throwable arg2,
                                  File arg3) {
                if (response != null) {
                    if (arg2.getMessage() != null) {
                        response.onError(new String(arg2.getMessage()));
                    }
                }
            }
        });
    }

    private boolean showToast(Context ctx, String msg) {
        boolean flag = true;
        if (!"".equals(msg))
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
        return flag;
    }

    /**
     * 判断是否有网络
     *
     * @param context
     * @return
     */
    private boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 异常回调
     *
     * @param response
     * @param error
     * @param context
     */
    private <T> void onError(HttpResponseHandler<T> response, String error,
                             Context context) {
        if (response != null && ((context instanceof Activity) && !((Activity) context)
                .isFinishing()) && error != null) {
            if (showToast(context, error)) {
                response.onError(error);
            }
        }
    }

    public static final String C_sSession_Share = "SessionShare";
    public static final String C_sSession_Key = "SessionKey";

    /**
     * 记录session
     *
     * @param arg1
     * @param ctx
     */
    private void setSession(Header[] arg1, Context ctx) {
        for (Header h : arg1) {
            if (h.getName().equals("Set-Cookie")) {
                Editor editor = ctx.getSharedPreferences(C_sSession_Share, -1)
                        .edit();
                editor.putString(C_sSession_Key, h.getValue());
                editor.commit();
                break;
            }
        }
    }

    public static void setSession(String sessionId, Context ctx) {
        Editor editor = ctx.getSharedPreferences(C_sSession_Share, -1)
                .edit();
        editor.putString(C_sSession_Key, sessionId);
        editor.commit();
    }

    private void getSession(Context ctx) {
        SharedPreferences share = ctx
                .getSharedPreferences(C_sSession_Share, -1);
        String sesseion = share.getString(C_sSession_Key, "");
        Log.e("tag", "sessionID=" + sesseion);
        headers[2] = new BasicHeader("Cookie", "sid=" + sesseion);
    }

}