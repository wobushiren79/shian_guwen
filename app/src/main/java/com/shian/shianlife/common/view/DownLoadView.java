package com.shian.shianlife.common.view;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.shian.shianlife.R;
import com.shian.shianlife.common.utils.FilePathUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

@SuppressLint("InflateParams")
public class DownLoadView extends FrameLayout implements OnClickListener, View.OnLongClickListener {

	final static String attachment = "attachment";

	String fileUrl;

	@InjectView(R.id.text)
	TextView mTextView;

	@InjectView(R.id.progress)
	ProgressBar mProgressBar;

	/**
	 * 下载文件
	 */
	File downloadFile;

	int type;

	public DownLoadView(Context context) {
		super(context);
		init();
	}

	public DownLoadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DownLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"), fileUrl.length());
		downloadFile = new File(FilePathUtils.getDownloadFolder(), attachment + "/" + fileName);
		if (downloadFile.exists()) {
			mTextView.setText("打开");
			type = 1;
		} else {
			mTextView.setText("下载");
			type = 0;
		}
	}

	private void init() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.view_download, null);
		addView(view);
		ButterKnife.inject(this);
		setOnClickListener(this);
		setOnLongClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (type == 0 || type == 3) {
			HttpUtils mHttpUtils = new HttpUtils();
			mHttpUtils.download(fileUrl, downloadFile.getAbsolutePath(), false, false, new RequestCallBack<File>() {

				@Override
				public void onStart() {
					super.onStart();
					mTextView.setText("下载中0%");
					type = 4;
				}

				@Override
				public void onLoading(long total, long current, boolean isUploading) {
					super.onLoading(total, current, isUploading);
					mProgressBar.setMax(100);
					int progress = (int) (current * 100 / total);
					mProgressBar.setProgress(progress);
					mTextView.setText("下载中" + progress + "%");
				}

				@Override
				public void onSuccess(ResponseInfo<File> arg0) {
					type = 1;
					mTextView.setText("打开");
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					type = 3;
					downloadFile.delete();
					mTextView.setText("重新下载");
				}
			});
		} else if (type == 1) {
			try {
				String mimeTypeForName = FilePathUtils.getMimeTypeForName(downloadFile.getAbsolutePath());
				Intent intent = new Intent();
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setAction(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(downloadFile), mimeTypeForName);
				((Activity) getContext()).startActivity(intent);
			} catch (Exception e) {
				type = 3;
				downloadFile.delete();
				mTextView.setText("重新下载");
			}
		}
	}

	@Override
	public boolean onLongClick(View v) {
		if (type == 1 && downloadFile.exists()) {
			showDialog();
		}
		return true;
	}

	private void showDialog() {
		TipsDialog mDialog = new TipsDialog(getContext());
		mDialog.setTitle("是否删除附件");
		mDialog.setTopButton("取消", null);
		mDialog.setBottomButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				downloadFile.delete();
				type = 0;
				mTextView.setText("下载");
			}
		});
		mDialog.show();

	}

}
