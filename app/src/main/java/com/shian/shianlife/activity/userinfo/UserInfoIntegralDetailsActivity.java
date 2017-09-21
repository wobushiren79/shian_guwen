package com.shian.shianlife.activity.userinfo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.shianlife.R;
import com.shian.shianlife.base.BaseActivity;

public class UserInfoIntegralDetailsActivity extends BaseActivity {

    PullToRefreshListView mPullListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_integral_details);

        setTitle("积分明细");
        initView();
    }

    private void initView() {
        mPullListView = (PullToRefreshListView) findViewById(R.id.pull_listview);

        mPullListView.setAdapter(adapter);
    }

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 3;
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
                convertView = View.inflate(UserInfoIntegralDetailsActivity.this, R.layout.view_details_items, null);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvPoint = (TextView) convertView.findViewById(R.id.tv_point);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.dimen_130dp));
            convertView.setLayoutParams(layoutParams);
            return convertView;
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvTime;
            TextView tvPoint;
        }
    };
}
