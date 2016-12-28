package com.shian.shianlife.fragment;

import java.util.LinkedHashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifavor.cycleviewpager.view.CycleViewPager;

import com.shian.shianlife.R;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    private View v;
    private Handler mHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, null, false);
        ButterKnife.inject(this, v);

        final WebView webview=(WebView)v.findViewById(R.id.web);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        v.findViewById(R.id.btn_back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webview.goBack();
            }
        });
        webview.loadUrl("http://m.e-funeral.cn/index.html");
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        final CycleViewPager mCycleViewPager = (CycleViewPager) v
                .findViewById(R.id.cvp_order);

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("世安广告 1",
                "http://img3.imgtn.bdimg.com/it/u=2863599132,929299683&fm=21&gp=0.jpg");
        map.put("世安广告2",
                "http://img5.imgtn.bdimg.com/it/u=1319787381,824476088&fm=21&gp=0.jpg");
        map.put("世安广告 3",
                "http://img3.imgtn.bdimg.com/it/u=2791706699,2937974108&fm=21&gp=0.jpg");
        map.put("世安广告 4",
                "http://img5.imgtn.bdimg.com/it/u=4244423416,3770856977&fm=23&gp=0.jpg");
        map.put("世安广告 5",
                "http://img3.imgtn.bdimg.com/it/u=3725619427,3480650754&fm=21&gp=0.jpg");
        map.put("世安广告 6",
                "http://img5.imgtn.bdimg.com/it/u=2225230626,2273977563&fm=21&gp=0.jpg");
        map.put("世安广告 7",
                "http://img4.imgtn.bdimg.com/it/u=2757297171,813906151&fm=21&gp=0.jpg");
        map.put("世安广告 8",
                "http://img5.imgtn.bdimg.com/it/u=501369453,1464539144&fm=21&gp=0.jpg");
        mCycleViewPager.setURLMap(map).setDuration(2000).start();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                mCycleViewPager.requestLayout();
                mCycleViewPager.invalidate();
            }
        }, 500);
    }

    @OnClick({R.id.zxdt, R.id.bsfa, R.id.xgmd, R.id.spxg, R.id.rcpy, R.id.wwgj})
    void webClick(View v) {
        Intent in = new Intent(getActivity(), WebActivity.class);
        String url = "";
        switch (v.getId()) {
            case R.id.zxdt:
                url = "http://m.e-funeral.cn/html/news.html";
                break;
            case R.id.bsfa:
                url = "http://m.e-funeral.cn/html/zsfa.html";
                break;
            case R.id.xgmd:
                url = "http://m.e-funeral.cn/html/gmxg.html";
                break;
            case R.id.spxg:
                url = "http://m.e-funeral.cn/html/yxa.html";
                break;
            case R.id.rcpy:
                url = "http://m.e-funeral.cn/html/wshk.html";
                break;
            case R.id.wwgj:
                url = "https://h5.koudaitong.com/v2/showcase/homepage?alias=1be0nue78";
                break;


        }
        in.putExtra("url", url);
        getActivity().startActivity(in);
    }
}
