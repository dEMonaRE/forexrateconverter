package com.emrezorlu.app.demo.forexrateconverter.fxrates.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;
import com.emrezorlu.app.demo.forexrateconverter.common.proxy.ExchangeRatesApiClient;
import com.emrezorlu.app.demo.forexrateconverter.config.ExchangeRateApiProperties;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.FxRate;
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
	public ResponseApiLatestRates getExchangeRates(String from) {
		ResponseApiLatestRates exchangeRates = exchangeRatesApiClient.getExchangeRates(apiProperties.getApiKey());
		log.debug(apiProperties.toString());
		log.info(exchangeRates.toString());
		saveRates(modelMapper.map(exchangeRates, FxRate.class));
		return exchangeRates;

	}

	private void saveRates(FxRate fxRate) {
		fxRateRepository.save(modelMapper.map(fxRate, FxRateEntity.class));
	}
}
