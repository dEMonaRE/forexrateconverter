package com.emrezorlu.app.demo.forexrateconverter.common.external.response;

import java.util.HashMap;

import lombok.Data;

@Data
public class ResponseApiSymbols {
	private boolean success;
	private HashMap<String, String> symbols;

	/***
	 * { "success": true, "symbols": { "AED": "United Arab Emirates Dirham", "AFN":
	 * "Afghan Afghani", "ALL": "Albanian Lek", "AMD": "Armenian Dram", [...] } }
	 * 
	 */
}
