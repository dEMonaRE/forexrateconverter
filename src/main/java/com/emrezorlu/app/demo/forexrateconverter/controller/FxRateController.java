package com.emrezorlu.app.demo.forexrateconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emrezorlu.app.demo.forexrateconverter.common.constants.ApiPath;
import com.emrezorlu.app.demo.forexrateconverter.common.datamodel.ResponseDTO;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.service.FxRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Book ")
@RestController
@RequestMapping(path = ApiPath.FX_RATE, produces = "application/json")
@AllArgsConstructor
public class FxRateController {
	@Autowired
	FxRateService fxRateService;

	@Operation(summary = "getAllExchangeRates")
	@GetMapping(path = "/getAll")
	public ResponseDTO<ResponseApiLatestRates> getAllExchangeRates(
			@RequestParam(value = "from", required = true) String from) {
		return new ResponseDTO<>(fxRateService.getExchangeRates(from), HttpStatus.OK);
	}
}