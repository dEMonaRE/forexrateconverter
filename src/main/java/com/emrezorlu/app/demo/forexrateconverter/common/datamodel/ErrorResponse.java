package com.emrezorlu.app.demo.forexrateconverter.common.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
	private String errorMessage;
	private int errorCode;
}
