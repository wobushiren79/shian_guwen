package com.shian.shianlife.mvp.login.view;

import android.content.Context;

/**
 * Created by zm.
 */

public interface ISubSystemLoginView {

    Context getContext();

    void loginCemeterySuccess();

    void loginCemeteryFail();

    void loginGoodsSuccess();

    void loginGoodsFail();

    void loginOrderCenterSuccess();

    void loginOrderCenterFail();
}
