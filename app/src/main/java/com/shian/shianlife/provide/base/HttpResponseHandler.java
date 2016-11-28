package com.shian.shianlife.provide.base;

public abstract class HttpResponseHandler<T>
{
    public abstract void onStart();

    public abstract void onSuccess(T result);

    public abstract void onError(String message);

    public void onOffLine(T result)
    {
    }
}
