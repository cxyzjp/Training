/******************************************************************
 *
 *    Powered By .
 *
 *    Copyright (c) 
 *    http://
 *
 *    Package:     com.weshop.order.utils
 *
 *    Filename:    DateConvert.java
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
 *    Create at:   2016年11月4日 下午2:01:29
 *
 *    Revision:
 *
 *    2016年11月4日 下午2:01:29
 *        - first revision
 *
 *****************************************************************/
package com.ms.base.objectmapper;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @ClassName DateConvert
 * @Description TODO
 * @author lihongwang
 * @Date 2016年11月4日 下午2:01:29
 * @version 1.0.0
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator jsonGenerator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// 保留时间戳到10位
		 Long timeStamp = value.getTime();
		 if (timeStamp.toString().length() > 10) {
		 int len = timeStamp.toString().length() - 10;
		 
		 double pow = Math.pow(10, len);
		 timeStamp = (long) (timeStamp / pow);
		 }
		 jsonGenerator.writeString(String.valueOf(timeStamp));

		// 转换时间格式为 yyyy-MM-dd HH:mm:ss 可读格式
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// jsonGenerator.writeString(sdf.format(value));

		// jsonGenerator.writeString(String.valueOf(value.getTime()));
	}
}
