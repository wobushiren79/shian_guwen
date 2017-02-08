package com.shian.shianlife.provide.base;

import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Log;

import com.shian.shianlife.common.utils.JSONUtil;
import com.shian.shianlife.provide.params.ContentParams;

/**
 * 接口参数
 * 
 * @author Administrator
 * 
 */
public class BaseHttpParams
{
	public String getHttpParams() {
		ContentParams<BaseHttpParams> pa=new ContentParams<BaseHttpParams>();
		pa.setContent(this);
		String contentParams=JSONUtil.writeEntityToJSONString(pa);
		Log.e("tag", "contentParams="+contentParams);
		return contentParams;
	}
}
