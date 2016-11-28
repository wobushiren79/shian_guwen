package com.shian.shianlife.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.util.Log;

//yyyy-MM-dd HH:mm:ss

/**
 * 日期转换类
 * 
 * @author Administrator
 * 
 */
public class TransitionDate {
	/**
	 * 将指定的日期格式字符串转成另一种指定的日期格式字符串
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr1
	 *            日期格式如：yyyy-MM-dd HH:mm:ss
	 * @param frmtstr2
	 *            另一种日期格式如：yyyy-MM-dd HH:mm:ss
	 * @return frmtstr2 指定的日期字符串
	 */
	public static String StrToStr(String datestr, String frmtstr1,
			String frmtstr2) {
		return DateToStr(StrToDate(datestr, frmtstr1), frmtstr2);
	}

	/**
	 * 将指定的日期格式字符串转成Date日期格式
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr
	 *            日期格式如：yyyy-MM-dd HH:mm:ss
	 * @return Date日期格式
	 */
	public static Date StrToDate(String datestr, String frmtstr) {
		Date date = null;
		try {
			if (datestr != null && !"".equals(datestr) && frmtstr != null
					&& !"".equals(frmtstr)) {
				SimpleDateFormat sdf = new SimpleDateFormat(frmtstr);
				date = sdf.parse(datestr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将指定的日期格式字符串转成Date日期格式
	 * 
	 * @param date
	 *            Date日期格式
	 * @param frmtstr
	 *            日期格式如：yyyy-MM-dd HH:mm:ss
	 * @return frmtstr日期格式
	 */
	public static String DateToStr(Date date, String frmtstr) {
		if (date == null)
			return "";
		SimpleDateFormat dd = new SimpleDateFormat(frmtstr);
		String date1 = dd.format(date);
		return date1;
	}

	/**
	 * 判断是不是今天之后的第几天
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr
	 *            日期的格式如：yyyy-MM-dd HH:mm:ss
	 * @param index
	 *            今天之后的第几天
	 * @return boolean类型，如果是返回true，否则false
	 */
	private static boolean isToday(String datestr, String frmtstr, int index) {
		Date now_date;
		try {
			now_date = StrToDate(datestr, frmtstr);
			Calendar nowCalendar = Calendar.getInstance();
			nowCalendar.add(Calendar.DAY_OF_YEAR, index);
			nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
			nowCalendar.set(Calendar.MINUTE, 0);
			nowCalendar.set(Calendar.SECOND, 0);
			nowCalendar.set(Calendar.MILLISECOND, 0);

			if (now_date.equals(nowCalendar.getTime())) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isToday(Date datestr, String frmtstr, int index) {
		String now_date;
		try {
			now_date = TransitionDate.DateToStr(datestr, "yyyy-M-dd");
			Calendar nowCalendar = Calendar.getInstance();
			nowCalendar.add(Calendar.DAY_OF_YEAR, index);
			nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
			nowCalendar.set(Calendar.MINUTE, 0);
			nowCalendar.set(Calendar.SECOND, 0);
			nowCalendar.set(Calendar.MILLISECOND, 0);
			if (now_date.equals(nowCalendar.get(Calendar.YEAR) + "-"
					+ (nowCalendar.get(Calendar.MONTH) + 1) + "-"
					+ nowCalendar.get(Calendar.DAY_OF_MONTH))) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断是不是今天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isToday(String datestr, String frmtstr) {
		return isToday(datestr, frmtstr, 0);
	}

	/**
	 * 判断是不是昨天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isVorgestern(String datestr, String frmtstr) {
		return isToday(datestr, frmtstr, -1);
	}

	/**
	 * 判断是不是明天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isTomorrow(String datestr, String frmtstr) {
		return isToday(datestr, frmtstr, 1);
	}

	/**
	 * 判断是不是后天
	 * 
	 * @param datestr
	 * @param frmtstr
	 * @return
	 */
	public static boolean isAfterTomorrow(String datestr, String frmtstr) {
		return isToday(datestr, frmtstr, 2);
	}

	private static String chineseNumber[] = { "日", "一", "二", "三", "四", "五", "六" };

	/**
	 * 根据日期获取星期
	 * 
	 * @param date
	 *            日期
	 * @return "日","一", "二", "三", "四", "五", "六"其中的一个
	 */
//	public static String getWeek(Date date, Resources resource) {
//		Calendar nowCalendar = Calendar.getInstance();
//		nowCalendar.setTime(date);
//		String cN[] = { resource.getString(R.string.Schedule_SundayL),
//				resource.getString(R.string.Schedule_MondayL),
//				resource.getString(R.string.Schedule_TuesdayL),
//				resource.getString(R.string.Schedule_WednesdayL),
//				resource.getString(R.string.Schedule_ThursdayL),
//				resource.getString(R.string.Schedule_FridayL),
//				resource.getString(R.string.Schedule_SaturdayL) };
//		return cN[nowCalendar.get(Calendar.DAY_OF_WEEK) - 1];
//	}

	/**
	 * 根据日期获取星期
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param frmtstr
	 *            日期的格式如：yyyy-MM-dd HH:mm:ss
	 * @return "日","一", "二", "三", "四", "五", "六"其中的一个
	 */
//	public static String getWeek(String datestr, String frmtstr,
//			Resources resource) {
//		return getWeek(StrToDate(datestr, frmtstr), resource);
//	}

	/**
	 * 今年的日子：显示 月-日 非今年的日子：年-月-日
	 * 
	 * @param calendar
	 * @return
	 */
	public static String getStDate(String calendar) {
		Calendar nowCalendar = Calendar.getInstance();
		int year_temp = nowCalendar.get(Calendar.YEAR);// 当前年份
		Date dat = StrToDate(calendar, "yyyy-MM-dd");
		nowCalendar.setTime(dat);
		int year = nowCalendar.get(Calendar.YEAR);
		if (year == year_temp) {
			return StrToStr(calendar, "yyyy-MM-dd", "MM-dd");
		} else {
			return calendar;
		}
	}

	/**
	 * 时间间隔计算
	 * 
	 * 列表时间显示规则（其他模块相同）： <br>
	 * 今年的日期不显示年份，其余的年份都显示年份<br>
	 * 昨天、今天不显示日期，显示“昨天”、“今天” <br>
	 * 时间显示方式24:59,24小时制，只显示到分，不显示秒
	 */
	public static String getDaysBeforeNow(Date date, Context context) {
		if (date == null) {
			return "";
		}
		long sysTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date()));
		long ymdhms = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
				.format(date));
		String today = "昨天";
		String strDay = "今天";
		try {
			if (ymdhms == 0) {
				return "";
			}
			long between = (sysTime / 10000000000L) - (ymdhms / 10000000000L);
			if (between != 0) {// 年份大于一年 显示全时间格式
				return DateToStr(date, "yyyy-M-d H:mm");
			}

			// 天数
			between = (sysTime / 1000000L) - (ymdhms / 1000000L);
			if (between > 1 || between < 0) {
				return DateToStr(date, "M-d H:mm");
			}
			if (between == 1) {
				return strDay + " " + DateToStr(date, "H:mm");
			}

			// 小时
			between = (sysTime / 10000) - (ymdhms / 10000);
			if (between <= 23) {
				return today + " " + DateToStr(date, "H:mm");
			}

			return DateToStr(date, "M-d H:mm");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 比较两个日期时间的大小
	 * 
	 * @param sDataFormat
	 *            时间格式 "yyyy-MM-dd hh:mm" "yyyy-MM-dd"
	 * @param data1
	 *            时间1
	 * @param data2
	 *            时间2
	 * @return <br>
	 *         1 时间1在时间2的后面 <br>
	 *         -1 时间1在时间2的前面 <br>
	 *         0 同一时刻 <br>
	 *         -2 发生异常
	 */
	public static int compare_date(String sDataFormat, String data1,
			String data2) {
		DateFormat df = new SimpleDateFormat(sDataFormat);
		try {
			Date dt1 = df.parse(data1);
			Date dt2 = df.parse(data2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			Log.e("error", "转化时间出错 " + exception.toString());
			return -2;
		}
	}

	// /**
	// * 时间间隔计算
	// *
	// * 刚刚 xx分钟前
	// * hh小时前
	// * 今天 HH:mm
	// * 昨天 HH:mm
	// * 前天 HH:mm
	// * 上个月 yyyy-MM-dd HH:mm
	// * 去年yyyy-MM-dd HH:mm
	// * 前年 yyyy-MM-dd HH:mm
	// */
	// public static String getDaysBeforeNow(Date date) {
	// long sysTime = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
	// .format(new Date()));
	// long ymdhms = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")
	// .format(date));
	// //String strYear = "年前";
	// //String strMonth = "月前";
	// String today="今天";
	// //String strDay = "天前";
	// String strHour = "小时前";
	// String strMinute = "分钟前";
	// try {
	// if (ymdhms == 0) {
	// return "";
	// }
	// long between = (sysTime / 10000000000L) - (ymdhms / 10000000000L);
	// if (between > 0) {
	// //return between + strYear;
	// return DateToStr(date, "yyyy-MM-dd HH:mm");
	// }
	// // between = (sysTime / 100000000L) - (ymdhms / 100000000L);
	// // if (between > 0) {
	// // return between + strMonth;
	// // }
	// between = (sysTime / 1000000L) - (ymdhms / 1000000L);
	// if (between > 0) {
	// //return between + strDay;
	// return DateToStr(date, "MM-dd HH:mm");
	// }
	// between = (sysTime / 10000) - (ymdhms / 10000);
	// if(between>6){
	// return today+" "+DateToStr(date, "HH:mm");
	// }
	// if (between > 0) {
	// return between + strHour;
	// }
	// between = (sysTime / 100) - (ymdhms / 100);
	// if (between > 0) {
	// return between + strMinute;
	// }
	// return "1" + strMinute;
	// } catch (Exception e) {
	// return "";
	// }
	// }

}
