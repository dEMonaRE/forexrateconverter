package com.emrezorlu.app.demo.forexrateconverter.fxrates.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrezorlu.app.demo.forexrateconverter.common.enums.Currency;
import com.emrezorlu.app.demo.forexrateconverter.common.enums.ErrorCode;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.BusinessException;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.common.proxy.ExchangeRatesApiClient;
import com.emrezorlu.app.demo.forexrateconverter.common.util.DateUtils;
import com.emrezorlu.app.demo.forexrateconverter.config.ExchangeRateApiProperties;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.FxRate;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.ResponseRate;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.entity.FxRateEntity;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.repository.FxRateRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class FxRateServiceImpl implements FxRateService {

	@Autowired
	FxRateRepository fxRateRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ExchangeRatesApiClient exchangeRatesApiClient;
	@Autowired
	ExchangeRateApiProperties apiProperties;

	@Override
	public ResponseRate getAllExchangeRates(String sourceCurrency) {
		checkSourceSymbol(sourceCurrency);
		ResponseApiLatestRates exchangeRates = exchangeRatesApiClient.getAllExchangeRates(apiProperties.getApiKey(),
				sourceCurrency);
		ResponseRate responseRate = modelMapper.map(exchangeRates, ResponseRate.class);
		responseRate.setLocalDateTime(DateUtils.convertToLocalDateTime(responseRate.getTimestamp()));
		saveRates(modelMapper.map(responseRate, FxRate.class));
		return responseRate;

	}

	@Override
	public ResponseRate getExchangeRate(String sourceCurrency, String targetCurrency) {
		checkSourceSymbol(sourceCurrency);
		checkTargetSymbol(targetCurrency);
		ResponseApiLatestRates exchangeRates = exchangeRatesApiClient.getExchangeRate(apiProperties.getApiKey(),
				sourceCurrency, targetCurrency);
		ResponseRate responseRate = modelMapper.map(exchangeRates, ResponseRate.class);
		responseRate.setLocalDateTime(DateUtils.convertToLocalDateTime(responseRate.getTimestamp()));
		saveRates(modelMapper.map(responseRate, FxRate.class));
		return responseRate;

	}

	private void saveRates(FxRate fxRate) {
		log.debug(fxRate.toString());
		fxRateRepository.save(modelMapper.map(fxRate, FxRateEntity.class));
	}

	@Override
	public ResponseApiSymbols getSymbols() {
		return exchangeRatesApiClient.getSymbols(apiProperties.getApiKey());
	}

	private void checkSourceSymbol(String currency) {
		// Assumption : get latest rates and convert manually
		if (!Currency.EUR.name().equals(currency)) {
			throw new BusinessException("EUR should be used for Source Currency", ErrorCode.INVALID_CURRENCY_SYMBOL);
		}
	}

	private void checkTargetSymbol(String currency) {
		// Assumption : external symbol service CACHED for extensive usage
		ResponseApiSymbols responseSymbols = exchangeRatesApiClient.getSymbols(apiProperties.getApiKey());

		boolean isTargetCurrencyExists = responseSymbols.getSymbols().containsKey(currency);

		if (!isTargetCurrencyExists) {
			throw new BusinessException("Target Currency not exists", ErrorCode.INVALID_CURRENCY_SYMBOL);
		}

	}
}
