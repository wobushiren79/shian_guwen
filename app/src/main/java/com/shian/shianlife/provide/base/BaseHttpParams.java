package com.shian.shianlife.provide.base;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
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

	/**
	 * 将实体类转换成请求参数,json字符串形式返回
	 *
	 * @return
	 */
	public String getJsonParams() {
		String jsonStr = new Gson().toJson(this);
		if (TextUtils.isEmpty(jsonStr)) {
			jsonStr = "";
		}
		return jsonStr;
	}

	/**
	 * 将实体类转换成请求参数,以map<k,v>形式返回
	 *
	 * @return
	 */
	public Map<String, String> getMapParams() {

		Class<? extends BaseHttpParams> clazz = this.getClass();
		Class<? extends Object> superclass = clazz.getSuperclass();

		Field[] fields = clazz.getDeclaredFields();
		Field[] superFields = superclass.getDeclaredFields();

		if (fields == null || fields.length == 0) {
			return Collections.emptyMap();
		}

		Map<String, String> params = new HashMap<String, String>();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				params.put(field.getName(), String.valueOf(field.get(this)));
			}

			for (Field superField : superFields) {
				superField.setAccessible(true);
				params.put(superField.getName(), String.valueOf(superField.get(this)));
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return params;
	}

	/**
	 * 套上Content
	 *
	 * @return
	 */
	public String getContentJson() {
		BaseRequestParams<BaseHttpParams> baseData = new BaseRequestParams<>();
		baseData.setContent(this);
		return baseData.getJsonParams();
	}

}
