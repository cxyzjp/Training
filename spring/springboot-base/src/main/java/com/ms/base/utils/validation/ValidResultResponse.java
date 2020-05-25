package com.ms.base.utils.validation;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ms.base.bean.BaseResponse;
import com.ms.exception.base.BaseErrorCode;

public class ValidResultResponse {
	public static void processValidResult(BindingResult result,
			BaseResponse<Object> baseResponse) {
		List<FieldError> errorList = result.getFieldErrors();
		StringBuffer errorMsg = new StringBuffer();
		for (FieldError error : errorList) {
			errorMsg.append(error.getField());
			errorMsg.append(error.getDefaultMessage());
			errorMsg.append("\n");
		}
		baseResponse.setCode(BaseErrorCode.PARAMETERS_ERROR.code());
		baseResponse.setMessage(errorMsg.toString());
	}

}
