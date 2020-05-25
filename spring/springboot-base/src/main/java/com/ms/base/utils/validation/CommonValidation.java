package com.ms.base.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidation {
	
	private static String REGEX_ALPHABET_LINE = "^[a-zA-Z0-9_-]+$";
	private static String REGEX_ALPHABET = "^[a-zA-Z]+$";
	
	private static Pattern pattern;
	
	// 验证包含字母，下划线，中横线的字符串
	public static boolean withAlphabetAndLine(String validateStr) {
		pattern = Pattern.compile(REGEX_ALPHABET_LINE, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(validateStr);
		return matcher.matches();
	}

	// 验证包含字母的字符串
	public static boolean withAlphabet(String validateStr) {
		pattern = Pattern.compile(REGEX_ALPHABET, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(validateStr);
		return matcher.matches();
	}
	
	// 檢查字符長度
	public static boolean lowerThanLen(String validStr, Integer len) {
		return (validStr.length() <= len);
	}

}
