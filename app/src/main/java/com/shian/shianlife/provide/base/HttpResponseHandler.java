package com.shian.shianlife.provide.base;

import java.util.List;

import okhttp3.Request;

public abstract class HttpResponseHandler<T> {
    public abstract void onStart(Request request, int id);

    public abstract void onSuccess(T result);

    public abstract void onError(String message);

    public void onOffLine(T result) {
    }
}
