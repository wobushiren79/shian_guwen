package com.shian.shianlife.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;

/**
 * Created by asus on 2016/7/30.
 */
public class WebActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        WebView web = new WebView(this);
        web.loadUrl(getIntent().getStringExtra("url"));
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        setContentView(web);
    }
}
