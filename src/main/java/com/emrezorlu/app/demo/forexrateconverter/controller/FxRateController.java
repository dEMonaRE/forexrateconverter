package com.emrezorlu.app.demo.forexrateconverter.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emrezorlu.app.demo.forexrateconverter.common.constants.ApiPath;
import com.emrezorlu.app.demo.forexrateconverter.common.datamodel.ResponseDTO;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.ResponseRate;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.service.FxRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Foreign Exchange Rate ")
@RestController
@RequestMapping(path = ApiPath.FX_RATE, produces = "application/json")
@AllArgsConstructor
public class FxRateController {
	@Autowired
	FxRateService fxRateService;

	@Operation(summary = "getAllExchangeRates")
	@GetMapping(path = "/getAllRates")
	public ResponseDTO<ResponseRate> getAllRates(
			@RequestParam(value = "sourceCurrency", required = true) @NotBlank(message = "Source currency should not be null") @Size(min = 3, max = 3, message = "Currency Code should be ISO 4217 standarts") String sourceCurrency) {
		return new ResponseDTO<>(fxRateService.getAllExchangeRates(sourceCurrency), HttpStatus.OK);
	}

	@Operation(summary = "getRate")
	@GetMapping(path = "/getRate")
	public ResponseDTO<ResponseRate> getRate(
			@RequestParam(value = "sourceCurrency", required = true) @NotBlank(message = "Source currency should not be null") @Size(min = 3, max = 3, message = "Currency Code should be ISO 4217 standarts") String sourceCurrency,
			@RequestParam(value = "targetCurrency", required = true) @NotBlank(message = "Target currency should not be null") @Size(min = 3, max = 3, message = "Currency Code should be ISO 4217 standarts") String targetCurrency) {
		return new ResponseDTO<>(fxRateService.getExchangeRate(sourceCurrency, targetCurrency), HttpStatus.OK);
	}

	@Operation(summary = "getSymbols")
	@GetMapping(path = "/getSymbols")
	public ResponseDTO<ResponseApiSymbols> getSymbols() {
		return new ResponseDTO<>(fxRateService.getSymbols(), HttpStatus.OK);
	}
}