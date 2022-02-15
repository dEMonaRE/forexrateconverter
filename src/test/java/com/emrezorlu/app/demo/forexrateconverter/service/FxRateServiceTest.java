package com.emrezorlu.app.demo.forexrateconverter.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import com.emrezorlu.app.demo.forexrateconverter.common.proxy.ExchangeRatesApiClient;
import com.emrezorlu.app.demo.forexrateconverter.config.ExchangeRateApiProperties;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.repository.FxRateRepository;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.service.FxRateService;
import com.emrezorlu.app.demo.forexrateconverter.util.AbstractServiceTest;

public class FxRateServiceTest extends AbstractServiceTest {
	@Mock
	private FxRateRepository fxRateRepository;
	@Spy
	private ModelMapper modelMapper;
	@Mock
	private ExchangeRatesApiClient exchangeRatesApiClient;
	@Mock
	private ExchangeRateApiProperties apiProperties;

	@InjectMocks
	private FxRateService fxRateService;

}
