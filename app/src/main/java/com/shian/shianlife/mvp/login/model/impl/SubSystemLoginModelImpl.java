package com.shian.shianlife.mvp.login.model.impl;

import android.content.Context;

import com.shian.shianlife.mvp.login.model.ISubSystemLoginModel;
import com.shian.shianlife.provide.MHttpManagerFactory;


/**
 * Created by zm.
 */

public class SubSystemLoginModelImpl implements ISubSystemLoginModel {
    @Override
    public void subSystemStoreLogin(Context context, String loginKey) {
        MHttpManagerFactory.getSystemManager().loginStoreSystem(context, loginKey);
    }
    @Override
    public void subSystemCemeteryLogin(Context context, String loginKey) {
        MHttpManagerFactory.getSystemManager().loginCemeterySystem(context, loginKey);
    }
}
