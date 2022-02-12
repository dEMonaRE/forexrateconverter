package com.emrezorlu.app.demo.forexrateconverter.common.external.datamodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExhangeRateInfo {
	private Long timestamp;
	private BigDecimal rate;
}
