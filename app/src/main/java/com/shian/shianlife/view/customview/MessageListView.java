package com.shian.shianlife.view.customview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.MessageDetailActviity;
import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.common.utils.TimeUtils;
import com.shian.shianlife.common.utils.Utils;
import com.shian.shianlife.provide.MHttpManagerFactory;
import com.shian.shianlife.provide.base.HttpResponseHandler;
import com.shian.shianlife.provide.params.HpPageParams;
import com.shian.shianlife.provide.result.HrMessageList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class MessageListView extends LinearLayout {

    int type = -1;
    int readState = 0;
    int page = 0;
    int pageSize = 10;


    View view;
    PullToRefreshListView listView;

    List<HrMessageList.MessageList> listOrderData = new ArrayList<>();
    List<HrMessageList.MessageList> listOrderUnReadData = new ArrayList<>();


    RadioGroup mRGRead;
    RadioButton mRBAllRead;
    RadioButton mRBUnRead;

    /**
     * @param context
     * @param type    1.服务 2.通知
     */
    public MessageListView(Context context, int type) {
        this(context, null);
        this.type = type;
        initData();
        initView();
    }

    public MessageListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = View.inflate(context, R.layout.layout_message_listview_items, this);
    }

    private void initData() {
        if (type == 1) {
            getOrderData(true);
        } else if (type == 2) {

        }
    }

    /**
     * 设置状态
     *
     * @param readState 0为全部。1为未读
     */
    public void setReadState(int readState) {
        this.readState = readState;
        baseAdapter.notifyDataSetChanged();
    }

    private void getOrderData(final boolean isRefresh) {
        HpPageParams params = new HpPageParams();
        params.setPageNum(page);
        params.setPageSize(pageSize);
        params.setCtgId(type + 1);//1.全部 2.服务。3 通知
        MHttpManagerFactory.getAccountManager().getMessageList(getContext(),
                params, new HttpResponseHandler<HrMessageList>() {

                    @Override
                    public void onSuccess(HrMessageList result) {
                        if (isRefresh) {
                            listOrderData.clear();
                        }
                        listOrderData.addAll(result.getList());
                        listOrderUnReadData.clear();
                        for (HrMessageList.MessageList item : listOrderData) {
                            if (item.getReadStatus() == 1) {
                                listOrderUnReadData.add(item);
                            }
                        }
                        baseAdapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onError(String message) {
                        listView.onRefreshComplete();
                    }
                });
    }


    private void initView() {
        listView = (PullToRefreshListView) view.findViewById(R.id.pull_listview);
        mRGRead = (RadioGroup) findViewById(R.id.rg_readstate);
        mRBAllRead = (RadioButton) findViewById(R.id.rb_readall);
        mRBUnRead = (RadioButton) findViewById(R.id.rb_readun);


        mRGRead.setOnCheckedChangeListener(onRBReadcheck);
        listView.setAdapter(baseAdapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(onRefreshListener2);
        listView.setOnItemClickListener(onItemClickListener);
    }


    PullToRefreshBase.OnRefreshListener2 onRefreshListener2 = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            loadNewData();
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            loadMoreData();
        }
    };

    public void loadMoreData() {
        if (type == 1) {
            page++;
            getOrderData(false);
        } else if (type == 2) {

        }
    }

    public void loadNewData() {
        if (type == 1) {
            page = 0;
            getOrderData(true);
        } else if (type == 2) {

        }
    }



    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            if (type == 1) {
                if (readState == 0) {
                    return listOrderData.size();
                } else {
                    return listOrderUnReadData.size();
                }

            } else {
                return 0;
            }

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
                convertView = View.inflate(getContext(), R.layout.layout_message_items, null);
                holder = new ViewHolder();
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holder.ivRead = (ImageView) convertView.findViewById(R.id.iv_read);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            HrMessageList.MessageList data;

            if (type == 1) {
                if (readState == 0) {
                    data = listOrderData.get(position);
                } else {
                    data = listOrderUnReadData.get(position);
                }
            } else {
                if (readState == 0) {
                    data = new HrMessageList.MessageList();
                } else {
                    data = new HrMessageList.MessageList();
                }
            }
            holder.tvTitle.setText(data.getHead());
            holder.tvTime.setText(TimeUtils.formatTime(data.getServerCreateTime()));
            holder.tvContent.setText(data.getBody());
            if (data.getReadStatus() == 1) {
                holder.ivRead.setVisibility(VISIBLE);
            } else {
                holder.ivRead.setVisibility(INVISIBLE);
            }
            return convertView;
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvTime;
            TextView tvContent;
            ImageView ivRead;
        }
    };

    RadioGroup.OnCheckedChangeListener onRBReadcheck = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == mRBAllRead.getId()) {
                setReadState(0);
            } else if (checkedId == mRBUnRead.getId()) {
                setReadState(1);
            }
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent in = new Intent(getContext(),
                    MessageDetailActviity.class);
            if (readState == 0) {
                listOrderData.get(position-1).setReadStatus(0);
                in.putExtra("message", JSONUtil.writeEntityToJSONString(listOrderData.get(position-1)));
            } else if (readState == 1) {
                listOrderUnReadData.get(position-1).setReadStatus(0);
                in.putExtra("message", JSONUtil.writeEntityToJSONString(listOrderUnReadData.get(position-1)));
            }
            getContext().startActivity(in);
        }
    };

   public void notifyDataSetChanged(){
       baseAdapter.notifyDataSetChanged();
   }
}
