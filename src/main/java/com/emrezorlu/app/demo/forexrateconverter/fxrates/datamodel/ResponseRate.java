package com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRate {
	private String base;
	private boolean success;
	private Long timestamp;
	private LocalDateTime localDateTime;
	private Date date;
	private HashMap<String, BigDecimal> rates;

	/***
	 * example response { "success": true, "timestamp": 1519296206, "base": "USD",
	 * "date": "2021-03-17", "rates": { "GBP": 0.72007, "JPY": 107.346001, "EUR":
	 * 0.813399, } }
	 * 
	 */
}
