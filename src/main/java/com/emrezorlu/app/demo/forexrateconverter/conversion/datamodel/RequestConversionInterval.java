package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestConversionInterval {

	@Min(value = 1, message = "pagesize should not be less than 1")
	@NotNull(message = "pageSize cannot be null")
	private Integer pageSize;

	@Min(value = 0, message = "pageNumber should not be less than 0")
	@NotNull(message = "pageNumber cannot be null")
	private Integer pageNumber;

	@NotNull(message = "start date sholud not be null and format should be : 2022-02-14T01:00:00.000-01:00")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime from;

	@NotNull(message = "start date sholud not be null and format should be : 2022-02-14T01:00:00.000-01:00")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime to;

}
