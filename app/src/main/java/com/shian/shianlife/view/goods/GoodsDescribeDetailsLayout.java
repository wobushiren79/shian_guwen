package com.shian.shianlife.view.goods;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseLayout;

import butterknife.InjectView;

/**
 * Created by zm.
 */

public class GoodsDescribeDetailsLayout extends BaseLayout {
    @InjectView(R.id.web)
    WebView webView;

    public GoodsDescribeDetailsLayout(Context context) {
        this(context, null);
    }

    public GoodsDescribeDetailsLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, R.layout.layout_goods_describle_details, attrs);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
//        webView.loadUrl("https://www.baidu.com/");
    }

    public void setWebData(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

//        webView.getSettings().setUseWideViewPort(true);
//        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        //JS映射
        webView.addJavascriptInterface(this, "App");
        webView.loadDataWithBaseURL(null, getHtmlData(html), "text/html", "utf-8", null);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:App.resize(document.body.getBoundingClientRect().height)");
                super.onPageFinished(view, url);
            }
        });
    }

    @JavascriptInterface
    public void resize(final float height) {
        Activity activity = (Activity) getContext();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Toast.makeText(getActivity(), height + "", Toast.LENGTH_LONG).show();
                //此处的 layoutParmas 需要根据父控件类型进行区分，这里为了简单就不这么做了
                webView.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
            }
        });
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
