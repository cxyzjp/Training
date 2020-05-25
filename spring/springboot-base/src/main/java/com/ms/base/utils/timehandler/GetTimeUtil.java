package com.ms.base.utils.timehandler;

import java.util.Date;

public class GetTimeUtil {

	public static Date getNow() {
		return new Date();
	}

	/**
	 * 判断是否是昨天 00:00:00之后
	 */
	public static boolean isTodayOrYesterday(Date todayStartTime, Date date) {
		if (date == null) {
			return false;
		}

		if ((todayStartTime.getTime() - date.getTime()) <= 86400000)
			return true;
		return false;
	}

	/**
	 * 获取今天 00:00:00 的时间
	 */
/*	public static Date getTodayStart() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Date start = null;
		try {
			start = formater2.parse(formater.format(new Date()) + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return start;
	}*/

	/**
	 * 获取今天 23:59:59 的时间
	 */
/*	public static Date getTodayEnd() {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date end = null;
		try {
			end = formater2.parse(formater.format(new Date()) + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return end;
	}*/

}
