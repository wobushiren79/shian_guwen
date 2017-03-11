package com.shian.shianlife.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpmodel.DynamicItemsInfo;
import com.shian.shianlife.provide.phpresult.PHPHrGetDynamic;

import java.util.ArrayList;
import java.util.List;

public class NoticeListActivity extends BaseActivity {

    int page = 0;
    int pageNumber = 10;
    PullToRefreshListView mListView;

    List<DynamicItemsInfo> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        setTitle("动态");

        initView();

        // 开始就呈现下拉状态
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mListView.setRefreshing(true);
                getData(true);
            }
        }, 500);
    }

    private void initView() {
        mListView = (PullToRefreshListView) findViewById(R.id.pull_listview);

        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(onRefreshListener2);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(onItemClickListener);
    }


    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        }
    };

    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            page = 0;
            getData(true);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            page++;
            getData(false);
        }
    };

    /**
     * 获取数据
     */
    private void getData(final boolean isClean) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("number", pageNumber);
        requestParams.put("pagerNumber", page);
        MHttpManagerFactory.getPHPManager().getDynamicInfo(NoticeListActivity.this, requestParams, new HttpResponseHandler<PHPHrGetDynamic>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(PHPHrGetDynamic result) {
                if (isClean) {
                    data.clear();
                }
                data.addAll(result.getItems());
                adapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
            }

            @Override
            public void onError(String message) {
                mListView.onRefreshComplete();
            }
        });
    }

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(NoticeListActivity.this, R.layout.layout_message_items, null);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holder.ivRead = (ImageView) convertView.findViewById(R.id.iv_read);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DynamicItemsInfo item = data.get(position);
            holder.tvTitle.setText(item.getTitle());
            holder.tvTime.setText(item.getTime());
            holder.tvContent.setVisibility(View.GONE);
            holder.ivRead.setVisibility(View.GONE);

            return convertView;
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvTime;
            TextView tvContent;
            ImageView ivRead;
        }
    };
}
