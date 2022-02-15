package com.emrezorlu.app.demo.forexrateconverter.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emrezorlu.app.demo.forexrateconverter.common.constants.ApiPath;
import com.emrezorlu.app.demo.forexrateconverter.common.datamodel.ResponseDTO;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.BusinessException;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionDate;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionInterval;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseCoversionList;
import com.emrezorlu.app.demo.forexrateconverter.conversion.service.ConversionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Currency Conversion")
@RestController
@RequestMapping(path = ApiPath.CONVERSION, produces = "application/json")
@AllArgsConstructor
public class ConversionController {
	@Autowired
	ConversionService conversionService;

	@Operation(summary = "converts currency source ccy amount to target ccy ")
	@PostMapping("/convertCurrency")
	public ResponseDTO<ResponseConversion> convertCurrency(@RequestBody @Valid RequestConversion request)
			throws BusinessException {
		ResponseConversion response = conversionService.convertCurrency(request);
		return new ResponseDTO<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "getTransactionById")
	@GetMapping(path = "/getTransactionById")
	public ResponseDTO<ResponseConversion> getConversionTransaction(
			@RequestParam(value = "transactionId", required = true) @NotBlank(message = "Transaction Id should not be null") String transactionId) {
		return new ResponseDTO<>(conversionService.getTransactionById(transactionId), HttpStatus.OK);
	}

	@Operation(summary = "get conversion list by paging & interval format: 2021-12-21T01:00:00.000-01:00 ")
	@PostMapping(path = "/getConversionListByInterval")
	public ResponseDTO<ResponseCoversionList> getConversionListByInterval(
			@RequestBody @Valid RequestConversionInterval request) {
		return new ResponseDTO<>(conversionService.getConversionListByInterval(request), HttpStatus.OK);

	}

	@Operation(summary = "get conversion list by paging & Date format: 2022-02-14 or 2022-02-14T00:00:00.000+00:00 both accepted")
	@PostMapping(path = "/getConversionListByDate")
	public ResponseDTO<ResponseCoversionList> getConversionListByInterval(
			@RequestBody @Valid RequestConversionDate request) {
		return new ResponseDTO<>(conversionService.getConversionListByDate(request), HttpStatus.OK);

	}

}
