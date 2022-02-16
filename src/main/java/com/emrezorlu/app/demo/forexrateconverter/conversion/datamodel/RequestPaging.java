package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RequestPaging {
	@Min(value = 1, message = "pagesize should not be less than 1")
	@NotNull(message = "pageSize cannot be null")
	private Integer pageSize;

	@Min(value = 0, message = "pageNumber should not be less than 0")
	@NotNull(message = "pageNumber cannot be null")
	private Integer pageNumber;
}
