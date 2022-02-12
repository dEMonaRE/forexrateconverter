package com.emrezorlu.app.demo.forexrateconverter.common.external.response;

import java.math.BigDecimal;
import java.util.Date;

import com.emrezorlu.app.demo.forexrateconverter.common.external.datamodel.ConversionQuery;
import com.emrezorlu.app.demo.forexrateconverter.common.external.datamodel.ExhangeRateInfo;

import lombok.Data;

@Data
public class ResponseApiConvertRate {

	private boolean success;
	private ConversionQuery query;
	private ExhangeRateInfo info;
	private boolean historical;
	private Date date;
	private BigDecimal result;

	/***
	 * example:
	 * 
	 * "success": true, "query": { "from": "GBP", "to": "JPY", "amount": 25 },
	 * "info": { "timestamp": 1519328414, "rate": 148.972231 }, "historical": ""
	 * "date": "2018-02-22" "result": 3724.305775
	 * 
	 */
}
