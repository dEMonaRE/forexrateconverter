package com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FxRate {
	private String id;
	private String base;
	private boolean success;
	private Long timestamp;
	private LocalDateTime localDateTime;
	private Date date;
	private HashMap<String, BigDecimal> rates;
}
