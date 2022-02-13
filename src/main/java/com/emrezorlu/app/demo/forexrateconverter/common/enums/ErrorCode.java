package com.emrezorlu.app.demo.forexrateconverter.common.enums;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	UNHANDLED(1000), //
	INVALID_CURRENCY_SYMBOL(1001), //
	INVALID_EXCHANGE_PAIR(1002), //
	INVALID_ARGUMENTS(1003), //
	INVALID_TRANSACTION_ID(1004), //
	NO_TRANSACTIONS_FOR_PERIOD(1005), //
	FEIGN_CLIENT_ERROR(1101), //
	INTERNAL_ERROR(2000);

	private final int code;

	ErrorCode(int code) {
		this.code = code;
	}

	public static HttpStatus getHttpStatus(ErrorCode errorCode) {
		if (errorCode.code > 1000 && errorCode.code < 1100) {
			return HttpStatus.BAD_REQUEST;
		} else if (errorCode.code >= 2000 && errorCode.code < 3000) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} else if (errorCode.code >= 1100 && errorCode.code < 2000) {
			return HttpStatus.SEE_OTHER;
		} else {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}
