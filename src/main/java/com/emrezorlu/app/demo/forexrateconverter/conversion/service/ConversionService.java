package com.emrezorlu.app.demo.forexrateconverter.conversion.service;

import javax.validation.constraints.NotBlank;

import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionInterval;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseCoversionInterval;

public interface ConversionService {

	ResponseConversion convertCurrency(RequestConversion request);

	ResponseConversion getTransactionById(@NotBlank String transactionId);

	ResponseCoversionInterval getConversionListByInterval(RequestConversionInterval request);

}
