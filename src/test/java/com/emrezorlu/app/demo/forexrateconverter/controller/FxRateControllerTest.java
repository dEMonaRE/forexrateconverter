package com.emrezorlu.app.demo.forexrateconverter.controller;

import static com.emrezorlu.app.demo.forexrateconverter.util.TestUtils.objectOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emrezorlu.app.demo.forexrateconverter.common.constants.ApiPath;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.ControllerExceptionHandler;
import com.emrezorlu.app.demo.forexrateconverter.common.external.response.ResponseApiSymbols;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.datamodel.ResponseRate;
import com.emrezorlu.app.demo.forexrateconverter.fxrates.service.FxRateService;
import com.emrezorlu.app.demo.forexrateconverter.util.AbstractServiceTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class FxRateControllerTest extends AbstractServiceTest {
	@InjectMocks
	private FxRateController controller;

	@Mock
	private FxRateService fxRateService;

	private ObjectMapper mapper;
	private ObjectWriter ow;
	private MockMvc mockMvc;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setControllerAdvice(new ControllerExceptionHandler()).build();

		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

		ow = mapper.writer().withDefaultPrettyPrinter();
	}

	@Test
	public void whenGetAllExchangeRates_thenReturnData() throws Exception {
		when(fxRateService.getAllExchangeRates(anyString())).thenReturn(objectOf(ResponseRate.class));
		MvcResult andExpect = mockMvc
				.perform(get(ApiPath.FX_RATE + "/getAllRates?sourceCurrency=A").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}

	@Test
	public void whenGetRate_thenReturnData() throws Exception {
		when(fxRateService.getExchangeRate(anyString(), anyString())).thenReturn(objectOf(ResponseRate.class));
		MvcResult andExpect = mockMvc.perform(
				get(ApiPath.FX_RATE + "/getRate?sourceCurrency=A&targetCurrency=A").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}

	@Test
	public void whenGetSymbols_thenReturnData() throws Exception {
		when(fxRateService.getSymbols()).thenReturn(objectOf(ResponseApiSymbols.class));
		MvcResult andExpect = mockMvc.perform(get(ApiPath.FX_RATE + "/getSymbols").contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(andExpect);
	}
}
