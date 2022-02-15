package com.emrezorlu.app.demo.forexrateconverter.common.exception;

import com.emrezorlu.app.demo.forexrateconverter.common.enums.ErrorCode;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;

	public BusinessException(String message) {
		super(message);
		this.errorCode = ErrorCode.GENERIC;
	}

	public BusinessException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
