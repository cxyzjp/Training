package com.cxy.exception;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.cxy.base.BaseResponse;

/**
 * 异常增强，以JSON的形式返回给客服端
 * 异常增强类型：NullPointerException,RunTimeException,ClassCastException,
 * NoSuchMethodException,IOException,IndexOutOfBoundsException
 * 以及springmvc自定义异常等，如下： SpringMVC自定义异常对应的status code Exception HTTP Status Code
 * ConversionNotSupportedException 500 (Internal Server Error)
 * HttpMessageNotWritableException 500 (Internal Server Error)
 * HttpMediaTypeNotSupportedException 415 (Unsupported Media Type)
 * HttpMediaTypeNotAcceptableException 406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException 405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException 404 (Not Found) TypeMismatchException
 * 400 (Bad Request) HttpMessageNotReadableException 400 (Bad Request)
 * MissingServletRequestParameterException 400 (Bad Request)
 *
 */
@ControllerAdvice
public class CustomExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	// 运行时异常
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public BaseResponse<Object> runtimeExceptionHandler(HttpServletResponse response, RuntimeException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.RUNTIME_EXCEPTION.code(),
				BaseErrorCode.RUNTIME_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 空指针异常
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public BaseResponse<Object> nullPointerExceptionHandler(HttpServletResponse response, NullPointerException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.NULL_POINTER_EXCEPTION.code(),
				BaseErrorCode.NULL_POINTER_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 类型转换异常
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public BaseResponse<Object> classCastExceptionHandler(HttpServletResponse response, ClassCastException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.CLASS_CAST_EXCEPTION.code(),
				BaseErrorCode.CLASS_CAST_EXCEPTION.getDescription(), ex.getMessage());
	}

	// IO异常
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public BaseResponse<Object> iOExceptionHandler(HttpServletResponse response, IOException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.IO_EXCEPTION.code(),
				BaseErrorCode.IO_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 未知方法异常
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public BaseResponse<Object> noSuchMethodExceptionHandler(HttpServletResponse response, NoSuchMethodException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.NOT_SUCH_METHOD_EXCEPTION.code(),
				BaseErrorCode.NOT_SUCH_METHOD_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 数组越界异常
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseBody
	public BaseResponse<Object> indexOutOfBoundsExceptionHandler(HttpServletResponse response,
			IndexOutOfBoundsException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.INDEX_OUT_OF_BOUNDS_EXCEPTION.code(),
				BaseErrorCode.INDEX_OUT_OF_BOUNDS_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 400错误
	@ExceptionHandler({ HttpMessageNotReadableException.class, TypeMismatchException.class,
			MissingServletRequestParameterException.class })
	@ResponseBody
	public BaseResponse<Object> requestNotReadable(HttpServletResponse response, HttpMessageNotReadableException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.BAD_REQUEST.code(),
				BaseErrorCode.BAD_REQUEST.getDescription(), ex.getMessage());
	}

	// 404
	@ExceptionHandler({ NoHandlerFoundException.class })
	@ResponseBody
	public BaseResponse<Object> noHandlerFound(HttpServletResponse response, NoHandlerFoundException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.NO_FOUND_EXCEPTION.code(),
				BaseErrorCode.NO_FOUND_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 405错误
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	public BaseResponse<Object> request405(HttpServletResponse response, HttpRequestMethodNotSupportedException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.METHOD_NOT_SUPPORTED_EXCEPTION.code(),
				BaseErrorCode.METHOD_NOT_SUPPORTED_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 406错误
	@ExceptionHandler({ HttpMediaTypeNotAcceptableException.class })
	@ResponseBody
	public BaseResponse<Object> request406(HttpServletResponse response, HttpMediaTypeNotAcceptableException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.NOT_ACCEPTABLE_EXCEPTION.code(),
				BaseErrorCode.NOT_ACCEPTABLE_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 415错误
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	@ResponseBody
	public BaseResponse<Object> request415(HttpServletResponse response, HttpMediaTypeNotSupportedException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.NOT_SUPPORTED_EXCEPTION.code(),
				BaseErrorCode.NOT_SUPPORTED_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 500错误
	@ExceptionHandler({ ConversionNotSupportedException.class, HttpMessageNotWritableException.class })
	@ResponseBody
	public BaseResponse<Object> server500(HttpServletResponse response, RuntimeException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.SERVER_EXCEPTION.code(),
				BaseErrorCode.SERVER_EXCEPTION.getDescription(), ex.getMessage());
	}

	// OutOfMemoryError
	@ExceptionHandler({ OutOfMemoryError.class })
	@ResponseBody
	public BaseResponse<Object> serverOutOfMemoryError(HttpServletResponse response, OutOfMemoryError ex) {
		logger.error(ex.toString(), ex);
		return sendSystemException(response, BaseErrorCode.OUT_OF_MEMORY_EXCEPTION.code(),
				BaseErrorCode.OUT_OF_MEMORY_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 上传文件超过设定大小
	@ExceptionHandler({ MaxUploadSizeExceededException.class })
	@ResponseBody
	public BaseResponse<Object> serverUploadFileSizeIsGreaterThanSettingSize(HttpServletResponse response,
			MaxUploadSizeExceededException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.MAX_UPLOAD_SIZE_EXCEPTION.code(),
				BaseErrorCode.MAX_UPLOAD_SIZE_EXCEPTION.getDescription(), ex.getMessage());
	}

	// MultipartException
	@ExceptionHandler({ MultipartException.class })
	@ResponseBody
	public BaseResponse<Object> multipartException(HttpServletResponse response, MultipartException ex) {
		stackTrace(ex);
		return sendSystemException(response, BaseErrorCode.MULTIPART_EXCEPTION.code(),
				BaseErrorCode.MULTIPART_EXCEPTION.getDescription(), ex.getMessage());
	}

	// 参数校验
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseBody
	public BaseResponse<Object> bindExceptionHandler(HttpServletResponse response, MethodArgumentNotValidException ex) {
		stackTrace(ex);
		
		List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
		StringBuffer errorMsg = new StringBuffer();
		for (FieldError error : errorList) {
			errorMsg.append(error.getField());
			errorMsg.append(error.getDefaultMessage());
			errorMsg.append("\n");
		}
		return sendSystemException(response, BaseErrorCode.PARAMETERS_ERROR.code(),
				BaseErrorCode.PARAMETERS_ERROR.getDescription(), errorMsg.toString());
	}

	// service exception, CustomException
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public BaseResponse<Object> customException(HttpServletResponse response, CustomException customException) {
		stackTrace(customException);
		recordErrorLog(customException.getCode(), customException.getCustomMessage(), customException.getDescription());

		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setCode(customException.getCode());
		baseResponse.setMessage(customException.getCustomMessage());
		baseResponse.setDescription(customException.getDescription());
		return baseResponse;
	}

	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public BaseResponse<Object> apiException(HttpServletResponse response, ServiceException serviceException) {
		stackTrace(serviceException);
		recordErrorLog(serviceException.getCode(), serviceException.getCustomMessage(),
				serviceException.getDescription());

		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setCode(serviceException.getCode());
		baseResponse.setMessage(serviceException.getCustomMessage());
		baseResponse.setDescription(serviceException.getDescription());
		return baseResponse;
	}

	@ExceptionHandler(ApiException.class)
	@ResponseBody
	public BaseResponse<Object> apiException(HttpServletResponse response, ApiException apiException) {
		stackTrace(apiException);
		recordErrorLog(apiException.getCode(), apiException.getCustomMessage(), apiException.getDescription());

		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setCode(apiException.getCode());
		baseResponse.setMessage(apiException.getCustomMessage());
		baseResponse.setDescription(apiException.getDescription());
		return baseResponse;
	}

	@ExceptionHandler(WebException.class)
	public String apiException(HttpServletResponse response, WebException webException) {
		stackTrace(webException);
		recordErrorLog(webException.getCode(), webException.getCustomMessage(), webException.getDescription());
		return "redirect:" + webException.getRedirectUrl();
	}
	
	private BaseResponse<Object> sendSystemException(HttpServletResponse response, String code, String msg,
			String desc) {
		recordErrorLog(code, msg, desc);

		BaseResponse<Object> baseResponse = new BaseResponse<Object>();
		baseResponse.setCode(code);
		baseResponse.setMessage(msg);
		baseResponse.setDescription(desc);
		return baseResponse;
	}

	public void recordErrorLog(String code, String msg, String desc) {
		logger.error("----------------------------");
		logger.error(code);
		logger.error(msg);
		logger.error(desc);
		logger.error("----------------------------");
	}
	
	public void stackTrace(Exception ex) {
		logger.error(ex.toString(), ex);
	}
}