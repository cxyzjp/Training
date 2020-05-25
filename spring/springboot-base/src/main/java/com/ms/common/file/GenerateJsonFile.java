/******************************************************************
 *
 *    Powered By .
 *
 *    Copyright (c) 
 *    http://
 *
 *    Package:     com.weshop.order.utils
 *
 *    Filename:    GenerateJsonFile.java
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
 *    Create at:   2017年1月10日 下午2:05:27
 *
 *    Revision:
 *
 *    2017年1月10日 下午2:05:27
 *        - first revision
 *
 *****************************************************************/
package com.ms.common.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName GenerateJsonFile
 * @Description TODO
 * @author lihongwang
 * @Date 2017年1月10日 下午2:05:27
 * @version 1.0.0
 */
public class GenerateJsonFile {
	public static void writeToJson(String filePath, String object)
			throws IOException {
		File file = new File(filePath);
		char[] stack = new char[1024];
		int top = -1;

		String string = object.toString();

		StringBuffer sb = new StringBuffer();
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if ('{' == c || '[' == c) {
				stack[++top] = c;
				sb.append("\n" + charArray[i] + "\n");
				for (int j = 0; j <= top; j++) {
					sb.append("\t");
				}
				continue;
			}
			if ((i + 1) <= (charArray.length - 1)) {
				char d = charArray[i + 1];
				if ('}' == d || ']' == d) {
					top--;
					sb.append(charArray[i] + "\n");
					for (int j = 0; j <= top; j++) {
						sb.append("\t");
					}
					continue;
				}
			}
			if (',' == c) {
				sb.append(charArray[i] + "");
				for (int j = 0; j <= top; j++) {
					sb.append("");
				}
				continue;
			}
			sb.append(c);
		}

		Writer write = new FileWriter(file);
		write.write(sb.toString());
		write.flush();
		write.close();
	}
}
