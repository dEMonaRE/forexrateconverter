package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestConversion {
	@NotBlank(message = "source currency should not be null")
	private String sourceCurrency;
	@NotBlank(message = "target currency should not be null")
	private String targetCurrency;
	@NotNull
	@Min(value = 0, message = "amount should not be less than 0")
	private BigDecimal amount;

}
