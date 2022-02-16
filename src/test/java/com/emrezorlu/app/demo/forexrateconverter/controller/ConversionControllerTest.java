package com.emrezorlu.app.demo.forexrateconverter.controller;

import static com.emrezorlu.app.demo.forexrateconverter.util.TestUtils.objectOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.emrezorlu.app.demo.forexrateconverter.common.constants.ApiPath;
import com.emrezorlu.app.demo.forexrateconverter.common.enums.Currency;
import com.emrezorlu.app.demo.forexrateconverter.common.exception.ControllerExceptionHandler;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.RequestConversionDate;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseConversion;
import com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel.ResponseCoversionList;
import com.emrezorlu.app.demo.forexrateconverter.conversion.service.ConversionService;
import com.emrezorlu.app.demo.forexrateconverter.util.AbstractServiceTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ConversionControllerTest extends AbstractServiceTest {

	@InjectMocks
	private ConversionController controller;

	@Mock
	private ConversionService conversionService;

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
	public void whenGetTransactionById_thenReturnData() throws Exception {
		when(conversionService.getTransactionById(anyString())).thenReturn(objectOf(ResponseConversion.class));
		ResultActions andExpect = mockMvc
				.perform(get(ApiPath.CONVERSION + "/getTransactionById?transactionId=A")
						.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().string(containsString("\"amount\":10")));

		assertNotNull(andExpect);
	}

	@Test
	public void whenConvertCurrency_thenReturnBadRequest() throws Exception {
		String requestJson = ow.writeValueAsString(objectOf(RequestConversion.class));
		when(conversionService.convertCurrency(any(RequestConversion.class)))
				.thenReturn(objectOf(ResponseConversion.class));
		mockMvc.perform(
				post(ApiPath.CONVERSION + "/convertCurrency").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().is4xxClientError());

	}

	@Test
	public void whenConvertCurrency_thenReturnData() throws Exception {

		RequestConversion request = objectOf(RequestConversion.class);
		request.setSourceCurrency(Currency.EUR.name());
		request.setTargetCurrency(Currency.TRY.name());
		String requestJson = ow.writeValueAsString(request);

		when(conversionService.convertCurrency(any(RequestConversion.class)))
				.thenReturn(objectOf(ResponseConversion.class));
		ResultActions andExpect = mockMvc.perform(
				post(ApiPath.CONVERSION + "/convertCurrency").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isOk());

		assertNotNull(andExpect);
	}

	@Test
	public void whenGetConversionListByDate_thenReturnData() throws Exception {
		String requestJson = ow.writeValueAsString(objectOf(RequestConversionDate.class));
		when(conversionService.getConversionListByDate(any(RequestConversionDate.class)))
				.thenReturn(objectOf(ResponseCoversionList.class));
		ResultActions andExpect = mockMvc.perform(post(ApiPath.CONVERSION + "/getConversionListByDate")
				.contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk());

		assertNotNull(andExpect);
	}

}
