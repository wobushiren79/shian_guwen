package com.shian.shianlife.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

	public static String formatTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
		return format.format(new Date(time));
	}

	public static String formatTime(long time, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
		return format.format(new Date(time));
	}

}
