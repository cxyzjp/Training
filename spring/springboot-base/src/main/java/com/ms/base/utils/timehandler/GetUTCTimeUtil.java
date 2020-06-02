/******************************************************************
 *
 *    Powered By .
 *
 *    Copyright (c) 
 *    http://
 *
 *    Package:     com.weshop.base.utils
 *
 *    Filename:    GetUTCTimeUtil.java
 *
 *    Description: TODO
 *
 *    Copyright:   Copyright (c) 2001-2014
 *
 *    Company:     Digital Telemedia Co.,Ltd
 *
 *    @author:     fih01
 *
 *    @version:    1.0.0
 *
 *    Create at:   2016年11月2日 下午4:06:59
 *
 *    Revision:
 *
 *    2016年11月2日 下午4:06:59
 *        - first revision
 *
 *****************************************************************/
package com.ms.base.utils.timehandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class GetUTCTimeUtil {

	private static DateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm:ss"<br />
	 * 如果获取失败，返回null
	 * 
	 * @return
	 */
	public static String getUTCTimeStr() {
		StringBuffer UTCTimeBuffer = getDateFormatStringBuffer();
		return UTCTimeBuffer.toString();
	}

	public static Date getUTCDate() {
		StringBuffer UTCTimeBuffer = getDateFormatStringBuffer();
		Date date = null;
		try {
			date = format.parse(UTCTimeBuffer.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static StringBuffer getDateFormatStringBuffer() {
		StringBuffer UTCTimeBuffer = new StringBuffer();
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		UTCTimeBuffer.append(year).append("-").append(month).append("-")
				.append(day);
		UTCTimeBuffer.append(" ").append(hour).append(":").append(minute)
				.append(":").append(second);
		return UTCTimeBuffer;
	}

	// public static void main(String[] args) {
	// String UTCTimeStr = getUTCTimeStr();
	// System.out.println(UTCTimeStr);
	// System.out.println(getUTCDate());
	// }

}