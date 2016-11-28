package com.shian.shianlife.common.utils;

import android.app.Activity;
import android.util.Log;

public class LogM
{
    private static final String TAG = "victor";
    private static final boolean Flag = false;

    public static void setDebug(boolean Flag)
    {
        Flag = true;
        if (Flag)
            i("###", "LogM is open");
        else
            i("###", "LogM is close");
    }

    public static void d(String s, Object obj)
    {
        try
        {
            Log.d(s, obj.toString());
            return;
        } catch (Exception exception)
        {
            return;
        }
    }

    public static void d(String s, Object obj, Throwable throwable)
    {
        try
        {
            Log.d(s, obj.toString(), throwable);
            return;
        } catch (Exception exception)
        {
            return;
        }
    }

    public static void e(String s, Object obj)
    {
        try
        {
            if (!Flag)
                return;
        } catch (Exception exception)
        {
            return;
        }
        Log.e(s, obj.toString());
        return;
    }

    public static void e(String s, Object obj, Throwable throwable)
    {
        try
        {
            if (!Flag)
                return;
        } catch (Exception exception)
        {
            return;
        }
        Log.e(s, obj.toString(), throwable);
        return;
    }

    public static void i(String s, Object obj)
    {
        try
        {
            Log.i(s, obj.toString());
            return;
        } catch (Exception exception)
        {
            return;
        }
    }

    public static void i(String message)
    {
        if (Flag)
        {
            Log.i(TAG, message);
        }
    }

    public static void i(Activity ac, String message)
    {
        if (Flag)
        {
            Log.i(TAG + "@" + ac.getClass().toString(), message);
        }
    }

    public static void i(String s, Object obj, Throwable throwable)
    {
        try
        {
            Log.i(s, obj.toString(), throwable);
            return;
        } catch (Exception exception)
        {
            return;
        }
    }

    public static void w(String s, Object obj)
    {
        try
        {
            if (!Flag)
                return;
        } catch (Exception exception)
        {
            return;
        }
        Log.w(s, obj.toString());
        return;
    }

    public static void w(String s, Object obj, Throwable throwable)
    {
        try
        {
            if (!Flag)
                return;
        } catch (Exception exception)
        {
            return;
        }
        Log.w(s, obj.toString(), throwable);
        return;
    }

}
