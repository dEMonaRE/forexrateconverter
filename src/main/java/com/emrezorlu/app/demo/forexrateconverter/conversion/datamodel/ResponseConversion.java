package com.emrezorlu.app.demo.forexrateconverter.conversion.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseConversion {
	private Conversion conversion;
}
