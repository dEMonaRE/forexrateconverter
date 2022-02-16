package com.emrezorlu.app.demo.forexrateconverter.conversion.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emrezorlu.app.demo.forexrateconverter.common.enums.Currency;
import com.emrezorlu.app.demo.forexrateconverter.common.enums.ErrorCode;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.BusinessException;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.common.proxy.ExchangeRatesApiClient;
import com.emrezorlu.app.demo.forexrateconverter.common.util.DateUtils;
import com.emrezorlu.app.demo.forexrateconverter.config.ExchangeRateApiProperties;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.Conversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionDate;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionInterval;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseCoversionList;
import com.emrezorlu.app.demo.forexrateconverter.conversion.entity.ConversionEntity;
import com.emrezorlu.app.demo.forexrateconverter.conversion.repository.ConversionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ConversionServiceImpl implements ConversionService {

	@Autowired
	ConversionRepository conversionRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ExchangeRatesApiClient exchangeRatesApiClient;
	@Autowired
	ExchangeRateApiProperties apiProperties;

	@Override
	@Transactional
	public ResponseConversion convertCurrency(RequestConversion request) {
		log.debug(request.toString());
		// Convert Api wont work on free subscription
		// Assumption : get latest rates and convert manually
		checkSourceSymbol(request.getSourceCurrency());
		// Assumption : external symbol service CACHED for extensive usage
		checkTargetSymbol(request.getTargetCurrency());

		ResponseApiLatestRates responseExchangeRates = exchangeRatesApiClient.getExchangeRate(apiProperties.getApiKey(),
				request.getSourceCurrency(), request.getTargetCurrency());

		if (responseExchangeRates == null || !responseExchangeRates.isSuccess()) {
			throw new BusinessException("External Api error on ", ErrorCode.FEIGN_CLIENT_ERROR);
		}
		BigDecimal rate = responseExchangeRates.getRates().entrySet().iterator().next().getValue();
		String targetCurrency = responseExchangeRates.getRates().entrySet().iterator().next().getKey();
		BigDecimal amount = request.getAmount().multiply(rate);

		ConversionEntity entity = ConversionEntity.builder().date(responseExchangeRates.getDate()).amount(amount)
				.rate(rate).sourceCurrency(responseExchangeRates.getBase()).targetCurrency(targetCurrency)
				.timestamp(responseExchangeRates.getTimestamp())
				.localDateTime(DateUtils.convertToLocalDateTime(responseExchangeRates.getTimestamp())).build();
		conversionRepository.save(entity);

		return ResponseConversion.builder().conversion(modelMapper.map(entity, Conversion.class)).build();
	}

	@Override
	public ResponseConversion getTransactionById(@NotBlank String transactionId) {

		Optional<ConversionEntity> optionalConversion = conversionRepository.findById(transactionId);

		if (optionalConversion.isPresent()) {
			return ResponseConversion.builder().conversion(modelMapper.map(optionalConversion.get(), Conversion.class))
					.build();
		}
		throw new BusinessException("No Data found by id : " + transactionId, ErrorCode.NO_TRANSACTION_BY_ID);
	}

	@Override
	public ResponseCoversionList getConversionListByInterval(RequestConversionInterval request) {
		log.debug(request.toString());

		Page<ConversionEntity> pageableConversionEntity = conversionRepository.findAllByLocalDateTimeBetween(
				request.getFrom(), request.getTo(),
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by("id")));

		if (pageableConversionEntity.isEmpty()) {
			throw new BusinessException("No transaction found for given date period : " + request.toString(),
					ErrorCode.NO_TRANSACTIONS_FOR_PERIOD);
		}
		Page<Conversion> conversionPage = mapEntityPageIntoDtoPage(pageableConversionEntity, Conversion.class);

		return ResponseCoversionList.builder().conversion(conversionPage.getContent()).build();
	}

	private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> modelClass) {
		return entities.map(objectEntity -> modelMapper.map(objectEntity, modelClass));
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

	@Override
	public ResponseCoversionList getConversionListByDate(RequestConversionDate request) {

		Page<ConversionEntity> pageableConversionEntity = conversionRepository.findAllByDate(
				DateUtils.shortDateFormatForQuery(request.getDate()),
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by("id")));

		if (pageableConversionEntity.isEmpty()) {
			throw new BusinessException("No transaction found for given date period : " + request.toString(),
					ErrorCode.NO_TRANSACTIONS_FOR_PERIOD);
		}
		Page<Conversion> conversionPage = mapEntityPageIntoDtoPage(pageableConversionEntity, Conversion.class);

		return ResponseCoversionList.builder().conversion(conversionPage.getContent()).build();
	}
}
