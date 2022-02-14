package com.emrezorlu.app.demo.forexrateconverter.conversion.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("ForexConversion")
public class ConversionEntity {

	@Id
	private String id;
	private String sourceCurrency;
	private String targetCurrency;
	private BigDecimal rate;
	private BigDecimal amount;
	private Long timestamp;
	private LocalDateTime localDateTime;
	private Date date;
}
