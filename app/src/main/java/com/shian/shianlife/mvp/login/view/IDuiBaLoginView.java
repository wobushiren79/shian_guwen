package com.shian.shianlife.mvp.login.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginBean;
import com.shian.shianlife.mvp.login.bean.DuiBaLoginResultBean;

/**
 * Created by zm.
 */

public interface IDuiBaLoginView extends BaseMVPView {
    void loginDuiBaSuccess(DuiBaLoginResultBean resultBean);

    void loginDuiBaFail(String msg);

    Integer getIntegral();
}
