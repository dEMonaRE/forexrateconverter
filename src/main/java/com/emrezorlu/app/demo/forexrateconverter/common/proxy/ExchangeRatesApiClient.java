package com.emrezorlu.app.demo.forexrateconverter.common.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiLatestRates;

@FeignClient(name = "exchangeRatesApiClient", url = "${application.exchange-rates-api.url}")
@Component
public interface ExchangeRatesApiClient {

	@GetMapping("/latest")
	ResponseApiLatestRates getExchangeRates(@RequestParam("access_key") String accessKey);

	@GetMapping("/convert")
	ResponseApiLatestRates convertRates(@RequestParam("access_key") String accessKey);

}
