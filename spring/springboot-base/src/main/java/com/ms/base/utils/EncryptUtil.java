package com.ms.base.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtil {
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static String encrypt(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	public static boolean match(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}