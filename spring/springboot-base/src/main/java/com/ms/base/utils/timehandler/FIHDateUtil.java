package com.ms.base.utils.timehandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ms.exception.base.CustomException;

public class FIHDateUtil {

	public static String convertToStr(Date date) {
		return convertToStr(date, "yyyy-MM-dd");
	}
	
	public static String convertToStr(Date date, String format) {
		if(date == null || format == null){
			return null;
		}
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String string = formater.format(date);
		return string;
	}
	
	public static Date convertToDate(String dateStr) {
		return convertToDate(dateStr, "yyyy-MM-dd");
	}
	
	public static Date convertToDate(String dateStr, String format) {
		if(dateStr == null || format == null){
			return null;
		}
		
		SimpleDateFormat formater = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			throw new CustomException();
		}
		return date;
	}
	
	
	public static Integer convertToYear(Date date) {
		if(date == null)
			return null;
		String yearStr = convertToStr(date, "yyyy");
		return Integer.parseInt(yearStr);
	}
	
	public static Integer convertToMonth(Date date) {
		if(date == null)
			return null;
		String monthStr = convertToStr(date, "MM");
		return Integer.parseInt(monthStr);
	}
	
	public static Integer convertToDay(Date date) {
		if(date == null)
			return null;
		String dayStr = convertToStr(date, "dd");
		return Integer.parseInt(dayStr);
	}
	
}
