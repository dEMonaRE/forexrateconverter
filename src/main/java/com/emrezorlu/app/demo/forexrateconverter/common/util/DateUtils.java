package com.emrezorlu.app.demo.forexrateconverter.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateUtils {
	private static final ZoneId zone = ZoneId.of("GMT+3");

	public static LocalDateTime convertToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), zone);
	}
}
