package com.shian.shianlife.mvp.userinfo.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.userinfo.bean.UserInfoIntegralListResultBean;

/**
 * Created by zm.
 */

public interface IUserInfoIntegralListView extends BaseMVPView {

    void getIntegralListSuccess(UserInfoIntegralListResultBean resultBean);

    void getIntegralListFail(String msg);

    Integer getPageNumber();

    Integer getPageSize();

}
