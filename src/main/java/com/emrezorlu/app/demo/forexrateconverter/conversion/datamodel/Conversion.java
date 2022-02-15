package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversion {
	private String id;
	private String sourceCurrency;
	private String targetCurrency;
	private BigDecimal rate;
	private BigDecimal amount;
	private Long timestamp;
	private LocalDateTime localDateTime;
	@DateTimeFormat(iso = ISO.DATE)
	private Date date;
}
