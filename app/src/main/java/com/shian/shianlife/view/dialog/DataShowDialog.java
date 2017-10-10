package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;


import com.shian.shianlife.R;
import com.shian.shianlife.adapter.DataShowDialogListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by zm.
 */

public class DataShowDialog extends Dialog {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rc_content)
    RecyclerView rcContent;
    @InjectView(R.id.iv_cancel)
    ImageView ivCancel;

    private String title = "";
    private OnClickListener cancelClickListener;
    private DataShowDialogListAdapter listAdapter;
    private List<DataShowDialogResultBean> listData;

    public DataShowDialog(@NonNull Context context) {
        super(context, R.style.tipsDialogStyle);
        listData = new ArrayList<>();
        listAdapter = new DataShowDialogListAdapter(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_data_show_layout);
        ButterKnife.inject(this);
        initView();
        initData();
    }


    private void initView() {
        tvTitle.setText(title);
    }

    private void initData() {
        listAdapter.setData(listData);
        rcContent.setLayoutManager(new LinearLayoutManager(getContext()));
        rcContent.setAdapter(listAdapter);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(List<DataShowDialogResultBean> listData) {
        this.listData = listData;
    }

    public void setCancelOnClick(OnClickListener onClickListener) {
        cancelClickListener = onClickListener;
    }

    @OnClick(R.id.iv_cancel)
    public void onViewClicked() {
        cancel();
        if (cancelClickListener != null) {
            cancelClickListener.onClick(this, 0);
        }
    }


    public static class DataShowDialogResultBean {
        public DataShowDialogResultBean(String title, String content) {
            this.title = title;
            this.content = content;
        }

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
