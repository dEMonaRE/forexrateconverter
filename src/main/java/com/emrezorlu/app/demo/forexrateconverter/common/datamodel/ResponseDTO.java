package com.emrezorlu.app.demo.forexrateconverter.common.datamodel;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
	private T data;
	private HttpStatus httpStatus;

	// can be added error message or transaction status
}
