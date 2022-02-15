package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestConversion {
	@NotBlank(message = "Source currency should not be null")
	@Size(min = 3, max = 3, message = "Currency Code should be ISO 4217 standarts")
	private String sourceCurrency;

	@Size(min = 3, max = 3, message = "Currency Code should be ISO 4217 standarts")
	@NotBlank(message = "Target currency should not be null")
	private String targetCurrency;

	@NotNull(message = "Amount should not be null")
	@DecimalMin(value = "0.01", message = "Amount should not be less than 0.01")
	private BigDecimal amount;

}
