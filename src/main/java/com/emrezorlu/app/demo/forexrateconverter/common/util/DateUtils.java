package com.emrezorlu.app.demo.forexrateconverter.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateUtils {
	private static final ZoneId zone = ZoneId.of("UTC");

	public static Instant startOfDay(Long timestamp) {
		LocalDateTime startOfDay = Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("UTC")).toLocalDate()
				.atStartOfDay();
		return startOfDay.atZone(zone).toInstant();
	}

	public static Instant endOfDay(Long timestamp) {
		LocalDateTime endOfDay = Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("UTC")).toLocalDate()
				.atTime(LocalTime.MAX);
		return endOfDay.atZone(zone).toInstant();
	}

	public static LocalDateTime convertToLocalDateTime(Long timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of("GMT+3"));
	}
}
