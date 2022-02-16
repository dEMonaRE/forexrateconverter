package com.emrezorlu.app.demo.forexrateconverter.service;

import static com.emrezorlu.app.demo.forexrateconverter.util.RandomGenerator.randomBigdecimal;
import static com.emrezorlu.app.demo.forexrateconverter.util.RandomGenerator.randomString;
import static com.emrezorlu.app.demo.forexrateconverter.util.TestUtils.listOf;
import static com.emrezorlu.app.demo.forexrateconverter.util.TestUtils.objectOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.emrezorlu.app.demo.forexrateconverter.common.enums.Currency;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.BusinessException;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.common.proxy.ExchangeRatesApiClient;
import com.emrezorlu.app.demo.forexrateconverter.config.ExchangeRateApiProperties;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.Conversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionDate;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionInterval;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseCoversionList;
import com.emrezorlu.app.demo.forexrateconverter.conversion.entity.ConversionEntity;
import com.emrezorlu.app.demo.forexrateconverter.conversion.repository.ConversionRepository;
import com.emrezorlu.app.demo.forexrateconverter.conversion.service.ConversionServiceImpl;
import com.emrezorlu.app.demo.forexrateconverter.util.AbstractServiceTest;

public class ConversionServiceTest extends AbstractServiceTest {
	@Mock
	private ConversionRepository conversionRepository;
	@Mock
	private ExchangeRatesApiClient exchangeRatesApiClient;
	@Mock
	private ExchangeRateApiProperties apiProperties;
	@Spy
	private ModelMapper modelMapper;

	@InjectMocks
	private ConversionServiceImpl conversionService;

	@Test
	public void givenRequest_whenConvertCurrency_thenReturnData() {

		when(apiProperties.getApiKey()).thenReturn(randomString());

		ResponseApiSymbols responseApiSymbols = objectOf(ResponseApiSymbols.class);
		HashMap<String, String> symbols = new HashMap<>();
		symbols.put(Currency.TRY.name(), "Turkish Lira");
		responseApiSymbols.setSymbols(symbols);

		when(exchangeRatesApiClient.getSymbols(anyString())).thenReturn(responseApiSymbols);

		when(exchangeRatesApiClient.getExchangeRate(anyString(), anyString(), anyString()))
				.thenReturn(objectOf(ResponseApiLatestRates.class));

		when(conversionRepository.save(any(ConversionEntity.class))).thenAnswer((i) -> i.getArgument(0));

		RequestConversion request = RequestConversion.builder().sourceCurrency(Currency.EUR.name())
				.targetCurrency(Currency.TRY.name()).amount(randomBigdecimal()).build();

		ResponseConversion convertCurrency = conversionService.convertCurrency(request);

		assertNotNull(convertCurrency);
		verify(conversionRepository, times(1)).save(any(ConversionEntity.class));
	}

	@Test
	public void givenId_whenGetTransaction_thenReturnData() {

		Optional<ConversionEntity> entity = Optional.of(objectOf(ConversionEntity.class));

		when(conversionRepository.findById(anyString())).thenReturn(entity);

		ResponseConversion response = conversionService.getTransactionById(anyString());

		assertNotNull(response);
		verify(conversionRepository, times(1)).findById(anyString());
	}

	@Test
	public void givenId_whenGetTransaction_thenNoDataFound() {

		Optional<ConversionEntity> entity = Optional.empty();

		when(conversionRepository.findById(anyString())).thenReturn(entity);

		String transcationId = "1";

		Exception exception = assertThrows(BusinessException.class,
				() -> conversionService.getTransactionById(transcationId));

		assertEquals("No Data found by id : " + transcationId, exception.getMessage());
	}

	@Test
	public void givenDates_whenGetConversionListByInterval_thenReturnResponse() {
		Page<ConversionEntity> page = new PageImpl<ConversionEntity>(listOf(ConversionEntity.class),
				PageRequest.of(1, 1), 0);

		given(conversionRepository.findAllByLocalDateTimeBetween(any(), any(), any(PageRequest.class)))
				.willReturn(page);

		RequestConversionInterval request = RequestConversionInterval.builderRequestInterval().from(LocalDateTime.MIN)
				.to(LocalDateTime.MAX).pageNumber(1).pageSize(1).build();

		ResponseCoversionList response = conversionService.getConversionListByInterval(request);

		assertNotNull(response);

		Conversion conversion = response.getConversion().get(0);

		assertNotNull(conversion);

	}

	@Test
	public void givenDates_whenGetConversionListByDate_thenReturnResponse() {
		Page<ConversionEntity> page = new PageImpl<ConversionEntity>(listOf(ConversionEntity.class),
				PageRequest.of(1, 1), 0);

		given(conversionRepository.findAllByDate(any(), any(PageRequest.class))).willReturn(page);

		RequestConversionDate request = RequestConversionDate.builderRequestDate().date(new Date()).pageNumber(1)
				.pageSize(1).build();

		ResponseCoversionList response = conversionService.getConversionListByDate(request);

		assertNotNull(response);

		Conversion conversion = response.getConversion().get(0);

		assertNotNull(conversion);

	}
}
