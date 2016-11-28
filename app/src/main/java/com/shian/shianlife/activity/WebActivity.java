package com.shian.shianlife.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.shian.shianlife.base.BaseActivity;

/**
 * Created by asus on 2016/7/30.
 */
public class WebActivity extends BaseActivity {
    private final String LOG_TAG = "WEB_ACTIVITY";

    @Override
    protected void onCreate(Bundle arg0) {

        super.onCreate(arg0);
        if (LOGFLAG) {
            Log.v(LOG_TAG, "onCreate");
        }
        RelativeLayout rl = new RelativeLayout(this);
        final WebView web = new WebView(this);
        rl.addView(web, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        Button btn = new Button(this);
        btn.setText("返回");

        ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(220, 150);  //item的宽高
        mp.setMargins(0, 100, 25, 100);//分别是margin_top那四个属性
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mp);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rl.addView(btn, lp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.goBack();
            }
        });
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(getIntent().getStringExtra("url"));
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        setContentView(rl);
    }
}
