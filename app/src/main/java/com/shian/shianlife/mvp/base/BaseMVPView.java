package com.shian.shianlife.mvp.base;

import android.content.Context;

/**
 * Created by zm.
 */

public interface BaseMVPView {
    Context getContext();

    void showToast(String msg);
}
