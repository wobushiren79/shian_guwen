package com.shian.shianlife.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpConsultIdParams;
import com.shian.shianlife.provide.result.HrUserInfo;

import java.util.List;

import butterknife.InjectViews;

/**
 * Created by asus on 2016/7/9.
 */
public class UserInfoActivity extends BaseActivity {
    @InjectViews({R.id.tv_username, R.id.tv_phone, R.id.tv_address, R.id.tv_no})
    List<TextView> tvList;
    @InjectViews({R.id.et_email, R.id.et_js})
    List<EditText> etList;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.fragment_userinfo);
        setTitle("个人资料");
        initData();
    }

    private void initData() {
        final HrUserInfo userInfo = JSONUtil.parseJSONString(getIntent().getStringExtra("data"), HrUserInfo.class);
        tvList.get(0).append(userInfo.getName()==null?"":userInfo.getName());
        tvList.get(1).append(userInfo.getMobile()==null?"":userInfo.getMobile());
        tvList.get(2).append(userInfo.getServiceArea()==null?"":userInfo.getServiceArea());
        tvList.get(3).append(userInfo.getJobNo()==null?"":userInfo.getJobNo());
        etList.get(0).setText(userInfo.getEmail()==null?"":userInfo.getEmail());
        etList.get(1).setText(userInfo.getIntroduce());
        findViewById(R.id.tv_editorder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HpConsultIdParams params = new HpConsultIdParams();
                params.setAppStatus(getSharedPreferences("settings",-1).getBoolean("rb",true)?1:2);
                params.setEmail(etList.get(0).getText().toString());
                params.setIntroduce(etList.get(1).getText().toString());
                MHttpManagerFactory.getAccountManager().changeInfo(UserInfoActivity.this, params, new HttpResponseHandler<Object>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(Object result) {
                        ToastUtils.show(UserInfoActivity.this, "保存成功");
                        finish();
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

    }
}
