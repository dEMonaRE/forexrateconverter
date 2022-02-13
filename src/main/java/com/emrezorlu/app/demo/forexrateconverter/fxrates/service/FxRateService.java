package com.emrezorlu.app.demo.forexrateconverter.fxrates.service;

import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.ResponseRate;

public interface FxRateService {

	ResponseRate getAllExchangeRates(String sourceCurrency);

	ResponseRate getExchangeRate(String sourceCurrency, String targetCurrency);

	ResponseApiSymbols getSymbols();

}
