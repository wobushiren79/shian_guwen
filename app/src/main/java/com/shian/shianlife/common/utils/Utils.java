package com.shian.shianlife.common.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

public class Utils {

	public static String getDateUtils(long paramLong) {
		return TransitionDate.DateToStr(new Date(paramLong), "yyyy-MM-dd HH:mm");
	}

	public static void call(final View v, final String phone) {
		if (!TextUtils.isEmpty(phone)) {
			v.setVisibility(View.VISIBLE);
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View vv) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
					((Activity)v.getContext()).startActivity(intent);
				}
			});
		} else {
			v.setVisibility(View.GONE);
		}
	}

	public static boolean isPhoneNumber(CharSequence input) {
		if (input == null) {
			return false;
		} else {
			String regex = "(\\+\\d+)?1[3458]\\d{9}$";
			return Pattern.matches(regex, input);
		}
	}

	public static int getArrayINdex(String[] arr,String text){
		int index=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i].equals(text)){
				index=i;
				break;
			}
		}
		return index;
	}
}
