package com.shian.shianlife.mvp.userinfo.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralResultBean;

/**
 * Created by zm.
 */

public interface IUserInfoIntegralView extends BaseMVPView {

    void getUserInfoIntegralSuccess(UserInfoIntegralResultBean resultBean);

    void getUserInfoIntegralFail(String msg);

    void setUserInfoIntegral(Integer integral);

    void setUserInfoContinuousDay(Integer day);
}
