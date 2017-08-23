package com.shian.shianlife.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpparams.PHPHpOpinionParams;
import com.shian.shianlife.provide.result.HrUserInfo;
import com.shian.shianlife.thisenum.SystemTypeEnum;

import okhttp3.Request;

public class IdeaFeedbackActivity extends BaseActivity {
    EditText mEditText;
    TextView mBTSubmit;
    TextView mTVTextNum;


//    String[] UserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_feedback);
//        UserInfo = getIntent().getStringArrayExtra("UserInfo");
        setTitle("意见反馈");
        initView();
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.edit);
        mBTSubmit = (TextView) findViewById(R.id.bt_submit);
        mTVTextNum = (TextView) findViewById(R.id.tv_textnum);

        mBTSubmit.setOnClickListener(onClickListener);
        mEditText.addTextChangedListener(textWatcher);


        changeNum(0);
    }

    TextWatcher textWatcher = new TextWatcher() {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            changeNum(s.length());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void changeNum(int num) {
        mTVTextNum.setText(num + "/" + 100);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBTSubmit) {
                submit();
            }
        }
    };


    private void submit() {
        if (mEditText.getText().toString().equals("")) {
            ToastUtils.show(IdeaFeedbackActivity.this, "还没有填写反馈信息");
            return;
        }
        if (AppContansts.userInfoData == null ||
                AppContansts.userInfoData.getName() == null ||
                AppContansts.userInfoData.getMobile() == null) {
            ToastUtils.show(IdeaFeedbackActivity.this, "账号错误 请重新登陆");
            return;
        }
        PHPHpOpinionParams params = new PHPHpOpinionParams();
        params.setUser(AppContansts.userInfoData.getName());
        params.setTel(AppContansts.userInfoData.getMobile());
        params.setContent(mEditText.getText().toString());
        params.setUserType(SystemTypeEnum.funeral.getCode());
        MHttpManagerFactory.getPHPManager().setOpinion(IdeaFeedbackActivity.this, params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {
                ToastUtils.show(IdeaFeedbackActivity.this, "提交成功");
                finish();
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(IdeaFeedbackActivity.this, "提交失败");
            }
        }, true);
    }
}
