package com.shian.shianlife.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.utils.Utils;

/**
 * Created by asus on 2016/7/30.
 */
public class WebActivity extends BaseActivity {

    TextView mTVBack;
    ImageView mIVCancel;

    WebView mWebView;
    ProgressBar mPB;
    String dir;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_web);
        initView();
        dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationDatabasePath(dir);
        webSettings.setDomStorageEnabled(true);//允许DCOM

        Utils.LogVPrint(getIntent().getStringExtra("url"));
        mWebView.loadUrl(getIntent().getStringExtra("url"));
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mPB.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == mPB.getVisibility()) {
                        mPB.setVisibility(View.VISIBLE);
                    }
                    mPB.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initView() {
        mPB = (ProgressBar) findViewById(R.id.myProgressBar);
        mWebView = (WebView) findViewById(R.id.web);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mIVCancel = (ImageView) findViewById(R.id.iv_cancel);

        mTVBack.setOnClickListener(onClickListener);
        mIVCancel.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVBack && mWebView.canGoBack()) {
                mWebView.goBack();
            } else if (v == mIVCancel) {
                finish();
            }
        }
    };
}
