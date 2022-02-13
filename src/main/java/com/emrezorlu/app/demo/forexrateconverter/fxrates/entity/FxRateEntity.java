package com.emrezorlu.app.demo.forexrateconverter.fxrates.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

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
@Document("ForexRate")
public class FxRateEntity {

	@Id
	private String id;
	private String base;
	private boolean success;
	private Long timestamp;
	private LocalDateTime localDateTime;
	private Date date;
	private HashMap<String, BigDecimal> rates;
}
