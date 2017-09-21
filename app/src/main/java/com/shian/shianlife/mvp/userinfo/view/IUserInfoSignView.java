package com.shian.shianlife.mvp.userinfo.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoSignResultBean;

/**
 * Created by zm.
 */

public interface IUserInfoSignView extends BaseMVPView {
    void userInfoSignSuccess(UserInfoSignResultBean resultBean);

    void userInfoSignFail(String msg);
}
