package com.shian.shianlife.view.dialog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shian.shianlife.R;
import com.shian.shianlife.base.SaBaseApplication;
import com.shian.shianlife.common.utils.CheckUtils;
import com.shian.shianlife.common.utils.ToastUtils;
import com.shian.shianlife.mvp.download.bean.DownLoadFileResultBean;
import com.shian.shianlife.mvp.download.presenter.IDownLoadFilePresenter;
import com.shian.shianlife.mvp.download.presenter.impl.DownLoadFilePresenterImpl;
import com.shian.shianlife.mvp.download.view.IDownLoadFileView;
import com.zhy.http.okhttp.request.RequestCall;

/**
 * Created by zm.
 */

public class AppUpdateDialog extends Dialog implements View.OnClickListener, IDownLoadFileView {
    private TextView mTvUpdate;
    private TextView mTvCancel;
    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvProgress;
    private ProgressBar mProgressBar;
    private LinearLayout mLLDownLoad;

    private String appDownloadUrl;
    private String appDownloadName;
    private String titleTest;
    private String contentTest;

    private RequestCall requestCall;
    private IDownLoadFilePresenter downLoadFilePresenter;

    private boolean isMustBeUpdate;

    public AppUpdateDialog(Context context, String appDownloadUrl, String appDownloadName) {
        super(context, R.style.CustomDialog);
        this.appDownloadUrl = appDownloadUrl;
        this.appDownloadName = appDownloadName;
        isMustBeUpdate = false;
        downLoadFilePresenter = new DownLoadFilePresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_appupdate);

        initView();
    }

    @Override
    public void show() {
        super.show();

    }

    private void initView() {
        mTvUpdate = (TextView) findViewById(R.id.dialog_appupdate_update);
        mTvCancel = (TextView) findViewById(R.id.dialog_appupdate_cancel);
        mTvTitle = (TextView) findViewById(R.id.dialog_appupdate_title);
        mTvContent = (TextView) findViewById(R.id.dialog_appupdate_content);
        mTvProgress = (TextView) findViewById(R.id.dialog_appupdate_progress);
        mProgressBar = (ProgressBar) findViewById(R.id.dialog_appupdate_pb);
        mLLDownLoad = (LinearLayout) findViewById(R.id.ll_download);

        if (titleTest != null)
            mTvTitle.setText(titleTest);
        if (contentTest != null)
            mTvContent.setText(contentTest);

        mTvUpdate.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
    }

    public void setTitleTest(String titleTest) {
        this.titleTest = "更新内容（" + titleTest + "）";
    }

    public void setContentTest(String contentTest) {
        this.contentTest = contentTest;
    }

    public void setMustBeUpdate(boolean isMustBeUpdate) {
        this.isMustBeUpdate = isMustBeUpdate;
    }

    @Override
    public void onClick(View v) {
        if (v == mTvCancel) {
            this.cancel();
        } else if (v == mTvUpdate) {
            upDateApp();
        }
    }

    /**
     * 马上更新
     */
    private void upDateApp() {
        boolean hasPermission = CheckUtils.getPermissionToReadUserContacts(getContext(),
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                "更新需要文件读写权限",
                9999);
        if (hasPermission) {
            requestCall = downLoadFilePresenter.startDownLoad();
            mTvUpdate.setVisibility(View.GONE);
            mLLDownLoad.setVisibility(View.VISIBLE);
        } else {
            ToastUtils.show(getContext(), "更新需要文件读写权限");
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(getContext(), msg);
    }

    @Override
    public String getDownLoadFileUrl() {
        return appDownloadUrl;
    }

    @Override
    public String getDownLoadFileName() {
        return appDownloadName;
    }

    @Override
    public void downloadSuccess(DownLoadFileResultBean resultBean) {
        if (!resultBean.getDownloadFile().exists()) {
            ToastUtils.show(getContext(), "没有File");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(resultBean.getDownloadFile()),
                "application/vnd.android.package-archive");
        getContext().startActivity(intent);
    }

    @Override
    public void downloadFail(String msg) {
        ToastUtils.show(getContext(), msg);
        mTvUpdate.setVisibility(View.VISIBLE);
        mLLDownLoad.setVisibility(View.GONE);
        mProgressBar.setSecondaryProgress(0);
        mTvProgress.setText("0%");
    }

    @Override
    public void downloadInProgress(long total, float progress) {
        int progressInt = (int) (progress * 100);
        mTvProgress.setText(progressInt + "%");
        mProgressBar.setSecondaryProgress(progressInt);
    }

    @Override
    public void cancel() {
        super.cancel();
        if (requestCall != null) {
            requestCall.cancel();
        }
        if (isMustBeUpdate) {
            ((SaBaseApplication)getContext().getApplicationContext()).finishActivity();
        }
    }
}
