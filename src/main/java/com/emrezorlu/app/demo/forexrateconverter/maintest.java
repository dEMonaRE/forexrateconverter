package com.emrezorlu.app.demo.forexrateconverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;

public class maintest {

	public static void main(String[] args) {
		Date time = new Date((long) 1644440102 * 1000);
		System.out.println(time);

		long test_timestamp = 1644440102;
		LocalDateTime triggerTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(test_timestamp),
				TimeZone.getDefault().toZoneId());

		LocalDateTime test1 = LocalDateTime.ofInstant(Instant.ofEpochSecond(test_timestamp), ZoneOffset.UTC);
		LocalDateTime test2 = LocalDateTime.ofInstant(Instant.ofEpochSecond(test_timestamp), ZoneId.of("GMT+3"));

		System.err.println(triggerTime);

		System.err.println(test1);
		System.err.println(test2);
	}

	@Value("${spring.datasource.username}")
	String username;

}
