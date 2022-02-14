package com.emrezorlu.app.demo.forexrateconverter.common.exception;

import java.util.Optional;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.emrezorlu.app.demo.forexrateconverter.common.datamodel.ResponseError;
import com.emrezorlu.app.demo.forexrateconverter.common.enums.ErrorCode;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidationErrorHandler {

	@ExceptionHandler(BindException.class)
	public final ResponseEntity<ResponseError> handleBindException(BindException ex) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ResponseError response = ResponseError.builder().errorCode(ErrorCode.INVALID_ARGUMENTS.getCode())
				.errorMessage(getMessages(ex.getBindingResult())).build();
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ResponseError> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ResponseError response = ResponseError.builder().errorCode(ErrorCode.INVALID_ARGUMENTS.getCode())
				.errorMessage(getMessages(ex.getBindingResult())).build();
		return new ResponseEntity<>(response, httpStatus);
	}

	private String getMessages(BindingResult bindingResult) {
		return Optional.ofNullable(bindingResult.getFieldError()).map(DefaultMessageSourceResolvable::getDefaultMessage)
				.orElse("");
	}
}
