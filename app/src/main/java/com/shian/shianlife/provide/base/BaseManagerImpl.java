package com.shian.shianlife.provide.base;

import android.content.Context;

/**
 * Created by zm.
 */

public class BaseManagerImpl {
    protected HttpRequestExecutor excutor = new HttpRequestExecutor();
    protected String baseUrl;

    protected BaseManagerImpl() {
    }

    protected <T> void requestGet(Context context,
                                  String method,
                                  Class<T> cls,
                                  BaseHttpParams params,
                                  HttpResponseHandler<T> response) {
        excutor.requestGet(context, method, cls, params, response, false, baseUrl, null, "content", HttpRequestExecutor.Response_Type_Obj);
    }


    protected <T> void requestGet(Context context,
                                  String method,
                                  Class<T> cls,
                                  BaseHttpParams params,
                                  HttpResponseHandler<T> response,
                                  boolean isDialog) {
        excutor.requestGet(context, method, cls, params, response, isDialog, baseUrl, null, "content", HttpRequestExecutor.Response_Type_Obj);
    }

    protected <T> void requestPost(Context context,
                                   String method,
                                   Class<T> cls,
                                   BaseHttpParams params,
                                   HttpResponseHandler<T> response) {
        excutor.requestPost(context, method, cls, params, response, false, baseUrl, null, "content", true, HttpRequestExecutor.Response_Type_Obj);
    }


    protected <T> void requestPost(Context context,
                                   String method,
                                   Class<T> cls,
                                   BaseHttpParams params,
                                   HttpResponseHandler<T> response,
                                   boolean isDialog) {
        excutor.requestPost(context, method, cls, params, response, isDialog, baseUrl, null, "content", true, HttpRequestExecutor.Response_Type_Obj);
    }

    protected <T> void requestPostFormToListForObj(Context context,
                                                   String method,
                                                   Class<T> cls,
                                                   BaseHttpParams params,
                                                   HttpResponseHandler<T> response) {
        excutor.requestPostForm(context, method, cls, params, response, false, baseUrl, null, "list", HttpRequestExecutor.Response_Type_Obj);
    }

    protected <T> void requestPostFormToListForObj(Context context,
                                                   String method,
                                                   Class<T> cls,
                                                   BaseHttpParams params,
                                                   HttpResponseHandler<T> response,
                                                   boolean isDialog) {
        excutor.requestPostForm(context, method, cls, params, response, isDialog, baseUrl, null, "list", HttpRequestExecutor.Response_Type_Obj);
    }

    protected <T, E> void requestPostFormToListForList(Context context,
                                                       String method,
                                                       Class<T> cls,
                                                       BaseHttpParams params,
                                                       HttpResponseHandler<E> response) {
        excutor.requestPostForm(context, method, cls, params, response, false, baseUrl, null, "list", HttpRequestExecutor.Response_Type_List);
    }

    protected <T, E> void requestPostFormToListForList(Context context,
                                                       String method,
                                                       Class<T> cls,
                                                       BaseHttpParams params,
                                                       HttpResponseHandler<E> response,
                                                       boolean isDialog) {
        excutor.requestPostForm(context, method, cls, params, response, isDialog, baseUrl, null, "list", HttpRequestExecutor.Response_Type_List);
    }


}
