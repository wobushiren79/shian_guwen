package com.shian.shianlife.provide.imp.impl;

import java.io.File;

import org.codehaus.jackson.JsonNode;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ObjectMapperFactory;
import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.imp.FileManager;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;

public class FileManagerImpl implements FileManager {
    private static FileManager manager;

    private FileManagerImpl() {
    }

    public static FileManager getInstance() {
        if (manager == null) {
            manager = new FileManagerImpl();
        }
        return manager;
    }

    @Override
    public void upLoadFile(final Context context, String file, String path,
                           final FileHttpResponseHandler<HrUploadFile> response) {
        RequestParams params = new RequestParams();

//		SharedPreferences share = context.getSharedPreferences("SessionShare",
//				-1);
//		String sesseion = share.getString("SessionKey", "");
//
//		params.addHeader("Cookie","sid="+ sesseion);
        params.addHeader("Cookie", "sid=" + AppContansts.userLoginInfo.getSessionId());
        params.addHeader("systemType", "2");

        params.addBodyParameter(file, new File(path));

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, AppContansts.Funeral_BaseUrl
                + "/file/upload", params, new RequestCallBack<String>() {

            @Override
            public void onStart() {
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (response != null) {
                    response.onProgress(total, current);
                }
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String contentString = responseInfo.result;
                Log.i("tag", contentString);
                if (response != null) {
                    try {
                        JsonNode node = ObjectMapperFactory.getInstance()
                                .readTree(contentString);
                        String error = node.findValue("code").toString();
                        String errorMsg = node.findValue("message").toString();
                        String validErrors = node.findValue("validErrors")
                                .toString();
                        if ("1000".equals(error)) {
                            JsonNode jn = node.findValue("content");
                            if (jn == null)
                                response.onSuccess(null);
                            else {
                                HrUploadFile result = ObjectMapperFactory
                                        .getInstance().readValue(jn,
                                                HrUploadFile.class);
                                response.onSuccess(result);
                            }
                        } else {
                            response.onError(errorMsg + "\n" + validErrors);
                        }
                    } catch (Exception e) {
                        response.onError("数据异常");
                    }
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("tag", msg);
                if (response != null) {
                    response.onError(msg);
                }
            }
        });
    }
    @Override
    public RequestCall downloadFile(Context context, String downloadUrl, String fileName, final FileHttpResponseHandler<File> responseHandler) {
        RequestCall call = OkHttpUtils
                .get()
                .url(downloadUrl)
                .addHeader("Accept-Encoding", "identity")
                .build();
        call.execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName) {
            @Override
            public void onError(Call call, Exception e, int id) {
                responseHandler.onError(call.toString());
            }

            @Override
            public void onResponse(File response, int id) {
                responseHandler.onSuccess(response);
            }

            @Override
            public void inProgress(float progress, long total, int id) {
                responseHandler.onProgress(total, progress);
            }

        });
        return call;
    }
}
