package com.shian.shianlife.mvp.userinfo.view;

import com.shian.shianlife.mvp.base.BaseMVPView;
import com.shian.shianlife.mvp.userinfo.bean.ChangePassWordSMSResultBean;

/**
 * Created by zm.
 */

public interface IChangePassWordSMSView extends BaseMVPView {
    void getSMSCodeSuccess(ChangePassWordSMSResultBean result);

    void getSMSCodeFail(String msg);

    void changePassWordSMSSuccess(ChangePassWordSMSResultBean result);

    void changePassWordSMSFail(String msg);

    /**
     * 获取用户手机
     *
     * @return
     */
    String getUserPhone();

    /**
     * 获取新密码
     *
     * @return
     */
    String getNewPassWord();

    /**
     * 获取短信验证码
     *
     * @return
     */
    String getMsgCode();


}
