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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.LoginActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.common.utils.FilePathUtils;
import com.shian.shianlife.common.utils.GsonTools;
import com.shian.shianlife.common.utils.ObjectMapperFactory;
import com.shian.shianlife.common.utils.SharePerfrenceUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.mapapi.CustomDialog;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonNode;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;

/**
 * 数据传输底层封装
 *
 * @author Paul
 */
@SuppressWarnings("deprecation")
public class HttpRequestExecutor {


    private CustomDialog dialog;

    /**
     * get请求
     *
     * @param context
     * @param method
     * @param data
     * @param params
     * @param responseHandler
     * @param <T>
     */
    public <T> void requestGet(final Context context,
                               final String method,
                               final Class<T> data,
                               final BaseHttpParams params,
                               final HttpResponseHandler<T> responseHandler,
                               final boolean isShowDialog,
                               final String baseUrl,
                               final Map<String, String> header,
                               final String dataName) {
        if (checkNetWorkAndDialog(context, responseHandler, isShowDialog)) return;

        Log.v("tag", baseUrl + "/" + method);
        Log.v("tag", params.getContentJson());

        GetBuilder getBuilder = OkHttpUtils.get();
        getBuilder.url(baseUrl + "/" + method);
        getBuilder.addHeader("client-Type", "wechatapp");
        getBuilder.addHeader("systemType", "2");
        if (header != null)
            getBuilder.headers(header);
        getBuilder.params(params.getMapParams());
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                if (responseHandler != null) {
                    responseHandler.onStart(request, id);
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                onErrorCallBack(responseHandler, errorMessage, context);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

            @Override
            public void onResponse(String response, int id) {
                Log.v("tag", response);
                if (dataName.equals("list")) {
                    dataToJsonForList(context, response, data, responseHandler);
                } else if (dataName.equals("content")) {
                    dataToJsonForContent(context, response, data, responseHandler);
                } else {
                    onErrorCallBack(responseHandler, "数据解析异常", context);
                }
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

        });
    }


    /**
     * POST请求
     *
     * @param context
     * @param method
     * @param data
     * @param params
     * @param responseHandler
     * @param <T>
     */
    public <T, E> void requestPost(final Context context,
                                   final String method,
                                   final Class<T> data,
                                   final BaseHttpParams params,
                                   final HttpResponseHandler<E> responseHandler,
                                   final boolean isShowDialog,
                                   final String baseUrl,
                                   final Map<String, String> header,
                                   final String dataName,
                                   boolean hasConentParams) {
        if (checkNetWorkAndDialog(context, responseHandler, isShowDialog)) return;

        Log.v("tag", baseUrl + "/" + method);


        PostStringBuilder getBuilder = OkHttpUtils.postString();
        getBuilder.url(baseUrl + "/" + method);
        if (header != null)
            getBuilder.headers(header);
        getBuilder.mediaType(MediaType.parse("application/json; charset=utf-8"));
        //判断请求参数是否需要套上content
        if (hasConentParams) {
            Log.e("tag", params.getContentJson());
            getBuilder.content(params.getContentJson());
        } else {
            Log.e("tag", params.getJsonParams());
            getBuilder.content(params.getJsonParams());
        }

        getBuilder.addHeader("client-Type", "wechatapp");
        getBuilder.addHeader("systemType", "2");
        RequestCall requestCall = getBuilder.build();
        requestCall.execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                if (responseHandler != null) {
                    responseHandler.onStart(request, id);
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                String errorMessage = e.getMessage();
                onErrorCallBack(responseHandler, errorMessage, context);
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

            @Override
            public void onResponse(String response, int id) {
                Log.v("tag", response);
                if (dataName.equals("list")) {
                    dataToJsonForList(context, response, data, responseHandler);
                } else if (dataName.equals("content")) {
                    dataToJsonForContent(context, response, data, responseHandler);
                } else {
                    onErrorCallBack(responseHandler, "数据解析异常", context);
                }
                if (dialog != null)
                    dialog.cancel();
                dialog = null;
            }

        });

    }

    /**
     * 检测网络和弹窗
     *
     * @param context
     * @param responseHandler
     * @param isShowDialog
     * @param <T>
     * @return
     */
    private <T> boolean checkNetWorkAndDialog(Context context, HttpResponseHandler<T> responseHandler, boolean isShowDialog) {
        if (!CheckUtils.isNetworkConnected(context)) {
            onErrorCallBack(responseHandler, context.getString(R.string.net_work_off), context);
            return true;
        }
        if (isShowDialog && dialog == null) {
            dialog = new CustomDialog(context);
            dialog.show();
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
    private <T> void onErrorCallBack(HttpResponseHandler<T> response, String error,
                                     Context context) {
        if (response != null && error != null) {
//            if (showToast(context, error)) {
            response.onError(error);
            if (error.contains("405") || error.contains("503")) {
                jumpLogin(context);
            }
//            }
        }
    }

    private void jumpLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 数据处理
     *
     * @param context
     * @param response
     * @param data
     * @param responseHandler
     * @param <T>
     */
    private <T, E> void dataToJsonForContent(Context context, String response, final Class<T> data, HttpResponseHandler<E> responseHandler) {
        if (response != null) {
            try {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(response);
                Map<String, Object> map = gson.fromJson(jsonElement, Map.class);

                double code = (double) map.get("code");
                int codeInt = (int) code;
                if (codeInt == 1000) {
                    T result = GsonTools.getObjectByMapKey("content", map, data.newInstance());
                    if (result != null) {
                        responseHandler.onSuccess((E) result);
                    } else {
                        responseHandler.onSuccess(null);
                    }
                } else if ("1009".equals(codeInt)) {
                    jumpLogin(context);
                } else {
                    String msg = (String) map.get("message");
                    CrashReport.putUserData(context, response, msg);
                    onErrorCallBack(responseHandler, msg, context);
                }

            } catch (Exception e) {
                onErrorCallBack(responseHandler, "数据解析异常", context);
            }
        }
    }

    /**
     * 数据处理
     *
     * @param context
     * @param response
     * @param data
     * @param responseHandler
     * @param <T>
     */
    private <T, E> void dataToJsonForList(Context context, String response, final Class<T> data, HttpResponseHandler<E> responseHandler) {
        if (response != null) {
            try {
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(response);
                Map<String, Object> map = gson.fromJson(jsonElement, Map.class);

                double code = (double) map.get("code");
                int codeInt = (int) code;
                if (codeInt == 1000) {
                    List<T> result = GsonTools.getListByMapKey("list", map, data.newInstance());
                    if (result != null) {
                        responseHandler.onSuccess((E) result);
                    } else {
                        responseHandler.onSuccess(null);
                    }
                } else if ("1009".equals(codeInt)) {
                    jumpLogin(context);
                } else {
                    String msg = (String) map.get("message");
                    CrashReport.putUserData(context, response, msg);
                    onErrorCallBack(responseHandler, msg, context);
                }

            } catch (Exception e) {
                onErrorCallBack(responseHandler, "数据解析异常", context);
            }
        }
    }
//            try {
//                JsonNode node = ObjectMapperFactory.getInstance().readTree(new String(response));
//                String code = node.findValue("code").toString();
//                String errorMsg = node.findValue("message").toString();
//                if ("1000".equals(code)) {
//                    JsonNode jn = node.findValue(dataName);
//                    if (jn == null)
//                        responseHandler.onSuccess(null);
//                    else {
//                        T result = ObjectMapperFactory.getInstance().readValue(
//                                jn, data);
//                        responseHandler.onSuccess(result);
//                    }
//                } else if ("1009".equals(code)) {
//                    jumpLogin(context);
//                } else {
//                    CrashReport.putUserData(context, response, errorMsg);
//                    onErrorCallBack(responseHandler, errorMsg, context);
//                }
//            } catch (Exception e) {
//                onErrorCallBack(responseHandler, "", context);
//            }
}