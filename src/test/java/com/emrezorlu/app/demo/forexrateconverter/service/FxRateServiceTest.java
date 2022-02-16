package com.emrezorlu.app.demo.forexrateconverter.service;

import static com.emrezorlu.app.demo.forexrateconverter.util.RandomGenerator.randomString;
import static com.emrezorlu.app.demo.forexrateconverter.util.TestUtils.objectOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import com.emrezorlu.app.demo.forexrateconverter.common.enums.Currency;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.common.proxy.ExchangeRatesApiClient;
import com.emrezorlu.app.demo.forexrateconverter.config.ExchangeRateApiProperties;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.ResponseRate;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.entity.FxRateEntity;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.repository.FxRateRepository;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.service.FxRateServiceImpl;
import com.emrezorlu.app.demo.forexrateconverter.util.AbstractServiceTest;

public class FxRateServiceTest extends AbstractServiceTest {
	@Mock
	private FxRateRepository fxRateRepository;
	@Mock
	private ExchangeRatesApiClient exchangeRatesApiClient;
	@Mock
	private ExchangeRateApiProperties apiProperties;
	@Spy
	private ModelMapper mapper;

	@InjectMocks
	private FxRateServiceImpl fxRateService;

	/** works as well for custom input for mocking **/
//	private FxRateService fxRateService;
//
//	@BeforeEach
//	public void setup() {
//		fxRateService = new FxRateServiceImpl(fxRateRepository, new ModelMapper(), exchangeRatesApiClient,
//				apiProperties);
//	}

	@Test
	public void whenGetAllExchangeRates_thenReturnData() {

		when(apiProperties.getApiKey()).thenReturn(randomString());

		when(exchangeRatesApiClient.getAllExchangeRates(anyString(), anyString()))
				.thenReturn(objectOf(ResponseApiLatestRates.class));

		when(fxRateRepository.save(any(FxRateEntity.class))).thenAnswer((i) -> i.getArgument(0));

		ResponseRate allExchangeRates = fxRateService.getAllExchangeRates(Currency.EUR.name());

		assertNotNull(allExchangeRates);
		verify(fxRateRepository, times(1)).save(any(FxRateEntity.class));
	}

	@Test
	public void whenGetExchangeRate_thenReturnData() {
		ResponseApiLatestRates responseApi = objectOf(ResponseApiLatestRates.class);

		when(apiProperties.getApiKey()).thenReturn(randomString());

		ResponseApiSymbols responseApiSymbols = objectOf(ResponseApiSymbols.class);
		HashMap<String, String> symbols = new HashMap<>();
		symbols.put(Currency.TRY.name(), "Turkish Lira");
		responseApiSymbols.setSymbols(symbols);

		when(exchangeRatesApiClient.getSymbols(anyString())).thenReturn(responseApiSymbols);

		when(exchangeRatesApiClient.getExchangeRate(anyString(), anyString(), anyString())).thenReturn(responseApi);

		when(fxRateRepository.save(any(FxRateEntity.class))).thenAnswer((i) -> i.getArgument(0));

		ResponseRate exchangeRate = fxRateService.getExchangeRate(Currency.EUR.name(), Currency.TRY.name());

		assertNotNull(exchangeRate);
		verify(fxRateRepository, times(1)).save(any(FxRateEntity.class));
	}

	@Test
	public void whenGetSymbols_thenReturnData() {

		when(apiProperties.getApiKey()).thenReturn(randomString());

		ResponseApiSymbols responseApiSymbols = objectOf(ResponseApiSymbols.class);

		when(exchangeRatesApiClient.getSymbols(anyString())).thenReturn(responseApiSymbols);

		ResponseApiSymbols symbols = fxRateService.getSymbols();

		assertNotNull(symbols);
	}

}
