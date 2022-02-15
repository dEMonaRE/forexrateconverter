package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestConversionDate extends RequestPaging {

	@Builder(builderMethodName = "builderRequestDate")
	public RequestConversionDate(Date date, Integer pageSize, Integer pageNumber) {
		super(pageSize, pageNumber);
		this.date = date;
	}

	@NotNull(message = "date sholud not be null and format should be : 2022-02-14")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;

}
