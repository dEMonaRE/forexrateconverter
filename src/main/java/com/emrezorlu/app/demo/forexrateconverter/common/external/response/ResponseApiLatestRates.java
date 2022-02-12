package com.emrezorlu.app.demo.forexrateconverter.common.external.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import lombok.Data;

@Data
public class ResponseApiLatestRates {
	private String base;
	private boolean success;
	private Long timestamp;
	private Date date;
	private HashMap<String, BigDecimal> rates;

	/***
	 * example response { "success": true, "timestamp": 1519296206, "base": "USD",
	 * "date": "2021-03-17", "rates": { "GBP": 0.72007, "JPY": 107.346001, "EUR":
	 * 0.813399, } }
	 * 
	 */
}
