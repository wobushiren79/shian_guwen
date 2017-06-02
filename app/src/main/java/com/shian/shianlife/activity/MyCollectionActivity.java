package com.shian.shianlife.activity;

import android.os.Bundle;
import android.os.Handler;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;
import com.shian.shianlife.R;
import com.shian.shianlife.adapter.FindAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpmodel.SiftListData;
import com.shian.shianlife.provide.phpresult.PHPHrGetSiftListData;
import com.shian.shianlife.thisenum.SystemTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class MyCollectionActivity extends BaseActivity {

    PullToRefreshListView mListView;

    FindAdapter findAdapter;
    List<SiftListData> listDatas = new ArrayList<>();

    int number = 10;
    int pagerNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        setTitle("我的收藏");
        initView();
        // 开始就呈现下拉状态
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.setRefreshing(true);
                pagerNumber = 0;
                getData(true);
            }
        }, 500);
    }

    private void initView() {
        mListView = (PullToRefreshListView) findViewById(R.id.pull_listview);

        findAdapter = new FindAdapter(MyCollectionActivity.this, listDatas);
        mListView.setAdapter(findAdapter);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(onRefreshListener2);
    }

    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            pagerNumber = 0;
            getData(true);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            pagerNumber++;
            getData(false);
        }
    };

    private void getData(final boolean isClean) {
        RequestParams params = new RequestParams();
        params.put("type", "2");
        params.put("userid", (int) AppContansts.userLoginInfo.getUserId());
        params.put("number", number);
        params.put("pagerNumber", pagerNumber);
        params.put("userType", SystemTypeEnum.platform.getCode());
        MHttpManagerFactory.getPHPManager().getSiftListData(MyCollectionActivity.this, params, new HttpResponseHandler<PHPHrGetSiftListData>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(PHPHrGetSiftListData result) {
                if (isClean) {
                    listDatas.clear();
                }
                listDatas.addAll(result.getItems());
                findAdapter.setData(listDatas);
                mListView.onRefreshComplete();
            }

            @Override
            public void onError(String message) {
                mListView.onRefreshComplete();
            }
        });
    }
}
