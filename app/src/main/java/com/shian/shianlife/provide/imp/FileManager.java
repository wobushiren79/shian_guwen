package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.result.HrUploadFile;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;

public interface FileManager extends HttpManager{
	public void upLoadFile(Context context,String file,String path,FileHttpResponseHandler<HrUploadFile> handler);

	/**
	 * 文件下载
	 * @param context
	 * @param downloadUrl
	 * @param responseHandler
	 */
	public RequestCall downloadFile(Context context, String downloadUrl, String fileName, final FileHttpResponseHandler<File> responseHandler);
}
