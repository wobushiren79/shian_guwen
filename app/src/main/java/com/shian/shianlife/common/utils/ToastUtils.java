package com.shian.shianlife.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void show(Context c, String s) {
        if (c != null && s != null)
            Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }

    public static void showLongTime(Context c, String s) {
        if (c != null && s != null)
            Toast.makeText(c, s, Toast.LENGTH_LONG).show();
    }
}
