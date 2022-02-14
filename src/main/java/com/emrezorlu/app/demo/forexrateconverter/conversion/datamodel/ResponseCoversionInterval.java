package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseCoversionInterval {
	private List<Conversion> conversion;

}
