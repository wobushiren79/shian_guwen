package com.shian.shianlife.provide.imp;

import android.content.Context;

import com.shian.shianlife.provide.base.FileHttpResponseHandler;
import com.shian.shianlife.provide.base.HttpManager;
import com.shian.shianlife.provide.result.HrUploadFile;

public interface FileManager extends HttpManager{
	public void upLoadFile(Context context,String file,String path,FileHttpResponseHandler<HrUploadFile> handler);
}
