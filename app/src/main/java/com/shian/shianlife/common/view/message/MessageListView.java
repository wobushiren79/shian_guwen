package com.shian.shianlife.common.view.message;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper;
import com.chanven.lib.cptr.loadmore.SwipeRefreshHelper.OnSwipeRefreshListener;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.MessageDetailActviity;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.TArrayListAdapter;
import com.shian.shianlife.common.utils.TArrayListAdapter.IOnDrawViewEx;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.common.utils.ViewGropMap;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.model.OrderListModel;
import com.shian.shianlife.provide.params.HpPageParams;
import com.shian.shianlife.provide.result.HrMessageList;
import com.shian.shianlife.provide.result.HrMessageList.MessageList;

public class MessageListView extends FrameLayout {
    private final String LOG_TAG = "MESSAGE_LISTVIEW";
    private SwipeRefreshLayout mSryt;
    private ListView mListView;
    private TArrayListAdapter<MessageList> adapter;

    private SwipeRefreshHelper mSwipeRefreshHelper;
    private int pageSize = 20;
    private int page = 0;
    private View v;
    private int type;

    public MessageListView(Context context, int t) {
        super(context, null);
        type = t;
        v = LayoutInflater.from(context).inflate(R.layout.view_order_common,
                null, false);
        addView(v);
        initView();
        initDate();
    }

    public void refresh() {
        mSryt.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHelper.autoRefresh();
            }
        });
    }

    private void initView() {
        mSryt = (SwipeRefreshLayout) v.findViewById(R.id.sryt_swipe_listview);
        mListView = (ListView) v.findViewById(R.id.lv_swipe_listview);
        mSryt.setColorSchemeColors(Color.BLUE);
        adapter = new TArrayListAdapter<MessageList>(getContext());
    }

    private void initDate() {
        adapter.setLayout(R.layout.view_item_message);
        adapter.setDrawViewEx(overDrawViewEx);
        mListView.setAdapter(adapter);
        mSwipeRefreshHelper = new SwipeRefreshHelper(mSryt);
        mSwipeRefreshHelper
                .setOnSwipeRefreshListener(new OnSwipeRefreshListener() {
                    @Override
                    public void onfresh() {
                        page = 0;
                        loadMoreData(true);
                    }
                });

        mSwipeRefreshHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                loadMoreData(false);
            }
        });
    }

    protected void loadMoreData(final boolean isF) {
        if (isF) {
            adapter.clear();
            page = 0;
        } else
            page++;
        HpPageParams params = new HpPageParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        params.setCtgId(type);
        MHttpManagerFactory.getAccountManager().getMessageList(getContext(),
                params, new HttpResponseHandler<HrMessageList>() {

                    @Override
                    public void onSuccess(HrMessageList result) {
                        adapter.addListData(result.getList());
                        adapter.notifyDataSetChanged();
                        mSwipeRefreshHelper.refreshComplete();
                        mSwipeRefreshHelper.loadMoreComplete(true);

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        // mSwipeRefreshHelper.loadMoreComplete(true);
                        // mSwipeRefreshHelper.setLoadMoreEnable(true);
                    }
                });

    }

    /**
     * 服务完成
     */
    IOnDrawViewEx<MessageList> overDrawViewEx = new IOnDrawViewEx<MessageList>() {

        @Override
        public void OnDrawViewEx(Context aContext, final MessageList model,
                                 ViewGropMap view, int aIndex) {
            TextView tv_qt01 = (TextView) view.getView(R.id.tv_msg_title);
            tv_qt01.setText(model.getHead());
            TextView tv_qt11 = (TextView) view.getView(R.id.tv_msg_content);
            tv_qt11.setText(model.getBody());
            TextView tv_qt21 = (TextView) view.getView(R.id.tv_msg_date);
            tv_qt21.setText(model.getServerCreateTime());
            ImageView ivMsg = (ImageView) view.getView(R.id.iv_message);
            if (model.getReadStatus() == 1) {
                ivMsg.setVisibility(View.VISIBLE);
            } else {
                ivMsg.setVisibility(View.GONE);
            }

            view.getView(R.id.ll_message).setOnClickListener(
                    new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            Intent in = new Intent(getContext(),
                                    MessageDetailActviity.class);
                            Utils.LogVPrint(JSONUtil.writeEntityToJSONString(model));
                            in.putExtra("message",
                                    JSONUtil.writeEntityToJSONString(model));
                            getContext().startActivity(in);
                        }
                    });
        }
    };

}
