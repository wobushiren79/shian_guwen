package com.shian.shianlife.mvp.shared;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zm.
 */

public class BaseSharedModel {

    protected SharedPreferences.Editor getEditShared(Context context, String Tag) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Tag, MODE_PRIVATE).edit();
        return editor;
    }

    protected SharedPreferences getDataShared(Context context, String Tag) {
        SharedPreferences share = context.getSharedPreferences(Tag, MODE_PRIVATE);
        return share;
    }

    protected void clearShared(Context context, String Tag) {
        SharedPreferences.Editor editor= getEditShared(context,Tag);
        editor.clear();
    }
}
