package com.ms.exception.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.base.bean.BaseResponse;

//格式化返回客户端数据格式（json）
public class ResponseFormat {
	
	public static String retParam(int status, Object data) {
		// OutputJson json = new OutputJson(status, messageMap.get(String
		// .valueOf(status)), data);
		// return json.toString();
		return null;
	}

	public static void sendSystemExceptionResponseInfo(HttpServletResponse response, 
			String code, String msg, String description) {
		sendExceptionResponseInfo(response, code, msg, description, ExceptionType.SYSTEM_EXCEPTION.code());
	}

	public static void sendCustomExceptionResponseInfo(
			HttpServletResponse response, CustomException customException) {
		sendExceptionResponseInfo(response, 
				customException.getCode(),
				customException.getCustomMessage(),
				customException.getDescription(),
				ExceptionType.SERVICE_EXCEPTION.code());
	}

	public static void sendExceptionResponseInfo(HttpServletResponse response,
			String code, String message, String description, String type) {
		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setCode(code);
		baseResponse.setMessage(message);
		baseResponse.setDescription(description);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		ObjectMapper om = new ObjectMapper();
		JsonNode valueToTree = om.valueToTree(baseResponse);
		try {
			out = response.getWriter();
			out.append(valueToTree.toString());
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}