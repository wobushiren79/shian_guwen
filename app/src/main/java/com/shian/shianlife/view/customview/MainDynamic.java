package com.shian.shianlife.view.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.WebActivity;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.phpmodel.DynamicItemsInfo;
import com.shian.shianlife.provide.phpresult.PHPHrGetDynamic;
import com.shian.shianlife.view.ScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/6.
 */

public class MainDynamic extends LinearLayout {
    View view;

    ScrollListView mListView;
    ImageView mIVMore;

    List<DynamicItemsInfo> items = new ArrayList<>();


    private int showNumber = 3;//动态通知数量
    private CallBack callBack;

    public MainDynamic(Context context) {
        this(context, null);
    }

    public MainDynamic(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.view_maindynamic_layout, this);

        initView();
        getData();
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * 获取数据
     */
    private void getData() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("number", showNumber);
        requestParams.put("pagerNumber", 0);
        MHttpManagerFactory.getPHPManager().getDynamicInfo(getContext(), requestParams, new HttpResponseHandler<PHPHrGetDynamic>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(PHPHrGetDynamic result) {
                items = result.getItems();
                adapter.notifyDataSetChanged();
                callBack.loadingComplete();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    private void initView() {
        mListView = (ScrollListView) view.findViewById(R.id.listView);
        mIVMore = (ImageView) view.findViewById(R.id.iv_more);

        mIVMore.setOnClickListener(onClickListener);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(onItemClickListener);
    }


    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int urlId = items.get(position).getId();
            Intent intent = new Intent(getContext(), WebActivity.class);
            intent.putExtra("url", "http://www.baidu.com");
            getContext().startActivity(intent);
        }
    };

    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mIVMore) {
                ToastUtils.show(getContext(), "more");
            }
        }
    };

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return items.size();
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
                convertView = View.inflate(getContext(), R.layout.view_main_dynamic_layout_items, null);
                holder = new ViewHolder();
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvTop = (TextView) convertView.findViewById(R.id.tv_top);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MainDynamic.this.getResources().getDimensionPixelOffset(R.dimen.dimen_72dp));
            DynamicItemsInfo data = items.get(position);
            convertView.setLayoutParams(layoutParams);
            holder.tvContent.setText(data.getTitle());
            holder.tvTime.setText(data.getTime());
            if (position == 0) {
                holder.tvTop.setVisibility(VISIBLE);
            } else {
                holder.tvTop.setVisibility(GONE);
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvContent;
            TextView tvTime;
            TextView tvTop;
        }
    };

    public interface CallBack {

        void loadingComplete();

    }
}
