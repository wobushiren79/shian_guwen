package com.shian.shianlife.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.shianlife.R;
import com.shian.shianlife.adapter.FindAdapter;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.common.contanst.AppContansts;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpmodel.SiftListData;
import com.shian.shianlife.provide.phpparams.PHPHpSiftListParams;
import com.shian.shianlife.provide.phpresult.PHPHrGetSiftListData;
import com.shian.shianlife.thisenum.SystemTypeEnum;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class FindActivity extends BaseActivity {
    ImageView mIVCollection;
    PullToRefreshListView mListView;

    int number = 10;
    int pagerNumber = 0;

    FindAdapter findAdapter;
    List<SiftListData> listDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initView();
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
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
        setTitle("活动");
        mListView = (PullToRefreshListView) findViewById(R.id.pull_listview);
        mIVCollection = (ImageView)findViewById(R.id.iv_collection);

        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(onRefreshListener2);
        findAdapter = new FindAdapter(this, listDatas);
        mListView.setAdapter(findAdapter);
        mIVCollection.setOnClickListener(onClickListener);
    }

    private void initData() {
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
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVCollection) {
                Intent intent = new Intent(FindActivity.this, MyCollectionActivity.class);
                startActivity(intent);
            }
        }
    };

    private void getData(final boolean isClean) {
        if (AppContansts.systemLoginInfo == null && AppContansts.systemLoginInfo.getUserId() == null) {
            ToastUtils.show(this, "账号未登录，请退出重新登录");
            return;
        }
        PHPHpSiftListParams params = new PHPHpSiftListParams();
        params.setType(1);
        params.setUserid(AppContansts.systemLoginInfo.getUserId());
        params.setNumber(number);
        params.setPagerNumber(pagerNumber);
        params.setUserType(SystemTypeEnum.funeral.getCode());
        MHttpManagerFactory.getPHPManager().getSiftListData(this, params, new HttpResponseHandler<PHPHrGetSiftListData>() {


            @Override
            public void onStart(Request request, int id) {

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
