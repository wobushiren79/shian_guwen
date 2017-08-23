package com.shian.shianlife.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectViews;
import me.leolin.shortcutbadger.ShortcutBadger;
import okhttp3.Request;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpReadMessage;
import com.shian.shianlife.provide.result.HrCommentResult;
import com.shian.shianlife.provide.result.HrMessageList.MessageList;

public class MessageDetailActviity extends BaseActivity {
    private final String LOG_TGA = "MESSAGEDETAIL_ACTIVITY";
    @InjectViews({R.id.tv_msg_title, R.id.tv_msg_time, R.id.tv_msg_content})
    List<TextView> tvList;
    private MessageList message;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        setContentView(R.layout.activity_messagesetail);
        setTitle("消息详情");
        setMessageVisible(View.GONE);
        message = JSONUtil.parseJSONString(getIntent()
                .getStringExtra("message"), MessageList.class);
        tvList.get(0).setText(message.getHead());
        if (message.getServerCreateTime() == null) {
            tvList.get(1).setVisibility(View.GONE);
        }
        tvList.get(1)
                .setText(message.getServerCreateTime());
        tvList.get(2).setText(message.getBody());
        boolean isB = getIntent().getBooleanExtra("isBroadcast", false);
        if (!isB) {
            MHttpManagerFactory.getFuneralManager().getMessageCount(this,
                    new HttpResponseHandler<HrCommentResult>() {

                        @Override
                        public void onStart(Request request, int id) {

                        }

                        @Override
                        public void onSuccess(HrCommentResult result) {
                            // TODO Auto-generated method stub
                            AppContansts.MessageCount = result.getCount();
                        }


                        @Override
                        public void onError(String message) {
                            // TODO Auto-generated method stub

                        }
                    });
            HpReadMessage params = new HpReadMessage();
            List<Long> l = new ArrayList<Long>();
            l.add(message.getId());
            params.setMsgIds(l);
            MHttpManagerFactory.getFuneralManager().readMessage(this, params,
                    new HttpResponseHandler<Object>() {

                        @Override
                        public void onStart(Request request, int id) {

                        }

                        @Override
                        public void onSuccess(Object result) {
                            // TODO Auto-generated method stub

                        }



                        @Override
                        public void onError(String message) {
                            // TODO Auto-generated method stub

                        }
                    });
        }
    }
}
