package com.emrezorlu.app.demo.forexrateconverter.fxrates.service;

import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;

public interface FxRateService {

	ResponseApiLatestRates getExchangeRates(String from);

}
