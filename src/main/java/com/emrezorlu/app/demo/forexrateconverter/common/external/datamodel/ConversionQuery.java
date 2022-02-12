package com.emrezorlu.app.demo.forexrateconverter.common.external.datamodel;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConversionQuery {
	private String from;
	private String to;
	private BigDecimal amount;
}
