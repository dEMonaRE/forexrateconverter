package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestConversionInterval extends RequestPaging {

	@Builder(builderMethodName = "builderRequestInterval")
	public RequestConversionInterval(LocalDateTime from, LocalDateTime to, Integer pageSize, Integer pageNumber) {
		super(pageSize, pageNumber);
		this.from = from;
		this.to = to;
	}

	@NotNull(message = "start date sholud not be null and format should be : 2022-02-14T01:00:00.000-01:00")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime from;

	@NotNull(message = "start date sholud not be null and format should be : 2022-02-14T01:00:00.000-01:00")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime to;

}
