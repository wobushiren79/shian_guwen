package com.shian.shianlife.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.contanst.IntentName;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpmodel.SiftListData;
import com.shian.shianlife.provide.phpparams.PHPHpSiftDataParams;
import com.shian.shianlife.thisenum.SystemTypeEnum;

import okhttp3.Request;

/**
 * Created by asus on 2016/7/30.
 */
public class WebActivity extends BaseActivity {

    TextView mTVBack;
    TextView mTVTitle;
    ImageView mIVCancel;
    ImageView mIVCollection;
    ImageView mIVShare;

    WebView mWebView;
    ProgressBar mPB;
    String dir;

    boolean isCollection;
    String url = "";
    SiftListData data;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_web);
        initView();
        openCollection();

        dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setDatabaseEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setGeolocationDatabasePath(dir);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);//允许DCOM
        //允许视频播放
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        url = getIntent().getStringExtra(IntentName.INTENT_URL);
        mWebView.loadUrl(url);
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

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTVTitle.setText(title);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });


    }

    /**
     * 是否要开启收藏功能
     */
    private void openCollection() {
        isCollection = getIntent().getBooleanExtra("isCollection", false);
        if (isCollection) {
            mIVCollection.setVisibility(View.VISIBLE);
            data = (SiftListData) getIntent().getSerializableExtra("shareData");
            if (data.getIsCollection() == 0) {
                mIVCollection.setImageResource(R.drawable.zhy_find_collection_1);
            } else {
                mIVCollection.setImageResource(R.drawable.zhy_find_collection_2);
                mIVCollection.setClickable(false);
            }
        } else {
            mIVCollection.setVisibility(View.GONE);
        }
    }

    private void initView() {
        mPB = (ProgressBar) findViewById(R.id.myProgressBar);
        mWebView = (WebView) findViewById(R.id.web);
        mTVBack = (TextView) findViewById(R.id.tv_back);
        mIVCancel = (ImageView) findViewById(R.id.iv_cancel);
        mIVCollection = (ImageView) findViewById(R.id.iv_collection);
        mIVShare = (ImageView) findViewById(R.id.iv_share);
        mTVTitle = (TextView) findViewById(R.id.tv_title);


        mTVBack.setOnClickListener(onClickListener);
        mIVCancel.setOnClickListener(onClickListener);
        mIVCollection.setOnClickListener(onClickListener);
        mIVShare.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTVBack && mWebView.canGoBack()) {
                mWebView.goBack();
            } else if (v == mIVCancel) {
                finish();
            } else if (v == mIVShare) {
                share();
            } else if (v == mIVCollection) {
                setData(2, data.getId());
            }
        }
    };

    private void share() {
        // intent.setType("text/plain"); //纯文本
            /*
             * 图片分享 it.setType("image/png"); 　//添加图片 File f = new
             * File(Environment.getExternalStorageDirectory()+"/name.png");
             *
             * Uri uri = Uri.fromFile(f); intent.putExtra(Intent.EXTRA_STREAM,
             * uri); 　
             */
//        if (data == null) {
//            return;
//        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

    /**
     * @param type （1.为点赞   2.为收藏）
     */
    private void setData(int type, int siftID) {
        if (AppContansts.systemLoginInfo == null && AppContansts.systemLoginInfo.getUserId() == null) {
            ToastUtils.show(this, "账号未登录，请退出重新登陆");
            return;
        }
        mIVCollection.setImageResource(R.drawable.zhy_find_collection_2);
        mIVCollection.setClickable(false);
        ToastUtils.show(WebActivity.this, "收藏成功");
        PHPHpSiftDataParams params = new PHPHpSiftDataParams();
        params.setType(type);
        params.setUserid(AppContansts.systemLoginInfo.getUserId());
        params.setSiftID(siftID);
        params.setUserType(SystemTypeEnum.funeral.getCode());
        MHttpManagerFactory.getPHPManager().setSiftData(WebActivity.this, params, new HttpResponseHandler<Object>() {


            @Override
            public void onStart(Request request, int id) {

            }

            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
