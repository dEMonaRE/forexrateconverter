package com.emrezorlu.app.demo.forexrateconverter.common.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ResponseError {
	private String errorMessage;
	private Integer errorCode;
}
